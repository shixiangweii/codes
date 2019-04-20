package interview.clone.entity;

import lombok.ToString;

import java.util.List;

/**
 * Description:
 * User: shixiangweii
 * Date: 2019-01-27
 * Time: 22:47
 *
 * @author shixiangweii
 */
@ToString
public class User implements Cloneable {
    public String name;
    public int age;
    public List<String> list;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
