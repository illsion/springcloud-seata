package com.itheima.item.filter;

import com.alibaba.fescar.core.context.RootContext;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class RequestHeaderInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        String xid = RootContext.getXID();
        System.out.println("获取并设置XID"+xid);
        if(StringUtils.isNotBlank(xid)){
            template.header("Fescar-Xid",xid);
        }
    }
}
