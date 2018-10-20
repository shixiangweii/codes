package kaitao.cache.app;

import java.util.List;
import java.util.Map;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-10-19
 * Time: 11:57
 *
 * @author shixiangweii
 */
public interface RedisCache {
    /**
     * 写
     *
     * @param key                         key
     * @param json                        json
     * @param remoteCacheExpiresInSeconds remote cache expire secs
     */
    void set(String key, String json, int remoteCacheExpiresInSeconds);

    Map<String, String> mget(List<String> keys);
}
