package org.example.repository;

import org.example.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AnalyticsRepository {

    private final DatabaseConnection databaseConnection;

    public AnalyticsRepository(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public void saveEvent(
            String calculationId,
            Long chatId,
            String step
    ) {

        String sql = """
                INSERT OR IGNORE INTO analytics_events
                (calculation_id, chat_id, step)
                VALUES (?, ?, ?)
                """;

        try (
                Connection connection = databaseConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, calculationId);
            statement.setLong(2, chatId);
            statement.setString(3, step);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
