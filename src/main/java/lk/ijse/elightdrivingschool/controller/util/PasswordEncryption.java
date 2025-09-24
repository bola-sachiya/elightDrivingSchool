package lk.ijse.elightdrivingschool.controller.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncryption {

    public static String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(12));
    }
}
