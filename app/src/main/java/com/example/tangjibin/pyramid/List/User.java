package com.example.tangjibin.pyramid.List;
public class User {
    private String Bed_num;
    private String Name;
    private String Sex;
    private String ID;

    public User(String Bed_num,String Name,String Sex,String ID){
        this.Bed_num=Bed_num;
        this.Name=Name;
        this.Sex=Sex;
        this.ID=ID;
    }

    public String getBed_num() {
        return Bed_num;
    }

    public void setBed_num(String Bed_num) {
        this.Bed_num = Bed_num;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String Sex) {
        this.Sex = Sex;
    }

    public String getID(){return ID;}

    public void setID(String Id){this.ID=Id;}
    }


