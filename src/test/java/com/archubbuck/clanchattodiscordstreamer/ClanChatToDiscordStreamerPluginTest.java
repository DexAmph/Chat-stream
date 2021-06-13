package com.archubbuck.clanchattodiscordstreamer;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class ClanChatToDiscordStreamerPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(ClanChatToDiscordStreamerPlugin.class);
		RuneLite.main(args);
	}
}