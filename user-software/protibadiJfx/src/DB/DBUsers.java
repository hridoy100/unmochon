package DB;

import javafx.scene.image.Image;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUsers {
    public List<User> getAllUser()
    {
        String sql = "SELECT * FROM user";
        List<List<String>> resultList = new ArrayList<>();
        List<User> res = new ArrayList<>();
        try{
            Connection con = new MySqlDB().getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.execute("USE protibadi");
            ResultSet rs = pst.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            Image img = null ;
            while (rs.next())
            {
                Blob foto = rs.getBlob("screenshot");
//                InputStream is = foto.getBinaryStream();
//                img = new Image(is) ; // false = no background loading
//                is.close();
                User user = new User(rs.getString("userid"), rs.getString("userlink"), foto);
//                List<String> row = new ArrayList<>();
//                row.add(rs.getString("userid"));
//                row.add(rs.getString("userlink"));
//                row.add(rs.getBlob("screenshot").toString());
//                resultList.add(row);
                res.add(user);

            }
            rs.close();
            pst.close();
            con.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        //System.out.printf(resultList.toString());
        return res;
    }

    public Image getImage(String userid) {
        String sql = "SELECT * FROM user where userid='" + userid + "'";
        try {
            Connection con = new MySqlDB().getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.execute("USE protibadi");
            ResultSet rs = pst.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            Image img = null;
            while(rs.next()){
                InputStream is = rs.getBinaryStream("screenshot");
                OutputStream os = new FileOutputStream(new File("screenshot.jpg"));
                byte[] content = new byte[1024];
                int size =0;
                while ((size=is.read(content))!=-1){
                    os.write(content, 0, size);
                }
                os.close();
                is.close();
                return new Image("file:screenshot.jpg");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
