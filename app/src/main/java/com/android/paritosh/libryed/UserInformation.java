package com.android.paritosh.libryed;

/**
 * Created by PARITOSH on 12/23/2017.
 */

public class UserInformation {
    public String name;
    public String halltckt;

    public UserInformation() {

    }

    public UserInformation(String name) {
        this.name = name;
        //this.halltckt = halltckt;
    }
    public UserInformation(String name, String halltckt) {
        this.name = name;
        this.halltckt = halltckt;
    }
}