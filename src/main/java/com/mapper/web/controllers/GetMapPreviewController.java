package com.mapper.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mapper.Config;
import com.mapper.beans.MapPreview;
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
import java.sql.Statement;
import java.util.Random;

@Controller
public class GetMapPreviewController {

    @RequestMapping(value = "/getmappreview", method = RequestMethod.GET)
    @ResponseBody
    public MapPreview[] saveMap(@RequestParam(name = "IDs") String input) {
        String[] idArr = input.split("\\,");
        MapPreview[] mapsArr = new MapPreview[idArr.length];

        try {
            for (int i = 0; i < idArr.length; i++) {
                File file = new File(Config.MAPS_DIR + "/" + idArr[i] + ".json");
                ObjectMapper mapper = new ObjectMapper();
                SerializedMap serMap = mapper.readValue(file, SerializedMap.class);
                MapPreview mapPreview = new MapPreview(serMap);
                mapsArr[i] = mapPreview;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        return mapsArr;
    }
}