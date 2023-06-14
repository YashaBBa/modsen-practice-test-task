package service.impl;

import com.sun.tools.javac.Main;
import service.ReadeFromFileService;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadeFromFileServiceImpl implements ReadeFromFileService {
    private final String PROPERTIES_FILE = "prop.properties";

    @Override
    public double getExchangeRate(String valuteType) {
        FileInputStream fileInputStream;

        double exchangeRate = 0;
        try {
            ClassLoader classLoader = Main.class.getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(PROPERTIES_FILE);
            Properties properties= new Properties();
            properties.load(inputStream);
            exchangeRate = Double.parseDouble(properties.getProperty(valuteType));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return exchangeRate;

    }
}
