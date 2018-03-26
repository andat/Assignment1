package dataAccess.repository;

import java.util.List;

public class Cache<T> {

    private List<T> cachedData;

    public void save(List<T> data){
        this.cachedData = data;
    }

    public boolean hasResult() {
        return cachedData.isEmpty();
    }

    public List<T> load() {
        System.out.println("Loaded from cache.");
        return cachedData;
    }

    public void invalidate() {
        cachedData.clear();
    }
}
