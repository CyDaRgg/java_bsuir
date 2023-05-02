package services;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class InMemoryCache {
    private Map<String, String> cache = new HashMap<>();

    public String isContains(String first, String second, String third, String fourth) {
        Set<String> temp = cache.keySet();

        for (String i : temp) {
            if (i.contains(first) && i.contains(second) && i.contains(third) && i.contains(fourth)) {
                return cache.get(i);
            }
        }
        return null;
    }

    public String get(String key) {
        return cache.get(key);
    }

    public void put(String key, String value) {
        cache.put(key, value);
    }

}
