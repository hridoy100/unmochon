package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;

public class Main extends Application {

    Stage stage;
    public IEImagePane imagePane;
    public IEFileManager fileManager;
    ImageEditor imageEditor;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        imageEditor = new ImageEditor();
        imageEditor.SetMain(this);
        ShowFirstWindow();
//        ShowImageEditor("Screenshot_43.png");
    }
    public void ShowFirstWindow() throws IOException {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("firstWindow.fxml"));
        Parent root = loader.load();
        // Loading the controller
        FirstWindowController firstWindowController = loader.getController();
        firstWindowController.SetMain(this);
        // Set the primary stage
        stage.setTitle("Welcome To Unmochon");
        stage.setScene(new Scene(root));
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
//                alert.setContentText("connection successful");
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

    public static void main(String[] args) {
        launch(args);
    }
}
