package kaitao.cache.app;

import com.alibaba.fastjson.JSON;
import com.google.common.cache.Cache;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 本地缓存服务
 * Description:
 * User: shixiangweii
 * Date: 2018-10-19
 * Time: 11:01
 *
 * @author shixiangweii
 */
public class LocalCacheService {

    @Resource
    private RedisCache redisCache;

    @Resource
    private ThreadPoolExecutor asyncTaskExecutor;

    public volatile boolean writeLocalCache = true;

    public volatile boolean writeRemoteCache = true;

    public volatile boolean readLocalCache = true;

    public volatile boolean readRemoteCache = true;

    public void addCache(String key, Cache<?, ?> cache) {

    }


    public Cache getLocalCache(String key) {
        return null;
    }

    public void set(final String key, final Object value, final int remoteCacheExpiresInSeconds) {
        if (value == null) {
            return;
        }
        // 复制值对象
        // 本地缓存是引用，则如果不复制，那么源值修改时候，本地缓存会更新，但是redis中的数据不会更新
        final Object finalValue = copy(value);
        if (writeLocalCache) {
            Cache localCache = getLocalCache(key);
            if (localCache != null) {
                localCache.put(key, finalValue);
            }
        }
        if (!writeRemoteCache) {
            return;
        }

        // 异步更新分布式缓存（尽快让主的用户请求的执行路线返回）
        asyncTaskExecutor.execute(() -> {
            try {
                redisCache.set(key, JSON.toJSONString(finalValue), remoteCacheExpiresInSeconds);
            } catch (Exception e) {
                // logger
            }
        });
    }

    private Map innerMget(List<String> keys, List<Class> types) throws Exception {
        Map<String, Object> result = new HashMap<>();
        List<String> missKeys = new LinkedList<>();
        List<Class> missTypes = new LinkedList<>();
        if (readLocalCache) {
            for (int i = 0; i < keys.size(); i++) {
                String key = keys.get(i);
                Class type = types.get(i);
                Cache localCache = getLocalCache(key);
                if (localCache != null) {
                    Object value = localCache.getIfPresent(key);
                    result.put(key, value);
                    if (value == null) {
                        missKeys.add(key);
                        missTypes.add(type);
                    }
                } else {
                    missKeys.add(key);
                    missTypes.add(type);
                }
            }
        }

        if (!readRemoteCache) {
            return result;
        }

        final Map<String, String> missResult = new HashMap<>();
        // 对key分区，不一次性批量调用太大
        final List<List<String>> keysPage = partition(missKeys, 10);
        List<Future<Map<String, String>>> pageFutures = new LinkedList<>();

        try {
            for (final List<String> partitionKeys : keysPage) {
                Future<Map<String, String>> tp = asyncTaskExecutor.submit(() -> redisCache.mget(partitionKeys));
                pageFutures.add(tp);
            }
            for (Future<Map<String, String>> future : pageFutures) {
                missResult.putAll(future.get(3000, TimeUnit.MILLISECONDS));
            }
        } catch (Exception e) {
            pageFutures.forEach(future -> future.cancel(true));
            throw e;
        }

        // 合并missResult到result
        return result;
    }

    private List<List<String>> partition(List<String> missKeys, int size) {
        // todo 分布式下key分区
        // 这个分区算法怎么写呢？？
        return null;
    }

    private Object copy(Object value) {
        return null;
    }
}
