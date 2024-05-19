/**
 Ho Anh Khoa & Nguyen Minh Phu <Group 13>
 */
package JavaFiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://ep-bitter-pond-a1rb22nk.ap-southeast-1.aws.neon.tech/insuranceClaim?sslmode=require";
    private static final String USER = "insuranceClaim_owner";
    private static final String PASSWORD = "rIUt8eTby0EY";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
