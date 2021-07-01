import org.apache.commons.validator.routines.UrlValidator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class WorkToFile {

    public String[] readFile() {
        String[] readText;
        String[] tempReadText;
        List<String> readFile = new ArrayList<>();
        String read;
        String fileName = "urlSites.txt";
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("File \"urlSites\" is not found" + "\nFile \"urlSites\" is created");
            try (FileOutputStream fos = new FileOutputStream(file);
                 BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos)))
            {
                String[] createText = {"https://www.simbirsoft.com/", "https://www.google.com/", "https://mail.ru/"};
                for (String s : createText) {
                    bw.write(s);
                    bw.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (FileInputStream fis = new FileInputStream(file);
             BufferedReader br = new BufferedReader(new InputStreamReader(fis)))
        {
            while ((read = br.readLine()) != null) {
                readFile.add(read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        tempReadText = readFile.toArray(new String[]{});
        readText = checkUrl(tempReadText);
        if (readText == null) {
            System.exit(0);
        }
        return readText;
    }

    private String[] checkUrl(String[] checkUrl) {
        List<String> tempReadTextCheck = new ArrayList<>();
        UrlValidator urlValidator = new UrlValidator();
        for (String s : checkUrl) {
            if (urlValidator.isValid(s)) {
                System.out.println("url подтвержден");
                tempReadTextCheck.add(s);
            } else {
                System.out.println("url некорректен");
                System.out.println("Строка: " + s);
                return null;
            }
        }
        return tempReadTextCheck.toArray(new String[]{});
    }
}
