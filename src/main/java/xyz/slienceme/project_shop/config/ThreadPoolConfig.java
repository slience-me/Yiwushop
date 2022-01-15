package xyz.slienceme.project_shop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
public class ThreadPoolConfig {

    // 核心线程池大小
    @Value("${thread.pool.core-pool-size}")
    int corePoolSize;
    // 最大线程池大小
    @Value("${thread.pool.maximum-pool-size:10}")
    int maximumPoolSize;
    // 线程最大空闲时间
    @Value("${thread.pool.keep-alive-time:10}")
    long keepAliveTime;
    // 线程等待队列大小
    @Value("${thread.pool.work-queue.capacity:2}")
    int workQueueCapacity;

    @Bean
    public ThreadPoolExecutor threadPoolExecutor() {
        // 时间单位
        TimeUnit unit = TimeUnit.SECONDS;
        // 线程等待队列
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(workQueueCapacity);
        // 线程创建工厂
        ThreadFactory threadFactory = new NameTreadFactory();
        // 拒绝策略
        RejectedExecutionHandler handler = new MyIgnorePolicy();

        return new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit,
                workQueue, threadFactory, handler);
    }

    static class NameTreadFactory implements ThreadFactory {

        private final AtomicInteger mThreadNum = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, "my-thread-" + mThreadNum.getAndIncrement());
            System.out.println(t.getName() + " has been created");
            return t;
        }
    }

    public static class MyIgnorePolicy implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            doLog(r, e);
        }

        private void doLog(Runnable r, ThreadPoolExecutor e) {
            // 可做日志记录等
            System.err.println(r.toString() + " rejected");
        }
    }

}
