package com.example.demo.strategy;

import java.math.BigDecimal;
import org.springframework.stereotype.Component;

/**
 * Strategy Pattern: Context
 *
 * ทำหน้าที่เลือก DiscountStrategy ที่เหมาะสมโดยอัตโนมัติ ตามค่า discountType
 * ที่ผู้ใช้เลือกจากฟอร์ม (NONE / STUDENT / SEASONAL)
 *
 * - D (Dependency Inversion Principle): พึ่งพา Abstraction (DiscountStrategy)
 *   ผ่าน Constructor Injection แทนที่จะสร้าง instance ของ Concrete Strategy เอง
 * - O (Open/Closed Principle): หากต้องการเพิ่มส่วนลดใหม่ เช่น "VIP" เพียงสร้างคลาสใหม่
 *   ที่ implement DiscountStrategy และเพิ่ม case ใน resolveStrategy โดยไม่ต้องแก้โค้ดเดิม
 */
@Component
public class DiscountContext {

    private final DiscountStrategy noDiscountStrategy;
    private final DiscountStrategy studentDiscountStrategy;
    private final DiscountStrategy seasonalSaleStrategy;

    public DiscountContext(NoDiscountStrategy noDiscountStrategy,
                            StudentDiscountStrategy studentDiscountStrategy,
                            SeasonalSaleStrategy seasonalSaleStrategy) {
        this.noDiscountStrategy = noDiscountStrategy;
        this.studentDiscountStrategy = studentDiscountStrategy;
        this.seasonalSaleStrategy = seasonalSaleStrategy;
    }

    /**
     * เลือก Strategy ที่เหมาะสมตามรหัสประเภทส่วนลด
     *
     * @param discountType รหัสประเภทส่วนลด: "NONE", "STUDENT", "SEASONAL"
     * @return DiscountStrategy ที่ตรงกับประเภทที่ระบุ (ค่าเริ่มต้นคือ NoDiscountStrategy)
     */
    public DiscountStrategy resolveStrategy(String discountType) {
        if (discountType == null) {
            return noDiscountStrategy;
        }
        return switch (discountType) {
            case "STUDENT" -> studentDiscountStrategy;
            case "SEASONAL" -> seasonalSaleStrategy;
            default -> noDiscountStrategy;
        };
    }

    /**
     * คำนวณราคาสุทธิโดยเลือก Strategy ให้อัตโนมัติจาก discountType
     */
    public BigDecimal calculateFinalPrice(String discountType, BigDecimal originalPrice) {
        return resolveStrategy(discountType).calculatePrice(originalPrice);
    }

    /**
     * ดึงชื่อของส่วนลดที่ใช้ สำหรับแสดงผลใน UI
     */
    public String getDiscountName(String discountType) {
        return resolveStrategy(discountType).getName();
    }
}
