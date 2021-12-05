package sample;

import DB.CommunicateWithPhp;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static sample.Main.fileManager;
import static sample.Main.imageEditor;
import static sample.NewFirstController.*;


/**
 * This program demonstrates how to capture a screenshot (full screen) as an
 * image which will be saved into a file.
 *
 */
public class FullScreenCapture {

    private Main main;
    boolean inserted;

    public static ImageView container;

    public static StackPane scontainer;

    private static final long serialVersionUID = 1L;
    //public static void main(String[] args) throws UnsupportedFlavorException {
    public boolean CaptureDetails() throws UnsupportedFlavorException {
        inserted = true;
        //FullScreenCapture f = new FullScreenCapture();
        try {

            ///////Thread.sleep(300);
            Robot robot = new Robot();

            robot.keyPress(KeyEvent.VK_ALT);
            robot.keyPress(KeyEvent.VK_D);
            Thread.sleep(500);
            robot.keyRelease(KeyEvent.VK_D);
            robot.keyRelease(KeyEvent.VK_ALT);
            Thread.sleep(100);

            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_C);
            Thread.sleep(500);
            robot.keyRelease(KeyEvent.VK_C);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            Thread.sleep(200);

            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_F5);
            Thread.sleep(220);///////////////1300
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_F5);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        /*main.stage.toFront();
                        //main.stage.setAlwaysOnTop(true);
                        main.stage.setIconified(false);
                        main.stage.show();
                        main.stage.requestFocus();
                        main.stage.toFront();*/

                        ////////showTimer();
                        /*Stage popUpStage=Timer.stage;
                        popUpStage.setAlwaysOnTop(true);
                        //popUpStage.setAlwaysOnTop(false);
                        if (popUpStage.isIconified())
                            popUpStage.setIconified(false);
                        popUpStage.show();
                        popUpStage.requestFocus();
                        popUpStage.toFront();*/
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                        inserted = false;
                    }
                }
            });

            String selectedText =(String)Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor); // it extracts the highlighted text of system clipboard
            if(DEBUG_MODE.DEBUG_FullScreenCapture)
                System.out.println(selectedText);
            Main.selectedText=selectedText;
            Main.ID = ExtractID(selectedText);
//            selectedText=selectedText.replace("&","_");
            Thread.sleep(5000);

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
            image1= SwingFXUtils.toFXImage(screenFullImage, null );


            //ImageIO.write(screenFullImage, "jpg", new File("./src/DB/"+fileName));
            File file=new File(fileName);
            //ImageIO.write(screenFullImage, "jpg", file);
            //Thread.sleep(100);
            //tempfileone=file;
            //main.ShowImageEditor(fileName);
            //ImageEditor.main("FullScreenshot.jpg");


            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    main.stage.setIconified(false);
                    main.stage.toFront();

                    GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
                    final int width = gd.getDisplayMode().getWidth();
                    final int height = gd.getDisplayMode().getHeight();

                    main.stage.setHeight(height);
                    main.stage.setWidth(width);
                    main.stage.setX(0);
                    main.stage.setY(0);
                }
            });
            Thread.sleep(100);
            try {
                //Thread.sleep(100);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        //stage.setFullScreen(true);
                        //Thread.sleep(100);
                        //stage.setFullScreen(false);

                        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
                        final int width = gd.getDisplayMode().getWidth();
                        final int height = gd.getDisplayMode().getHeight();

                        try {
                            fileManager = imageEditor.initFileManager(fileName);
                            final Image image = image1;//fileManager.loadImage(new File(fileName));
                            canvas.setWidth(image.getWidth());
                            canvas.setHeight(image.getHeight());
                            dcanvas.setWidth(image.getWidth());
                            dcanvas.setHeight(image.getHeight());
                            canvas.getGraphicsContext2D().drawImage(image, 0, 0, image.getWidth(), image.getHeight());
                        }catch (Exception e)
                        {
                            e.printStackTrace();
                            inserted=false;
                        }
                        dcanvas.getGraphicsContext2D().clearRect(0,0,5000,5000);
                        simage_wrapper.setScaleX(0.4*width/window_width);
                        simage_wrapper.setScaleY(0.3*height/window_height);

                        //simage_wrapper.setScaleX(2);
                        //simage_wrapper.setScaleY(2);
                    }
                });

                //simage_wrapper.setPrefWidth(window_width/2);
                //simage_wrapper.setPrefHeight(window_height/2);
                //scontainer.setPrefWidth(window_width/2);
                //scontainer.setPrefHeight(window_height/2);
                //scontainer.setLayoutX(0);
                //scontainer.setLayoutY(0);
                //simage_wrapper.setLayoutX(0);
                //simage_wrapper.setLayoutY(0);

                //container.setImage(image);
                //container.setScaleX(0.85);
                //container.setScaleY(0.85);
                //scontainer.setBackground(new Background(BackgroundC));
                //scontainer.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

                brush_set=0;
            }
            catch (Exception e)
            {
                e.printStackTrace();
                inserted=false;
            }


        } catch (AWTException | IOException | InterruptedException ex) {
            System.err.println(ex);
            inserted=false;
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