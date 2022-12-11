package com.mapper;

public class Config {
    public static final String address = System.getenv("SQLaddress");
    public static final String USER = System.getenv("SQLlogin");
    public static final String PASSWORD = System.getenv("SQLpassword");
    public static final String MAPS_DIR = "src/main/resources/static/maps/";
    public static final int MAX_REQUESTED_IDS = 20;
}
