package com.team02.best_properta.entity.enums;

public enum RoleType {

    ADMIN("Admin"),
    MANAGER("Dean"),
    CUSTOMER("Customer"),
    ANONYMOUS("Anonymous");


    public final String name;

    RoleType(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }
}