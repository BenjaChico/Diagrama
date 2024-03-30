module com.example.proyectodeprogramacion {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.proyectodeprogramacion to javafx.fxml;
    exports com.example.proyectodeprogramacion;
}