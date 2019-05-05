package com.itheima.filter;

import com.alibaba.fescar.core.context.RootContext;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;

import static org.springframework.web.context.request.RequestAttributes.SCOPE_SESSION;

@Component
public class RequestHeaderInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        System.out.println("当前线程对象:"+Thread.currentThread());
        System.out.println("当前线程对比名字:"+  RequestContextHolder.getRequestAttributes());
//        String xid = RootContext.getXID();
        String xid = (String) RequestContextHolder.getRequestAttributes().getAttribute("xid",SCOPE_SESSION);
        System.out.println("获取并设置XID"+xid);
        RequestContextHolder.currentRequestAttributes().setAttribute("",null,1);
        if(StringUtils.isNotBlank(xid)){
            template.header("Fescar-Xid",xid);
        }
    }
}
