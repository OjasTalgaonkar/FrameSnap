package Controllers;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ControllerHome {

    // @FXML
    // private GridPane CategoryGrid;

    // @FXML
    // private MenuItem Close;

    // @FXML
    // private MenuItem Instructions;

    // @FXML
    // private MenuItem importImg;

    // @FXML
    // private MenuItem open;

    // @FXML
    // private MenuItem rename;

    // private ImageCategory selectedCategory = null;

    // // for gallery
    // @FXML
    // private ImageView PhotoView;

    // @FXML
    // private MenuItem goBack;

    // @FXML
    // private Button goDown;

    // @FXML
    // private Button goUp;

    // ArrayList<Image> images = new ArrayList<>();

    // Image imageInView = null;

    // @FXML
    // public void initialize() {
    // addImageCategory("Landscape", 0, 0);
    // addImageCategory("Portrait", 1, 0);
    // addImageCategory("Animals", 2, 0);
    // addImageCategory("Food", 3, 0);
    // for (int i = 0; i < 4; i++) {
    // addImageCategory("???", i, 1);
    // }

    // open.setOnAction(e -> {
    // selectedCategory.openImages(images);
    // });

    // imageInView = images.get(0);

    // PhotoView.setImage(imageInView);

    // goDown.setOnAction(e -> {
    // if (imageInView == images.get(0)) {
    // imageInView = images.get(images.size() - 1);
    // } else {
    // imageInView = images.get(images.indexOf(imageInView) - 1);
    // }
    // PhotoView.setImage(imageInView);
    // });

    // goUp.setOnAction(e -> {
    // if (imageInView == images.get(images.size() - 1)) {
    // imageInView = images.get(0);
    // } else {
    // imageInView = images.get(images.indexOf(imageInView) + 1);
    // }
    // });

    // goBack.setOnAction(e -> {
    // loadFXML("home.fxml");
    // });

    // }

    // private void addImageCategory(String categoryName, int columnIndex, int
    // rowIndex) {
    // ImageCategory imageCategory = new ImageCategory(categoryName);
    // StackPane stackPane = new StackPane(imageCategory);
    // stackPane.getStyleClass().add("category-pane");
    // stackPane.setOnMouseClicked(event -> selectCategory(imageCategory));
    // CategoryGrid.add(stackPane, columnIndex, rowIndex);
    // }

    // private void selectCategory(ImageCategory category) {
    // if (selectedCategory != null) {
    // selectedCategory.getStyleClass().remove("selected");
    // }
    // selectedCategory = category;
    // selectedCategory.getStyleClass().add("selected");
    // }

    // private void loadFXML(String fxmlFileName) {
    // try {
    // FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFileName));
    // Parent root = fxmlLoader.load();
    // Scene scene = new Scene(root);
    // Stage stage = new Stage();
    // stage.setScene(scene);
    // stage.setTitle("Home Window");
    // stage.show();
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    // }
}
