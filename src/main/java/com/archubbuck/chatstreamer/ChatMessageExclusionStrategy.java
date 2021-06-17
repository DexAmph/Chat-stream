package com.archubbuck.chatstreamer;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import net.runelite.api.MessageNode;

public class ChatMessageExclusionStrategy implements ExclusionStrategy {
    @Override
    public boolean shouldSkipField(FieldAttributes f) {
        return false;
    }

    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        return clazz.getName().equals(MessageNode.class.getName());
    }
}
