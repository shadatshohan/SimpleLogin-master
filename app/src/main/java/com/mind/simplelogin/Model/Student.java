package com.mind.simplelogin.Model;

public class Student {
    String email,password,roll;

    public Student()
    {

    }

    public Student(String email, String password, String roll) {
        this.email = email;
        this.password = password;
        this.roll = roll;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }
}
