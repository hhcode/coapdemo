package com.huang.coap.enums;

import lombok.Getter;

/**
 * 线程池类型
 *
 * @Author huangjihui
 * @Date 2018/11/2 14:40
 */
public enum ThreadPoolTypeEnum {
    /**
     * coap
     */
    COAP("coap");

    @Getter
    private String value;


    ThreadPoolTypeEnum(String value) {
        this.value = value;
    }

}
