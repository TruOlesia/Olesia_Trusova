package com.ot.conferences.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConferenceDto {
    private Long id;
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotNull(message = "Location is mandatory")
    private String location;
    @NotNull(message = "Start date is mandatory")
    @FutureOrPresent
    private Date startDate;
    private Date endDate;
    private ConferenceStatus status;
    private UserDto moderator;


}
