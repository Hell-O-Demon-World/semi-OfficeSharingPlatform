package com.golfzonaca.officesharingplatform.web.reservation.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class ReservationViewFrom {
    @NotNull
    private boolean desk;
    @NotNull
    private List<Boolean> meetingRoom;
    @NotNull
    private List<Boolean> office;
}
