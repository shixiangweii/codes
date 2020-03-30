package com.sxw.ddb;

import com.alibaba.druid.pool.DruidDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

/**
 * 客户端集成式
 * 多个数据源
 * 计算路由
 * 底层代码变复杂（过去所有的项目，数据库操作的代码都是静态，查询的表，语句，选择的数据源，但是现在变成根据分库键动态计算）
 *
 * <p>
 * 数据库提供数据存储、查询、事务能力，
 * 其实分库分表，主要是，设计，如何使用提供的能力
 * <p>
 * 可运维性、运维便利性
 * 手动建表很麻烦
 * 对表的操作很麻烦，改一个字段，要改4个表，改4次；当128,1024个分表当时候，难道还是手工吗？所以工具！工具！工具！
 * <p>
 * SQL动态拼接（分库名，分表名的拼接）
 * <p>
 * 并行查询，数据合并
 *
 * @author shixi
 */
public class MeetingDemo1 {
    /**
     * 多个数据源
     * 项目中连接多个数据源
     */
    static String db00 = "jdbc:mysql://127.0.0.1:3306/gov_meeting_00?useUnicode=true&characterEncoding=utf8";
    static String db01 = "jdbc:mysql://127.0.0.1:3306/gov_meeting_01?useUnicode=true&characterEncoding=utf8";
    static Map<String, String> urls = new HashMap<>();

    public static void main(String[] args) throws Exception {
        /**
         * 数据源和分片规则对应关系
         */
        urls.put("db00", db00);
        urls.put("db01", db01);
        createMeeting("sxw发起的会议", Arrays.asList(6, 7, 99, 77, 890));
        getUserMeetingList(1);
        getMemberList(1);
    }

    static void joinMeeting() {
        // 保证meeting.userIds和member分表中数据的最终一致性
    }

    static void createMeeting(String meetingName, List<Integer> userList) {

    }

    static void getUserMeetingList(int userId) throws Exception {
        // 计算分片键路由
        int tbIdx = userId % 4;
        int dbIdx = tbIdx / 2;
        // 判断使用哪个数据源
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        // sql拼接分库
        String jdbcUrl = "db" + String.format("%02d", dbIdx);
        dataSource.setUrl(urls.get(jdbcUrl));
        dataSource.setUsername("root");
        dataSource.setPassword("rootsxw");
        // 计算分表
        // 论证不清楚使用的分片算法，是否是均匀的
        // 拼接分表
        String sql = "select * from member_" + String.format("%02d", tbIdx % 2) + " where user_id = " + userId;
        Connection conn = dataSource.getConnection();
        PreparedStatement psst = conn.prepareStatement(sql);
        ResultSet rs = psst.executeQuery();
        List<Integer> meetingIds = new LinkedList<>();
        while (rs.next()) {
            int meetingId = rs.getInt(3);
            meetingIds.add(meetingId);
        }
        rs.close();
        psst.close();
        dataSource.close();

        dataSource = new DruidDataSource();
        dataSource.setUrl(db00);
        dataSource.setUsername("root");
        dataSource.setPassword("rootsxw");
        sql = "select * from meeting where id in ( ";
        StringBuilder sb = new StringBuilder(sql);
        meetingIds.forEach(meetingId -> sb.append(meetingId).append(","));
        sb.append("-1 ) ");
        conn = dataSource.getConnection();
        psst = conn.prepareStatement(sb.toString());
        rs = psst.executeQuery();
        while (rs.next()) {
            System.out.println("meetingId " + rs.getInt(1) + " meetingName " + rs.getString(2));
        }
        rs.close();
        psst.close();
        dataSource.close();
    }

    static void getMemberList(int meetingId) throws Exception {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(db00);
        dataSource.setUsername("root");
        dataSource.setPassword("rootsxw");
        Connection conn = dataSource.getConnection();
        PreparedStatement psst = conn.prepareStatement("select * from meeting where id = " + meetingId);
        ResultSet rs = psst.executeQuery();
        rs.next();
        String userIds = rs.getString(3);
        System.out.println(userIds);
        String[] split = userIds.split(",");
        for (String userIdStr : split) {
            // 并行计算后合并
            int userId = Integer.parseInt(userIdStr);
            // 计算分片键路由
            int tbIdx = userId % 4;
            int dbIdx = tbIdx / 2;
            // 判断使用哪个数据源
            DruidDataSource d = new DruidDataSource();
            d.setDriverClassName("com.mysql.jdbc.Driver");
            String jdbcUrl = "db" + String.format("%02d", dbIdx);
            d.setUrl(urls.get(jdbcUrl));
            d.setUsername("root");
            d.setPassword("rootsxw");
            // 计算分表
            // 论证不清楚使用的分片算法，是否是均匀的
            String sql = "select * from member_" + String.format("%02d", tbIdx % 2) + " where user_id = " + userId + " and meeting_id = " + meetingId;
            Connection c = d.getConnection();
            PreparedStatement p = c.prepareStatement(sql);
            ResultSet r = p.executeQuery();
            while (r.next()) {
                System.out.println("userId " + r.getInt(2) + " joinTime " + r.getInt(4));
            }
            r.close();
            p.close();
            d.close();
        }
    }
}
