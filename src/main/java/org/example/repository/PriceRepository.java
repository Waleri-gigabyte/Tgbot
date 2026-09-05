package org.example.repository;

import org.example.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PriceRepository {

    private final DatabaseConnection databaseConnection;

    public PriceRepository(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public Double findPrice(
            String sourceLanguage,
            String targetLanguage,
            String documentType
    ) {

        String sql = """
                SELECT price_per_page
                FROM prices
                WHERE source_language = ?
                  AND target_language = ?
                  AND document_type = ?
                """;

        try (
                Connection connection = databaseConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, sourceLanguage);
            statement.setString(2, targetLanguage);
            statement.setString(3, documentType);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    return resultSet.getDouble("price_per_page");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}