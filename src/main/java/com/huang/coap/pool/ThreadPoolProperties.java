package com.huang.coap.pool;

import lombok.Data;

/**
 * 线程池相关参数
 *
 * @Author huangjihui
 * @Date 2018/11/2 14:32
 */
@Data
public class ThreadPoolProperties {

    /**
     * 核心线程数
     */
    Integer corePoolSize;

    /**
     * 最大线程池数量
     */
    Integer maxPoolSize;

    /**
     * 队列最大长度
     */
    Integer maxQueueSize;

    /**
     *
     */
    Long keepAliveSeconds = 60L;

}
