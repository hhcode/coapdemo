package com.huang.coap;

import com.huang.coap.resources.ResourceManager;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.californium.core.coap.CoAP.ResponseCode;
import org.eclipse.californium.core.coap.Request;
import org.eclipse.californium.core.coap.Response;
import org.eclipse.californium.core.network.Exchange;
import org.eclipse.californium.core.server.MessageDeliverer;
import org.eclipse.californium.core.server.resources.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;

/**
 * 消息分发
 *
 * @Author huangjihui
 * @Date 2018/11/1 16:49
 */
@Slf4j
@Component
public class MessageDistribute implements MessageDeliverer {

    @Autowired
    private ResourceManager resourceManager;

    @Autowired
    private ExecutorService coapExecutorService;

    @Override
    public void deliverRequest(Exchange exchange) {
        Request request = exchange.getRequest();
        String path = request.getOptions().getUriPathString();
        final Resource resource = resourceManager.getCoapResource(path);
        if (null != resource) {
            coapExecutorService.execute(() -> resource.handleRequest(exchange));
        } else {
            log.info("did not find resource [{}] requested by [{}]", path,
                    request.getSourceContext().getPeerAddress());
            exchange.sendResponse(new Response(ResponseCode.NOT_FOUND));
        }

    }

    @Override
    public void deliverResponse(Exchange exchange, Response response) {
        coapExecutorService.submit(() ->
        {
            if (response == null) {
                log.warn("Response is null");
            } else {
                exchange.getRequest().setResponse(response);
            }
        });
    }
}
