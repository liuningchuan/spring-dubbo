package com.liuning.web.filter;

import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.slf4j.MDC;

import java.util.UUID;

/**
 * 接收上游系统dubbo调用传入的TraceId并放入MDC码中
 */
@Activate(group = {"provider"}, order = -9999)
public class ProviderFilter implements Filter {

    public ProviderFilter(){
    }

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        String traceId = invocation.getAttachment("TRACE-ID");
        if (StringUtils.isBlank(traceId)) {
            traceId = UUID.randomUUID().toString().replaceAll("-", "");
        }

        MDC.put("TraceId", traceId);

        Result result;
        try {
            result = invoker.invoke(invocation);
        } finally {
            MDC.remove("TraceId");
        }

        return result;
    }
}
