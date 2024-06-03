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
    private boolean CoverSet;

    public ImageCategory(String category) {
        Cover = new ImageView();
        Cover.setFitHeight(131);
        Cover.setFitWidth(150);
        Cover.setPreserveRatio(true);
        setCover();
        CoverSet = false;

        Category = new Label(category);
        images = new ArrayList<Image>();

        this.getChildren().addAll(Cover, Category);
        setAlignment(Pos.CENTER);
    }

    public void setCover() {
        if (CoverSet == false) { // only executes when cover hasn't been set manually
            try {
                if (this.images == null || images.size() == 0) {
                    Cover.setImage(new Image(new FileInputStream("src\\main\\resources\\Controllers\\NotFound.png")));
                } else {
                    Cover.setImage(images.get(images.size() - 1));
                }

                Cover.setFitHeight(131);
                Cover.setFitWidth(150);
                Cover.setPreserveRatio(true);
            } catch (Exception e) {
                System.out.println("Error");
            }
        }
    }

    public void setCover(Image image) { // to set custom covers
        Cover.setImage(image);
        CoverSet = true; // Cover wont change automatically when new images are added
    }

    public Image getCover() {
        return Cover.getImage();
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

    public void removeImage(Image image) {
        images.remove(image);
    }

    public ArrayList<Image> getImages() {
        return images;
    }

    public void setCategory(String categoryName) {
        Category.setText(categoryName);
    }

    public String getCategoryName() {
        return Category.getText();
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
