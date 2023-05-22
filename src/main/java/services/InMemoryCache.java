package services;

import dao.AverageMedianResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class InMemoryCache {
    private Map<String, AverageMedianResponse> cache = new HashMap<>();

    public AverageMedianResponse isContains(String first, String second, String third, String fourth) {
        Set<String> currentKeys = cache.keySet();

        for (String key : currentKeys) {
            if (key.contains(first) && key.contains(second) && key.contains(third) && key.contains(fourth)) {
                return cache.get(key);
            }
        }
        return null;
    }

    public AverageMedianResponse get(String key) {
        return cache.get(key);
    }

    public void put(String key, AverageMedianResponse value) {
        cache.put(key, value);
    }

}
