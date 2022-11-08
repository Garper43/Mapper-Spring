package com.mapper.web;

import com.mapper.beans.SerializedMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Controller
public class SavemapController {
    private final String MAPS_DIR = "src/main/resources/static/maps/";

    @RequestMapping(value = "/savemap", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity saveMap(@RequestBody SerializedMap input, BindingResult result) {
        System.out.println("POSTING");
        System.out.println(input.toString());
        ResponseEntity status;

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=password");
            System.out.println("Successfully connected");
        } catch(SQLException e) {
            e.printStackTrace();
        }

        try {
            File file = new File(MAPS_DIR + input.name + ".json");
            FileWriter writer = new FileWriter(file);

            writer.write(input.toString());

            writer.close();
            status = new ResponseEntity(HttpStatus.ACCEPTED);
        } catch(Exception e) {
            e.printStackTrace();
            status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return status;
    }
}