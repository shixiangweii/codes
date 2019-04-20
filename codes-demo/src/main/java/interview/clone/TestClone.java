package interview.clone;

import interview.clone.entity.User;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Description:
 * User: shixiangweii
 * Date: 2019-01-27
 * Time: 22:49
 *
 * @author shixiangweii
 */
public class TestClone {

    @Test
    public void test() throws CloneNotSupportedException {
        User user = new User();
        user.age = 12;
        user.name = "shixiangwei";
        user.list = new ArrayList<>();
        user.list.add("1");

        User user1 = (User) user.clone();
        user1.age = 22;
        user1.name = "施向伟";
        user1.list.add("2");

        System.out.println(user);
        System.out.println(user1);

        // 其实还是对值类型，引用类型的认识
        user1.list = new ArrayList<>();

        System.out.println(user);
        System.out.println(user1);
    }
}
