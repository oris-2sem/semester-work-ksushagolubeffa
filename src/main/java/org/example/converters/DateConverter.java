package org.example.converters;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateConverter{

    public String convert(LocalDate date) {
        try {
            String europeanDatePattern = "dd.MM.yyyy";
            DateTimeFormatter europeanDateFormatter = DateTimeFormatter.ofPattern(europeanDatePattern);
            return europeanDateFormatter.format(date);

        } catch (DateTimeParseException exception) {
            throw new IllegalArgumentException(exception);
        }
    }

}
