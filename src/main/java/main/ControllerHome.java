package main;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;

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

    public void initialize() {
        CategoryGrid.add(new ImageCategory("Landscape"), 0, 0);
        CategoryGrid.add(new ImageCategory("Portrait"), 1, 0);
        CategoryGrid.add(new ImageCategory("Animals"), 2, 0);
        CategoryGrid.add(new ImageCategory("Food"), 3, 0);
        for (int i = 0; i < 4; i++) {
            CategoryGrid.add(new ImageCategory("???"), i, 1);
        }

    }

}
