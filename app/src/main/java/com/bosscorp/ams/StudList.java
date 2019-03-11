package com.bosscorp.ams;
public class StudList {

    String name,status;
    public StudList()
    {}

    public StudList(String name, String status){
        this.name =name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
