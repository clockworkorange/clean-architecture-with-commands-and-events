package es.jripoll.application.interceptors;

import java.util.Arrays;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import lombok.extern.slf4j.Slf4j;

@Logging
@Interceptor
@Slf4j
public class LoggingInterceptor {

  @AroundInvoke
  Object logging(InvocationContext ctx) throws Exception {
    final String methodName = ctx.getMethod().getDeclaringClass().getName();
    final Object[] inputArguments = Arrays.stream(ctx.getParameters()).toArray();

    log.debug("[START] method: [{}], args: [{}]", methodName, inputArguments);

    final Object ret = ctx.proceed();
    log.debug("[END] method: [{}], returns: [{}]", methodName, ret);

    return ret;
  }

}