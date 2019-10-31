package com.liuning.filter;

import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.slf4j.MDC;

import java.util.UUID;

/**
 *  dubbo调用下游系统时将traceId传入下游系统
 */
@Activate(group = {"consumer"}, order = -9999)
public class ConsumerFilter implements Filter {

    public ConsumerFilter() {
    }

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        String traceId = MDC.get("TraceId");
        if (StringUtils.isBlank(traceId)) {
            traceId = UUID.randomUUID().toString().replaceAll("-", "");
        }
        invocation.setAttachment("TRACE-ID", traceId);
        return invoker.invoke(invocation);
    }
}
