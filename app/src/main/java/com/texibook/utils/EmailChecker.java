package com.texibook.utils;

import java.util.regex.Pattern;

/**
 * Created by pc6 on 3/15/2017.
 */

public class EmailChecker {

    public static boolean isValid(String email) {

        Pattern EMAIL_ADDRESS_PATTERN = Pattern
                .compile("[a-zA-Z0-9+._%-+]{1,256}" + "@"
                        + "[a-zA-Z0-9][a-zA-Z0-9-]{0,64}" + "(" + "."
                        + "[a-zA-Z0-9][a-zA-Z0-9-]{0,25}" + ")+");
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
    }
}