package com.example.demo.controller;

import com.example.demo.model.Game;
import com.example.demo.service.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller (Presentation Layer)
 *
 * - SRP: รับผิดชอบเฉพาะการรับ HTTP request และเลือก View ที่จะแสดงผล
 *   ไม่มี business logic หรือการเข้าถึงฐานข้อมูลโดยตรง (มอบหมายให้ GameService ทำแทน)
 */
@Controller
@RequestMapping("/games")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    /** READ: แสดงรายการเกมทั้งหมด */
    @GetMapping
    public String listGames(Model model) {
        model.addAttribute("games", gameService.getAllGames());
        return "games/list";
    }

    /** CREATE: แสดงฟอร์มเพิ่มเกมใหม่ */
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("game", new Game());
        return "games/add";
    }

    /** CREATE: บันทึกเกมใหม่ลงฐานข้อมูล */
    @PostMapping("/save")
    public String saveGame(@ModelAttribute("game") Game game, RedirectAttributes redirectAttributes) {
        gameService.saveGame(game);
        redirectAttributes.addFlashAttribute("message", "เพิ่มเกม \"" + game.getTitle() + "\" สำเร็จ");
        return "redirect:/games";
    }

    /** UPDATE: แสดงฟอร์มแก้ไขเกม พร้อมข้อมูลเดิมจากฐานข้อมูล */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("game", gameService.getGameById(id));
        return "games/edit";
    }

    /** UPDATE: บันทึกการแก้ไขเกม */
    @PostMapping("/update/{id}")
    public String updateGame(@PathVariable Long id, @ModelAttribute("game") Game game,
                              RedirectAttributes redirectAttributes) {
        game.setId(id);
        gameService.saveGame(game);
        redirectAttributes.addFlashAttribute("message", "อัปเดตเกม \"" + game.getTitle() + "\" สำเร็จ");
        return "redirect:/games";
    }

    /** DELETE: แสดงหน้ายืนยันการลบเกม */
    @GetMapping("/delete/{id}")
    public String showDeleteConfirm(@PathVariable Long id, Model model) {
        model.addAttribute("game", gameService.getGameById(id));
        return "games/delete";
    }

    /** DELETE: ลบเกมออกจากฐานข้อมูล */
    @PostMapping("/delete/{id}")
    public String deleteGame(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", "ลบเกมสำเร็จ");
        gameService.deleteGame(id);
        return "redirect:/games";
    }
}
