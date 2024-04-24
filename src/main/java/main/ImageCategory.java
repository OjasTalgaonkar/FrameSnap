package lab;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class ImageCategory extends VBox {
    private ImageView Cover;
    private Label Category;
    private ArrayList<Image> images;

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

}
