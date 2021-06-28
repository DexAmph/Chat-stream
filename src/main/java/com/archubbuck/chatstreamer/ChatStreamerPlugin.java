package com.archubbuck.chatstreamer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.events.ChatMessage;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.util.Text;
import net.runelite.http.api.RuneLiteAPI;
import okhttp3.*;

import javax.inject.Inject;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static net.runelite.http.api.RuneLiteAPI.JSON;

@Slf4j
@PluginDescriptor(
        name = "Chat Streamer"
)
public class ChatStreamerPlugin extends Plugin {
    @Inject
    private Client client;

    @Inject
    private ChatStreamerConfig config;

    @Override
    protected void startUp() throws Exception {
        log.info("ChatStreamer started!");
    }

    @Override
    protected void shutDown() throws Exception {
        log.info("ChatStreamer stopped!");
    }

    @Provides
    ChatStreamerConfig provideConfig(ConfigManager configManager) {
        return configManager.getConfig(ChatStreamerConfig.class);
    }

    @Subscribe
    public void onChatMessage(ChatMessage chatMessage) {

        String webhook = getWebhookByChatMessageType(chatMessage.getType());

        if (webhook.isEmpty()) {
            return;
        }

        if (chatMessage.getType().equals(ChatMessageType.FRIENDSCHAT) && (!config.friendsChatName().isEmpty() &&
                !Text.standardize(chatMessage.getSender()).equalsIgnoreCase(Text.standardize(config.friendsChatName())))) {
            return;
        }

        if (chatMessage.getType().equals(ChatMessageType.CLAN_CHAT) && (!config.clanChatName().isEmpty() &&
                !Text.standardize(chatMessage.getSender()).equalsIgnoreCase(Text.standardize(config.clanChatName())))) {
            return;
        }

        if (chatMessage.getType().equals(ChatMessageType.CLAN_GUEST_CHAT) && (!config.clanGuestChatName().isEmpty() &&
                !Text.standardize(chatMessage.getSender()).equalsIgnoreCase(Text.standardize(config.clanGuestChatName())))) {
            return;
        }

        Gson gsonBuilder = new GsonBuilder().addSerializationExclusionStrategy(new ChatMessageExclusionStrategy()).create();

        String requestContent = gsonBuilder.toJson(
                webhook.startsWith("https://discord.com/api/webhooks")
                        ? new DiscordMessage(chatMessage)
                        : chatMessage
        );

        log.info(requestContent);

        sendMessage(webhook, requestContent);
    }

