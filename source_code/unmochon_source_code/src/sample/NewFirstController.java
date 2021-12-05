package sample;

import DB.CommunicateWithPhp;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Window;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.awt.Font.*;

import javafx.scene.paint.Color;

import static javafx.scene.media.MediaPlayer.Status.PLAYING;
import static javafx.scene.text.Font.font;
import static sample.Main.*;

import javafx.geometry.Insets;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class NewFirstController implements Initializable {
    public static Image image1, image2;

    public static Stage stage;
    public static ProgressBar pb;
    public static Slider slider;
    public static String nam, contact, comment;
    public static AnchorPane simage_wrapper;
    public static double window_width = -1, window_height = -1;
    public static double window_x = -1, window_y = -1;
    public static Canvas canvas;
    public static Canvas dcanvas;
    public static File tempfile = null;
    public static File tempfileone = null;
    public static Color editColor = null;
    public static int brush_set = 0;
    public static Main main;
    @FXML
    public ImageView container;
    @FXML
    public StackPane scontainer;
    @FXML
    AnchorPane root;
    @FXML
    Button bi1, bi3, bi4, bi5, bi7;
    @FXML
    MenuButton bi2, bi6;
    @FXML
    HBox hBox;
    Media media;
    @FXML
    MenuItem gi, bi, bli, ri, eraser, bwidth, hints, uvideo;
    @FXML
    AnchorPane image_wrapper;
    @FXML
    Canvas can, drawingcanvas;
    public static Button sbi3,sbi4;
    public static MenuButton sbi2;
    String turl = "https://www.youtube.com/watch?v=HGHu-SzL-5E&list=LL&index=2";

    public static String getDefaultBrowser() {
        try {
            // Get registry where we find the default browser
            Process process = Runtime.getRuntime().exec("REG QUERY HKEY_CLASSES_ROOT\\http\\shell\\open\\command");
            Scanner kb = new Scanner(process.getInputStream());
            while (kb.hasNextLine()) {
                // Get output from the terminal, and replace all '\' with '/' (makes regex a bit more manageable)
                String registry = (kb.nextLine()).replaceAll("\\\\", "/").trim();

                // Extract the default browser
                Matcher matcher = Pattern.compile("/(?=[^/]*$)(.+?)[.]").matcher(registry);
                if (matcher.find()) {
                    // Scanner is no longer needed if match is found, so close it
                    kb.close();
                    String defaultBrowser = matcher.group(1);

                    // Capitalize first letter and return String
                    defaultBrowser = defaultBrowser.substring(0, 1).toUpperCase() + defaultBrowser.substring(1, defaultBrowser.length());
                    return defaultBrowser;
                }
            }
            // Match wasn't found, still need to close Scanner
            kb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Have to return something if everything fails
        return "Error: Unable to get default browser";
    }

    public static boolean isInternetOn() {
        try {
            final URL url = new URL("http://www.google.com");
            final URLConnection conn = url.openConnection();
            conn.setConnectTimeout(1000);
            conn.connect();
            conn.getInputStream().close();
            return true;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            return false;
        }/*
        try {
            Socket socket = new Socket("www.google.com", 80);
            boolean netAccess = socket.isConnected();
            socket.close();
            return netAccess;
        }catch (Exception e)
        {
            return false;
        }*/
    }

    private static void initialPhase() {
        try {
            stage.setHeight(stage.getMinHeight());
            stage.setWidth(stage.getMinWidth());
            canvas.getGraphicsContext2D().clearRect(0,0,5000,5000);
            dcanvas.getGraphicsContext2D().clearRect(0,0,5000,5000);
            //image1.cancel();
            //image2.cancel();
            editColor=null;
            sbi2.setDisable(true);
            sbi4.setDisable(true);
            sbi3.setDisable(true);

            image1=null;
            image2=null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void uploadToServer(String n, String con, String com) {
        nam = n;
        contact = con;
        comment = com;
        CommunicateWithPhp communicateWithPhp = new CommunicateWithPhp();
        try {
            //Main.showMessage("Image saved successfully","","icons/check.png");
            if (communicateWithPhp.InsertDetailsIntoDB3(ID, nam, contact, comment, selectedText, tempfile.getName())) {
                /*Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        File f=new File(tempfile.getName());
                        initialPhase();
                        try {
                            if (f.delete()) {
                                //Main.showMessage("delete korse", "", "./check.png");
                                System.out.println("Deleted the file: " + tempfile.getName());
                                System.out.println("absolute path: " + tempfile.getAbsolutePath());
                            } else {
                                System.out.println("Failed to delete the file: " + tempfile.getName());
                                System.out.println("absolute path: " + tempfile.getAbsolutePath());
                            }
                        }catch (Exception e)
                        {
                            e.printStackTrace();
                            //Main.showMessage(e.toString(), "", "./check.png");
                        }

                    }
                });*/
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Main.showMessage("Submission Successful", "", "/check.png");
                        initialPhase();

                        try {
                            BufferedImage bImage = SwingFXUtils.fromFXImage(simage_wrapper.snapshot(null, null), null);
                            //image2 = SwingFXUtils.toFXImage(bImage, null);
                            ImageIO.write(bImage, "png", tempfile);

                            Files.delete(tempfile.toPath());
                        }catch (Exception e)
                        {
                            showMessage(""+e,"","/close.png");
                        }
                    }
                });
            } else {
                //initialPhase();
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            Main.showMessage("Submission Failed", "", "/close.png");

                            BufferedImage bImage = SwingFXUtils.fromFXImage(simage_wrapper.snapshot(null, null), null);
                            //image2 = SwingFXUtils.toFXImage(bImage, null);
                            ImageIO.write(bImage, "png", tempfile);

                            Files.delete(tempfile.toPath());
                        }catch (Exception e)
                        {
                            showMessage(e.getMessage(),"","/close.png");
                        }
                    }
                });

                //tempfile.delete();

                //BufferedImage bImage = SwingFXUtils.fromFXImage(simage_wrapper.snapshot(null, null), null);
                //image2 = SwingFXUtils.toFXImage(bImage, null);
                //ImageIO.write(bImage, "png", tempfile);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }
    }

    public static void showTimer() {
        try {
            FXMLLoader loader = new FXMLLoader();
            //loader.setLocation(getClass().getResource("confirmdialouge.fxml"));
            //loader.setLocation(getClass().getResource("hints.fxml"));
            loader.setLocation(aClass.getResource("timer.fxml"));
            Parent root = loader.load();
            // Loading the controller
            Timer hintsController = loader.getController();
            //ConfirmDialouge hintsController = loader.getController();
            //firstWindowController.SetMain(main);
            // Set the primary stage
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            scene.setFill(Color.TRANSPARENT);
            //scene.getStylesheets().add(this.getClass().getResource("style.css").toExternalForm());
            stage.setResizable(true);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setOpacity(0.0f);
            stage.setAlwaysOnTop(true);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.show();
            System.out.println("stage called");
            hintsController.stage = stage;
            ///////////stage.setIconified(true);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("" + e.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        canvas = can;
        dcanvas = drawingcanvas;
        sbi2=bi2;
        sbi3=bi3;
        sbi4=bi4;
        final IEToolManager toolManager = new IEToolManager();
        NewDrawings.createNewCanvas(container.getImage(), toolManager);
        FullScreenCapture.container = this.container;
        FullScreenCapture.scontainer = this.scontainer;
        initButtonsUi(bi1);
        initButtonsUi(bi2);
        initButtonsUi(bi3);
        initButtonsUi(bi4);
        initButtonsUi(bi5);
        initButtonsUi(bi6);
        initButtonsUi(bi7);
        //bi2.setText("Edit \uE315");
        bi2.setText("Edit");
        bi6.setText("Tutorial");
        bi2.setAlignment(Pos.CENTER);
        simage_wrapper = image_wrapper;
        float sliderWidth = 66;
        Slider slider = new Slider();
        this.slider = slider;
        slider.setMin(0);
        slider.setMax(50);
        slider.setMinWidth(sliderWidth);
        slider.setMaxWidth(sliderWidth);

        slider.setMinHeight(15);
        slider.setMaxHeight(15);

        ProgressBar pb = new ProgressBar();
        pb.setMinWidth(sliderWidth);
        pb.setMaxWidth(sliderWidth);

        pb.setMinHeight(15);
        pb.setMaxHeight(15);
        this.pb = pb;
        pb.setProgress(15 / 50);
        slider.setValue(15 / 50);
        //slider.setStyle("-fx-background-color:transparent;");

        //final ProgressIndicator pi = new ProgressIndicator(0);

        slider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                pb.setProgress(new_val.doubleValue() / 50);
                //pi.setProgress(new_val.doubleValue() / 50);
            }
        });
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(pb, slider);
        pb.setBackground(Background.EMPTY);
        slider.setBackground(Background.EMPTY);
        bwidth.setGraphic(stackPane);
        pb.getStyleClass().add("pb");


        pb.setProgress(5);
        slider.setValue(5);

        Thread thread = new Thread() {
            @Override
            public void start() {
                try {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            stage.widthProperty().addListener((ChangeListener<? super Number>) new ChangeListener<Number>() {
                                @Override
                                public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {

                                    /*if(canvas!=null)
                                    {
                                        try {
                                            //canvas.prefWidth(root.getWidth()/1.1);
                                            //canvas.prefHeight(root.getHeight()/1.1);
                                            //canvas.resize(root.getWidth()/1.1, root.getHeight()/1.1);
                                            //canvas.getGraphicsContext2D().lineTo(4.4,4);
                                            canvas.setScaleX(root.getWidth()/window_width);
                                            canvas.setScaleY(root.getHeight()/window_height);
                                        }catch (Exception e)
                                        {

                                        }
                                    }
                                    if(container!=null)
                                    {
                                        try {
                                            container.setScaleX(root.getWidth()/window_width);
                                            container.setScaleY(root.getHeight()/window_height);
                                            //container.setFitWidth(root.getWidth()/1.1);
                                            //container.setFitHeight(root.getHeight()/1.1);
                                        }catch (Exception e)
                                        {

                                        }
                                    }*/
                                    //scontainer.prefWidth(stage.getWidth()/2);
                                    //simage_wrapper.setTranslateX((100-window_width));
                                    //container.prefWidth(stage.getWidth()*0.9);
                                    //simage_wrapper.prefWidth(stage.getWidth()*0.9);
                                    //root.getWidth()/1.1);
                                    simage_wrapper.setScaleX(0.4 * root.getWidth() / window_width);
                                }
                            });
                            stage.heightProperty().addListener((ChangeListener<? super Number>) new ChangeListener<Number>() {
                                @Override
                                public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {

                                    //simage_wrapper.setPrefWidth(stage.getWidth()/2);
                                    /*if(canvas!=null)
                                    {
                                        try {
                                            //canvas.prefWidth(root.getWidth()/1.1);
                                            //canvas.prefHeight(root.getHeight()/1.1);
                                            //canvas.resize(root.getWidth()/1.1, root.getHeight()/1.1);
                                            //canvas.getGraphicsContext2D().lineTo(4.4,4);
                                            canvas.setScaleX(root.getWidth()/window_width);
                                            canvas.setScaleY(root.getHeight()/window_height);
                                        }catch (Exception e)
                                        {

                                        }
                                    }
                                    if(container!=null)
                                    {
                                        try {
                                            container.setScaleX(root.getWidth()/window_width);
                                            container.setScaleY(root.getHeight()/window_height);
                                            //container.setFitWidth(root.getWidth()/1.1);
                                            //container.setFitHeight(root.getHeight()/1.1);
                                        }catch (Exception e)
                                        {

                                        }
                                    }*/
                                    //////////////simage_wrapper.setTranslateY((100-window_height));
                                    //simage_wrapper.prefHeight(root.getHeight()/1.1);
                                    //scontainer.setScaleY(1.1*root.getHeight()/window_height);
                                    simage_wrapper.setScaleY(0.3 * root.getHeight() / window_height);

                                }
                            });

                            /*stage.maximizedProperty().addListener(new ChangeListener<Boolean>() {
                                @Override
                                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                                    if(stage.isMaximized()) {
                                        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
                                        final double width = gd.getDisplayMode().getWidth();
                                        final double height = gd.getDisplayMode().getHeight();
                                        simage_wrapper.setScaleY(0.3 * height / window_height);
                                        simage_wrapper.setScaleX(0.4 * width / window_width);
                                    }
                                    else
                                    {
                                        final double width = stage.getWidth();
                                        final double height = stage.getHeight();
                                        simage_wrapper.setScaleY(0.3 * height / window_height);
                                        simage_wrapper.setScaleX(0.4 * width / window_width);
                                    }
                                }
                            });*/
                            stage.maximizedProperty().addListener(new ChangeListener<Boolean>() {
                                @Override
                                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                                    Thread thread1=new Thread()
                                    {
                                        @Override
                                        public void run()
                                        {
                                            Platform.runLater(new Runnable() {
                                                @Override
                                                public void run() {
                                                    simage_wrapper.setScaleY(0.3 * root.getHeight() / window_height);
                                                    simage_wrapper.setScaleX(0.4 * root.getWidth() / window_width);
                                                }
                                            });
                                        }
                                    };
                                    thread1.start();
                                }
                            });
                            //stage.onCloseRequestProperty().addListener();
                            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                                @Override
                                public void handle(WindowEvent event) {
                                    try {
                                        if (tempfile != null && simage_wrapper != null) {
                                            initialPhase();
                                            BufferedImage bImage = SwingFXUtils.fromFXImage(simage_wrapper.snapshot(null, null), null);
                                            //image2 = SwingFXUtils.toFXImage(bImage, null);
                                            try {
                                                ImageIO.write(bImage, "png", tempfile);
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                        System.exit(0);
                                    }catch (Exception e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();

        //loadTimer();
        //bwidth.setDisable(true);
        //

        //slider.setLayoutY(slider.getLayoutY()-2);

        //pb.setLayoutY(slider.getLayoutY());

/*      bi2.setStyle("-fx-font-size: 74;-fx-text-inner-color: \"#aaaaaa\";");
        bi3.setStyle("-fx-font-size: 74;-fx-text-inner-color: \"#aaaaaa\";");

        bt1.setStyle("-fx-font-size: 34;-fx-text-inner-color: \"#aaaaaa\";");
        bt2.setStyle("-fx-font-size: 34;-fx-text-inner-color: \"#aaaaaa\";");
        bt2.setStyle("-fx-font-size: 34;-fx-text-inner-color: \"#aaaaaa\";");
        bt3.setStyle("-fx-font-size: 34;-fx-text-inner-color: \"#aaaaaa\";");*/
    }

    private void updateColor(Button bi2, Color color) {
        //bi2.setTextFill(color);
        bi2.setDisable(false);
    }

    private void updateColor(MenuButton bi2, Color color) {
        //bi2.setTextFill(color);
        bi2.setDisable(false);
    }

    private void initButtonsUi(Button bi1) {
        bi1.setTextAlignment(TextAlignment.LEFT);
        bi1.setPadding(new Insets(0, 0, 0, 0));
        bi1.setTextFill(new Color(0.44, 0.44, 0.44, 1));
        bi1.setStyle("-fx-font-size:15;");
        bi1.setDisable(true);
        if (bi1 != bi3 && bi1 != bi4) {
            bi1.setTextFill(new Color(0.44, 0.44, 0.44, 1));
            bi1.setDisable(false);
        }
    }

    private void initButtonsUi(MenuButton bi1) {
        bi1.setTextAlignment(TextAlignment.LEFT);
        bi1.setPadding(new Insets(0, 0, 0, 0));
        bi1.setTextFill(new Color(0.44, 0.44, 0.44, 1));
        bi1.setStyle("-fx-font-size:15;");
        bi1.setDisable(true);

        if (bi1 == bi2) {
        } else if (bi1 == bi6) {
            bi1.setDisable(false);
        }
    }

    @FXML
    private void exitApp() {
        final Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Exit");
        alert.setHeaderText("");
        alert.setContentText("Are you sure you want to exit the app?");
        //alert.setContentText("Are you sure to exit the app?Any unsaved work will be lost.");
        alert.getDialogPane().lookupButton(ButtonType.OK).addEventFilter(ActionEvent.ACTION, event -> {
            alert.close();
            System.exit(0);
        });
        alert.getDialogPane().lookupButton(ButtonType.CANCEL).addEventFilter(ActionEvent.ACTION, event -> {
            alert.close();
        });
        Optional<ButtonType> option = alert.showAndWait();
    }

    String getOSType() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.indexOf("win") >= 0)
            return "windows";
        else if (os.indexOf("mac") >= 0) {
            return "mac";
        } else if (os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0) {
            return "linux";
        }
        return null;
    }

    private void tutorialold() throws IOException {
        try {
            File f = new File("/home/apurba/Desktop/unmochon/source_code/unmochon_source_code/tutorial.mp4");
            f = new File("tu.mp4");
            //Converts media to string URL
            Media media = new Media(f.toURI().toString());//"tutorial.mp4");
            //Media media = new Media(Paths.get("tutorial.mp4").toUri().toString());//"tutorial.mp4");
            //Media media=new Media("https://www.youtube.com/watch?v=evd2mLwMjho");
            ////////////f.toURI().toString()
            //Media media=new Media(getClass().getResource("sam.flv").toExternalForm());
            MediaPlayer player = new MediaPlayer(media);
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        player.play();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("gfgfg");
                }
            };
            player.setOnReady(runnable);
            MediaView viewer = new MediaView(player);
            player.setAutoPlay(true);
            player.setOnError(new Runnable() {
                @Override
                public void run() {
                    System.out.println("err" + player.getError().getMessage());
                }
            });
            //
            viewer.setMediaPlayer(player);
            //setVideoMediaStatus(PLAYING);
            Stage st = new Stage();
            Pane rot = new Pane();
            //rot.getChildren().addAll(mediaview);
            Scene sc = new Scene(rot, 2050, 1600);
            st.setScene(sc);
            st.setTitle("About Unmochon");
            st.show();
            stage.initModality(Modality.APPLICATION_MODAL);
            try {
                player.stop();
                player.play();
            } catch (Exception e) {
                e.printStackTrace();
            }
            player.setOnStalled(new Runnable() {
                @Override
                public void run() {
                    System.out.println("kgfgkf");
                }
            });
            System.out.println("gfgfg");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void aboutold() {
        try {
            File f = new File("/home/apurba/Desktop/unmochon/source_code/unmochon_source_code/tutorial.mp4");
            f = new File("tu.mp4");
            //Converts media to string URL
            Media media = new Media(f.toURI().toString());//"tutorial.mp4");
            //Media media = new Media(Paths.get("tutorial.mp4").toUri().toString());//"tutorial.mp4");
            //Media media=new Media("https://www.youtube.com/watch?v=evd2mLwMjho");
            ////////////f.toURI().toString()
            //Media media=new Media(getClass().getResource("sam.flv").toExternalForm());
            MediaPlayer player = new MediaPlayer(media);
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        player.play();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("gfgfg");
                }
            };
            player.setOnReady(runnable);
            MediaView viewer = new MediaView(player);
            player.setAutoPlay(true);
            player.setOnError(new Runnable() {
                @Override
                public void run() {
                    System.out.println("err" + player.getError().getMessage());
                }
            });
            //
            viewer.setMediaPlayer(player);
            //setVideoMediaStatus(PLAYING);
            Stage st = new Stage();
            Pane rot = new Pane();
            //rot.getChildren().addAll(mediaview);
            Scene sc = new Scene(rot, 2050, 1600);
            st.setScene(sc);
            st.setTitle("About Us");
            st.show();
            try {
                player.stop();
                player.play();
            } catch (Exception e) {
                e.printStackTrace();
            }
            player.setOnStalled(new Runnable() {
                @Override
                public void run() {
                    System.out.println("kgfgkf");
                }
            });
            System.out.println("gfgfg");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void about() {
        try {
            if (About2.stage != null) {
                try {
                    About2.stage.close();
                    About2.stage = null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("about_us2.fxml"));
            Parent root = loader.load();
            // Loading the controller
            About2 firstWindowController = loader.getController();
            // Set the primary stage
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(true);
            stage.setTitle("About Us");
            stage.setMinHeight(500);
            stage.setHeight(555);
            stage.setMinWidth(800);
            stage.setWidth(1010);
            stage.show();
            About.stage = stage;
            //firstWindowController.stage = stage;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void aboutoldd() {
        try {

            Text tx = new Text("Zoom is for you. We help you express ideas, connect to\n" +
                    " others, and build toward a future limited only by your imagination.\n" +
                    " Our frictionless communications platform is the only one that started\n" +
                    " with video as its foundation, and we have set the standard for \n" +
                    "innovation ever since. That is why we are an intuitive, scalable, \n" +
                    "and secure choice for large enterprises, small businesses, and \n" +
                    "individuals alike. Founded in 2011, Zoom is publicly traded (NASDAQ:ZM)\n" +
                    " and headquartered in San Jose, California.");

            tx.setLayoutX(10);
            tx.setLayoutY(25);
            tx.setFont(font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 20));
            Stage st = new Stage();
            Pane rot = new Pane();

            rot.getChildren().addAll(tx);
            Scene sc = new Scene(rot, 1050, 600);
            st.setScene(sc);
            st.setTitle("About Us");
            st.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //showTimer();
    }

    private void save2() {

        //fileManager.saveImageDialog(mainStage, imagePane.getCurrentImage());
        //fileManager.saveFile(mainStage);
        try {
            Robot robot = new Robot();

            //			Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit()
            //					.getScreenSize());

            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit()
                    .getScreenSize());
            BufferedImage capture = new Robot().createScreenCapture(screenRect);
            //screenRect = new Rectangle(0, 60, screenRect.width, screenRect.height);
            //BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
            //BufferedImage bImage = SwingFXUtils.toFXImage(capture, null);;//SwingFXUtils.fromFXImage((Image) capture, null);


            //ImageIO.write(screenFullImage, "jpg", new File("fakdefak.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void save() {
        //BufferedImage bImage = SwingFXUtils.fromFXImage(container.snapshot(null, null), null);
        FileChooser stegoImageSaver = new FileChooser();
        stegoImageSaver.setTitle("Save File");
        //stegoImageSaver.setInitialDirectory(new File("\\home"));
        stegoImageSaver.getExtensionFilters().addAll(
                //new FileChooser.ExtensionFilter("JPG Files", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG Files", "*.png")
                //,new FileChooser.ExtensionFilter("BMP Files", "*.bmp")
        );
        stegoImageSaver.setInitialFileName("temp.png");
        File f1 = stegoImageSaver.showSaveDialog(null);
        if (f1 != null) {
            String name = f1.getName();
            String extension = name.substring(1 + name.lastIndexOf(".")).toLowerCase();
            try {
            /*File outputFile = new File("formattedPicture.png");
            WritableImage writableImage = new WritableImage((int)image_wrapper.getWidth(),
                    (int)image_wrapper.getHeight());
            image_wrapper.snapshot(null, writableImage);
            RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
            //Write the snapshot to the chosen file
            ImageIO.write(renderedImage, "png", outputFile);*///ei part tao sundor moto kaj kore

                File outputFile = f1;//new File(name);
                //image_wrapper.setPrefHeight(window_height*0.6);
                //image_wrapper.setMaxHeight(window_height*0.6);
                double x = image_wrapper.getScaleX();
                double y = image_wrapper.getScaleY();
                image_wrapper.setScaleX(1);
                image_wrapper.setScaleY(1);
                BufferedImage bImage = SwingFXUtils.fromFXImage(image_wrapper.snapshot(null, null), null);
                ImageIO.write(bImage, "png", outputFile);
                image_wrapper.setScaleX(x);
                image_wrapper.setScaleY(y);
                Main.showMessage("Saved successfully", "", "/check.png");
            } catch (Exception e) {
                //throw new RuntimeException(e);
            }
            //ImageIO.write(img, extension, f1);
        }
    }

    @FXML
    private void upload() {
        try {
            if (UserInput.stage != null) {
                try {
                    UserInput.stage.close();
                    UserInput.stage = null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("user_input.fxml"));
            Parent root = loader.load();
            // Loading the controller
            UserInput firstWindowController = loader.getController();
            // Set the primary stage
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(true);
            stage.setTitle("User Info Collection");
            stage.setMinHeight(400);
            stage.setMinWidth(550);
            stage.show();
            //stage.initModality(Modality.APPLICATION_MODAL);
            firstWindowController.stage = stage;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    Window getSelectedWindow(Window[] windows) {
        Window result = null;
        for (int i = 0; i < windows.length; i++) {
            Window window = windows[i];
            if (window.isActive()) {
                result = window;
            } else {
                Window[] ownedWindows = window.getOwnedWindows();
                if (ownedWindows != null) {
                    result = getSelectedWindow(ownedWindows);
                }
            }
        }
        return result;
    }

    private boolean isBrowserRunning() {
        if(getOSType().equals("linux"))
                return true;
        try {
            String line;
            Process p = Runtime.getRuntime().exec
                    (System.getenv("windir") + "\\system32\\" + "tasklist.exe");
            BufferedReader input =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = input.readLine()) != null) {
                System.out.println(line); //<-- Parse data here.
                if (line.contains("chrome") || line.contains("msedge") || line.contains("firefox")) {
                    input.close();
                    return true;
                }
            }
            input.close();
        } catch (Exception err) {
            err.printStackTrace();
        }
        return false;
    }

    @FXML
    private void takeScreenshot() {
        if (!isBrowserRunning()) {
            main.showMessageold("Browser Tab is not open", "You cannot take screenshot without browser opened", "icons/close.png");
            return;
        }
        //showTimer();
        //main.stage.setIconified(true);
        editColor = null;
        brush_set = 0;
        final IEToolManager toolManager = new IEToolManager();
        //for(int i=1;i<image_wrapper.getChildren().size();i++)
        if (simage_wrapper.getChildren().size() > 1) {
            ///simage_wrapper.getChildren().remove(1,simage_wrapper.getChildren().size());
        }
        //simage_wrapper.getChildren().add(NewDrawings.createNewCanvas(container.getImage(),toolManager));

        updateColor(bi2, new Color(0.44f, 0.44f, 0.44f, 1));
        updateColor(bi3, new Color(0.44f, 0.44f, 0.44f, 1));
        updateColor(bi4, new Color(0.44f, 0.44f, 0.44f, 1));
        if (window_height == -1) {
            window_height = main.stage.getHeight();
            window_width = main.stage.getWidth();
            window_x = main.stage.getX();
            window_y = main.stage.getY();
        }

        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        final int width = gd.getDisplayMode().getWidth();
        final int height = gd.getDisplayMode().getHeight();

        //main.stage.setResizable(true);
        Thread thread = new Thread() {
            @Override
            public void run() {
                //main.stage.hide();
                try {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            main.stage.setIconified(true);
                            main.stage.setWidth(1);
                            main.stage.setHeight(1);
                        }
                    });

                    container.setFitWidth(width);
                    container.setFitHeight(height - (int) hBox.getHeight());

                    /*double ratio=width/window_width;
                    window_width=width;
                    bi1.setMinWidth(bi1.getWidth()*ratio);
                    bi2.setMinWidth(bi2.getWidth()*ratio);
                    bi3.setMinWidth(bi3.getWidth()*ratio);
                    bi4.setMinWidth(bi4.getWidth()*ratio);
                    bi5.setMinWidth(bi5.getWidth()*ratio);
                    bi6.setMinWidth(bi6.getWidth()*ratio);
                    bi7.setMinWidth(bi7.getWidth()*ratio);*/

                    Thread.sleep(100);

                    try {
                        System.out.println("before fullscreen capture");
                        FullScreenCapture fullScreenCapture = new FullScreenCapture();
                        fullScreenCapture.SetMain(main);

                        if (fullScreenCapture.CaptureDetails()) {
                            System.out.println("successful");
                            ///ShowAlert("Correct Credentials", "Screenshot successful", "Successfully added to database", "CONFIRMATION");
                        } else {
                            System.out.println("NewFirstController-> Failed");
                            ///ShowAlert("Incorrect Credentials", "Incorrect Credentials", "The username and password you provided is not correct.", "ERROR");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {

                    }
                });

                //container.setX(-5);
                //container.setY(-21);
            }
        };
        //main.stage.setIconified(true);
        thread.start();
    }

    @FXML
    private void greenselected() {
        editColor = Color.GREEN;
        setupBrush();
    }

    @FXML
    private void eraserselected() {
        editColor = null;
        setupBrush();
    }

    private void setupBrush() {
        if (brush_set == 1)
            return;
        brush_set = 1;
        final IEToolManager toolManager = new IEToolManager();
        //for(int i=1;i<image_wrapper.getChildren().size();i++)
        /*if(simage_wrapper.getChildren().size()>1)
        {
            simage_wrapper.getChildren().remove(1,simage_wrapper.getChildren().size());
        }
        canvas=NewDrawings.createNewCanvas(container.getImage(),toolManager);
        simage_wrapper.getChildren().add(canvas);*/
        //System.out.println("canvas created");
    }

    @FXML
    private void redselected() {
        editColor = Color.RED;
        setupBrush();
    }

    @FXML
    private void blueselected() {
        editColor = Color.BLUE;
        setupBrush();
    }

    @FXML
    private void blackselected() {
        editColor = Color.BLACK;
        setupBrush();
    }

    public void SetMain(Main main) {
        this.main = main;
    }

    @FXML
    public void hintsselected(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            //loader.setLocation(getClass().getResource("confirmdialouge.fxml"));
            loader.setLocation(getClass().getResource("hintslatest2.fxml"));
            Parent root = loader.load();
            // Loading the controller
            HintsLatest2 hintsController = loader.getController();
            //ConfirmDialouge hintsController = loader.getController();
            //firstWindowController.SetMain(main);
            // Set the primary stage
            Stage stage = new Stage();
            stage.setTitle("Tips to use Unmochon");

            GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
            final double width = gd.getDisplayMode().getWidth();
            final double height = gd.getDisplayMode().getHeight();


            //stage.setMinHeight(510);
            //stage.setMinWidth(680);
            stage.setWidth(width*0.67);
            stage.setHeight(height*0.9);

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            //stage.setOpacity(0.0f);
            stage.show();
            hintsController.stage = stage;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("" + e.getMessage());
        }
    }

    public void uvideoselected(ActionEvent actionEvent) throws Exception {
        String os = getOSType();
        if (os.equals("windows")) {
            Runtime rt = Runtime.getRuntime();
            String url = turl;
            try {
                rt.exec("rundll32 url.dll,FileProtocolHandler " + url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (os.equals("linux")) {
            Runtime rt = Runtime.getRuntime();
            String url = turl;
            String[] browsers = {"google-chrome", "firefox", "mozilla", "epiphany", "konqueror",
                    "netscape", "opera", "links", "lynx"};

            StringBuffer cmd = new StringBuffer();
            for (int i = 0; i < browsers.length; i++)
                if (i == 0)
                    cmd.append(String.format("%s \"%s\"", browsers[i], url));
                else
                    cmd.append(String.format(" || %s \"%s\"", browsers[i], url));
            // If the first didn't work, try the next browser and so on

            rt.exec(new String[]{"sh", "-c", cmd.toString()});
        } else if (os.equals("mac")) {
            Runtime rt = Runtime.getRuntime();
            String url = turl;
            rt.exec("open " + url);
        }
    }

}
