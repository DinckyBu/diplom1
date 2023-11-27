package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {
    private static String url = System.getProperty("db.url");
    private static String user = System.getProperty("db.user");
    private static String pass = System.getProperty("db.password");
    private static final QueryRunner runner = new QueryRunner();
    private SQLHelper(){}
    @SneakyThrows
    private static Connection getConn() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }
    @SneakyThrows
    public static String getPaymentStatus() {
        var requestSQL = "SELECT status FROM payment_entity ORDER BY created DESC Limit 1";
        return getStatus(requestSQL);
    }
    @SneakyThrows
    public static String getCreditStatus() {
        var requestSQL = "SELECT status FROM credit_request_entity ORDER BY created DESC Limit 1";
        return getStatus(requestSQL);
    }
    @SneakyThrows
    private static String getStatus(String requestSQL) {
        String result = null;
        var connection = getConn();
        result = runner.query(connection, requestSQL, new ScalarHandler<String>());
        System.out.println(result);
        return result;
    }

    @SneakyThrows
    public static void cleanDatabase(){
        var connection = getConn();
        runner.execute(connection, "DELETE FROM credit_request_entity");
        runner.execute(connection, "DELETE FROM order_entity");
        runner.execute(connection, "DELETE FROM payment_entity");
    }
}
