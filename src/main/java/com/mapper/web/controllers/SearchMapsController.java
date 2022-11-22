package com.mapper.web.controllers;

import com.mapper.beans.SerializedMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class SearchMapsController {
    @RequestMapping(value = "/searchMap", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity saveMap(@RequestParam(name = "name") String name, BindingResult result) {
        return new ResponseEntity(HttpStatus.OK);
    }
}
