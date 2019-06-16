package interview.bigdecimal;

import com.sxw.code.util.BigDecimalUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;

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

    @Test
    public void test2() {
        System.out.println(getFormatNumber(0.01f,1));
        System.out.println(String.format("%.2f", 0.01));

        System.out.println(new BigDecimal("0.9278479823749823").setScale(5, BigDecimal.ROUND_HALF_UP).toString());
    }


    public static String getFormatNumber(float number, int formatType) {
        DecimalFormat decimalFormat = null;
        if (formatType == 0) {
            decimalFormat = new DecimalFormat("0.##");
            return decimalFormat.format(number);
        } else {
            if (number == 0) {
                return "0.00";
            }
            decimalFormat = new DecimalFormat("0.00");
            return decimalFormat.format(number);
        }
    }
}
