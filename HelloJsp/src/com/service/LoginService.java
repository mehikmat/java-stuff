package com.service;

import com.model.Login;

/**
 * Created by hdhamee on 12/13/16.
 */
public class LoginService {

    public boolean check(Login s)
    {
        return "Admin".equalsIgnoreCase(s.getUname());
    }
}
