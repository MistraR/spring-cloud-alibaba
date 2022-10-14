package com.sample.mall.common;

import com.google.common.collect.Queues;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;

public class ThreadPoolExecutorTest {

    /**
     * 传入不同的线程池，向线程池提交15个任务，模拟每个任务执行需要2秒，观察线程池的状况
     *
     * @param threadPoolExecutor 具体的线程池
     * @throws Exception
     */
    public void execute(ThreadPoolExecutor threadPoolExecutor) {
        for (int i = 1; i <= 15; i++) {
            int j = i;
            threadPoolExecutor.submit(() -> {
                try {
                    System.out.println(sdf.format(new Date()) + "\ttask-" + j + "\tstart");
                    Thread.sleep(2000L);
                    System.err.println(sdf.format(new Date()) + "\ttask-" + j + "\tend" );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            System.out.println(sdf.format(new Date()) + "\ttask-" + j + "\tsubmit" );
        }

        try {
            // 查看线程数量和队列等待数量
            Thread.sleep(200L);
            System.out.println(sdf.format(new Date()) + "\t=== thread pool size：" + threadPoolExecutor.getPoolSize());
            System.out.println(sdf.format(new Date()) + "\t=== queue size：" + threadPoolExecutor.getQueue().size());
            // 等待20秒再查看线程数量和队列数量
            Thread.sleep(20000L);
            System.out.println(sdf.format(new Date()) + "\t=== thread pool size：" + threadPoolExecutor.getPoolSize());
            System.out.println(sdf.format(new Date()) + "\t=== queue size：" + threadPoolExecutor.getQueue().size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * corePoolSize = 5
     * maximumPoolSize = 10
     * keepAliveTime = 5s
     * workQueue = 无界队列
     * RejectedExecutionHandler = RejectedExecutionException
     */
    @Test
    public void testThreadPoolExecutor() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                5, 10,
                5, TimeUnit.SECONDS,
                Queues.newLinkedBlockingQueue()
        );
        execute(threadPoolExecutor);
    }

    /**
     * corePoolSize = 5
     * maximumPoolSize = 10
     * keepAliveTime = 5s
     * workQueue = 3
     * RejectedExecutionHandler = 自定义Handler
     */
    @Test
    public void testRejectedExecutionHandler()  {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                5, 10,
                5, TimeUnit.SECONDS,
                Queues.newLinkedBlockingQueue(2),
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        System.err.println(r.toString() + " rejected!");
                    }
                });
        execute(threadPoolExecutor);
    }

    /**
     * corePoolSize = 5
     * maximumPoolSize = 5
     * keepAliveTime = 0s
     * workQueue = 无界队列
     */
    @Test
    public void testFixedThreadPool() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                5, 5,
                0L, TimeUnit.MILLISECONDS,
                Queues.newLinkedBlockingQueue());
        execute(threadPoolExecutor);
//      Executors.newFixedThreadPool(5);
    }

    /**
     * corePoolSize = 0
     * maximumPoolSize = Integer.MAX_VALUE
     * keepAliveTime = 60s
     * workQueue = SynchronousQueue
     */
    @Test
    public void testCachedThreadPool() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                Queues.newSynchronousQueue());
        execute(threadPoolExecutor);
      Executors.newCachedThreadPool();

        try {
            Thread.sleep(60000L);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(sdf.format(new Date()) + "\t*** thread pool size：" + threadPoolExecutor.getPoolSize());
    }

    private static final SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
}
