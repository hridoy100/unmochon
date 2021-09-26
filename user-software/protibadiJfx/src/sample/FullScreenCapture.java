package sample;
import DB.CommunicateWithPhp;
import DB.InsertIntoDB;
import com.restfb.BinaryAttachment;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import com.restfb.types.Page;
import javafx.scene.control.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.awt.AWTException;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;


/**
 * This program demonstrates how to capture a screenshot (full screen) as an
 * image which will be saved into a file.
 *
 */
public class FullScreenCapture {

    private Main main;
    boolean inserted;

    private static final long serialVersionUID = 1L;
    //public static void main(String[] args) throws UnsupportedFlavorException {
    public boolean CaptureDetails() throws UnsupportedFlavorException {
        inserted = false;
        FullScreenCapture f = new FullScreenCapture();
        try {
            Thread.sleep(5000);
            Robot robot = new Robot();
//            String fileName = "FullScreenshot.jpg";
            Date date= new Date();
            //getTime() returns current time in milliseconds
            long time = date.getTime();
            //Passed the milliseconds to constructor of Timestamp class
            Timestamp ts = new Timestamp(time);
            String timeStamp = ts.toString();
            timeStamp = timeStamp.substring(0, timeStamp.indexOf("."));
            timeStamp = timeStamp.replaceAll(":", " ");
            String fileName = timeStamp+".jpg";

            if(DEBUG_MODE.DEBUG_FullScreenCapture)
                System.out.println(fileName);

            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit()
                    .getScreenSize());
            BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
            //ImageIO.write(screenFullImage, "jpg", new File("./src/DB/"+fileName));
            ImageIO.write(screenFullImage, "jpg", new File(fileName));

            //f.setLocation(500, 500);
            //JLabel text = new JLabel("A full screenshot saved!");
            //f.add(text);
            //f.setSize(200, 100);
            //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //f.getContentPane().setLayout(new FlowLayout());
            //f.setVisible(true);

            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_F5);
            Thread.sleep(3000);

            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_F5);

            robot.keyPress(KeyEvent.VK_ALT);
            robot.keyPress(KeyEvent.VK_D);
            Thread.sleep(500);
            robot.keyRelease(KeyEvent.VK_D);
            robot.keyRelease(KeyEvent.VK_ALT);
            Thread.sleep(500);

            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_C);
            Thread.sleep(500);
            robot.keyRelease(KeyEvent.VK_C);
            robot.keyRelease(KeyEvent.VK_CONTROL);

            Thread.sleep(500);

            String selectedText =(String)Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor); // it extracts the highlighted text of system clipboard
            if(DEBUG_MODE.DEBUG_FullScreenCapture)
                System.out.println(selectedText);

            String ID = ExtractID(selectedText);
//            selectedText=selectedText.replace("&","_");
            Thread.sleep(2000);
            main.ShowImageEditor(fileName);
            //ImageEditor.main("FullScreenshot.jpg");

            //Inserts into database when closed...


            main.stage.setOnHiding( e-> {
                CommunicateWithPhp communicateWithPhp = new CommunicateWithPhp();
                /*
                final FacebookClient fb = new DefaultFacebookClient(communicateWithPhp.getToken());

                //fb.publish("324917484323515/feed", FacebookType.class, Parameter.with("message", "Rest FB test"));
                try{
                    final Page page = fb.fetchObject("349243719108528", Page.class);
                    int length = selectedText.length();
                    if(length>36) length = 36;
//                    fb.publish("349243719108528/photos", FacebookType.class,  BinaryAttachment.with("FullScreenshot.jpg", new FileInputStream(new File("FullScreenshot.jpg"))),Parameter.with("message","www.facebook.com/"+selectedText.substring(length) ));
                    fb.publish("349243719108528/photos", FacebookType.class,  BinaryAttachment.with(fileName, new FileInputStream(new File(fileName))),Parameter.with("message","www.facebook.com/"+selectedText.substring(length) ));
                }
                catch (Exception exce){
                    exce.printStackTrace();
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("FACEBOOK FAILURE");
                    alert.setHeaderText("FAILED TO POST INTO OUR FACEBOOK PAGE");
                    alert.setContentText("Facebook error");
                    alert.showAndWait();
                }
                */
                try {
                    if(communicateWithPhp.InsertDetailsIntoDB3(ID,selectedText,fileName))
                    {
                        inserted = true;
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("SUCCESSFULLY INSERTED");
                        alert.setHeaderText("IMAGE INSERTED INTO DATABASE");
                        alert.setContentText("Connection Successful");
                        alert.showAndWait();
                    }
                    else
                    {
                        inserted = false;
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("FAILED TO INSERT");
                        alert.setHeaderText("FAILED TO INSERT INTO DATABASE");
                        alert.setContentText("Connection Error");
                        alert.showAndWait();
                        return;
                    }
                }
                catch (Exception ex){
                    ex.printStackTrace();
                    return;
                }


            });


              /*
            final FacebookClient fb = new DefaultFacebookClient("EAAFbJIHdvPUBAOyJPensazRimQcnxKz8fM6ZAzqmJ6VkOwFfGFtjkvcwrYo4rl0jEN2OQUh5CyNpRcZBqUQ8sBudKGT3TT4slttgLDDeHfXUOhPYAPRIQsoDOz41eJZCVxKX1PslbGh46470gtrCTsZCIlULKQOhbWhOOnjimssMwUqgZB8F7S1DWNBvSAVPfqhL4BNLYrwZDZD");
            final Page page = fb.fetchObject("349243719108528", Page.class);
            //fb.publish("324917484323515/feed", FacebookType.class, Parameter.with("message", "Rest FB test"));
            fb.publish("349243719108528/photos", FacebookType.class,  BinaryAttachment.with("FullScreenshot.jpg", new FileInputStream(new File("FullScreenshot.jpg"))),Parameter.with("message","www.facebook.com/"+selectedText.substring(36) ));
            */

//            CommunicateWithPhp communicateWithPhp = new CommunicateWithPhp();
//
//            //if(insertIntoDB.InsertDetails(ID, selectedText, fileName))
//            if(communicateWithPhp.InsertDetailsIntoDB(ID,selectedText,fileName))
//                return true;

        } catch (AWTException | IOException | InterruptedException ex) {
            System.err.println(ex);
        }
        return inserted;
    }

    String ExtractID(String link){

        String substr = link.substring(link.lastIndexOf('/')+1);
//        substr.replace("?","_");
        if(substr.equals("")){
            substr = link.substring(link.indexOf('/')+1);
        }
//        if(substr.contains("+")){
//            substr=substr.replace("+","_");
//        }
//        if(substr.contains("&"))
//            substr=substr.replace("&","$");
        //substr=substr.replace(" ","_");
        if(DEBUG_MODE.DEBUG_FullScreenCapture)
            System.out.println("extracted id: " +substr);
        return substr;
    }


    public void SetMain(Main main) {
        this.main = main;
    }

}