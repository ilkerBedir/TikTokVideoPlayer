module com.example.tiktokvideoplayer {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.swing;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.media;
    requires org.java_websocket;
    requires java.desktop;
    opens com.example.tiktokvideoplayer to javafx.fxml;
    exports com.example.tiktokvideoplayer;
}