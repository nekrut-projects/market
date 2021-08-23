package gb.market.utils;

import lombok.Data;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

//@Aspect
@Component
@Data
public class StatisticsAspect {
    private Map<String, Long> serviceStatistics;

    @PostConstruct
    public void init(){
        serviceStatistics = new HashMap<>();
    }

    @Pointcut("execution(public * gb.market.UserService.*(..))")
    public void userServiceMethodStatics() {
    }

    @Pointcut("execution(public * gb.market.ProductService.*(..))")
    public void productServiceMethodStatics() {
    }

    @Pointcut("execution(public * gb.market.OrderService.*(..))")
    public void orderServiceMethodStatics() {
    }

    @Pointcut("execution(public * gb.market.CategoryService.*(..))")
    public void categoryServiceMethodStatics() {
    }

    @Pointcut("userServiceMethodStatics() || productServiceMethodStatics() || orderServiceMethodStatics() || categoryServiceMethodStatics()")
    public void allServiceMethodsStatics() {
    }

    @Around("allServiceMethodStatics()")
    public Object methodProfiling(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("start profiling");
        long begin = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        long duration = end - begin;
        if (serviceStatistics.containsKey(proceedingJoinPoint.getSourceLocation().getFileName())){
            serviceStatistics.replace(proceedingJoinPoint.getSourceLocation().getFileName(),
                        serviceStatistics.get(proceedingJoinPoint.getSourceLocation().getFileName()) + duration);
        } else {
            serviceStatistics.put(proceedingJoinPoint.getSourceLocation().getFileName(), duration);
        }
        return out;
    }
}
