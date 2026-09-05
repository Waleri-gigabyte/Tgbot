package org.example;

import org.example.handler.BotHandler;
import org.example.repository.PriceRepository;
import org.example.service.CalculationService;
import org.example.session.SessionStorage;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import org.example.handler.BotHandler;
import org.example.handler.StepHandler;
import org.example.service.LanguageService;
import org.example.session.SessionStorage;
import org.example.telegram.KeyboardFactory;
import org.example.telegram.MessageSender;
import org.example.database.DatabaseConnection;

public class SimpleBot implements LongPollingSingleThreadUpdateConsumer {

    private final BotHandler botHandler;

    public SimpleBot(String token) {

        TelegramClient telegramClient = new OkHttpTelegramClient(token);

        SessionStorage sessionStorage = new SessionStorage();
        LanguageService languageService = new LanguageService();
        MessageSender messageSender = new MessageSender(telegramClient);
        KeyboardFactory keyboardFactory = new KeyboardFactory();

        DatabaseConnection databaseConnection = new DatabaseConnection();

        PriceRepository priceRepository = new PriceRepository(databaseConnection);
        CalculationService calculationService = new CalculationService(priceRepository);

        StepHandler stepHandler =
                new StepHandler(
                        sessionStorage,
                        languageService,
                        messageSender,
                        keyboardFactory,
                        calculationService
                );

        this.botHandler =
                new BotHandler(stepHandler);
    }

    @Override
    public void consume(Update update) {

        if (!update.hasMessage() || !update.getMessage().hasText()) {
            return;
        }

        String text = update.getMessage().getText().trim();
        Long chatId = update.getMessage().getChatId();

        botHandler.handle(chatId, text);
    }
}

