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



    /**
     *
     * 一开始的时候，自己想的太神奇了，直接想的是，跨库来left join,并且用的user.id=student.id来作为连接条件
     * 其实，自己想想，配置分库规则，分表规则的时候，都是用的user_id,student_id来配置规则，
     * 这里突然冒出来一个id,如果没有进行额外配置的话，当然会查不出
     * 所以这里其实还是自己对sharding-jdbc分库分表的思路，理解还是有偏差
     *
     * 这里自己也发现一个问题，当user_id=student_id这种等值连接的时候，如果分库逻辑都是用数据库数量，那么，其实，最终
     * 貌似，2个表一定会落在同一个库，在这种ID，相同下，单自己这个例子其实很大的问题，
     *
     * user_id，student_id，其实这种相连，纯粹是测语法，
     * 正常(user_id, student_id),(id,student_id)
     * 假设user一对一关联一个student,那么其实user.student_id并不是路由键，这种时候，怎去额外配置？
     * 这里就提现除另外一个问题，平时demo其实很多时候就是走个语法，稍微推敲下，多想一下，就有很多的问题，要去解决
     *
     *
     * 拆分的表与未进行拆分的表进行联表查，并传入分表字段值，可以直接扫描目标表
     *
     * 等值 inner join 直接student,user，都可以直接扫描目标表
     *
     * DEBUG StudentMapper.getStudentById - ==>  Preparing: select s.id, s.student_id as studentId, s.`name`, s.age ,u.user_id as userId from t_student s inner join t_user u on s.student_id=u.user_id where s.student_id=?
       DEBUG StudentMapper.getStudentById - ==> Parameters: 4(Integer)
       DEBUG StudentMapper.getStudentById - <==      Total: 1
       DEBUG spring.SqlSessionUtils - Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@7fc44dec]
       DEBUG datasource.DataSourceUtils - Returning JDBC Connection to DataSource
       Student(id=2, studentId=4, name=sxw, age=25, user=null, userId=4)


     */
    @Test
    public void testInnerJoin() {
        Student byId = studentService.getById(4);
        System.out.println(byId);
    }

    /**
     * DEBUG StudentMapper.getStudentsById - ==>  Preparing: select s.id, s.student_id as studentId, s.`name`, s.age ,u.user_id as userId from t_student s
     * left join t_user u on
     * s.student_id=u.user_id
     * where s.student_id=?
       DEBUG StudentMapper.getStudentsById - ==> Parameters: 4(Integer)
       DEBUG StudentMapper.getStudentsById - <==      Total: 3
       DEBUG spring.SqlSessionUtils - Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@2dbf4cbd]
       DEBUG datasource.DataSourceUtils - Returning JDBC Connection to DataSource
       [
        Student(id=2, studentId=4, name=sxw, age=25, user=null, userId=4),
        Student(id=2, studentId=4, name=sxw, age=25, user=null, userId=null),
        Student(id=2, studentId=4, name=sxw, age=25, user=null, userId=null)
       ]
     */
    @Test
    public void testLeftJoin() {
        /*
            https://blog.csdn.net/loveyou86400/article/details/80334880
            SQL中使用left join进行联表查询，并传入了分表字段，扫描所有表

            上面就可以看出，left join t_user的时候，根据传入的student_id=4,则user_id=4作为链接条件，
            会把[0]库里的，所有user表扫一遍，left join,所以，会有3条记录，刚好对应[0]中的3张user表

         */
        System.out.println(studentService.getStusById(4));
    }



    @Test
    public void testFindStuAll() {
        List<Student> all = studentService.findAll();
        System.out.println(all);
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
