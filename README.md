# Lab7-673380275-5-Sec2: Database Connectivity — Game Catalog CRUD
นางสาวปภาวรินทร์ นาเมืองรักษ์ รหัสนักศึกษา 673380275-5 Section2
---
## ส่วนที่ 1: Software Design & Principles Explanation (เขียนอธิบาย)
- **อธิบายสถาปัตยกรรมและ GRASP Patterns:** เขียนอธิบายการแบ่งหน้าที่ของคลาส (Entity, Repository, Service, Controller) ตามหลัก **GRASP Patterns** (เช่น Controller Pattern, High Cohesion, Low Coupling, Information Expert, Indirection)
- **อธิบาย High-Level SOLID Principles:** เขียนอธิบายการประยุกต์ใช้หลักการ SOLID (SRP, OCP, LSP, ISP, DIP) ในระบบ
- **อธิบาย Strategy Pattern:** เขียนอธิบายการประยุกต์ใช้ **Strategy Pattern** ในการคำนวณส่วนลดราคาเกม (`DiscountStrategy`, `NoDiscountStrategy`, `StudentDiscountStrategy`, `SeasonalSaleStrategy`, `DiscountContext`) พร้อมประโยชน์ด้าน Open/Closed Principle (OCP)
- **อธิบาย Layered Architecture:** เขียนอธิบายว่าทำไมต้องแยก Service Layer ออกจาก Controller และ Repository ประโยชน์ด้าน **Low Coupling** และ **High Cohesion**
- **อธิบาย Execution Flow:** เขียนอธิบายลำดับการทำงาน (Flow) เมื่อมี HTTP Request เข้ามาจาก Browser จนไปถึงการบันทึก/ดึงข้อมูลจาก PostgreSQL และคำนวณส่วนลดผ่าน Strategy Pattern
## ส่วนที่ 2: Code Implementation & Explanation
โครงสร้าง Code พร้อมคำอธิบาย (Entity, Repository, Strategy Package, Service, Controller) โดยอธิบายการใช้ **Dependency Injection (Constructor Injection)** ในทุก Layer
## ส่วนที่ 3: Web Application & Database Screenshots
**ข้อกำหนดสำคัญ:** ในขั้นตอนการเพิ่มเกมใหม่ **นักศึกษาต้องใส่รหัสนักศึกษาและ Section ของตนเอง** ลงในข้อมูลเกม (เช่น ในช่องชื่อเกม `Title` หรือแนวเกม `Genre` เช่น `Elden Ring (663380123-4 Sec 1)`)
          
          
### ตัวอย่างการกรอกข้อมูล

**ชื่อเกม (Title):** `Elden Ring (663380123-4 SEC 1)`
**แนวเกม (Genre):** `Action RPG`
**แพลตฟอร์ม (Platform):** `PC / PS5`
**คะแนน (Rating):** `9.8`
**ราคาปกติ (บาท):** `1790.00`
**ส่วนลด (Strategy):** `ส่วนลดนักศึกษา (10%)` (ระบบจะคำนวณราคาสุทธิอัตโนมัติเป็น 1,611.00 บาท)(ให้ถ่ายภาพหน้าจอ)
**ส่วนลด (Strategy):** `ส่วนลดเทศกาล (20%)` (ระบบจะคำนวณราคาสุทธิอัตโนมัติเป็น 1,432.00 บาท)(ให้ถ่ายภาพหน้าจอ)
**วันวางจำหน่าย (Release Date):** `2022-02-25`
     - หน้าจอเพิ่มเกมใหม่ (Create) ที่กำลังกรอกข้อมูลที่มีรหัสนักศึกษา + Section
     - หน้าจอแสดงรายการเกมทั้งหมด (Read) ที่เห็นแถบแจ้งเตือนสีเขียวสำเร็จ และข้อมูลเกมที่มีรหัสนักศึกษาในตาราง
     - หน้าจอแก้ไขเกม (Update) แสดงฟอร์มแก้ไขข้อมูลเกม
     - หน้าจอยืนยันลบ + ผลลัพธ์หลังลบ (Delete)
     - หน้าจอ Database (pgAdmin หรือ terminal `psql`) แสดงข้อมูลจริงในตาราง `games` ที่มีรหัสนักศึกษาบันทึกอยู่
