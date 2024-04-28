package Controllers;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ControllerHome {

    @FXML
    private GridPane CategoryGrid;

    @FXML
    private MenuItem Close;

    @FXML
    private MenuItem Instructions;

    @FXML
    private MenuItem importImg;

    @FXML
    private MenuItem open;

    @FXML
    private MenuItem rename;

    private ImageCategory selectedCategory = null;

    // for gallery
    @FXML
    private BorderPane theGallery;

    @FXML
    private MenuItem delete;

    @FXML
    private ImageView PhotoView;

    @FXML
    private MenuItem goBack;

    @FXML
    private Button goDown;

    @FXML
    private Button goUp;

    ArrayList<Image> images = new ArrayList<>();

    Image imageInView = null;

    @FXML
    public void initialize() throws FileNotFoundException {
        theGallery.setVisible(false);
        addImageCategory("Landscape", 0, 0);
        addImageCategory("Portrait", 1, 0);
        addImageCategory("Animals", 2, 0);
        addImageCategory("Food", 3, 0);
        for (int i = 0; i < 4; i++) {
            addImageCategory("???", i, 1);
        }

        open.setOnAction(e -> {
            theGallery.setVisible(true);
            images = selectedCategory.getImages();

            if (images.size() != 0) {
                imageInView = images.get(0);
            } else {
                imageInView = null;
            }

            PhotoView.setImage(imageInView);
        });

        rename.setOnAction(e -> {
            if (selectedCategory != null) {
                // Display a dialog to get the new label from the user
                TextInputDialog dialog = new TextInputDialog(selectedCategory.getCategoryName());
                dialog.setTitle("Rename Category");
                dialog.setHeaderText(null);
                dialog.setContentText("Enter new category name:");

                Optional<String> result = dialog.showAndWait();
                result.ifPresent(newName -> {
                    selectedCategory.setCategory(newName);
                });
            }
        });

        importImg.setOnAction(e -> {
            if (selectedCategory != null) {
                FileChooser fileChooser = new FileChooser(); // Opening file window
                File projectDirectory = new File(System.getProperty("user.dir"));
                fileChooser.setInitialDirectory(projectDirectory);
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif", "*.bmp", "*.jpeg"));

                fileChooser.setTitle("Import an image"); // taking user selected file
                File selectedFile = fileChooser.showOpenDialog(null);
                selectedCategory.addImage(new Image(selectedFile.toURI().toString()));
                System.out.println(selectedCategory.getImages().size());

            }

            selectedCategory.setCover();
        });

        Close.setOnAction(e -> {
            System.exit(0);
        });

        // for the gallery

        goDown.setOnAction(e -> {
            if (imageInView == images.get(0)) {
                imageInView = images.get(images.size() - 1);
            } else {
                imageInView = images.get(images.indexOf(imageInView) - 1);
            }
            PhotoView.setImage(imageInView);
        });

        goUp.setOnAction(e -> {
            if (imageInView == images.get(images.size() - 1)) {
                imageInView = images.get(0);
            } else {
                imageInView = images.get(images.indexOf(imageInView) + 1);
            }
            PhotoView.setImage(imageInView);
        });

        goBack.setOnAction(e -> {
            theGallery.setVisible(false);
        });

        delete.setOnAction(e -> {
            if (images.size() == 1) {
                images.remove(imageInView);
                PhotoView.setImage(null);
                goBack.fire();
            } else {
                images.remove(imageInView);
                selectedCategory.removeImage(imageInView);
                goUp.fire();

            }
            selectedCategory.setCover();
        });

    }

    private void addImageCategory(String categoryName, int columnIndex, int rowIndex) {
        ImageCategory imageCategory = new ImageCategory(categoryName);
        StackPane stackPane = new StackPane(imageCategory);
        stackPane.getStyleClass().add("category-pane");
        stackPane.setOnMouseClicked(event -> selectCategory(imageCategory));
        CategoryGrid.add(stackPane, columnIndex, rowIndex);
    }

    private void selectCategory(ImageCategory category) {
        if (selectedCategory != null) {
            selectedCategory.getStyleClass().remove("selected");
        }
        selectedCategory = category;
        selectedCategory.getStyleClass().add("selected");
        System.out.println("SELECTED" + selectedCategory);
    }

    private void loadFXML(String fxmlFileName) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFileName));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Home Window");
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
