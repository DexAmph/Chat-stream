package com.archubbuck.clanchattodiscordstreamer;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.ChatMessage;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.http.api.RuneLiteAPI;
import okhttp3.*;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static net.runelite.http.api.RuneLiteAPI.JSON;

@Slf4j
@PluginDescriptor(
	name = "ClanChatToDiscordStreamer"
)
public class ClanChatToDiscordStreamerPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private ClanChatToDiscordStreamerConfig config;

	@Override
	protected void startUp() throws Exception
	{
		log.info("ClanChatToDiscordStreamer started!");
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("ClanChatToDiscordStreamer stopped!");
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN)
		{
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "ClanChatToDiscordStreamer says " + config.greeting(), null);
		}
	}

	@Provides
	ClanChatToDiscordStreamerConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(ClanChatToDiscordStreamerConfig.class);
	}

	@Subscribe
	public void onChatMessage(ChatMessage event) {

		if (event.getType() != ChatMessageType.FRIENDSCHAT) {
			return;
		}

		if (config.webhook().isEmpty()) {
			return;
		}

		log.info("[{}] {}: {}", event.getSender(), event.getName(), event.getMessage());

		sendMessage(event);
	}

	public static OkHttpClient okHttpClient = RuneLiteAPI.CLIENT.newBuilder()
			.pingInterval(0, TimeUnit.SECONDS)
			.connectTimeout(30, TimeUnit.SECONDS)
			.readTimeout(30, TimeUnit.SECONDS)
			.build();

	public CompletableFuture<Boolean> sendMessage(ChatMessage event) {

		String timestamp = ZonedDateTime.ofInstant(Instant.ofEpochSecond(event.getTimestamp()), ZoneOffset.UTC).format(DateTimeFormatter.ISO_DATE_TIME);

		String endpoint = config.webhook();
		String requestBody = endpoint.startsWith("https://discord.com/api/webhooks")
				? String.format("{ \"content\": \"%s\" }", String.format("[%s] **%s**: %s", timestamp, event.getName(), event.getMessage()))
				: String.format("{ \"Author\": \"%s\", \"Content\": \"%s\" }", event.getName(), event.getMessage());

		Request request = new Request.Builder()
				.url(endpoint)
				.post(RequestBody.create(JSON, requestBody))
				.build();

		CompletableFuture<Boolean> future = new CompletableFuture<>();
		okHttpClient.newCall(request).enqueue(new Callback()
		{
			@Override
			public void onFailure(Call call, IOException e)
			{
				log.warn("Error sending message from clan chat", e);
				future.completeExceptionally(e);
			}

			@Override
			public void onResponse(Call call, Response response)
			{
				try
				{
					if (!response.isSuccessful())
					{
						throw new Exception(response.message());
					}

					future.complete(true);
				}
				catch (Exception e)
				{
					log.warn("Error sending message from clan chat", e);
					future.completeExceptionally(e);
				}
				finally
				{
					response.close();
				}
			}
		});

		return future;

	}
}
