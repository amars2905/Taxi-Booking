package com.texibook.model;


import com.texibook.model.login_responce.LoginModel;

/**
 * Created by Natraj on 7/11/2017.
 */

public class User {

    public static LoginModel user;

    public static LoginModel getUser() {
        return user;
    }

    public static void setUser(LoginModel user) {
        User.user = user;
    }
}
