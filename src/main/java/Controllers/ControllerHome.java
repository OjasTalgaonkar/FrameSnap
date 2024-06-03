package Controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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
    private MenuItem save;

    @FXML
    private MenuItem rename;

    @FXML
    private MenuItem reset;

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
    private MenuItem CustomCover;

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

                List<File> selectedFiles = fileChooser.showOpenMultipleDialog(null);

                if (selectedFiles != null) {
                    for (File file : selectedFiles) {
                        selectedCategory.addImage(new Image(file.toURI().toString()));
                    }
                    System.out.println(selectedCategory.getImages().size());
                }

            }

            selectedCategory.setCover();
        });

        save.setOnAction(e -> saveCategories());

        reset.setOnAction(e -> resetCategories());

        Close.setOnAction(e -> {
            System.exit(0);
        });

        loadCategories();

        // for the gallery

        goDown.setOnAction(e -> {
            if (imageInView == images.get(0)) {
                imageInView = images.get(images.size() - 1);
            } else {
                imageInView = images.get(images.indexOf(imageInView) - 1);
            }
            PhotoView.setImage(imageInView);
        });

        goDown.setOnMousePressed(e -> {
            goDown.getStyleClass().remove("circleBtns");
            goDown.getStyleClass().add("circleBtns_Click");

        });

        goDown.setOnMouseReleased(e -> {
            goDown.getStyleClass().remove("circleBtns_Click");
            goDown.getStyleClass().add("circleBtns");
        });

        goUp.setOnAction(e -> {
            if (imageInView == images.get(images.size() - 1)) {
                imageInView = images.get(0);
            } else {
                imageInView = images.get(images.indexOf(imageInView) + 1);
            }
            PhotoView.setImage(imageInView);
        });

        goUp.setOnMousePressed(e -> {
            goUp.getStyleClass().remove("circleBtns");
            goUp.getStyleClass().add("circleBtns_Click");

        });

        goUp.setOnMouseReleased(e -> {
            goUp.getStyleClass().remove("circleBtns_Click");
            goUp.getStyleClass().add("circleBtns");
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

        CustomCover.setOnAction(e -> {
            if (imageInView != null) {
                selectedCategory.setCover(imageInView);
            }
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

    private static final String SAVE_FILE = "image_categories.txt";

    private void saveCategories() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SAVE_FILE))) {
            for (Node node : CategoryGrid.getChildren()) {
                if (node instanceof StackPane) {
                    StackPane stackPane = (StackPane) node;
                    if (stackPane.getChildren().get(0) instanceof ImageCategory) {
                        ImageCategory category = (ImageCategory) stackPane.getChildren().get(0);
                        writer.write(category.getCategoryName() + "\n");
                        Image coverImage = category.getCover();
                        writer.write((coverImage != null ? coverImage.getUrl() : "null") + "\n");
                        for (Image image : category.getImages()) {
                            writer.write(image.getUrl() + "\n");
                        }
                        writer.write("END\n");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadCategories() {
        // Clear existing categories from the UI
        CategoryGrid.getChildren().clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(SAVE_FILE))) {
            String line;
            ImageCategory currentCategory = null;
            while ((line = reader.readLine()) != null) {
                if (line.equals("END")) {
                    currentCategory = null;
                } else if (currentCategory == null) {
                    currentCategory = new ImageCategory(line);
                    String coverImageUrl = reader.readLine();
                    if (!"null".equals(coverImageUrl)) {
                        currentCategory.setCover(new Image(coverImageUrl));
                    }
                    addImageCategoryToGrid(currentCategory);
                } else {
                    currentCategory.addImage(new Image(line));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addImageCategoryToGrid(ImageCategory category) {
        int rowIndex = CategoryGrid.getChildren().size() / 4; // Each row contains 4 categories
        int columnIndex = CategoryGrid.getChildren().size() % 4; // Each row contains 4 categories

        // Add additional rows if needed
        if (columnIndex == 0 && rowIndex > 0) {
            CategoryGrid.addRow(rowIndex);
        }

        StackPane stackPane = new StackPane(category);
        stackPane.getStyleClass().add("category-pane");
        stackPane.setOnMouseClicked(event -> selectCategory(category));
        CategoryGrid.add(stackPane, columnIndex, rowIndex);
    }

    private void resetCategories() {
        // Clear the existing categories from the UI
        CategoryGrid.getChildren().clear();

        // Clear the in-memory list of categories
        selectedCategory = null;

        // Delete the save file
        File saveFile = new File(SAVE_FILE);
        if (saveFile.exists()) {
            saveFile.delete();
        }

        // Optionally, reinitialize the default categories if needed

        addImageCategory("Landscape", 0, 0);
        addImageCategory("Portrait", 1, 0);
        addImageCategory("Animals", 2, 0);
        addImageCategory("Food", 3, 0);
        for (int i = 0; i < 4; i++) {
            addImageCategory("???", i, 1);
        }

        saveCategories();

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
