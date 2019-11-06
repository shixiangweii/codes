package com.sxw.code.util.vo;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.function.Function;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-12-09
 * Time: 20:05
 *
 * @author shixiangweii
 */
public class ViewObjUtils {

    public static Map<String, Object> getStrObjMap(int size) {
        return new HashMap<>(size <= 0 ? 16 : size);
    }

    public static List<Map<String, Object>> getList() {
        return new LinkedList<>();
    }

    public static List<Map<String, Object>> getList(int size) {
        return new ArrayList<>(size <= 0 ? 10 : size);
    }

    /**
     * 获取不同类型map
     *
     * @param size 容量
     * @param <K>  key类型
     * @param <V>  val类型
     * @return map
     */
    public static <K, V> Map<K, V> getMap(int size) {
        return new HashMap<>(size <= 0 ? 16 : size);
    }

    /**
     * 根据列表建立索引
     * 泛型，可以使得东西横向扩展，可以复用
     * 而接口，可以实现行为参数化
     * 泛型+接口，可以实现一个通用的行为参数化
     *
     * @param list
     * @param function
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> Map<K, V> getHashIdx(List<V> list, Function<V, K> function) {
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyMap();
        }
        Map<K, V> map = new HashMap<>(list.size() + 1);
        list.forEach(item -> map.put(function.apply(item), item));
        return map;
    }

    public static boolean isBlank(String str) {
        return StringUtils.isEmpty(str) || "null".equalsIgnoreCase(str) || "undefined".equalsIgnoreCase(str) || "NaN".equals(str);
    }

    public static <K, V> Map<K, List<V>> getHashListIdx(List<V> list, Function<V, K> function) {
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyMap();
        }
        Map<K, List<V>> map = new HashMap<>(list.size() + 1);
        list.forEach(item -> {
            K key = function.apply(item);
            if (map.get(key) == null) {
                map.put(key, new LinkedList<>());
            }
            map.get(key).add(item);
        });
        return map;
    }
}
