package springsecuritydemo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.servlet.handler.MatchableHandlerMapping;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ApplicationTest {

    @Autowired
    public MatchableHandlerMapping handlerMapping;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void test1() {
        System.out.println(handlerMapping.toString());
    }
}