import java.io.*;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDemo {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, NullPointerException {

        Class.forName("org.postgresql.Driver");
        Settings settings = new Settings();
        ClassLoader loader = Settings.class.getClassLoader();

        try (InputStream io = loader.getResourceAsStream("app.properties")) {
            settings.load(io);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(settings.getValue("url"), settings.getValue("login"), settings.getValue("password"))) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}
