package com.example.demo.strategy;

import java.math.BigDecimal;
import org.springframework.stereotype.Component;

/**
 * Concrete Strategy: ราคาปกติ ไม่มีส่วนลด (0%)
 */
@Component
public class NoDiscountStrategy implements DiscountStrategy {

    @Override
    public BigDecimal calculatePrice(BigDecimal originalPrice) {
        return originalPrice;
    }

    @Override
    public String getName() {
        return "ราคาปกติ";
    }
}
