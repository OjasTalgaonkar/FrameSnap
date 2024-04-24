module main {
    requires javafx.controls;
    requires javafx.fxml;

    opens lab to javafx.fxml;
    exports lab;
}
