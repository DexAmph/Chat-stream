package com.archubbuck.chatstreamer;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

@ConfigGroup("chatstreamer")
public interface ChatStreamerConfig extends Config
{
	String FRIENDS_CHAT_SECTION_NAME = "Friends Chat";
	String CLAN_CHAT_SECTION_NAME = "Clan Chat";
	String CLAN_GUEST_CHAT_SECTION_NAME = "Clan Guest Chat";

	@ConfigSection(
			name = CLAN_CHAT_SECTION_NAME,
			description = "Settings for the " + CLAN_CHAT_SECTION_NAME,
			position = 0
	)
	String clanChatSection = CLAN_CHAT_SECTION_NAME;

	@ConfigSection(
			name = CLAN_GUEST_CHAT_SECTION_NAME,
			description = "Settings for the " + CLAN_GUEST_CHAT_SECTION_NAME,
			position = 1
	)
	String clanGuestChatSection = CLAN_GUEST_CHAT_SECTION_NAME;

	@ConfigSection(
			name = FRIENDS_CHAT_SECTION_NAME,
			description = "Settings for the " + FRIENDS_CHAT_SECTION_NAME,
			position = 2
	)
	String friendsChatSection = FRIENDS_CHAT_SECTION_NAME;

	@ConfigItem(
			keyName = "gameMessageWebhook",
			name = "gameMessageWebhook",
			description = "A normal game message.",
			hidden = true
	)
	default String gameMessageWebhook() { return ""; }

	@ConfigItem(
			keyName = "modChatWebhook",
			name = "modChatWebhook",
			description = "A message in the public chat from a moderator",
			hidden = true
	)
	default String modChatWebhook() { return ""; }

	@ConfigItem(
			keyName = "publicChatWebhook",
			name = "publicChatWebhook",
			description = "A message in the public chat.",
			hidden = true
	)
	default String publicChatWebhook() { return ""; }

	@ConfigItem(
			keyName = "privateChatWebhook",
			name = "privateChatWebhook",
			description = "A private message from another player.",
			hidden = true
	)
	default String privateChatWebhook() { return ""; }

	@ConfigItem(
			keyName = "engineWebhook",
			name = "engineWebhook",
			description = "A message that the game engine sends.",
			hidden = true
	)
	default String engineWebhook() { return ""; }

	@ConfigItem(
			keyName = "loginLogoutNotificationWebhook",
			name = "loginLogoutNotificationWebhook",
			description = "A message received when a friend logs in or out.",
			hidden = true
	)
	default String loginLogoutNotificationWebhook() { return ""; }

	@ConfigItem(
			keyName = "privateChatOutWebhook",
			name = "privateChatOutWebhook",
			description = "A private message sent to another player.",
			hidden = true
	)
	default String privateChatOutWebhook() { return ""; }

	@ConfigItem(
			keyName = "modPrivateChatWebhook",
			name = "modPrivateChatWebhook",
			description = "A private message received from a moderator.",
			hidden = true
	)
	default String modPrivateChatWebhook() { return ""; }

	@ConfigItem(
			keyName = "friendsChatWebhook",
			name = "Webhook",
			description = "The location to send messages from the friends chat.",
			section = friendsChatSection
	)
	default String friendsChatWebhook() { return ""; }

	@ConfigItem(
			keyName = "friendsChatNotificationWebhook",
			name = "friendsChatNotificationWebhook",
			description = "A message received with information about the current friends chat.",
			hidden = true
	)
	default String friendsChatNotificationWebhook() { return ""; }

	@ConfigItem(
			keyName = "tradeSentWebhook",
			name = "tradeSentWebhook",
			description = "A trade request being sent.",
			hidden = true
	)
	default String tradeSentWebhook() { return ""; }

	@ConfigItem(
			keyName = "broadcastWebhook",
			name = "broadcastWebhook",
			description = "A game broadcast.",
			hidden = true
	)
	default String broadcastWebhook() { return ""; }

	@ConfigItem(
			keyName = "snapshotFeedbackWebhook",
			name = "snapshotFeedbackWebhook",
			description = "An abuse report submitted.",
			hidden = true
	)
	default String snapshotFeedbackWebhook() { return ""; }

	@ConfigItem(
			keyName = "itemExamineWebhook",
			name = "itemExamineWebhook",
			description = "Examine item description.",
			hidden = true
	)
	default String itemExamineWebhook() { return ""; }

	@ConfigItem(
			keyName = "npcExamineWebhook",
			name = "npcExamineWebhook",
			description = "Examine NPC description.",
			hidden = true
	)
	default String npcExamineWebhook() { return ""; }

	@ConfigItem(
			keyName = "objectExamineWebhook",
			name = "objectExamineWebhook",
			description = "Examine object description.",
			hidden = true
	)
	default String objectExamineWebhook() { return ""; }

	@ConfigItem(
			keyName = "friendNotificationWebhook",
			name = "friendNotificationWebhook",
			description = "Adding player to friend list.",
			hidden = true
	)
	default String friendNotificationWebhook() { return ""; }

