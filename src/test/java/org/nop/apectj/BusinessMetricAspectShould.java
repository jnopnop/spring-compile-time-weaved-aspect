package org.nop.apectj;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.nop.apectj.aspect.SpringMetricAspect;
import org.nop.apectj.component.MetricService;
import org.nop.apectj.component.PaymentOperations;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.AopTestUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BusinessMetricAspectShould {

	@MockBean
	private MetricService metricService;

	@Autowired
	private PaymentOperations paymentOperations;

	@Autowired
    private ApplicationContext applicationContext;

	@Test(expected = NoSuchBeanDefinitionException.class)
    public void notBeRegisteredInContext() {
	    applicationContext.getBean(SpringMetricAspect.class);
    }

    @Test
    public void notWrapTargetObjectWithProxy() {
	    assertThat((PaymentOperations) AopTestUtils.getTargetObject(paymentOperations)).isEqualTo(paymentOperations);
    }

	@Test
	public void invokeMetricIncrementAfterTargetMethodSuccessfullyReturns() {
		//when
		paymentOperations.handlePurchase();

		//then
		verify(metricService, times(1)).increment(eq(PaymentOperations.PURCHASE_METRIC_NAME));
	}
}
