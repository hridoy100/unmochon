package sample;

import javafx.beans.property.SimpleStringProperty;

import java.sql.Blob;

public class ScreenshotTable {
    private final SimpleStringProperty userid;
    private final SimpleStringProperty image_name;

    public ScreenshotTable(String userid, String image_name) {
        this.userid = new SimpleStringProperty(userid);
        this.image_name = new SimpleStringProperty(image_name);
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

    public String getImage_name() {
        return image_name.get();
    }

    public SimpleStringProperty image_nameProperty() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name.set(image_name);
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
                ", image_name=" + image_name +
                '}';
    }
}
