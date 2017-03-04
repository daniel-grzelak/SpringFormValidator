package com.daniel.controllers;

import com.daniel.annotations.PatientValidation;
import com.daniel.classes.Patient;
import com.daniel.classes.Sex;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Kapelusznik on 28.02.2017.
 */
@Controller
public class PatientController {
    @InitBinder
    protected void initBinder(WebDataBinder binder)
    {
        binder.setValidator(new PatientValidation());
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String formGet(Model m) {
        Patient patient = new Patient();
        m.addAttribute("patient", patient);

        List<Sex> sexList = new ArrayList<>();
        Collections.addAll(sexList, Sex.Female, Sex.Male);
        m.addAttribute("sexList", sexList);

        return "patient";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String formPost(@Valid @ModelAttribute Patient patient1, BindingResult result, Model m, HttpServletRequest request) {
        if (result.hasErrors())
        {


            for (Object object : result.getAllErrors()) {
                if(object instanceof FieldError) {
                    FieldError fieldError = (FieldError) object;
                    System.out.println(fieldError.getField() + " -> " + fieldError.getCode());
                }
            }
            Patient patient = new Patient();
            m.addAttribute("patient", patient);

            List<Sex> sexList = new ArrayList<>();
            Collections.addAll(sexList, Sex.Female, Sex.Male);
            m.addAttribute("sexList", sexList);
            return "patient";
        }

        MultipartFile multipartFile = patient1.getMultipartFile();
        String realPath = request.getSession().getServletContext().getRealPath("/");
        System.out.println(realPath);
        String staticPath = "static/patients/";
        String path = realPath + staticPath + multipartFile.getOriginalFilename();
        System.out.println("--------------->" + path);

        try {
            FileCopyUtils.copy(multipartFile.getBytes(), new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(multipartFile.getOriginalFilename());
        System.out.println(patient1.toString());
        return "redirect:/formok";
    }
}
