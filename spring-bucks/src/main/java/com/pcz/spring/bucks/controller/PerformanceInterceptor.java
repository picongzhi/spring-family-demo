package com.pcz.spring.bucks.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author picongzhi
 */
@Slf4j
public class PerformanceInterceptor implements HandlerInterceptor {
    private ThreadLocal<StopWatch> threadLocal = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        StopWatch stopWatch = new StopWatch();
        threadLocal.set(stopWatch);
        stopWatch.start();

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        threadLocal.get().stop();
        threadLocal.get().start();
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        StopWatch stopWatch = threadLocal.get();
        stopWatch.stop();

        String name = handler.getClass().getSimpleName();
        if (handler instanceof HandlerMethod) {
            String beanType = ((HandlerMethod) handler).getBeanType().getName();
            String methodName = ((HandlerMethod) handler).getMethod().getName();
            name = beanType + "." + methodName;
        }

        log.info("uri: {}, method: {}, status: {}, exception: {}, {}ms, {}ms, {}ms",
                request.getRequestURI(),
                name,
                response.getStatus(), ex == null ? "-" : ex.getClass().getSimpleName(),
                stopWatch.getTotalTimeMillis(),
                stopWatch.getTotalTimeMillis() - stopWatch.getLastTaskTimeMillis(),
                stopWatch.getLastTaskTimeMillis());
        threadLocal.remove();
    }
}
