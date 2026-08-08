package com.example.demo.service;

import com.example.demo.model.Game;
import com.example.demo.repository.GameRepository;
import com.example.demo.strategy.DiscountContext;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Service (Business Logic Layer)
 *
 * - SRP: รับผิดชอบ business logic ของเกม (CRUD + คำนวณราคาผ่าน Strategy Pattern)
 *   แยกออกจาก Controller (HTTP/View) และ Repository (Database) อย่างชัดเจน
 * - DIP: พึ่งพา Abstraction (GameRepository interface, DiscountContext)
 *   ผ่าน Constructor Injection แทนการสร้าง instance เอง
 */
@Service
public class GameService {

    private final GameRepository gameRepository;
    private final DiscountContext discountContext;

    public GameService(GameRepository gameRepository, DiscountContext discountContext) {
        this.gameRepository = gameRepository;
        this.discountContext = discountContext;
    }

    /**
     * ดึงรายการเกมทั้งหมด พร้อมคำนวณราคาสุทธิและชื่อส่วนลดผ่าน Strategy Pattern
     */
    public List<Game> getAllGames() {
        List<Game> games = gameRepository.findAll();
        games.forEach(this::applyDiscount);
        return games;
    }

    /**
     * ดึงข้อมูลเกมตาม id พร้อมคำนวณราคาสุทธิ
     */
    public Game getGameById(Long id) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ไม่พบเกมที่มี id: " + id));
        applyDiscount(game);
        return game;
    }

    /**
     * บันทึกเกมใหม่ หรืออัปเดตเกมที่มีอยู่
     */
    public Game saveGame(Game game) {
        return gameRepository.save(game);
    }

    /**
     * ลบเกมตาม id
     */
    public void deleteGame(Long id) {
        gameRepository.deleteById(id);
    }

    /**
     * คำนวณราคาสุทธิและชื่อส่วนลดของเกม โดยใช้ DiscountContext (Strategy Pattern)
     * แล้วเซ็ตค่าลงใน field @Transient ของ entity เพื่อนำไปแสดงผลใน View
     */
    private void applyDiscount(Game game) {
        game.setFinalPrice(discountContext.calculateFinalPrice(game.getDiscountType(), game.getPrice()));
        game.setDiscountName(discountContext.getDiscountName(game.getDiscountType()));
    }
}
