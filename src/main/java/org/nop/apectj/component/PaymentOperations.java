package org.nop.apectj.component;

import org.nop.apectj.annotation.BusinessMetric;
import org.springframework.stereotype.Service;

@Service
public class PaymentOperations {

    public static final String PURCHASE_METRIC_NAME = "purchase.count";

    @BusinessMetric(PURCHASE_METRIC_NAME)
    public void handlePurchase() {
        System.out.print("Handling purchase...");
    }
}
