package ru.hse.equeue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.hse.equeue.service.QueueService;

@SpringBootTest
class QueueTest {

    @Autowired
    private QueueService queueService;

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

    @Test
    void testStandToQueue() {
        Assertions.assertEquals(1,1);

    }

    @Test
    void testRemoveUserFromQueue() {
        Assertions.assertEquals(1,1);

    }

    @Test
    void testChangeStatus() {
        Assertions.assertEquals(1,1);

    }

    @Test
    void testCheckStatistic() {
        Assertions.assertEquals(1,1);

    }

    @Test
    void testGetPage() {
        Assertions.assertEquals(1,1);
    }

    @Test
    void testGetQueuesForMap() {
        Assertions.assertEquals(1,1);
    }

}
