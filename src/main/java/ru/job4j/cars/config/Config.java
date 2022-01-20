package ru.job4j.cars.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private final Properties properties = new Properties();

    public Config() {
        ClassLoader classLoader = this.getClass().getClassLoader();
        try (InputStream input = classLoader.getResourceAsStream("cars.properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String get(String name) {
        return properties.getProperty(name);
    }
}
