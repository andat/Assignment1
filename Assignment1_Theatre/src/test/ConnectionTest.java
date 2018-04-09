package test;

import dataAccess.ConnectionFactory;

import java.sql.Connection;

public class ConnectionTest {
    ConnectionFactory connectionFactory;

    @org.junit.Test
    public void testDbConnectionTimeout5() throws Exception {
        Connection connection = connectionFactory.getConnection();
        connection.isValid(5);
    }

}
