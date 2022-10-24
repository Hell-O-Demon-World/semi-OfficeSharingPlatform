package com.golfzonaca.backoffice.controller;

import com.golfzonaca.backoffice.domain.Room;
import com.golfzonaca.backoffice.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/rooms/create")
    public String save(int totalNumber, Boolean roomState) {
        Room room = new Room(totalNumber, roomState);
        roomService.save(room);
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