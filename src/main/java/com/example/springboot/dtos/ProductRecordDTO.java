package com.example.springboot.dtos;

import jakarta.validation.constraints.NotBlank;//don't let the name be blank
import jakarta.validation.constraints.NotNull;//dont't let the value be null

import java.math.BigDecimal;

//This record get the infos e put into the DB
public record ProductRecordDTO(@NotBlank String name, @NotNull BigDecimal value) {

}
