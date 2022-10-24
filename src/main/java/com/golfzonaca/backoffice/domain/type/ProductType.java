package com.golfzonaca.backoffice.domain.type;

public enum ProductType {
    DESK("데스크"), MEETINGROOM("회의실"), OFFICE("사무실");
    private final String description;
    ProductType(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
}