package com.golfzonaca.backoffice.controller;

import com.golfzonaca.backoffice.domain.Room;
import com.golfzonaca.backoffice.repository.JpaRoomRepository;
import com.golfzonaca.backoffice.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping("/rooms/create")
    public String save(@ModelAttribute Room room) {
        log.info("room = {}", room);
        roomService.save(room);
        log.info("create호출");
        return "redirect:/";
    }

    @GetMapping("/rooms/update")
    public String updateRoom(Room room, int totalNumber) {
        roomService.updateRoom(room, totalNumber);
        return "redirect:/";
    }

    @GetMapping("/rooms/delete")
    public String removeRoom(Room room) {
        roomService.removeRoom(room);
        return "redirect:/";
    }

    @GetMapping("/rooms/")
    public String findRooms(Model model) {
        List<Room> rooms = roomService.findRooms();
        model.addAttribute("rooms", rooms);
        return "rooms/roomList";
    }
}