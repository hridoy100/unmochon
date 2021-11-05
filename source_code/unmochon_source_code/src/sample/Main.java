package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;


public class Main extends Application {
    public static String ID=null;
    public static Class aClass;
    public static String selectedText=null;
    Stage stage;
    public IEImagePane imagePane;
    public static IEFileManager fileManager;
    public static ImageEditor imageEditor;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        imageEditor = new ImageEditor();
        imageEditor.SetMain(this);
        aClass=getClass();
        //loadFont();
        ShowFirstWindow();
        //loadTimer();
        //ShowImageEditor("Screenshot_43.png");
        //raf();
    }

    private void raf() {

        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("user_input.fxml"));
            Parent root = loader.load();
            // Loading the controller
            UserInput firstWindowController = loader.getController();
            // Set the primary stage
            Stage stage=new Stage();
            Scene scene=new Scene(root);
            stage.setScene(scene);
            stage.setResizable(true);
            stage.setTitle("User Info Collection");
            stage.setMinHeight(400);
            stage.setMinWidth(550);
            stage.show();
            firstWindowController.stage=stage;
        }catch(Exception e){e.printStackTrace();}

    }

    public static void loadTimer() {
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
            hintsController.stage=stage;
            stage.setIconified(true);
            Thread thread=new Thread(){
                @Override
                public void run()
                {
                    try {
                        Thread.sleep(444);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            Timer.stage.setIconified(false);
                        }
                    });
                }
            };
            thread.start();

        }catch (Exception e)
        {
            e.printStackTrace();
            System.out.println(""+e.getMessage());
        }
    }

    private void loadFont() {
        //Font.loadFont(App.class.getResource("fontawesome_solid.otf").toExternalForm(), 10);
    }

    public void ShowFirstWindow() throws IOException {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("new_first.fxml"));
        Parent root = loader.load();
        // Loading the controller

        NewFirstController firstWindowController = loader.getController();
        firstWindowController.SetMain(this);
        // Set the primary stage
        stage.setTitle("Welcome To Unmochon");
        NewFirstController.stage=stage;
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.setMinWidth(800);
        stage.setMinHeight(300);
        scene.getStylesheets().add(this.getClass().getResource("style.css").toExternalForm());
        stage.setResizable(true);
        stage.show();
//        try {
//            InetAddress addr = InetAddress. getByName("198.211.96.87");
//            System.out.println("Host name is: "+addr. getHostName());
//            System.out.println("Ip address is: "+ addr.getHostAddress());
//            Connection con = new MySqlDB().getConnection();
//            if (con.isClosed()) {
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("Incorrect Credentials");
//                alert.setHeaderText("Incorrect Credentials");
//                alert.setContentText("The username and password you provided is not correct.");
//                alert.showAndWait();
//            }
//            else {
//                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//                alert.setTitle("Correct Credentials");
//                alert.setHeaderText("correct Credentials");
//                al/ert.setContentText("connection successful");
//                alert.showAndWait();
//            }
//        }
//        catch(Exception e){
//            e.printStackTrace();
//        }
    }
    public void ShowAdminPage() throws IOException {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("loginPage.fxml"));
        Parent root = loader.load();
        // Loading the controller
        LoginController controller = loader.getController();
        controller.SetMain(this);
        // Set the primary stage
        stage.setTitle("User Verification");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void ShowUser() throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("userTable.fxml"));
        Parent root = loader.load();
        // Loading the controller
        UserTableController controller = loader.getController();
        controller.load();
        controller.setMain(this);
        // Set the primary stage
        stage.setTitle("Inserted users");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void ShowScreenshotTable(String userid) throws IOException
    {
        System.out.println("userid: "+userid);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("screenshotTable.fxml"));
        Parent root = loader.load();

        // Loading the controller
        ScreenshotTableController controller = loader.getController();
        controller.load(userid);
        controller.setMain(this);

        //controller.Initialize();

        // Set the primary stage
        stage.setTitle("User Screenshots");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void ShowImage(Image image) throws IOException {
        Stage stg = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("imageShow.fxml"));
        Parent root = loader.load();

        ImageShowController controller = loader.getController();
        controller.SetMain(this);
        controller.setImageView(image);
        double width=500,height=500;

        Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();

        stg.setTitle("Image");
        width = visualBounds.getWidth()-150;
        height=visualBounds.getHeight()-150;

        stg.setScene(new Scene(root, width, height));
        stg.show();
    }

    public void ShowImageEditor(String fileName) {
        stage.setTitle("Image Editor");
        final Screen screen = Screen.getPrimary();
        final Rectangle2D bounds = screen.getVisualBounds();
        final IEToolManager toolManager = new IEToolManager();
        final MenuBar menuBar = new MenuBar();
        final IEToolPane toolPane = new IEToolPane(toolManager);
        final IEColorPane colorPane = new IEColorPane(toolManager);
        imagePane = new IEImagePane(toolManager);
        fileManager=imageEditor.initFileManager(fileName);
        menuBar.getMenus().addAll(imageEditor.initMenus());

        stage.setX(bounds.getMinX()+50);
        stage.setY(bounds.getMinY()+50);
        stage.setWidth(bounds.getWidth()-100);
        stage.setHeight(bounds.getHeight()-100);

        VBox leftBox = new VBox();
        leftBox.setSpacing(10);
        leftBox.getChildren().addAll(toolPane, colorPane);
        leftBox.setStyle("-fx-background-color:grey;");

        BorderPane bp = new BorderPane();
        bp.setTop(menuBar);
        bp.setCenter(imagePane);
        bp.setLeft(leftBox);
        stage.setScene(new Scene(bp, bounds.getWidth(), bounds.getHeight()));
        stage.show();
        openImage(fileName);
    }
