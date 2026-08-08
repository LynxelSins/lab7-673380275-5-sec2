package com.example.demo.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.stereotype.Component;

/**
 * Concrete Strategy: ส่วนลดนักศึกษา 10%
 */
@Component
public class StudentDiscountStrategy implements DiscountStrategy {

    private static final BigDecimal DISCOUNT_RATE = new BigDecimal("0.10");

    @Override
    public BigDecimal calculatePrice(BigDecimal originalPrice) {
        BigDecimal discount = originalPrice.multiply(DISCOUNT_RATE);
        return originalPrice.subtract(discount).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String getName() {
        return "ส่วนลดนักศึกษา 10%";
    }
}
