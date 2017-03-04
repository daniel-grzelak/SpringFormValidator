package com.daniel.classes;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Kapelusznik on 28.02.2017.
 */
public class Patient {

    private String name;
    private String surname;
    private LocalDate date;
    private Sex sex;
    private String pesel;
    private MultipartFile multipartFile;
    private String multipartFilename;


    public Patient(String name, String surname, String date, Sex sex, String pesel, MultipartFile multipartFile, String multipartFilename) {
        this.name = name;
        this.surname = surname;

        this.sex = sex;
        this.pesel = pesel;
        this.multipartFile = multipartFile;
        this.multipartFilename = multipartFilename;
    }

    public Patient() {

    }

    public int controlNumber(String pesel) {
        int j = 0;
        String cipher = "1379";
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < pesel.length()-1; i++) {
         sb.append((Character.getNumericValue((pesel.charAt(i))) * Character.getNumericValue(cipher.charAt(j))) % 10);
            if(j == 3) {
                j = 0;
            }else {
                j++;
            }

        }

        int number = 0;
        for(int i = 0; i < sb.toString().length(); i++) {
            number += Character.getNumericValue(sb.toString().charAt(i));
        }



      return 10 - (number % 10);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(String date) {
       this.date = LocalDate.parse(date);
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    public String getMultipartFilename() {
        return multipartFilename;
    }

    public void setMultipartFilename(String multipartFilename) {
        this.multipartFilename = multipartFilename;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", date=" + date +
                ", sex='" + sex + '\'' +
                ", pesel='" + pesel + '\'' +
                ", multipartFile=" + multipartFile +
                ", multipartFilename='" + multipartFilename + '\'' +
                '}';
    }
}
