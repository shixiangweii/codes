package com.sxw.test;

import com.alibaba.druid.pool.DruidDataSource;
import org.junit.Test;

import java.sql.*;

public class DruidDemo {
    @Test
    public void druidTest() throws SQLException {
        Connection conn = null;
        PreparedStatement psst = null;
        ResultSet rs = null;

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://47.97.5.47:10001/wbcms?useUnicode=true&characterEncoding=utf8");
        dataSource.setUsername("wanbang");
        dataSource.setPassword("Wanbang2014");

        try {
            // 获得连接:
            conn = dataSource.getConnection();
            // 编写SQL：
            String sql = "SELECT id AS indexBtnId, `icon`, id*10 AS num FROM tb_yyb_app_index_btn LIMIT 1";
            psst = conn.prepareStatement(sql);
            // 执行sql:
            rs = psst.executeQuery();
            ResultSetMetaData data = rs.getMetaData();
            while (rs.next()) {
                // System.out.println(rs.getInt("id"));
                // System.out.println(rs.getString("name"));

                for (int i = 1; i <= data.getColumnCount(); i++) {
                    //获得所有列的数目及实际列数
                    int columnCount = data.getColumnCount();
                    //获得指定列的列名
                    String columnName = data.getColumnName(i);
                    //获得指定列的列值
                    String columnValue = rs.getString(i);
                    //获得指定列的数据类型
                    int columnType = data.getColumnType(i);
                    //获得指定列的数据类型名
                    String columnTypeName = data.getColumnTypeName(i);
                    //所在的Catalog名字
                    //catalog 目录，（认字认半边的问题同样在英文中也会出现...，之前还以为是日志的意思）
                    String catalogName = data.getCatalogName(i);
                    //对应数据类型的类
                    String columnClassName = data.getColumnClassName(i);
                    //在数据库中类型的最大字符个数
                    int columnDisplaySize = data.getColumnDisplaySize(i);
                    //默认的列的标题
                    String columnLabel = data.getColumnLabel(i);
                    //获得列的模式
                    //https://www.cnblogs.com/hanxiaomeng/p/5425741.html
                    //MySQL的SQL模式默认为空，该模式为非严格模式
                    String schemaName = data.getSchemaName(i);
                    //某列类型的精确度(类型的长度)
                    int precision = data.getPrecision(i);
                    //小数点后的位数
                    int scale = data.getScale(i);
                    //获取某列对应的表名
                    String tableName = data.getTableName(i);
                    // 是否自动递增
                    boolean isAutoInctement = data.isAutoIncrement(i);
                    //在数据库中是否为货币型
                    boolean isCurrency = data.isCurrency(i);
                    //是否为空
                    int isNullable = data.isNullable(i);
                    //是否为只读
                    boolean isReadOnly = data.isReadOnly(i);
                    //能否出现在where中
                    boolean isSearchable = data.isSearchable(i);
                    System.out.println(columnCount);
                    System.out.println("获得列" + i + "的字段名称columnName:" + columnName);
                    System.out.println("获得列" + i + "的字段值columnValue:" + columnValue);
                    System.out.println("获得列" + i + "的类型,返回SqlType中的编号columnType:" + columnType);
                    System.out.println("获得列" + i + "的数据类型名columnTypeName:" + columnTypeName);
                    System.out.println("获得列" + i + "所在的Catalog名字catalogName:" + catalogName);
                    System.out.println("获得列" + i + "对应数据类型的类:" + columnClassName);
                    System.out.println("获得列" + i + "在数据库中类型的最大字符个数columnDisplaySize:" + columnDisplaySize);
                    System.out.println("获得列" + i + "的默认的列的标题columnLabel:" + columnLabel);
                    System.out.println("获得列" + i + "的模式schemaName:" + schemaName);
                    System.out.println("获得列" + i + "类型的精确度(类型的长度)precision:" + precision);
                    System.out.println("获得列" + i + "小数点后的位数scale:" + scale);
                    System.out.println("获得列" + i + "对应的表名tableName:" + tableName);
                    System.out.println("获得列" + i + "是否自动递增:" + isAutoInctement);
                    System.out.println("获得列" + i + "在数据库中是否为货币型:" + isCurrency);
                    System.out.println("获得列" + i + "是否为空:" + isNullable);
                    System.out.println("获得列" + i + "是否为只读:" + isReadOnly);
                    System.out.println("获得列" + i + "能否出现在where中:" + isSearchable);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (psst != null) {
                psst.close();
            }
            if (conn != null) {
                conn.close();
            }
            dataSource.close();
        }
    }
}
