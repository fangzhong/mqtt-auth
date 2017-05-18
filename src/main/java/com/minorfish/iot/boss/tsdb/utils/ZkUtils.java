package com.minorfish.iot.boss.tsdb.utils;

import org.apache.zookeeper.*;

/**
 * Created by fangzhong on 2017/5/18.
 */
public class ZkUtils {

    public static void main(String[] args) throws Exception {
        ZooKeeper zk = new ZooKeeper("localhost:2181", 5000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println(watchedEvent.toString());
            }
        });

        zk.create("/zoo2", "mydata".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(new String(zk.getData("/zoo2",false,null)));
    }
}
