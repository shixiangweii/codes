package hyuki.design.decorator.client;

import hyuki.design.decorator.Display;
import hyuki.design.decorator.FullBorder;
import hyuki.design.decorator.SideBorder;
import hyuki.design.decorator.StringDisplay;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-04-10
 * Time: 15:04
 */
public class Main {

    public static void main(String[] args){
        Display b1 = new StringDisplay("Hello, world.");
        Display b2 = new SideBorder(b1, '#');
        Display b3 = new FullBorder(b2);

        b1.show();
        b2.show();
        b3.show();

        Display b4 = new SideBorder(
                new FullBorder(
                        new FullBorder(
                                new SideBorder(
                                        new FullBorder(
                                                new StringDisplay("Hello, world")
                                        )
                                        , '*')
                        )
                )
                , '/');

        b4.show();

    }

}
