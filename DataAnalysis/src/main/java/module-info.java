module DataAnalysis {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.Baja.app to javafx.fxml;
    exports com.Baja.app;
}