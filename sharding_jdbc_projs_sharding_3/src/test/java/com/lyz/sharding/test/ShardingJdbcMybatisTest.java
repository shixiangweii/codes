package com.lyz.sharding.test;

import com.lyz.sharding.entity.Student;
import com.lyz.sharding.entity.User;
import com.lyz.sharding.service.StudentService;
import com.lyz.sharding.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * 测试分库分表规则
 *
 * @author liuyazhuang
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    //    "classpath*:config/spring/spring-database.xml",
        "classpath*:config/spring/spring-sharding.xml"
})
public class ShardingJdbcMybatisTest {

    @Resource
    private UserService userService;

    @Resource
    private StudentService studentService;


    @Test
    public void testInit() {

    }

    @Test
    public void testUserInsert() {
        User u = new User();

        // [1][2]
        u.setUserId(11);

        u.setAge(25);
        u.setName("github");
        Assert.assertEquals(userService.insert(u), true);
    }

    @Test
    public void testStudentInsert() {
        Student student = new Student();
        // [1][1]
        student.setStudentId(21);
        student.setAge(21);
        student.setName("施向伟11-21-01");
        Assert.assertEquals(studentService.insert(student), true);
    }

    @Test
    public void testFindAll() {
        List<User> users = userService.findAll();
        if (null != users && !users.isEmpty()) {
            for (User u : users) {
                System.out.println(u);
            }
        }
    }

    @Test
    public void testSQLIN() {
        List<User> users = userService.findByUserIds(Arrays.asList(1));
        if (null != users && !users.isEmpty()) {
            for (User u : users) {
                System.out.println(u);
            }
        }
    }

    @Test
    public void testTransactionTestSucess() {
        userService.transactionTestSucess();
    }

    /**
     * https://blog.csdn.net/yanyan19880509/article/details/78335935
     * 1) 完全支持非跨库事务，例如：仅分表，或分库但是路由的结果在单库中
     * 2) 完全支持因逻辑异常导致的跨库事务。例如：同一事务中，跨两个库更新。更新完毕后，抛出空指针，则两个库的内容都能回滚。
     * 3) 不支持因网络、硬件异常导致的跨库事务。例如：同一事务中，跨两个库更新，更新完毕后、未提交之前，第一个库死机，则只有第二个库数据提交
     *
     * 下面的例子就验证了（2），其实，突然感觉，假设，数据库不出现物理错误，只是代码的逻辑异常，
     * 那么其实，假设数据库，db1,db2,db3...dbn，其实事务成功就是：T1 && T2 && T3...&& Tn
     * 就是n个事务的之间逻辑与，
     * 然后任意一个Ti失败，则就都回滚
     * 这么想的话，分库分表其实，感觉用sharding-jdbc就其实很简单了，似乎也就好理解了
     */
    @Test(expected = IllegalAccessException.class)
    public void testTransactionTestFailure() throws IllegalAccessException {
        userService.transactionTestFailure();
    }
}  
