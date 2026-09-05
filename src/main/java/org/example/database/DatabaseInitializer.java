package org.example.database;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {

    private final DatabaseConnection databaseConnection;

    public DatabaseInitializer(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public void initialize() {

        String sql = readSchema();

        try (
                Connection connection = databaseConnection.getConnection();
                Statement statement = connection.createStatement()
        ) {

            for (String query : sql.split(";")) {
                String trimmedQuery = query.trim();

                if (!trimmedQuery.isEmpty()) {
                    statement.execute(trimmedQuery);
                }
            }

            System.out.println("Database initialized");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String readSchema() {

        try (
                InputStream inputStream =
                        getClass()
                                .getClassLoader()
                                .getResourceAsStream("schema.sql")
        ) {

            if (inputStream == null) {
                throw new RuntimeException(
                        "schema.sql not found"
                );
            }

            return new String(
                    inputStream.readAllBytes(),
                    StandardCharsets.UTF_8
            );

        } catch (IOException e) {
            throw new RuntimeException(
                    "Could not read schema.sql",
                    e
            );
        }
    }
}