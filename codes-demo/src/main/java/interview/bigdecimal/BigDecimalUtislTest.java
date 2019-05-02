package interview.bigdecimal;

import com.sxw.code.util.BigDecimalUtils;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-12-27
 * Time: 12:34
 *
 * @author shixiangweii
 */
public class BigDecimalUtislTest {

    @Test
    public void test() {
        System.out.println(BigDecimalUtils.valueOf(1.99));
        System.out.println(new BigDecimal(1.99));

        System.out.println(BigDecimalUtils.getInt(new BigDecimal(9.89)));
        System.out.println(new BigDecimal(9.89).intValue());
    }
}
