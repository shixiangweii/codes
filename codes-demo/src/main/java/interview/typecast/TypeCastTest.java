package interview.typecast;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * User: shixiangweii
 * Date: 2019-06-21
 * Time: 10:34
 *
 * @author shixiangweii
 */
public class TypeCastTest {

    @SuppressWarnings("unchecked")
    @Test
    public void test() {
        Object obj = new ArrayList<Map<String, String>>();
        List<Map<String, String>> list = (List<Map<String, String>>) obj;
        System.out.println(list);
    }

}
