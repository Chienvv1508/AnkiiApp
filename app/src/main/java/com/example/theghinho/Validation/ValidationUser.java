package com.example.theghinho.Validation;

public  class ValidationUser {
    public static boolean validateUsername(String username) {
        if(username == null) return false;
        if (username.length() < 6) {
            return false;
        }


        return username.matches("[a-zA-Z][a-zA-Z0-9_]+");
    }
    public static boolean validatePass(String pass) {
        if(pass == null) return false;
        return pass.length() >= 8;
    }
    public static boolean validateName(String name) {

        if (name.isEmpty()) {
            return false;
        }

        if (!name.matches("[a-zA-Z ]+")) {
            return false;
        }

        return true;
    }

}
