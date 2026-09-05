package org.example;

import org.example.database.DatabaseConnection;
import java.sql.Connection;

import org.example.database.DatabaseInitializer;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;

public class Main {

    public static void main(String[] args) throws Exception {

        DatabaseConnection databaseConnection =
                new DatabaseConnection();

        DatabaseInitializer databaseInitializer =
                new DatabaseInitializer(databaseConnection);
        databaseInitializer.initialize();

        try (Connection connection =
                     databaseConnection.getConnection()) {

            System.out.println("Database connected");
        }

        String token = System.getenv("BOT_TOKEN");

        if (token == null || token.isBlank()) {
            throw new IllegalStateException("BOT_TOKEN is not set");
        }

        TelegramBotsLongPollingApplication botsApplication =
                new TelegramBotsLongPollingApplication();

        botsApplication.registerBot(
                token,
                new SimpleBot(token)
        );

        System.out.println("Bot started");
    }
}