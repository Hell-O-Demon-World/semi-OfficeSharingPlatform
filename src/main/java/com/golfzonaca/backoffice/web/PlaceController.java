package com.golfzonaca.backoffice.web;

import com.golfzonaca.backoffice.domain.Place;
import com.golfzonaca.backoffice.service.PlaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/places") //기본 url 시작을 위한 RequestMapping
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService placeService;

    @GetMapping
    public String places(@ModelAttribute("placeSearch") Long companyId, Model model) {
        log.info("places 호출");
        Optional<List<Place>> places = placeService.findAll(companyId);
        log.info("placeService 호출");
        model.addAttribute("places", places);
        log.info("model = {}", model);
        return "places";
    }

    @PostMapping("/add")
    public String addPlace(@ModelAttribute Place place, RedirectAttributes redirectAttributes) {
        log.info("컨트롤러 호출");
        Place savedPlace = placeService.save(place);
        log.info("저장 완료");
        redirectAttributes.addAttribute("id", savedPlace.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/places/{id}";
    }
}