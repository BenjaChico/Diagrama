package com.example.proyectodeprogramacion;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private GraphicsContext gc;
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        Canvas DibujoCanvas = (Canvas) scene.lookup("#DibujoCanvas");
        gc = DibujoCanvas.getGraphicsContext2D();
        dibujarLineaLimite(gc,668, 0);
        primaryStage.setTitle("Diagrama de Flujo");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    private void dibujarLineaLimite(GraphicsContext gc , double x, double y) {
        gc.setStroke(javafx.scene.paint.Color.BLACK);
        gc.setLineWidth(2.0);
        gc.strokeLine(x, y, x, y+ 800);
    }
    public static void main(String[] args) {
        launch(args);
    }
}
