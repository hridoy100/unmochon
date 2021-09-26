package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBAdministration {
    public boolean VerifyUser(String username, String password) {
        String sql = "SELECT * FROM admin where username='" + username + "' and password='" + password + "'";
        try {
            System.out.println("username: "+username + " password: "+password);
            Connection con = new MySqlDB().getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.execute("USE protibadi");
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean VerifyUserDummy(String username, String password) {
        if(username.equals("admin") && password.equals("admin")){
            return true;
        }
        else return false;
    }
}
