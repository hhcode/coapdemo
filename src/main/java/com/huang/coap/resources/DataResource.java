package com.huang.coap.resources;

import com.huang.coap.utils.CoapUitl;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.springframework.stereotype.Component;

/**
 * 设备认证类
 *
 * @Author Jason
 * @Date 2018年10月16日13:49:27
 */
@Slf4j
@Component("data")
public class DataResource extends CoapResource {

    public DataResource() {
        super("");
    }

    @Override
    public void handlePOST(CoapExchange exchange) {
        log.info("request payload : ", exchange.getRequestText());

        CoapUitl.sendCoapResponse(exchange, "Hello World!" + exchange.getRequestText(), CoAP.ResponseCode.CONTENT);
    }
}
