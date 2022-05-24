package ru.hse.equeue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.hse.equeue.service.UserService;

@SpringBootTest
class UserTests {

    @Autowired
    private UserService userService;

    @Test
    void testSave() {
        Assertions.assertEquals(1,1);
    }

    @Test
    void testUpdate() {
        Assertions.assertEquals(1,1);

    }

    @Test
    void testDelete() {
        Assertions.assertEquals(1,1);

    }

    @Test
    void testGet() {
        Assertions.assertEquals(1,1);

    }

}
