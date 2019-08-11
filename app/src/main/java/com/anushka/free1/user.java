package com.anushka.free1;

public class user {

        public String pass;
        public String email;

        // Default constructor required for calls to
        // DataSnapshot.getValue(User.class)
        public user() {
        }

        public user( String email,String pass) {
            this.email = email;
            this.pass=pass;
        }
    public String getemail() {

        return email;
    }

    public void setemail(String email) {

        this.email = email;
    }

    public String getpass() {
        return pass;
    }

    public void setpass(String pass) {

        this.pass = pass;
    }
    }