package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//import jfx.messagebox.MessageBox;

public class ImageEditor {
    private Main main;

//	private IEImagePane imagePane;
//	private Stage         mainStage;
	//private IEFileManager fileManager;

	/*
	public static void main(String args)
	{
		launch(args);
	}

	@Override
	public void start(Stage stage){
		stage.setTitle("Image Editor");
		final Screen        screen      = Screen.getPrimary();
		final Rectangle2D   bounds      = screen.getVisualBounds();
		final IEToolManager toolManager = new IEToolManager();
		final MenuBar       menuBar     = new MenuBar();
		final IEToolPane toolPane    = new IEToolPane(toolManager);
		final IEColorPane   colorPane   = new IEColorPane(toolManager);
		mainStage                 	    = stage;
		imagePane                       = new IEImagePane(toolManager);
	
		initFileManager();
		menuBar.getMenus().addAll(initMenus());

		stage.setX(bounds.getMinX());
		stage.setY(bounds.getMinY());
		stage.setWidth(bounds.getWidth());
		stage.setHeight(bounds.getHeight());

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
	}

	*/
	/**
	 * Initializes the file manager with the appropriate file extensions.
	 */
	String fileName;
	public IEFileManager initFileManager(String fileName)
	{
		this.fileName = fileName;
		ObservableList<String> extensionsStrings = 
			    FXCollections.observableArrayList(
			        ".png",
			        ".gif"
			    );
        return new IEFileManager(extensionsStrings);
	}
	
	/**
	 * Initializes Menus and their corresponding menu items.
	 * @return A list of the initialized menus.
	 */
	public List<Menu> initMenus()
	{
		final Menu fileMenu     = new Menu("File");
		fileMenu.getItems().addAll(initFileMenuItems());

		final Menu editMenu = new Menu("Edit");
		//editMenu.getItems().addAll(initEditMenuItems());

		final Menu helpMenu  = new Menu("Help");
		//helpMenu.getItems().addAll(initHelpMenuItems());

		return new ArrayList<Menu>(Arrays.asList(fileMenu));
	}

	/**
	 * Initializes the menu items for the file menu.
	 * @return A list containing the menu items for the file menu.
	 */
	public List<MenuItem> initFileMenuItems()
	{
		//Creates a new image.
		/*final MenuItem newImage = new MenuItem("New");
		newImage.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				newImageDialog();
			}
		});*/
		//Opens an existing image.
		final MenuItem open = new MenuItem("Open");
		open.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				main.openImage(fileName);
			}
		});
		//Saves the image the user is currently working on.
		final MenuItem save = new MenuItem("Save");
		save.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				try {
					saveImage();
				} catch (AWTException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		final MenuItem exit = new MenuItem("Cancel The Whole Process");
		exit.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				//openImage();
				System.exit(0);
			}
		});


		return new ArrayList<MenuItem>(Arrays.asList(open, save, exit));
	}

	/**
	 * Initializes the menu items for the help menu.
	 * @return A list containing the menu items for the help menu.
	 */
	public List<MenuItem> initHelpMenuItems()
	{
		final MenuItem about = new MenuItem("About");
		about.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				openAboutWindow();
			}
		});

		return new ArrayList<MenuItem>(Arrays.asList(about));
	}

	/**
	 * Initializes the menu items for the edit menu.
	 * @return A list containing the menu items for the edit menu.
	 */
	public List<MenuItem> initEditMenuItems()
	{
		final MenuItem undo = new MenuItem("Undo");
		undo.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {

			}
		});
		final MenuItem copy = new MenuItem("Copy");
		copy.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {

			}
		});

		return new ArrayList<MenuItem>(Arrays.asList(undo, copy));
	}

	/**
	 * Prompts the user for dimensions of a new image.
	 * @return The dimensions of the new image.
	 */



	/**
	 * Saves an image using the file manager. If no image
	 * is currently open then an error dialog is opened.
	 */
	private void saveImage() throws AWTException, IOException {
		if(main.imagePane.hasCurrentImage())
		{
			//fileManager.saveImageDialog(mainStage, imagePane.getCurrentImage());
			//fileManager.saveFile(mainStage);
			Robot robot = new Robot();

//			Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit()
//					.getScreenSize());

			Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit()
					.getScreenSize());
			screenRect = new Rectangle(0, 0, screenRect.width, screenRect.height);
			BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
			ImageIO.write(screenFullImage, "jpg", new File(fileName));



		}
		else
		{
			showErrorDialog("There is no image to save.", main.stage);
		}
	}

	/**
	 * Opens a window providing information about the image editor.
	 */
	private void openAboutWindow()
	{

	}

	/**
	 * Shows an error dialog with the given string.
	 * @param error The error message to be displayed.
	 */
	private void showErrorDialog(String error, Stage stage)
	{
		//MessageBox.show(stage, error, "Error", MessageBox.ICON_ERROR);
	}


    public void SetMain(Main main) {
        this.main = main;
    }
}
