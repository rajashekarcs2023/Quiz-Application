module com.topquiz {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.topquiz.gui to javafx.fxml;
    exports com.topquiz.gui;

    opens com.topquiz.logic.model to javafx.fxml;
    exports com.topquiz.logic.model;
    exports com.topquiz.gui.controller;
    opens com.topquiz.gui.controller to javafx.fxml;
}