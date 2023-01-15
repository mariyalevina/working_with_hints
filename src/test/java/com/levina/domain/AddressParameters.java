package com.levina.domain;

public enum AddressParameters {
    CITY1("Краснодар"),
    CITY2("Волгоград"),
    CITY3("Пенза");


    private final String description;

    AddressParameters(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
