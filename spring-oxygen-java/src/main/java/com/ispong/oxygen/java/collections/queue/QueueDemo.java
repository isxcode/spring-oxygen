package com.ispong.oxygen.java.collections.queue;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class QueueDemo {

    /**
     * 接口调用次数
     */
    public static int apiCount = 0;

    /**
     * 调用时刻
     */
    public static long apiInvokeTime = System.currentTimeMillis();

    /**
     * 调用第三方请求参数队列
     */
    public static Queue<String> queue = new LinkedList<>();

    /**
     * 调用接口
     */
    public void customApi() {
        queue.offer("param1");
    }

    /**
     * 写一个线程一直跑
     */
    public static void main(String[] args) {

        if (System.currentTimeMillis() - apiInvokeTime < 1000) {
            if (apiCount > 50) {
                apiCount = 0;
                apiInvokeTime = System.currentTimeMillis();
                // 线程睡眠一秒钟
            }
        } else {
            apiCount = 0;
            apiInvokeTime = System.currentTimeMillis();
        }

        // ！！！调用第三方api
        queue.remove();
        apiCount++;
    }
}
