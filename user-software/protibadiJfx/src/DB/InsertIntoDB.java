package DB;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class InsertIntoDB {
/*
    public boolean InsertDetails(String ID, String link, String imgFileName) {
        /*FileInputStream inputStream = null;
        PreparedStatement pst = null;
        String sql;
        //sql = "INSERT INTO user (userid,userlink,screenshot)" +" VALUES (2, 'hello', null)";
        try{
            File image = new File(imgFileName);
            inputStream = new FileInputStream(image);

            //sql = "INSERT INTO user (userid,userlink,screenshot)" +" VALUES ("+ID+", '" + link + "', '"+ (InputStream) inputStream+ "')";
            //sql = "INSERT INTO user (userid,userlink,screenshot)" +" VALUES ("+ID+", '" + link + "', ?)";
            sql = "INSERT INTO user (userid,userlink,screenshot)" +" VALUES (?,?,?)";
            Connection con = new MySqlDB().getConnection();
            pst = con.prepareStatement(sql);
            pst.setString(1, ID);
            pst.setString(2, link);
            pst.setBinaryStream(3, (InputStream) inputStream, (int)(image.length()));
            pst.execute("USE protibadi");
            pst.executeUpdate();
            pst.close();
            con.close();
            return true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return false;



    }*/
}
