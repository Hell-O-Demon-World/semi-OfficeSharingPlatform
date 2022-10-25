package com.golfzonaca.officesharingplatform.web.validation.form;

import org.hibernate.validator.constraints.NotBlank;

import java.util.Map;

public class SearchForm {

    @NotBlank
    private String searchWord;

    private Map<String, Boolean> SelectedRoomType;
}
