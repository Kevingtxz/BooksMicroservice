package com.kevin.booksmicroservice.base.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConvertToDateHelper {

    public static final LocalDate fromStringToLocalDate(String str) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateTime = LocalDate.parse(str, formatter);
        return dateTime;
    }
}
