package interview.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * User: shixiangweii
 * Date: 2019-04-22
 * Time: 8:20
 *
 * @author shixiangweii
 */
public class FindNumber {

    public static void main(String[] args) {
        int[] a = {4, 5, 1, 100, -1, 9};
        int sum = 99;
        // <数值，数组下标>
        Map<Integer, Integer> map = new HashMap<>(a.length);
        for (int i = 0; i < a.length; i++) {
            map.put(a[i], i);
        }
        for (int n : a) {
            int m = sum - n;
            if (map.containsKey(m)) {
                System.out.println("[" + map.get(m) + "]:" + m);
            }
        }
    }
}
