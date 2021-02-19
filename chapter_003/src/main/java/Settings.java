import java.io.InputStream;
import java.util.Properties;

public class Settings {

    private final Properties properties = new Properties();
    public void load(InputStream io) {
        try {
            this.properties.load(io);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getValue(String key) {
        return this.properties.getProperty(key);
    }

    @Override
    public String toString() {
        return "Settings{" +
                "properties=" + properties +
                '}';
    }
}
