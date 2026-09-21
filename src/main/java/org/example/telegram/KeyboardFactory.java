package org.example.telegram;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class KeyboardFactory {

    public ReplyKeyboardMarkup createDirectionKeyboard() {

        List<KeyboardRow> rows = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();
        row1.add("RU → EN");
        row1.add("EN → RU");

        KeyboardRow row2 = new KeyboardRow();
        row2.add("RU → PL");
        row2.add("PL → RU");

        KeyboardRow row3 = new KeyboardRow();
        row3.add("RU → DE");
        row3.add("DE → RU");

        KeyboardRow row4 = new KeyboardRow();
        row4.add("🔎 Найти язык");

        rows.add(row1);
        rows.add(row2);
        rows.add(row3);
        rows.add(row4);

        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup(rows);
        keyboard.setResizeKeyboard(true);

        return keyboard;
    }

    public ReplyKeyboardMarkup createLegalizationDocumentKeyboard() {

        List<KeyboardRow> rows = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();
        row1.add("Документ об образовании");

        KeyboardRow row2 = new KeyboardRow();
        row2.add("Нотариальный / судебный документ");

        KeyboardRow row3 = new KeyboardRow();
        row3.add("Свидетельство о рождении / справка о несудимости");

        KeyboardRow row4 = new KeyboardRow();
        row4.add("⬅️ Назад");
        row4.add("❌ Начать заново");

        rows.add(row1);
        rows.add(row2);
        rows.add(row3);
        rows.add(row4);

        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup(rows);
        keyboard.setResizeKeyboard(true);

        return keyboard;
    }

    public ReplyKeyboardMarkup createLegalizationUrgencyKeyboard() {

        List<KeyboardRow> rows = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();
        row1.add("Обычная — около 1,5 месяца");

        KeyboardRow row2 = new KeyboardRow();
        row2.add("Срочная — около 7 дней");

        KeyboardRow row3 = new KeyboardRow();
        row3.add("⬅️ Назад");
        row3.add("❌ Начать заново");

        rows.add(row1);
        rows.add(row2);
        rows.add(row3);

        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup(rows);
        keyboard.setResizeKeyboard(true);

        return keyboard;
    }

    public ReplyKeyboardMarkup createDocumentTypeKeyboard() {

        List<KeyboardRow> rows = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();
        row1.add("Личный документ");

        KeyboardRow row2 = new KeyboardRow();
        row2.add("Иной документ");

        KeyboardRow row3 = new KeyboardRow();
        row3.add("Сложный текст");

        KeyboardRow row4 = new KeyboardRow();
        row4.add("⬅️ Назад");
        row4.add("❌ Начать заново");

        rows.add(row1);
        rows.add(row2);
        rows.add(row3);
        rows.add(row4);

        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup(rows);
        keyboard.setResizeKeyboard(true);

        return keyboard;
    }

    public ReplyKeyboardMarkup createUrgencyKeyboard() {

        List<KeyboardRow> rows = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();
        row1.add("Обычный перевод");

        KeyboardRow row2 = new KeyboardRow();
        row2.add("Срочный перевод +50%");

        KeyboardRow row3 = new KeyboardRow();
        row3.add("⬅️ Назад");
        row3.add("❌ Начать заново");

        rows.add(row1);
        rows.add(row2);
        rows.add(row3);

        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup(rows);
        keyboard.setResizeKeyboard(true);

        return keyboard;
    }

    public ReplyKeyboardMarkup createApostilleKeyboard() {

        List<KeyboardRow> rows = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();
        row1.add("Апостиль не нужен");

        KeyboardRow row2 = new KeyboardRow();
        row2.add("Обычный апостиль");

        KeyboardRow row3 = new KeyboardRow();
        row3.add("Срочный апостиль");

        KeyboardRow row4 = new KeyboardRow();
        row4.add("⬅️ Назад");
        row4.add("❌ Начать заново");

        rows.add(row1);
        rows.add(row2);
        rows.add(row3);
        rows.add(row4);

        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup(rows);
        keyboard.setResizeKeyboard(true);

        return keyboard;
    }

    public ReplyKeyboardMarkup createYesNoKeyboard() {

        List<KeyboardRow> rows = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();
        row1.add("Да");
        row1.add("Нет");

        KeyboardRow row2 = new KeyboardRow();
        row2.add("⬅️ Назад");
        row2.add("❌ Начать заново");

        rows.add(row1);
        rows.add(row2);

        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup(rows);
        keyboard.setResizeKeyboard(true);

        return keyboard;
    }

    public ReplyKeyboardMarkup createConfirmationKeyboard() {

        KeyboardRow firstRow = new KeyboardRow();
        firstRow.add("✅ Да");

        KeyboardRow secondRow = new KeyboardRow();
        secondRow.add("❌ Начать заново");

        return ReplyKeyboardMarkup.builder()
                .keyboard(List.of(firstRow, secondRow))
                .resizeKeyboard(true)
                .build();
    }

    public InlineKeyboardMarkup createResultKeyboard() {

        InlineKeyboardButton managerButton = InlineKeyboardButton.builder()
                .text("[ \uD83D\uDCAC Чат с менеджером ↗ ]")
                .url("https://t.me/Amalteaplus")
                .build();


        InlineKeyboardRow managerRow = new InlineKeyboardRow();
        managerRow.add(managerButton);


        return InlineKeyboardMarkup.builder()
                .keyboard(List.of(
                        managerRow
                ))
                .build();
    }
}
