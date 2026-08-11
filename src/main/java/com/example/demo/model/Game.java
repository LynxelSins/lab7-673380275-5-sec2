package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Entity: Game
 *
 * เก็บข้อมูลเกมในระบบ Game Catalog
 * มี field คำนวณ (discountName, finalPrice) ที่ไม่ persist ลงฐานข้อมูล (@Transient)
 * ใช้สำหรับแสดงผลราคาสุทธิและชื่อโปรโมชั่นที่คำนวณผ่าน Strategy Pattern
 */
@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String genre;

    @Column(nullable = false)
    private String platform;

    @Column(nullable = false)
    private Double rating;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(name = "release_date", nullable = false)
    private LocalDate releaseDate;

    /** ประเภทส่วนลด: NONE, STUDENT, SEASONAL */
    @Column(name = "discount_type", nullable = false)
    private String discountType = "NONE";

    /** ชื่อของส่วนลดที่ใช้ คำนวณผ่าน DiscountContext ไม่บันทึกลงฐานข้อมูล */
    @Transient
    private String discountName;

    /** ราคาสุทธิหลังหักส่วนลด คำนวณผ่าน DiscountContext ไม่บันทึกลงฐานข้อมูล */
    @Transient
    private BigDecimal finalPrice;

    public Game() {
    }

    // ─── Getters & Setters ───

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title; 
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public String getDiscountName() {
        return discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(BigDecimal finalPrice) {
        this.finalPrice = finalPrice;
    }
}
