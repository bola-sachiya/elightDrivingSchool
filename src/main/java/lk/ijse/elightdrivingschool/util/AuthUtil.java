package lk.ijse.elightdrivingschool.util;

import lk.ijse.elightdrivingschool.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;

public class AuthUtil {
    @Getter
    @Setter
    private static UserDTO currentUser;

    public static String getRole() {
        return currentUser != null ? currentUser.getRole() : null;
    }

    public static void clear() {
        currentUser = null;
    }
}
