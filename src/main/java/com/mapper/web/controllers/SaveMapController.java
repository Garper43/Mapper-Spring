package com.mapper.web.controllers;

import com.mapper.Config;
import com.mapper.beans.serializedMap.SerializedMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileWriter;
import java.sql.*;
import java.util.Random;

@Controller
public class SaveMapController {

    @RequestMapping(value = "/savemap", method = RequestMethod.POST)
    @ResponseBody
    public int saveMap(@RequestBody SerializedMap map) {
        Random rand = new Random();
        boolean newMap = false;

        //TODO: find a more secure way to do this, the client shouldn't be trusted with setting the id
        //keep old id if map already has one, and pick one if it doesn't
        if(map.getId() == -1) {
            map.setId(rand.nextInt(1000));
            newMap = true;
        }

        try {
            //make a db entry for the map
            if(newMap) {
                Connection connection = DriverManager.getConnection(Config.ADDRESS);
                Statement statement = connection.createStatement();
                statement.executeUpdate("INSERT INTO MAPS (NAME, AUTHOR, META, ID) VALUES ('" + map.getName() + "', 'Garper_', '', " + map.getId() + ");");
                statement.close();
                connection.close();
            }

            //save map to a file
            map.saveAsFile(new File(Config.MAPS_DIR + map.getId() + ".json"));
        } catch(Exception e) {
            e.printStackTrace();
        }

        return map.getId();
    }
}