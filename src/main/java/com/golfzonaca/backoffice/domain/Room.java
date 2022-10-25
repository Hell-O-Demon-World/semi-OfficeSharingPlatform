package com.golfzonaca.backoffice.domain;

import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Setter
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private Long placeTypeId;
    private Long placeId;
    private Long companyId;
    private int totalNumber;
    private Boolean roomState;

    public Room(int totalNumber, Boolean roomState) {
        this.totalNumber = totalNumber;
        this.roomState = roomState;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }
}