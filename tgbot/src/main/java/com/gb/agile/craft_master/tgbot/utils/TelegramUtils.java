package com.gb.agile.craft_master.tgbot.utils;

import org.apache.camel.component.telegram.model.InlineKeyboardButton;
import org.apache.camel.component.telegram.model.InlineKeyboardMarkup;
import org.apache.camel.component.telegram.model.ReplyKeyboardMarkup;

import java.util.ArrayList;
import java.util.List;

public class TelegramUtils {

    public static ReplyKeyboardMarkup getReplyKeyboardMarkup(List<String> stringList) {
        List<InlineKeyboardButton> buttons = new ArrayList<>();
        for (String t : stringList) {
            buttons.add(InlineKeyboardButton.builder().text(t).build());
        }

        return ReplyKeyboardMarkup.builder()
                .keyboard()
                .addOneRowByEachButton(buttons)
                .close()
                .oneTimeKeyboard(true)
                .build();
    }

    public static InlineKeyboardMarkup getInlineKeyboardMarkup(List<String> stringList) {

        List<InlineKeyboardButton> buttons = new ArrayList<>();

        InlineKeyboardMarkup.Builder replyMarkup = InlineKeyboardMarkup.builder();

        for (String t : stringList) {
            replyMarkup.addRow(
                    List.of(InlineKeyboardButton.builder()
                            .text(t).callbackData(t).build()));
        }

        return replyMarkup.build();
    }

}
