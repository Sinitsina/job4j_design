package ru.job4j.io;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithComment() {
        //String path = "/Users/olga/IdeaProjects/job4j_design/chapter_002.app.properties";
        String path = "./app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(
                config.value("hibernate.dialect"),
                is("org.hibernate.dialect.PostgreSQLDialect")
        );
    }

    @Test
    public void whenPairWithCommentOtherFile() {
        String path = "./app2.properties";
        Config config = new Config(path);
        config.load();
        assertThat(
                config.value("hibernate.connection.driver_class"),
                is("org.postgresql.Driver")
        );
    }
}