package tmpAnton;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class HashingBcrypt {

     public String getHashPassword(String password) {
        return BCrypt.withDefaults().hashToString(4, password.toCharArray());
    }

    public boolean checkPassword(String password, String hashPassword) {
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), hashPassword);
        return result.verified;
    }

}
