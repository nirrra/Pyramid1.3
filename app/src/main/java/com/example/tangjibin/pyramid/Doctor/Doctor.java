package com.example.tangjibin.pyramid.Doctor;
public class Doctor  {
    private String ID;
    private String Name;
    private String address;
    private String tel;
    private String email;
    private String major;
    public Doctor(String ID,String Name,String address,String teo,String email,String major){
        this.ID=ID;
        this.address=address;
        this.Name=Name;
        this.tel=teo;
        this.email=email;
        this.major=major;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}
