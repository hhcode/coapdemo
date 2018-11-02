package com.huang.coap.utils;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.californium.core.coap.CoAP.ResponseCode;
import org.eclipse.californium.core.coap.CoAP.Type;
import org.eclipse.californium.core.coap.Response;
import org.eclipse.californium.core.server.resources.CoapExchange;

@Slf4j
public final class CoapUitl {

    /**
     * 应答模式返回
     *
     * @param exchange
     * @param payload
     * @param code
     */
    public static void sendCoapResponse(CoapExchange exchange, Object payload, ResponseCode code) {
        Response coapResp = Response
                .createResponse(exchange.advanced().getRequest(), code);
        coapResp.setType(Type.ACK);
        if (null != payload) {
            coapResp.setPayload(JSON.toJSONString(payload));
        }
        exchange.advanced().sendResponse(coapResp);
    }

}
