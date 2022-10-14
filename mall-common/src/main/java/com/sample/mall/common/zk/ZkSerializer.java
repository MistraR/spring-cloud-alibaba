package com.sample.mall.common.zk;

import org.I0Itec.zkclient.exception.ZkMarshallingError;
import java.nio.charset.StandardCharsets;

public class ZkSerializer implements org.I0Itec.zkclient.serialize.ZkSerializer {

    @Override
    public Object deserialize(byte[] bytes) throws ZkMarshallingError {
        return new String(bytes, StandardCharsets.UTF_8);
    }

    @Override
    public byte[] serialize(Object obj) throws ZkMarshallingError {
        return String.valueOf(obj).getBytes(StandardCharsets.UTF_8);
    }
}
