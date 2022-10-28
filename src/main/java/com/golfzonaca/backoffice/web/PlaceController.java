package com.golfzonaca.backoffice.web;

import com.golfzonaca.backoffice.domain.Place;
import com.golfzonaca.backoffice.domain.type.DaysType;
import com.golfzonaca.backoffice.repository.dto.PlaceUpdateDto;
import com.golfzonaca.backoffice.service.PlaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/places") //기본 url 시작을 위한 RequestMapping
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService placeService;

    @ModelAttribute("DaysType")
    public DaysType[] daysType() {
        return DaysType.values();
    }

/*
    @ModelAttribute("DaysType")
    public Map<String, String> daysType() {
        Map<String, String> daysType = new LinkedHashMap<>();
        daysType.put("Mon","월요일");
        daysType.put("Tue","화요일");
        daysType.put("Wed","수요일");
        daysType.put("Thu","목요일");
        daysType.put("Fri","금요일");
        daysType.put("Sat","토요일");
        daysType.put("Sun","일요일");
        return daysType;
    }
*/


    @GetMapping
    public String places(@ModelAttribute("placeSearch") Long companyId, Model model) {
        Optional<List<Place>> places = placeService.findAll(companyId);
        model.addAttribute("places", places);
        return "places";
    }

    @GetMapping("/{placeId}")
    public String place(@PathVariable long placeId, Model model) {
        log.info("place form 컨트롤러 호출");
        Place place = placeService.findById(placeId).get();
        log.info("place={}", place);
        model.addAttribute("place", place);
        return "place";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("place", new Place());
        return "addForm";
    }

    @PostMapping("/add")
    public String addPlace(@ModelAttribute Place place, RedirectAttributes redirectAttributes) {
        log.info("컨트롤러 호출");
        log.info("place={}", place);
        log.info("place.placeOpen={}", place.getPlaceOpenDays());
        Place savedPlace = placeService.save(place);
        log.info("저장 완료");
        log.info("place = {}", place);
        log.info("placeOpen={}", savedPlace.getPlaceOpenDays());
        redirectAttributes.addAttribute("id", savedPlace.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/places/{id}"; //postman으로 테스트 할 때 redirect 페이지 존재하지 않으면 bindingException
    }

    @GetMapping("/{placeId}/edit")
    public String editForm(@PathVariable Long placeId, Model model) {
        log.info("editForm 컨트롤러 호출");
        Place place = placeService.findById(placeId).get();
        log.info("place={}", place);
        model.addAttribute("place", place);
        return "editForm";
    }

    @PostMapping("/{placeId}/edit")
    public String edit(@PathVariable Long placeId, @ModelAttribute PlaceUpdateDto updateParam) {
        placeService.update(placeId, updateParam);
        return "redirect:/places/{placeId}";
    }

    @PostMapping("/{placeId}/delete")
    public String delete(@PathVariable Long placeId) {
        placeService.delete(placeId);
        return "redirect:/places";
    }
}