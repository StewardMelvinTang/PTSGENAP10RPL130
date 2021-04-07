package com.example.tugaspts_melvin;

public class DataClass {

    private int icon;
    private String nama;
    private String role;

    DataClass(int icon, String nama, String role){
        this.icon=icon;
        this.nama=nama;
        this.role=role;
    }

    public int getIcon() {
        return icon;
    }

    public String getNama() {
        return nama;
    }
    public String getRole() {
        return role;
    }
}
