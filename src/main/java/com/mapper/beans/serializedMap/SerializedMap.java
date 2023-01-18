package com.mapper.beans.serializedMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mapper.Config;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SerializedMap {

    private String name;
    private int id;
    private String imageSrc;
    private ToolData toolData;
    private String Description;

    public static SerializedMap readFromFile(String filePath) throws IOException {
        return readFromFile(new File(filePath));
    }

    public static SerializedMap readFromFile(File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(file, SerializedMap.class);
    }

    public void saveAsFile(String filePath) throws IOException {
        saveAsFile(new File(filePath));
    }

    public void saveAsFile(File file) throws IOException {
        file.createNewFile();
        FileWriter writer = new FileWriter(file);
        writer.write(toString());

        writer.close();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getImageSrc() {
        return imageSrc;
    }
    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public ToolData getToolData() {
        return toolData;
    }
    public void setToolData(ToolData toolData) {
        this.toolData = toolData;
    }

    public String getDescription() {
        return Description;
    }
    public void setDescription(String description) {
        Description = description;
    }

    //returns a JSON representation of the object
    public String toString() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(this);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return "";
    }
}

