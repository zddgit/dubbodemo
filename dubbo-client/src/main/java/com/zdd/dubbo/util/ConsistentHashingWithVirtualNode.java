package com.zdd.dubbo.util;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 带虚拟节点的一致性hash算法
 */
public class ConsistentHashingWithVirtualNode {
    private static String[] servers = {"192.168.0.0:111", "192.168.0.1:111",
            "192.168.0.2:111", "192.168.0.3:111", "192.168.0.4:111"};
    //虚拟节点的数目，这里写死，为了演示需要，一个真实结点对应5个虚拟节点
    private static final int VIRTUAL_NODES = 5;
    //虚拟节点，key表示虚拟节点的hash值，value表示虚拟节点的名称
    private static SortedMap<Integer, String> virtualNodes = new TreeMap<>();

    static {
        //先把原始的服务器添加到真实结点列表中
        for (String server : servers) {
            for (int i = 0; i < VIRTUAL_NODES; i++) {
                String serverkey = server + "&&VN" + i;
                int hash = getHash(serverkey);
                System.out.println("虚拟节点[" + serverkey + "]被添加, hash值为" + hash);
                virtualNodes.put(hash, server);
            }
        }
    }

    //得到应当路由到的结点
    private static String getServer(String key) {
        //得到该key的hash值
        int hash = getHash(key);
        SortedMap<Integer, String> subMap = virtualNodes.tailMap(hash);
        String virtualNode;
        if (subMap.isEmpty()) {
            //如果没有比该key的hash值大的，则从第一个node开始
            virtualNode = virtualNodes.get(virtualNodes.firstKey());
        } else {
            ///第一个Key就是顺时针过去离node最近的那个结点
            virtualNode = virtualNodes.get(subMap.firstKey());
        }
        //virtualNode虚拟节点名称要截取一下
        if (virtualNode != null) {
            return virtualNode;
        }
        return null;
    }


    //使用FNV1_32_HASH算法计算服务器的Hash值,这里不使用重写hashCode的方法，最终效果没区别
    private static int getHash(String str) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < str.length(); i++)
            hash = (hash ^ str.charAt(i)) * p;
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;

        // 如果算出来的值为负数则取其绝对值
        if (hash < 0)
            hash = Math.abs(hash);
        return hash;
    }

    public static void main(String[] args) {
        String[] keys = {"太阳", "月亮", "星星"};
        for (int i = 0; i < keys.length; i++)
            System.out.println("[" + keys[i] + "]的hash值为" +
                    getHash(keys[i]) + ", 被路由到结点[" + getServer(keys[i]) + "]");
    }


}
