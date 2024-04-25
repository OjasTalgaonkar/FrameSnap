package Controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ImageCategory extends VBox {
    private ImageView Cover;
    private Label Category;
    private ArrayList<Image> images;
    private boolean selected = false;

    public ImageCategory(String category) {
        Cover = new ImageView();
        Cover.setFitHeight(131);
        Cover.setFitWidth(150);
        Category = new Label(category);
        images = new ArrayList<Image>();

        this.getChildren().addAll(Cover, Category);
        setAlignment(Pos.CENTER);
    }

    public void setCover() throws FileNotFoundException {
        if (images.size() == 0) {
            Cover.setImage(new Image(new FileInputStream("src\\main\\resources\\lab\\photos_alt.webp")));
        } else {
            Cover.setImage(images.get(images.size() - 1));
        }
    }

    public boolean isSelected() {
        return selected;
    }

    public void select() {
        selected = true;
    }

    public void addImage(Image newImage) {
        images.add(newImage);
    }

    public void setCategory(String categoryName) {
        Category.setText(categoryName);
    }

    public void openImages() {
        loadFXML("Gallery.fxml");
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
            e.printStackTrace();
        }
    }

}