	@ConfigItem(
			keyName = "ignoreNotificationWebhook",
			name = "ignoreNotificationWebhook",
			description = "Adding player to ignore list.",
			hidden = true
	)
	default String ignoreNotificationWebhook() { return ""; }

	@ConfigItem(
			keyName = "clanChatWebhook",
			name = "Webhook",
			description = "The location to send messages from the clan chat.",
			section = clanChatSection
	)
	default String clanChatWebhook() { return ""; }

	@ConfigItem(
			keyName = "clanMessageWebhook",
			name = "clanMessageWebhook",
			description = "A system message in a clan chat.",
			hidden = true
	)
	default String clanMessageWebhook() { return ""; }

	@ConfigItem(
			keyName = "clanGuestChatWebhook",
			name = "Webhook",
			description = "The location to send messages from the guest clan chat.",
			section = clanGuestChatSection
	)
	default String clanGuestChatWebhook() { return ""; }

	@ConfigItem(
			keyName = "clanGuestMessageWebhook",
			name = "clanGuestMessageWebhook",
			description = "A system message in the guest clan chat.",
			hidden = true
	)
	default String clanGuestMessageWebhook() { return ""; }

	@ConfigItem(
			keyName = "autoTyperWebhook",
			name = "autoTyperWebhook",
			description = "An autotyper message from a player.",
			hidden = true
	)
	default String autoTyperWebhook() { return ""; }

	@ConfigItem(
			keyName = "modAutoTyperWebhook",
			name = "modAutoTyperWebhook",
			description = "An autotyper message from a mod.",
			hidden = true
	)
	default String modAutoTyperWebhook() { return ""; }

	@ConfigItem(
			keyName = "consoleWebhook",
			name = "consoleWebhook",
			description = "A game message. (ie. when a setting is changed)",
			hidden = true
	)
	default String consoleWebhook() { return ""; }

	@ConfigItem(
			keyName = "tradeReqWebhook",
			name = "tradeReqWebhook",
			description = "A message received when somebody sends a trade offer.",
			hidden = true
	)
	default String tradeReqWebhook() { return ""; }

	@ConfigItem(
			keyName = "tradeWebhook",
			name = "tradeWebhook",
			description = "A message received when completing a trade or a duel",
			hidden = true
	)
	default String tradeWebhook() { return ""; }

	@ConfigItem(
			keyName = "chalReqTradeWebhook",
			name = "chalReqTradeWebhook",
			description = "A message received when somebody sends a duel offer.",
			hidden = true
	)
	default String chalReqTradeWebhook() { return ""; }

	@ConfigItem(
			keyName = "chalReqFriendsChatWebhook",
			name = "chalReqFriendsChatWebhook",
			description = "A message received when someone sends a friends chat challenge offer.",
			hidden = true
	)
	default String chalReqFriendsChatWebhook() { return ""; }

	@ConfigItem(
			keyName = "spamWebhook",
			name = "spamWebhook",
			description = "A message that was filtered.",
			hidden = true
	)
	default String spamWebhook() { return ""; }

	@ConfigItem(
			keyName = "playerRelatedWebhook",
			name = "playerRelatedWebhook",
			description = "A message that is relating to the player.",
			hidden = true
	)
	default String playerRelatedWebhook() { return ""; }

	@ConfigItem(
			keyName = "tenSecTimeoutWebhook",
			name = "tenSecTimeoutWebhook",
			description = "A message that times out after 10 seconds.",
			hidden = true
	)
	default String tenSecTimeoutWebhook() { return ""; }

	@ConfigItem(
			keyName = "welcomeWebhook",
			name = "welcomeWebhook",
			description = "The \"Welcome to RuneScape\" message",
			hidden = true
	)
	default String welcomeWebhook() { return ""; }

	@ConfigItem(
			keyName = "clanCreationInvitationWebhook",
			name = "clanCreationInvitationWebhook",
			description = "Clan creation invitation.",
			hidden = true
	)
	default String clanCreationInvitationWebhook() { return ""; }

	@ConfigItem(
			keyName = "clanClanWarsChallengeWebhook",
			name = "clanClanWarsChallengeWebhook",
			description = "Clan wars challenge for clans rather than FCs",
			hidden = true
	)
	default String clanClanWarsChallengeWebhook() { return ""; }

	@ConfigItem(
			keyName = "unknownWebhook",
			name = "unknownWebhook",
			description = "An unknown message type.",
			hidden = true
	)
	default String unknownWebhook() { return ""; }

	@ConfigItem(
			keyName = "friendsChatName",
			name = "Name",
			description = "Only send messages when the friends chat name matches.",
			section = friendsChatSection
	)
	default String friendsChatName() { return ""; }

	@ConfigItem(
			keyName = "clanChatName",
			name = "Name",
			description = "Only send messages when the clan chat name matches.",
			section = clanChatSection
	)
	default String clanChatName() { return ""; }

	@ConfigItem(
			keyName = "clanGuestChatName",
			name = "Name",
			description = "Only send messages when the clan guest chat name matches.",
			section = clanGuestChatSection
	)
	default String clanGuestChatName() { return ""; }
}
