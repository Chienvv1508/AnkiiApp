package com.example.theghinho.Validation;

public class CommonValidation {
    public static boolean validateName(String name) {
        if(name == null) return false;
        if(name.compareTo("") == 0) return false;


        return name.matches("[a-zA-Z][a-zA-Z0-9_]+");
    }
}
