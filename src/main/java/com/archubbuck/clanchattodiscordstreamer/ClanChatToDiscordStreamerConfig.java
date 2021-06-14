package com.archubbuck.clanchattodiscordstreamer;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("clanchattodiscordstreamer")
public interface ClanChatToDiscordStreamerConfig extends Config
{
	@ConfigItem(
		keyName = "greeting",
		name = "Welcome Greeting",
		description = "The message to show to the user when they login"
	)
	default String greeting()
	{
		return "Hello";
	}

	@ConfigItem(
			keyName = "webhook",
			name = "Webhook",
			description = "A Discord webhook or private endpoint"
	)
	default String webhook() { return ""; }
}
