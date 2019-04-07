package com.shashank_bhat.lms.RecyclerObject;

public class lBooks {
    String Dep_id;
    String Dep_name;
    String Noc;
    public lBooks(String Dep_id,String Dep_name,String Noc){
        this.Dep_id = Dep_id;
        this.Dep_name = Dep_name;
        this.Noc = Noc;
    }

    public String getDep_id() {
        return Dep_id;
    }

    public String getDep_name() {
        return Dep_name;
    }

    public String getNoc() {
        return Noc;
    }


    public void setDep_id(String dep_id) {
        Dep_id = dep_id;
    }

    public void setDep_name(String dep_name) {
        Dep_name = dep_name;
    }

    public void setNoc(String noc) {
        Noc = noc;
    }
}
