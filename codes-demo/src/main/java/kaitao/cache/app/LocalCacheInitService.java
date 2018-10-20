package kaitao.cache.app;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import kaitao.cache.common.CacheKeys;
import kaitao.cache.common.Switches;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * 本地缓存初始化服务
 * Description:
 * User: shixiangweii
 * Date: 2018-10-19
 * Time: 10:37
 *
 * @author shixiangweii
 */
public class LocalCacheInitService extends BaseService {

    @Resource
    private LocalCacheService localCacheService;

    @Override
    public void afterPropertiesSet() throws Exception {
        Cache<String, Object> categoryCache = CacheBuilder.newBuilder()
                .softValues()
                .maximumSize(100 * 10000)
                .expireAfterWrite(Switches.CATEGORY.getExpiresInSeconds() / 2, TimeUnit.SECONDS).build();
        // 本地缓存过期时间为分布式缓存过期时间的一半 目的：防止本地缓存时间过长造成多实例间的数据不一致
        // 讲缓存key前缀，与本地缓存做关联，为了以后方便查到本地缓存
        addCache(CacheKeys.CATEGORY_KEY, categoryCache);
    }

    private void addCache(String key, Cache<?, ?> cache) {
        localCacheService.addCache(key, cache);
    }
}
