package com.archubbuck.chatstreamer;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class ChatStreamerPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(ChatStreamerPlugin.class);
		RuneLite.main(args);
	}
}