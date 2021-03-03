package com.example.kindergarten;

public class FirebasePost {
    public  String name;
    public String phone;
    public Integer attend;
    public Integer appear;

    public FirebasePost(){
    }

    public FirebasePost(String name, String phone, Integer attend, Integer appear) {
        this.name = name;
        this.phone = phone;
        this.attend = attend;
        this.appear = appear;
    }
}
