package pairmatching.global.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomFileReader {
    public static List<String> readFile(String path) {
        List<String> elements = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = reader.readLine()) != null) {
                elements.add(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return elements;
    }
}
