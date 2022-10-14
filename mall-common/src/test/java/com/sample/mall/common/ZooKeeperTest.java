package com.sample.mall.common;

import org.apache.zookeeper.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class ZooKeeperTest {

    private ZooKeeper zooKeeper;

    private static final String PATH = "/ZooKeeperTest";

    @Before
    public void setUp() throws Exception {
        zooKeeper = new ZooKeeper("localhost:2181", 5000, null);
    }

    @After
    public void tearDown() throws Exception {
        zooKeeper.close();
    }

    @Test
    public void TestCreate() throws Exception {
        String result = zooKeeper.create(
                PATH,
                "TestCreate".getBytes(StandardCharsets.UTF_8),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT
        );
        Assert.assertEquals(result, PATH);
    }

    @Test
    public void TestGetChildren() throws Exception {
        List<String> children = zooKeeper.getChildren(PATH, null);
        Assert.assertEquals(2, children.size());
    }

    @Test
    public void TestSetData() throws Exception {
        zooKeeper.setData(PATH, "newData".getBytes(StandardCharsets.UTF_8), 0);
    }

    @Test
    public void TestDelete() throws Exception {
        zooKeeper.delete(PATH + "/child1", 0);
    }

    @Test
    public void TestGetChildrenWithWatcher() throws Exception {
        List<String> children = zooKeeper.getChildren(PATH, event -> System.out.println(event));
        zooKeeper.delete(PATH + "/child2", 0);
    }
}
