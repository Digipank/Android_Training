package com.layoutdemo.database;

public class GetData {

    String fname, lname, email, pass, bod, mobile, gender;

    public GetData(String fname, String lname, String email, String pass, String bod, String gender, String mobile) {
        this.fname=fname;
        this.lname=lname;
        this.email=email;
        this.pass=pass;
        this.bod=bod;
        this.gender=gender;
        this.mobile=mobile;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname)
    {
        this.fname=fname;
    }

    public String getLname()
    {
        return lname;
    }
    public void setLname(String lname)
    {
        this.lname=lname;
    }

    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email=email;
    }

    public String getPass()
    {
        return pass;
    }
    public void setPass(String pass)
    {
        this.pass=pass;
    }

    public String getBod()
    {
        return  bod;
    }
    public void setBod(String bod)
    {
        this.bod=bod;
    }

    public String getMobile()
    {
        return  mobile;
    }
    public void setMobile(String mobile)
    {
        this.mobile=mobile;
    }

    public String getGender()
    {
        return gender;
    }
    public void setGender(String gender)
    {
        this.gender=gender;
    }
}
