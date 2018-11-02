package com.huang.coap.pool;

import com.huang.coap.enums.ThreadPoolTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * 线程池配置类
 *
 * @Author huangjihui
 * @Date 2018/11/1 17:27
 */
@Configuration
@Slf4j
public class ThreadPoolConfig {

    /**
     * 处理coap 请求的线程池
     *
     * @return
     */
    @Bean(name = "coapExecutorService")
    public ExecutorService getCoapExecutorService() {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) getExecutorService(getCoapThreadPoolProperties(), new BaseThreadFactory(ThreadPoolTypeEnum.COAP));
        executorService.allowCoreThreadTimeOut(true);
        executorService.setRejectedExecutionHandler((r, executor) -> {
            log.error("coap pools thread reject ", r);
            if (!executor.isShutdown()) {
                r.run();
            }
        });
        return executorService;
    }

    /**
     * coap thread pool 配置
     *
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "coap.thread-pool")
    public ThreadPoolProperties getCoapThreadPoolProperties() {
        return new ThreadPoolProperties();
    }


    private ExecutorService getExecutorService(ThreadPoolProperties poolProperties, ThreadFactory factory) {

        Integer maxQueueSize = poolProperties.getMaxQueueSize();
        if (maxQueueSize != null && maxQueueSize > 0) {
            return new ThreadPoolExecutor(poolProperties.getCorePoolSize(), poolProperties.getMaxPoolSize(),
                    poolProperties.getKeepAliveSeconds(), TimeUnit.SECONDS, new LinkedBlockingQueue<>(maxQueueSize), factory);
        }
        return new ThreadPoolExecutor(poolProperties.getCorePoolSize(), poolProperties.getMaxPoolSize(),
                poolProperties.getKeepAliveSeconds(), TimeUnit.SECONDS, new LinkedBlockingQueue<>(), factory);
    }
}
