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

    @Test(expected = IllegalAccessException.class)
    public void testTransactionTestFailure() throws IllegalAccessException {
        userService.transactionTestFailure();
    }
}  
