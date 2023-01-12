package com.levina.domain;

public enum AddressParameters {
    CITY1("Ульяновск"),
    CITY2("Казань"),
    CITY3("Москва");


    private final String description;

    AddressParameters(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
