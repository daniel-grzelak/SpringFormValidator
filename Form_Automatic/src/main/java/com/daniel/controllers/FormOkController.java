package com.daniel.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Kapelusznik on 04.03.2017.
 */
@Controller
public class FormOkController {
    @RequestMapping("/formok")
        public String test() {
            return "formok";
        }
    }

