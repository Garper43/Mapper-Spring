package com.mapper.web.controllers;

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
public class SaveMapController {
    private final String MAPS_DIR = "src/main/resources/static/maps/";

    @RequestMapping(value = "/savemap", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity saveMap(@RequestBody SerializedMap map, BindingResult result) {
        System.out.println("POSTING");
        System.out.println(map.toString());
        ResponseEntity status;
        Random rand = new Random();
        int id = rand.nextInt(1000);

        try {
            String address = System.getenv("SQLaddress");
            String user = System.getenv("SQLlogin");
            String password = System.getenv("SQLpassword");

            Connection connection = DriverManager.getConnection(address + "/mapper", user, password);
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO MAPS (NAME, AUTHOR, META, ID) VALUES ('" + map.name + "', 'Garper_', '', " + id + ");");
            statement.close();
            connection.close();

        } catch(SQLException e) {
            e.printStackTrace();
        }

        try {
            File file = new File(MAPS_DIR + id + ".json");
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