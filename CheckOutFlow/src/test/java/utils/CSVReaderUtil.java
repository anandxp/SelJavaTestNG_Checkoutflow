package utils;

import java.io.*;
import java.util.*;

public class CSVReaderUtil {

    public static Object[][] combineLoginAndSearch(String loginFile, String searchFile) {
        List<String[]> logins = readCSV(loginFile);
        List<String[]> urls = readCSV(searchFile);

        List<Object[]> data = new ArrayList<>();
        for (String[] login : logins) {
            for (String[] url : urls) {
                data.add(new Object[]{login[0], login[1], url[0]});
            }
        }
        return data.toArray(new Object[0][0]);
    }

    private static List<String[]> readCSV(String filePath) {
        List<String[]> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                records.add(line.split(","));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }
}
