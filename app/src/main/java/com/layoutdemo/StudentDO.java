package com.layoutdemo;

public class StudentDO {

    String name;
    String address;
    String id ;


    StudentDO(String name,String address,String id){
        this.name= name;
        this.address= address;
        this.id= id;
    }

   String  getAddress(){
        return this.address;
    }

    String  getId(){
        return this.id;
    }
    String  getName(){
        return this.name;
    }

    void setName(String new_name){
        this.name=new_name;
    }

    void setAddress(String new_address){
        this.address=new_address;
    }

    void setId(String new_id){
        this.id=new_id;
    }

}