/*
    public void newImageDialog()
    {
        //final Stage newImageStage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(stage);
        stage.setTitle("New Image");
        stage.setResizable(false);

        final GridPane gp = new GridPane();
        gp.setAlignment(Pos.CENTER);
        gp.setHgap(10);
        gp.setVgap(10);
        gp.setPadding(new Insets(25, 25, 25, 25));

        final Label widthLabel      = new Label("Width:");
        final Label 	heightLabel     = new Label("Height:");
        final TextField widthTextField  = new TextField();
        final TextField heightTextField = new TextField();
        final Button createButton    = new Button("Create");

        createButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                try
                {
                    int width = Integer.parseInt(widthTextField.getText());
                    int height = Integer.parseInt(heightTextField.getText());
                    imagePane.addTab(width, height, );
                    ((Node)(e.getSource())).getScene().getWindow().hide();
                }
                catch(NumberFormatException exception)
                {
                    exception.printStackTrace();
                    //showErrorDialog("Illegal arguments were provided for the new image.", newImageStage);
                }
            }
        });

        gp.add(widthLabel,      0, 0);
        gp.add(widthTextField,  1, 0);
        gp.add(heightLabel,     0, 1);
        gp.add(heightTextField, 1, 1);
        gp.add(createButton,    0, 2);

        stage.setScene(new Scene(gp, 300, 200));
        stage.show();
    }
    */
    /**
     * Uses the file manager to open an image. Any exception
     * is caught because we just want to show that an error
     * occurred when opening the image. We don't care what
     * exception is thrown because there were many possibilities
     * found during testing.
     */
    public void openImage(String fileName)
    {
        try
        {
            //final File imageFile = fileManager.chooseFile(mainStage);
            final Image image = fileManager.loadImage(new File(fileName));
            //imagePane.addTab(image, imageFile.getName());
            Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
            imagePane.addTab(image, fileName, visualBounds);
            imagePane.setMaxHeight(visualBounds.getHeight()-200);
            imagePane.setMaxWidth(visualBounds.getWidth()-170);
        }
        catch(Exception exception)
        {
            //There were many types of exceptions thrown when doing various testing.
            //We don't really care what is thrown, we care that there was an error
            //loading the image.
            exception.printStackTrace();
            //showErrorDialog("There was an error opening the selected image.", stage);
        }

    }
    public void showMessageold(String fheading,String fdescription,String filepath)
    {
        Alert alert;
        if(filepath.equals("icons/close.png"))
            alert=new Alert(Alert.AlertType.ERROR);
        else
            alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(fheading);
        alert.setContentText(fdescription);
        alert.showAndWait();
//                alert.setHeaderText("Incorrect Credentials");
//                alert.setContentText("The username and password you provided is not correct.");
//                alert.showAndWait();
    }

    public static void showMessage(String fheading,String fdescription,String filepath)
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(aClass.getResource("confirmdialouge.fxml"));
            //loader.setLocation(getClass().getResource("hints.fxml"));
            Parent root = loader.load();
            // Loading the controller
            //HintsController hintsController = loader.getController();
            ConfirmDialouge hintsController = loader.getController();
            //firstWindowController.SetMain(main);
            // Set the primary stage
            Stage stage = new Stage();
            stage.setTitle("Message");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            //stage.initStyle(StageStyle.UNDECORATED);
            //scene.getStylesheets().add(this.getClass().getResource("style.css").toExternalForm());
            stage.setResizable(false);
            stage.show();
            hintsController.stage=stage;
            hintsController.showMessage(fheading,fdescription,filepath);
        }catch (Exception e)
        {
            e.printStackTrace();
            System.out.println(""+e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            launch(args);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
