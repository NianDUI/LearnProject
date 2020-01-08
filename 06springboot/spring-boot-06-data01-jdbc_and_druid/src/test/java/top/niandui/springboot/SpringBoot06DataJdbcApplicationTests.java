package top.niandui.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;

@SpringBootTest
class SpringBoot06DataJdbcApplicationTests {

    @Autowired
    DataSource dataSource;

    @Test
    void contextLoads() throws Exception {
        System.out.println(dataSource.getClass());

        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();

    }

}
