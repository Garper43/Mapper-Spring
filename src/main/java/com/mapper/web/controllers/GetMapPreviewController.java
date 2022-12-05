package com.mapper.web.controllers;

import com.mapper.beans.SerializedMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Random;

@Controller
public class GetMapPreviewController {
    private final String MAPS_DIR = "src/main/resources/static/maps/";

    @RequestMapping(value = "/getmappreview", method = RequestMethod.POST)
    @ResponseBody
    public String saveMap(@RequestBody SerializedMap map, BindingResult result) {
        return "";
    }
}