package ifeve.concurrent.copyconwrite;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Description: http://ifeve.com/java-copy-on-write/
 * User: shixiangweii
 * Date: 2019-01-26
 * Time: 16:17
 * <p>
 * 为什么JUC下没有CopyOnWriteMap，因为有更好的"ConcurrentMap"了，
 * 下面这个真的只是一个练习的例子！！
 *
 * @author shixiangweii
 */
public class CopyOnWriteMap<K, V> implements Map<K, V>, Cloneable {
    /**
     * 保证可见性
     */
    private volatile Map<K, V> internalMap;

    public CopyOnWriteMap() {
        internalMap = new HashMap<>();
    }

    @Override
    public V put(K key, V value) {
        synchronized (this) {
            Map<K, V> newMap = new HashMap<>(internalMap);
            V val = newMap.put(key, value);
            internalMap = newMap;
            return val;
        }
    }

    @Override
    public V remove(Object key) {
        synchronized (this) {
            Map<K, V> newMap = new HashMap<>(internalMap);
            V removed = newMap.remove(key);
            internalMap = newMap;
            return removed;
        }
    }

    @Override
    public int size() {
        return internalMap.size();
    }

    @Override
    public boolean isEmpty() {
        return internalMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return internalMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return internalMap.containsValue(value);
    }

    @Override
    public V get(Object key) {
        return internalMap.get(key);
    }

    @Override
    public void putAll(@NotNull Map<? extends K, ? extends V> m) {
        synchronized (this) {
            Map<K, V> newMap = new HashMap<>(internalMap);
            newMap.putAll(m);
            internalMap = newMap;
        }
    }

    @Override
    public void clear() {
        synchronized (this) {
            internalMap = new HashMap<>(internalMap.size() + 1);
        }
    }

    @NotNull
    @Override
    public Set<K> keySet() {
        return internalMap.keySet();
    }

    @NotNull
    @Override
    public Collection<V> values() {
        return internalMap.values();
    }

    @NotNull
    @Override
    public Set<Entry<K, V>> entrySet() {
        return internalMap.entrySet();
    }
}
