import java.util.HashMap;
import java.util.Random;

class URLShortener {

    private HashMap<String, String> shortToLong = new HashMap<>();
    private HashMap<String, String> longToShort = new HashMap<>();
    private static final String BASE_URL = "http://short.ly/";
    private static final String CHAR_SET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public String shortenURL(String longURL) {
        if (longToShort.containsKey(longURL)) {
            return BASE_URL + longToShort.get(longURL);
        }

        String shortKey = generateKey();
        shortToLong.put(shortKey, longURL);
        longToShort.put(longURL, shortKey);

        return BASE_URL + shortKey;
    }

    public String getOriginalURL(String shortURL) {
        String key = shortURL.replace(BASE_URL, "");
        return shortToLong.getOrDefault(key, "URL not found");
    }

    private String generateKey() {
        Random random = new Random();
        StringBuilder key = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            key.append(CHAR_SET.charAt(random.nextInt(CHAR_SET.length())));
        }
        return key.toString();
    }

    public static void main(String[] args) {
        URLShortener service = new URLShortener();

        String shortURL = service.shortenURL("https://www.google.com/careers");
        System.out.println("Short URL: " + shortURL);

        System.out.println("Original URL: " + service.getOriginalURL(shortURL));
    }
}
