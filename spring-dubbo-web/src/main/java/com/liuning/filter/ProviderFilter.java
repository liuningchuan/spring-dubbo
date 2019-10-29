package com.liuning.filter;

import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.slf4j.MDC;

import java.util.UUID;

@Activate(group = {"provider"}, order = -9999)
public class ProviderFilter implements Filter {

    public ProviderFilter(){
    }

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        String traceId = invocation.getAttachment("TRACEID");
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
