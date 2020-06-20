package com.covid.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TestUtils {

    public static String readFileAsString(Object object, String filePath) throws Exception {
        ClassLoader classLoader = object.getClass().getClassLoader();
        assert classLoader != null;
        InputStream inputStream = classLoader.getResourceAsStream(filePath);

        StringBuilder jsonString = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                jsonString.append(line.trim());
            }
        }

        return jsonString.toString();
    }
}
