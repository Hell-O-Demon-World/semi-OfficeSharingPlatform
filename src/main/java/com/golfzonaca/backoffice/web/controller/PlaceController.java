package com.golfzonaca.backoffice.web.controller;

import com.golfzonaca.backoffice.domain.Place;
import com.golfzonaca.backoffice.domain.type.AddInfoType;
import com.golfzonaca.backoffice.domain.type.DaysType;
import com.golfzonaca.backoffice.repository.dto.PlaceUpdateDto;
import com.golfzonaca.backoffice.service.PlaceService;
import com.golfzonaca.backoffice.web.transformtype.TransformType;
import com.golfzonaca.backoffice.web.transformtype.form.PlaceViewForm;
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
    private final TransformType transformType;

    @ModelAttribute("DaysType")
    public DaysType[] daysType() {
        return DaysType.values();
    }

    @ModelAttribute("AddInfoType")
    public AddInfoType[] addInfoType() {
        return AddInfoType.values();
    }

    @GetMapping
    public String places(@ModelAttribute("placeSearch") Long companyId, Model model) {
        Optional<List<Place>> places = placeService.findAll(companyId);
        model.addAttribute("places", places);
        return "places";
    }

    @GetMapping("/{placeId}")
    public String place(@PathVariable Long placeId, Model model) {
        Place place = placeService.findById(placeId).get();
        PlaceViewForm placeViewForm = transformType.stringToList(place);
        model.addAttribute("place", placeViewForm);
        return "place";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("place", new Place());
        return "addForm";
    }

    @PostMapping("/add")
    public String addPlace(@ModelAttribute PlaceViewForm placeViewForm, RedirectAttributes redirectAttributes) {
        Place place = transformType.listToString(placeViewForm);
        Place savedPlace = placeService.save(place);
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

    @GetMapping("/{placeId}/delete")
    public String delete(@PathVariable Long placeId) {
        placeService.delete(placeId);
        return "redirect:/places";
    }
}