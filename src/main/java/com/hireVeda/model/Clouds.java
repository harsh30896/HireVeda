package com.hireVeda.model;

public class Clouds{
    public int all;
}

class Coord{
    public double lon;
    public double lat;
}

class Main{
    public double temp;
    public double feels_like;
    public double temp_min;
    public double temp_max;
    public int pressure;
    public int humidity;
    public int sea_level;
    public int grnd_level;
}

class Sys{
    public int type;
    public int id;
    public String country;
    public int sunrise;
    public int sunset;
}

class Weather2{
    public int id;
    public String main;
    public String description;
    public String icon;
}

class Wind2{
    public double speed;
    public int deg;
    public double gust;
}

