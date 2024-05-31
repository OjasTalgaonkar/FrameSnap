package Controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControllerIntro {

    @FXML
    private Button GoToImg;

    @FXML
    void initialize() {
        GoToImg.setOnAction(event -> {
            loadFXML("home.fxml", event);
        });

        GoToImg.setOnMouseEntered(ev -> {
            GoToImg.getStyleClass().remove("btn");
            GoToImg.getStyleClass().add("btn_hover");
        });

        GoToImg.setOnMouseExited(ev -> {
            GoToImg.getStyleClass().remove("btn_hover");
            GoToImg.getStyleClass().add("btn");
        });
    }

    private void loadFXML(String fxmlFileName, ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFileName));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Home Window");
            stage.show();

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
