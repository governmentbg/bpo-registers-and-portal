package bg.duosoft.bpo.publik.core.service.cache;

import java.util.List;

public interface CacheService {

    void clearCache();

    void clearCache(String name);

    List<String> selectCacheNames();
}
