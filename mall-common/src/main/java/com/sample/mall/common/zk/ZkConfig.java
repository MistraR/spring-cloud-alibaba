package com.sample.mall.common.zk;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import java.util.concurrent.CountDownLatch;

public class ZkConfig {

    private static final String zkServer = "localhost:2181";

    private ZkClient getZkClient() {
        ZkClient zkClient = new ZkClient(zkServer);
        zkClient.setZkSerializer(new ZkSerializer());
        return zkClient;
    }

    private CountDownLatch countDownLatch = new CountDownLatch(1);

    public void setConfigData(String path, String data) {
        ZkClient zkClient = this.getZkClient();
        if (zkClient.exists(path)) {
            zkClient.writeData(path, data);
            System.out.println("更新配置内容：" + data);
        } else {
            zkClient.createPersistent(path, data);
            System.out.println("创建配置内容：" + data);
        }
        zkClient.close();
    }

    public void getConfigData(String path) throws Exception {
        ZkClient zkClient = this.getZkClient();
        String data = zkClient.readData(path);
        System.out.println("获取到的配置内容：" + data);

        IZkDataListener dataListener = new IZkDataListener() {
            @Override
            public void handleDataChange(String path, Object changedData) {
                System.out.println("更新了配置内容：" + changedData);
            }

            @Override
            public void handleDataDeleted(String path) {
                System.out.println(path + "删除了配置内容");
            }
        };

        zkClient.subscribeDataChanges(path, dataListener);

        countDownLatch.await();
    }
}
