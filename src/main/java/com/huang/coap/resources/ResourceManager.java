package com.huang.coap.resources;

import org.eclipse.californium.core.CoapResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * coap资源管理
 *
 * @Author huangjihui
 * @Date 2018/11/2 14:51
 */
@Component
public final class ResourceManager {

    /**
     * 自动注入所有的coap资源 key作为资源path
     */
    @Autowired
    private Map<String, CoapResource> coapResources = new HashMap<>();


    /**
     * 通过path返回资源
     *
     * @param path
     * @return
     */
    public CoapResource getCoapResource(String path) {
        if (StringUtils.isEmpty(path)) {
            return null;
        }
        return coapResources.get(path);
    }
}
