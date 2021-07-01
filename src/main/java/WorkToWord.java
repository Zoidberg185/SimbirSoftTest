import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class WorkToWord {

    private final String[] site;

    private static final String SPLTRE = "[\\Q ,.!?\";:[]()\\E\\n\\r\\t]+";

    private static final String wordFilter = "[\\p{L}]+";

    public WorkToWord(String[] sites) {
        site = sites;
    }

    public void work() {
        for (String s : site) {
            System.out.println("Words of - " + s);
            System.out.println("................................");
            String text = readTextFromWebSite(s);
            if (text == null) {
                System.out.println("Bad response from site, may be wrong");
                break;
            }
            HashMap<String, Integer> result = calc(text);
            result.remove("");
            for (Map.Entry<String, Integer> pair : result.entrySet()) {
                System.out.printf("%s - %d\n", pair.getKey(), pair.getValue());
            }
            System.out.println("................................");
        }
    }

    private static HashMap<String, Integer> calc(String sites) {
        HashMap<String, Integer> res = new LinkedHashMap<>();
        for (String word : wordNormalize(sites).split(SPLTRE)) {
            if (isCorrect(word)) {
                if (res.containsKey(word)) {
                    res.put(word, res.get(word) + 1);
                } else {
                    res.put(word, 1);
                }
            }
        }
        return res;
    }

    private String readTextFromWebSite(String url) {
        int count = 0;
        StringBuilder sb = new StringBuilder();
        File input = null;
        while (true) {
            if (url.equals(site[count])) {
                input = new File("site_" + count + ".html");
                break;
            }
            count++;
        }
        Document document = null;
        try {
            document = Jsoup.parse(input, "UTF-8", url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements element = document.select("body");

        for (Element t : element) {
            sb.append(t.text()).append("\n");
        }
        return sb.toString();
    }

    private static String wordNormalize(final String word) {
        return word.trim().toUpperCase();
    }

    private static boolean isCorrect(final String word) {
        return (word != null
                && !word.isEmpty()
                && word.matches(wordFilter));
    }
}
