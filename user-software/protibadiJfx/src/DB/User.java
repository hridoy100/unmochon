package DB;

import javafx.scene.image.Image;

import java.sql.Blob;

public class User {
    public String userid;
    public String userlink;
    public Blob screenshot;

    public User(String userid, String userlink, Blob screenshot) {
        this.userid = userid;
        this.userlink = userlink;
        this.screenshot = screenshot;
    }
}
