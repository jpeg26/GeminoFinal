package com.mlabs.bbm.firstandroidapp_morningclass;



public class DataBaseHelper {

    private int id;
    private String uNam, eMail, pass, fNam, lNam;

    public DataBaseHelper() {}

    public void setAccounts(String uNam, String eMail, String pass, String fNam, String lNam) {
        this.uNam = uNam;
        this.eMail = eMail;
        this.pass = pass;
        this.fNam = fNam;
        this.lNam = lNam;

    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public String getuNam() {
        return this.uNam;
    }

    public String geteMail() {
        return this.eMail;
    }

    public String getPass() {
        return this.pass;
    }

    public String getfNam() {
        return this.fNam;
    }

    public String getlNam() {
        return this.lNam;
    }



}
