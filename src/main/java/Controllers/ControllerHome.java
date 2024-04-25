package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

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

    public void initialize() {
        addImageCategory("Landscape", 0, 0);
        addImageCategory("Portrait", 1, 0);
        addImageCategory("Animals", 2, 0);
        addImageCategory("Food", 3, 0);
        for (int i = 0; i < 4; i++) {
            addImageCategory("???", i, 1);
        }
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
    }

}
