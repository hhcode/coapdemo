package com.huang.coap;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.network.CoapEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.InetSocketAddress;

/**
 * coap启动类
 *
 * @Author huangjihui
 * @Date 2018/11/1 16:29
 */
@Slf4j
@Component
public final class CoapServerBootstrap {


    @Autowired
    private MessageDistribute messageDistribute;

    @Value("${coap.udp.server.port:5683}")
    private Integer coapPort;

    @PostConstruct
    public void init() {
        // 创建coap server 对象
        CoapServer coapServer = new CoapServer();

        // UDP
        coapServer.setMessageDeliverer(messageDistribute);
        //默认就是udp实现传输层
        InetSocketAddress coapBindToAddress = new InetSocketAddress(coapPort);
        CoapEndpoint coapEndpoint = new CoapEndpoint(coapBindToAddress);
        coapServer.addEndpoint(coapEndpoint);

        // coap server starter
        coapServer.start();
    }
}