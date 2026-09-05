package org.example.telegram;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.generics.TelegramClient;

public class MessageSender {

    private final TelegramClient telegramClient;

    public MessageSender(TelegramClient telegramClient){
        this.telegramClient = telegramClient;
    }

    public void sendMessage(Long chatId, String text) {

        SendMessage message = SendMessage.builder()
                .chatId(chatId)
                .text(text)
                .build();

        try {
            telegramClient.execute(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMessageWithKeyboard(
            Long chatId,
            String text,
            ReplyKeyboardMarkup keyboard
    ) {

        SendMessage message = SendMessage.builder()
                .chatId(chatId)
                .text(text)
                .replyMarkup(keyboard)
                .build();

        try {
            telegramClient.execute(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
