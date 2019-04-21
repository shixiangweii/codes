package interview.collection;

import ifeve.concurrent.copyconwrite.CopyOnWriteMap;
import org.junit.Test;

import java.util.Collections;
import java.util.Map;

/**
 * Description:
 * User: shixiangweii
 * Date: 2019-01-26
 * Time: 14:13
 * <p>
 * http://ifeve.com/java-copy-on-write/
 * <p>
 * Java中的Copy-On-Write容器
 *
 * @author shixiangweii
 */
public class TestCopyOnWriteList {

    @Test
    public void test() {
        Map<String, String> map = new CopyOnWriteMap<>();
        map.put("092112116", "shixiangwei");
        map.put("18019694171", "shixiangwei");
        map.get("092112116");
        map.putAll(Collections.emptyMap());
        map.remove("092112116");
        map.clear();
        System.out.println(map);
    }
}
