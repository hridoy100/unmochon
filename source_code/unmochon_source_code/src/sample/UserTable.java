package sample;

//import javafx.beans.property.SimpleBlobProperty;
import javafx.beans.property.SimpleStringProperty;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;

//import java.beans.*;
import java.sql.Blob;

public class UserTable {

    private final SimpleStringProperty userid;
    private final SimpleStringProperty userlink;
    //private final SimpleBlobProperty screenshot;

    public UserTable(String userid, String userlink, Blob screenshot) {
        this.userid = new SimpleStringProperty(userid);
        this.userlink = new SimpleStringProperty(userlink);
        //this.screenshot = new SimpleBlobProperty(screenshot);
    }

    public String getUserid() {
        return userid.get();
    }

    public SimpleStringProperty useridProperty() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid.set(userid);
    }

    public String getUserlink() {
        return userlink.get();
    }

    public SimpleStringProperty userlinkProperty() {
        return userlink;
    }

    public void setUserlink(String userlink) {
        this.userlink.set(userlink);
    }

//    public byte[] getScreenshot() {
//        return screenshot.get();
//    }

//    public SimpleBlobProperty screenshotProperty() {
//        return screenshot;
//    }

//    public void setScreenshot(Blob screenshot) {
//        this.screenshot.set(screenshot);
//    }


    @Override
    public String toString() {
        return "UserTable{" +
                "userid=" + userid +
                ", userlink=" + userlink +
                //", screenshot=" + screenshot +
                '}';
    }
}
