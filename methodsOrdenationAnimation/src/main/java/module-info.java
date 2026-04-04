module com.example.methodsordenationanimation {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.methodsordenationanimation to javafx.fxml;
    exports com.example.methodsordenationanimation;
}