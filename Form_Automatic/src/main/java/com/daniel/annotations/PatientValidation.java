package com.daniel.annotations;

import com.daniel.classes.Patient;
import com.daniel.classes.Sex;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;

/**
 * Created by Kapelusznik on 28.02.2017.
 */
public class PatientValidation implements Validator {
    public boolean supports(Class<?> clazz) {
        return Patient.class.equals(clazz);
    }

    public void validate(Object target, Errors errors) {
        Patient p = (Patient) target;

        if (p.getDate() == null) {
            errors.rejectValue("date", "Date is empty");
        } else if (p.getDate().isBefore(LocalDate.of(1945, 12, 31))) {
            errors.rejectValue("date", "Wrong date. Must be after after 1945-12-31 in format YYYY-MM-DD");
        }
        if (p.getSex() == null) {
            errors.rejectValue("sex", "Sex is empty");
        }
        if (p.getSurname() == null) {
            errors.rejectValue("surname", "Surname is empty");
        }
        if (p.getName() == null) {
            errors.rejectValue("name", "Name is empty");
        }
        if (!p.getMultipartFile().getOriginalFilename().matches(p.getName() + "_" + p.getSurname() + "_" + p.getPesel() + "[.jpg]") ) {
            errors.rejectValue("multipartFile", "No attachment or wrong filename. Must be Name_SURNAME_PESEL");
        }
        if (p.getPesel() == null) {
            errors.rejectValue("pesel", "Pesel is empty");
        }
        if (p.getMultipartFilename() == null) {
            errors.rejectValue("multipartFilename", "Multipartfilename is empty");
        }

        if (p.getName().matches("[A-Z][a-z]}")) {
            errors.rejectValue("name", "Invalid name. Please start with a capital letter.");
        }
        if (!p.getSurname().matches("([A-Z]|\\s)+")) {
            errors.rejectValue("surname", "Invalid surname. Must be UPPERCASE.");
        }

        if (p.getSex() == null) {
            errors.rejectValue("sex", "Must choose a sex.");
        } else if (p.getSex().equals(Sex.Female)) {


            if (!p.getPesel().matches(String.valueOf(String.valueOf(p.getDate().getYear()).substring(2, 4) + String.valueOf(p.getDate().getMonthValue()) + String.valueOf(p.getDate().getDayOfMonth()) + "[0-9]{3}(([0]|[2]|[4]|[6]|[8]){1})" + p.controlNumber(p.getPesel())))) {
                errors.rejectValue("pesel", "Wrong pesel");
            }


        } else if (p.getSex().equals(Sex.Male)) {
            if (!p.getPesel().matches(String.valueOf(String.valueOf(p.getDate().getYear()).substring(2, 4) + String.valueOf(p.getDate().getMonthValue()) + String.valueOf(p.getDate().getDayOfMonth()) + "[0-9]{3}(([1]|[3]|[5]|[7]|[9]){1})" + p.controlNumber(p.getPesel())))) {
                errors.rejectValue("pesel", "Wrong pesel");
            }
        }


    }
}
