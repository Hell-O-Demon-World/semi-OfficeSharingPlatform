package com.golfzonaca.backoffice.web.controller;

import com.golfzonaca.backoffice.auth.token.JwtRepostiory;
import com.golfzonaca.backoffice.domain.Company;
import com.golfzonaca.backoffice.domain.Place;
import com.golfzonaca.backoffice.domain.type.AddInfoType;
import com.golfzonaca.backoffice.domain.type.DaysType;
import com.golfzonaca.backoffice.repository.dto.PlaceUpdateDto;
import com.golfzonaca.backoffice.service.company.CompanyService;
import com.golfzonaca.backoffice.service.place.PlaceService;
import com.golfzonaca.backoffice.web.form.place.PlaceAddForm;
import com.golfzonaca.backoffice.web.form.place.PlaceEditForm;
import com.golfzonaca.backoffice.web.transformtype.TransformType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/places") //기본 url 시작을 위한 RequestMapping
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080")
public class PlaceController {
    private final JwtRepostiory jwtRepostiory;

    private final PlaceService placeService;
    private final CompanyService companyService;
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
    public String places(Model model) {
        Company company = companyService.findByCompanyLoginId(jwtRepostiory.getId()).get();
        List<Place> places = placeService.findAll(company.getId());
        model.addAttribute("places", places);
        return "place/places";
    }

    @GetMapping("/{placeId}")
    public String place(@PathVariable Long placeId, Model model) {
        Place place = placeService.findById(placeId).get();
        log.info("place={}", place);
        PlaceAddForm placeAddForm = transformType.stringToList(place);
        log.info("placeAddForm={}", placeAddForm);
        model.addAttribute("place", placeAddForm);
        return "place/place";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("place", new Place());
        return "place/addForm";
    }

    @PostMapping("/add")
    public String addPlace(@ModelAttribute PlaceAddForm placeAddForm, RedirectAttributes redirectAttributes) {
        Place place = transformType.listToString(placeAddForm);
        Place savedPlace = placeService.save(place);
        redirectAttributes.addAttribute("id", savedPlace.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/places/{id}"; //postman으로 테스트 할 때 redirect 페이지 존재하지 않으면 bindingException
    }

    @GetMapping("/{placeId}/edit")
    public String editForm(@PathVariable Long placeId, Model model) {
        log.info("editForm 컨트롤러 호출");
        Place findPlace = placeService.findById(placeId).get();
        PlaceAddForm place = transformType.stringToList(findPlace);
        log.info("place={}", place);
        model.addAttribute("place", place);
        return "place/editForm";
    }

    @PostMapping("/{placeId}/edit")
    public String edit(@PathVariable Long placeId, @ModelAttribute PlaceEditForm updateViewParam) {
        log.info("placeId={}", placeId);
        log.info("updateParam={}", updateViewParam);
        log.info("update");
        PlaceUpdateDto place = transformType.editTransform(updateViewParam);
        placeService.update(placeId, place);
        return "redirect:/places/{placeId}";
    }

    @GetMapping("/{placeId}/delete")
    public String delete(@PathVariable Long placeId) {
        placeService.delete(placeId);
        return "redirect:/places";
    }
}