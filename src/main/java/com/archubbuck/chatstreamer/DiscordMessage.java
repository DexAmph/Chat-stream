package com.archubbuck.chatstreamer;

import net.runelite.api.events.ChatMessage;
import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DiscordMessage {

    String content;

    public DiscordMessage(ChatMessage chatMessage) {

        String timestamp = ZonedDateTime.ofInstant(Instant.ofEpochSecond(chatMessage.getTimestamp()), ZoneOffset.UTC).format(DateTimeFormatter.ISO_DATE_TIME);

        String messageContent = "[" + timestamp + "]";

        if (!chatMessage.getSender().isEmpty()) {
            messageContent += " [" + chatMessage.getSender() + "]";
        }

        if (StringUtils.isNotEmpty(chatMessage.getName())) {
            messageContent += " **" + chatMessage.getName() + "**:";
        }

        messageContent += " " + chatMessage.getMessage();

        this.content = messageContent;
    }
}
