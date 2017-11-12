package org.nop.apectj.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.nop.apectj.annotation.BusinessMetric;
import org.nop.apectj.component.MetricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

@Aspect
@Configurable
public class SpringMetricAspect {

    @Autowired
    private MetricService metricService;

    @Pointcut("@annotation(businessMetric)")
    public void meteredMethod(BusinessMetric businessMetric) {
    }

    @AfterReturning(pointcut = "meteredMethod(businessMetric)", argNames = "businessMetric")
    public void afterReturning(BusinessMetric businessMetric) {
        metricService.increment(businessMetric.value());
    }
}
