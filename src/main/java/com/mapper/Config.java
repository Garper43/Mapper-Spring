package com.mapper;

public class Config {
    public static final String address = System.getenv("SQLaddress");
    public static final String USER = System.getenv("SQLlogin");
    public static final String PASSWORD = System.getenv("SQLpassword");
}
