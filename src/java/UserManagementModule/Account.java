package UserManagementModule;

import CarpoolDatabase.DbRepo;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.rowset.CachedRowSet;

public class Account {

    private String emailID = null;
    private String password = null;
    private DbRepo dbCon = null;

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Account(String un, String pw) {
        this.emailID = un;
        this.password = pw;
        this.dbCon = new DbRepo();
    }

    public Account() {
        this.dbCon = new DbRepo();
    }

    public boolean login() throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        CachedRowSet crs = CarpoolDatabase.DbRepo.getConfiguredConnection();
        crs.setCommand("Select * from ACCOUNTS WHERE USERNAME = ? ");
        crs.setString(1, emailID);
        crs.execute();
        
        
        String usernameDB = "";
        String pwdDB = "";
        //MessageDigest md = MessageDigest.getInstance("MD5");
        //byte[] sign = md.digest(this.password.getBytes());

        //String pwdHash = new String(sign, "UTF-8");

        if (crs.next()) {
            usernameDB = crs.getString("USERNAME");
            pwdDB = crs.getString("PASSWORD");
            return (this.emailID.equals(usernameDB) && password.equals(pwdDB));
        } else {
            return false;
        }
    }
}
