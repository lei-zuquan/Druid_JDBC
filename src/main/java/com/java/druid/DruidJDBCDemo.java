package com.java.druid;

import java.sql.*;
import java.util.Properties;

/**
 * @Author:
 * @Date: 2020-12-27 22:05
 * @Version: 1.0
 * @Modified By:
 * @Description:
 */
// 使用JDBC 的方法连接Druid
public class DruidJDBCDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 1. 加载Druid 的JDBC驱动
        Class.forName("org.apache.calcite.avatica.remote.Driver");
        // 2. 获取Druid 的JDBC连接方式
        Connection connection = DriverManager.getConnection("jdbc:avatica:remote:url=http://node-03:8888/druid/v2/sql/avatica/", new Properties());
        // 3. 创建statement
        Statement statement = connection.createStatement();
        // 4. 执行sql 查询
        String sql = "select * from \"metrics\"";
        ResultSet resultSet = statement.executeQuery(sql);

        // 5. 遍历查询结果
        while (resultSet.next()) {
            String url = resultSet.getString("url");
            String user = resultSet.getString("user");
            System.out.println("user:" + user + "\t url:" +url);
        }

        // 6. 关闭连接
        resultSet.close();
        statement.close();
        connection.close();
    }
}