    public static OkHttpClient okHttpClient = RuneLiteAPI.CLIENT.newBuilder()
            .pingInterval(0, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build();

    public CompletableFuture<Boolean> sendMessage(String endpoint, String message) {

        Request request = new Request.Builder()
                .url(endpoint)
                .post(RequestBody.create(JSON, message))
                .build();

        CompletableFuture<Boolean> future = new CompletableFuture<>();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                log.warn("Error sending message", e);
                future.completeExceptionally(e);
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    if (!response.isSuccessful()) {
                        throw new Exception(response.message());
                    }

                    future.complete(true);
                } catch (Exception e) {
                    log.warn("Error sending message", e);
                    future.completeExceptionally(e);
                } finally {
                    response.close();
                }
            }
        });

        return future;

    }

    public String getWebhookByChatMessageType(ChatMessageType chatMessageType) {

        if (chatMessageType == ChatMessageType.GAMEMESSAGE) {
            return config.gameMessageWebhook();
        }

        if (chatMessageType == ChatMessageType.MODCHAT) {
            return config.modChatWebhook();
        }

        if (chatMessageType == ChatMessageType.PUBLICCHAT) {
            return config.publicChatWebhook();
        }

        if (chatMessageType == ChatMessageType.PRIVATECHAT) {
            return config.privateChatWebhook();
        }

        if (chatMessageType == ChatMessageType.ENGINE) {
            return config.engineWebhook();
        }

        if (chatMessageType == ChatMessageType.LOGINLOGOUTNOTIFICATION) {
            return config.loginLogoutNotificationWebhook();
        }

        if (chatMessageType == ChatMessageType.PRIVATECHATOUT) {
            return config.privateChatOutWebhook();
        }

        if (chatMessageType == ChatMessageType.MODPRIVATECHAT) {
            return config.modPrivateChatWebhook();
        }

        if (chatMessageType == ChatMessageType.FRIENDSCHAT) {
            return config.friendsChatWebhook();
        }

        if (chatMessageType == ChatMessageType.FRIENDSCHATNOTIFICATION) {
            return config.friendsChatNotificationWebhook();
        }

        if (chatMessageType == ChatMessageType.TRADE_SENT) {
            return config.tradeSentWebhook();
        }

        if (chatMessageType == ChatMessageType.BROADCAST) {
            return config.broadcastWebhook();
        }

        if (chatMessageType == ChatMessageType.SNAPSHOTFEEDBACK) {
            return config.snapshotFeedbackWebhook();
        }

        if (chatMessageType == ChatMessageType.ITEM_EXAMINE) {
            return config.itemExamineWebhook();
        }

        if (chatMessageType == ChatMessageType.NPC_EXAMINE) {
            return config.npcExamineWebhook();
        }

        if (chatMessageType == ChatMessageType.OBJECT_EXAMINE) {
            return config.objectExamineWebhook();
        }

        if (chatMessageType == ChatMessageType.FRIENDNOTIFICATION) {
            return config.friendNotificationWebhook();
        }

        if (chatMessageType == ChatMessageType.IGNORENOTIFICATION) {
            return config.ignoreNotificationWebhook();
        }

        if (chatMessageType == ChatMessageType.CLAN_CHAT) {
            return config.clanChatWebhook();
        }

        if (chatMessageType == ChatMessageType.CLAN_MESSAGE) {
            return config.clanMessageWebhook();
        }

        if (chatMessageType == ChatMessageType.CLAN_GUEST_CHAT) {
            return config.clanGuestChatWebhook();
        }

        if (chatMessageType == ChatMessageType.CLAN_GUEST_MESSAGE) {
            return config.clanGuestMessageWebhook();
        }

        if (chatMessageType == ChatMessageType.AUTOTYPER) {
            return config.autoTyperWebhook();
        }

        if (chatMessageType == ChatMessageType.MODAUTOTYPER) {
            return config.modAutoTyperWebhook();
        }

        if (chatMessageType == ChatMessageType.CONSOLE) {
            return config.consoleWebhook();
        }

        if (chatMessageType == ChatMessageType.TRADEREQ) {
            return config.tradeReqWebhook();
        }

        if (chatMessageType == ChatMessageType.TRADE) {
            return config.tradeWebhook();
        }

        if (chatMessageType == ChatMessageType.CHALREQ_TRADE) {
            return config.chalReqTradeWebhook();
        }

        if (chatMessageType == ChatMessageType.CHALREQ_FRIENDSCHAT) {
            return config.chalReqFriendsChatWebhook();
        }

        if (chatMessageType == ChatMessageType.SPAM) {
            return config.spamWebhook();
        }

        if (chatMessageType == ChatMessageType.PLAYERRELATED) {
            return config.playerRelatedWebhook();
        }

        if (chatMessageType == ChatMessageType.TENSECTIMEOUT) {
            return config.tenSecTimeoutWebhook();
        }

        if (chatMessageType == ChatMessageType.WELCOME) {
            return config.welcomeWebhook();
        }

        if (chatMessageType == ChatMessageType.CLAN_CREATION_INVITATION) {
            return config.clanCreationInvitationWebhook();
        }

        if (chatMessageType == ChatMessageType.CLAN_CLAN_WARS_CHALLENGE) {
            return config.clanClanWarsChallengeWebhook();
        }

        if (chatMessageType == ChatMessageType.UNKNOWN) {
            return config.unknownWebhook();
        }

        return null;
    }
}

