package com.example.proyectodeprogramacion;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.io.IOException;




public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        primaryStage.setTitle("Diagrama de Flujo");
        primaryStage.setScene(scene);
        primaryStage.show();

        Canvas dibujoCanvas = (Canvas) scene.lookup("#DibujoCanvas");

        // Agregar un ChangeListener al tamaño de la escena para ajustar el tamaño del canvas
        scene.widthProperty().addListener((observable, oldValue, newValue) -> {
            double newWidth = newValue.doubleValue();
            dibujoCanvas.setWidth(newWidth); // Ajustar el ancho del canvas
            DibujarLineaLimite(dibujoCanvas);
        });

        scene.heightProperty().addListener((observable, oldValue, newValue) -> {
            double newHeight = newValue.doubleValue();
            dibujoCanvas.setHeight(newHeight); // Ajustar el alto del canvas
            DibujarLineaLimite(dibujoCanvas);
        });

        DibujarLineaLimite(dibujoCanvas);

    }

/** ***/
    private void DibujarLineaLimite(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0,0, canvas.getWidth(), canvas.getHeight());
        gc.setStroke(javafx.scene.paint.Color.BLACK);
        gc.setLineWidth(2.0);
        //gc.strokeLine(canvas.getWidth() - 140.0, 0, canvas.getWidth() - 140.0, canvas.getHeight());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
