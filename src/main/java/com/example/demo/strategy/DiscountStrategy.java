package com.example.demo.strategy;

import java.math.BigDecimal;

/**
 * Strategy Pattern: Interface กลางสำหรับกลยุทธ์การคำนวณส่วนลดราคาเกม
 *
 * - ISP (Interface Segregation Principle): มีเฉพาะ method ที่จำเป็นเท่านั้น
 * - LSP (Liskov Substitution Principle): ทุกคลาสที่ implement interface นี้
 *   สามารถใช้แทนกันได้อย่างสมบูรณ์ผ่าน DiscountContext
 */
public interface DiscountStrategy {

    /**
     * คำนวณราคาสุทธิหลังหักส่วนลด
     *
     * @param originalPrice ราคาปกติของเกม
     * @return ราคาสุทธิหลังหักส่วนลด
     */
    BigDecimal calculatePrice(BigDecimal originalPrice);

    /**
     * ชื่อของกลยุทธ์ส่วนลด สำหรับแสดงผลใน UI
     *
     * @return ชื่อส่วนลด เช่น "ราคาปกติ", "ส่วนลดนักศึกษา 10%"
     */
    String getName();
}
