package com.example.demo.repository;

import com.example.demo.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository (Data Access Layer)
 *
 * ใช้ Spring Data JPA จัดการ CRUD กับตาราง games ในฐานข้อมูล PostgreSQL โดยอัตโนมัติ
 * - SRP: รับผิดชอบเฉพาะการเข้าถึงฐานข้อมูลเท่านั้น ไม่มี business logic
 */
@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
}
