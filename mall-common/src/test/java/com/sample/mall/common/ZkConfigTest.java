package com.sample.mall.common;

import com.sample.mall.common.zk.ZkConfig;
import org.junit.Test;

public class ZkConfigTest {

    private static final String PATH = "/config";

    @Test
    public void testSetConfig() {
        ZkConfig config = new ZkConfig();
        config.setConfigData(PATH, "backlist:123,456,789");
    }

    @Test
    public void testGetConfig() throws Exception {
        ZkConfig config = new ZkConfig();
        config.getConfigData(PATH);
    }

}
