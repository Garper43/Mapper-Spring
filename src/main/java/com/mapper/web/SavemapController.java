package com.mapper.web;

import com.mapper.beans.SerializedMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileWriter;
import java.sql.*;
import java.util.Random;

@Controller
public class SavemapController {
    private final String MAPS_DIR = "src/main/resources/static/maps/";

    @RequestMapping(value = "/savemap", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity saveMap(@RequestBody SerializedMap map, BindingResult result) {
        System.out.println("POSTING");
        System.out.println(map.toString());
        ResponseEntity status;

        try {
            Random rand = new Random();

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mapper?user=root&password=password");
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO MAPS (NAME, AUTHOR, META, ID) VALUES (\"" + map.name + "\", \"Garper_\", \"\", " + rand.nextInt(1000) + ");");

            System.out.println("Successfully connected");
        } catch(SQLException e) {
            e.printStackTrace();
        }

        try {
            File file = new File(MAPS_DIR + map.name + ".json");
            FileWriter writer = new FileWriter(file);

            writer.write(map.toString());

            writer.close();
            status = new ResponseEntity(HttpStatus.ACCEPTED);
        } catch(Exception e) {
            e.printStackTrace();
            status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return status;
    }
}