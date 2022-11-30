package com.mapper.web.controllers;

import com.mapper.Config;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;

@Controller
public class SearchMapsController {

    @RequestMapping(value = "/searchmap", method = RequestMethod.GET)
    @ResponseBody
    public String saveMap(@RequestParam(name = "name") String name) {
        String[] keywords = name.split("\\|");
        System.out.println(Arrays.toString(keywords));
        String resultIDs = "";

        try {
            StringBuffer query = new StringBuffer("""
                SELECT ID 
                FROM MAPS 
                WHERE
            """);

            for(String keyword : keywords) {
                query.append("NAME LIKE '%" + keyword + "%' ");
            }
            query.append(";");

            System.out.println(query.toString());

            Connection connection = DriverManager.getConnection(Config.address + "/mapper", Config.USER, Config.PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query.toString());

            while(resultSet.next()) {
                resultIDs += "" + resultSet.getInt(1) + ",";
            };

            statement.close();
            connection.close();

        } catch(Exception e) {
            e.printStackTrace();
        }

        return resultIDs;
    }
}
