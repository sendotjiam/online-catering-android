package com.sendo.onlinecatering;

public class Users {
    int user_id;
    String username;
    String password;
    String email;
    String phone_number;
    String gender;
    String dob;
    long olshopcash;

    public Users(int user_id, String username, String password, String email, String phone_number, String gender, String dob, long olshopcash) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone_number = phone_number;
        this.gender = gender;
        this.dob = dob;
        this.olshopcash = olshopcash;
    }

    public Users(){

    }

    public int getId() {
        return user_id;
    }

    public void setId(int id) {
        this.user_id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() { return phone_number; }

    public void setPhone(String phone) {
        this.phone_number = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dob;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dob = dateOfBirth;
    }

    public long getWallet() {
        return olshopcash;
    }

    public void setWallet(long wallet) {
        this.olshopcash = wallet;
    }
}
