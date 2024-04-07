package com.example.proyectodeprogramacion;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.control.Button;
import java.util.Scanner;

public class HelloController {
    @FXML
    public Canvas DibujoCanvas;

    private String figura = "";

    private double inicioX = -1;
    private double inicioY = -1;

    @FXML
    private VBox botonesVBox;

    @FXML
    private TextField textoTextField;

    public void initialize() {
        DibujarLineaLimite(660, 0);
        Button boton = new Button("Mi Botón");
        botonesVBox.getChildren().add(boton);
        DibujoCanvas.setOnMouseClicked(event -> {
            double x = event.getX();
            double y = event.getY();
            if (x <= 660) {
                String texto = textoTextField.getText(); // Obtener el texto del TextField
                switch (figura) {
                    case "boton1":
                        // Proximamente
                        break;
                    case "boton2":
                        // Proximamente
                        break;
                    case "boton3":
                        DibujarProceso(x, y, texto);
                        if (inicioX != -1 && inicioY != -1) {
                            DibujarFlecha(inicioX, inicioY, x, y);
                        }
                        inicioX = x;
                        inicioY = y;
                        break;
                    // Otros casos aquí
                }
            } else {
                System.out.println("No se puede colocar aquí");
            }
        });
    }

    private void DibujarFlecha(double inicioX, double inicioY, double finalX, double finalY) {
        GraphicsContext gc = DibujoCanvas.getGraphicsContext2D();
        double TamañoFlecha = 10;
        double angulo = Math.atan2(finalY - inicioY, finalX - inicioX);

        if(inicioX < 660) {
            gc.strokeLine(inicioX, inicioY, finalX, finalY);
            gc.strokeLine(finalX, finalY, finalX - TamañoFlecha * Math.cos(angulo - Math.PI / 6), finalY - TamañoFlecha * Math.sin(angulo - Math.PI / 6));
            gc.strokeLine(finalX, finalY, finalX - TamañoFlecha * Math.cos(angulo + Math.PI / 6), finalY - TamañoFlecha * Math.sin(angulo + Math.PI / 6));
        }
    }




    private void Dibujar_Entrada_Salida(double x, double y) {
        GraphicsContext gc = DibujoCanvas.getGraphicsContext2D();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Texto Entrada/Salida: ");
        String texto = scanner.nextLine();


        gc.beginPath();
        gc.moveTo(x, y);
        gc.lineTo(x + 100, y);
        gc.lineTo(x + 100 - (50) / 4, y + 50);
        gc.lineTo(x - (50) / 4, y + 50);
        gc.closePath();

        gc.setFont(new Font(20)); // Tamaño de fuente 20
        gc.strokeText(texto, x + 15, y + 30); //escribir texto

        gc.stroke();
    }
    private void Dibujar_Documento(double x, double y) {
        GraphicsContext gc = DibujoCanvas.getGraphicsContext2D();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Texto Entrada/Salida: ");
        String texto = scanner.nextLine();


        gc.beginPath();
        gc.moveTo(x, y);
        gc.lineTo(x + 100, y);
        gc.lineTo(x + 100, y + 50);
        gc.closePath();
        gc.quadraticCurveTo(x+100,y+50,x+150,y+200);
        gc.closePath();

        gc.setFont(new Font(20)); // Tamaño de fuente 20
        gc.strokeText(texto, x + 15, y + 30); //escribir texto

        gc.stroke();
    }


    private void DibujarProceso(double x, double y) {
        GraphicsContext gc = DibujoCanvas.getGraphicsContext2D();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Texto Proceso: ");
        String texto = scanner.nextLine();

        gc.beginPath();
        gc.moveTo(x, y);
        gc.lineTo(x + 100, y);
        gc.lineTo(x + 100, y + 50);
        gc.lineTo(x, y + 50);
        gc.closePath();

        gc.setFont(new Font(20)); // Tamaño de fuente 20
        gc.strokeText(texto, x + 20, y + 30); //escribir texto

        gc.stroke();
    }


    private void DibujarDecision(double x, double y) {
        GraphicsContext gc = DibujoCanvas.getGraphicsContext2D();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Texto Decisión: ");
        String texto = scanner.nextLine();

        //Dibuja figura Decisión
        gc.beginPath();
        gc.moveTo(x, y);
        gc.lineTo(x + 70, y + 50);
        gc.lineTo(x, y + 100);
        gc.lineTo(x - 70, y + 50);
        gc.closePath();

        //Dibuja Lineas Horizontales de figura Decisión
        gc.moveTo(x - 70, y + 50);
        gc.lineTo(x - 150, y + 50);
        gc.moveTo(x + 70, y + 50);
        gc.lineTo(x + 150, y + 50);
        gc.strokeText("V", x - 130, y + 45);
        gc.strokeText("F", x + 120, y + 45);

        gc.setFont(new Font(20));
        gc.strokeText(texto, x - 35, y + 55);

        gc.stroke();
    }

    private void DibujarLineaLimite(double x, double y) {
        GraphicsContext gc = DibujoCanvas.getGraphicsContext2D();
        gc.setStroke(javafx.scene.paint.Color.BLACK);
        gc.setLineWidth(2.0);
        gc.strokeLine(x, y, x, y+ 800);
    }


    @FXML
    private void handleButton1Click() {
        figura = "boton1";
    }

    @FXML
    private void handleButton2Click() {
        figura = "boton2";
    }

    @FXML
    private void handleButton3Click() {
        figura = "boton3";
    }

    @FXML
    private void handleButton4Click() {
        figura = "boton4";
    }

    @FXML
    private void handleButton5Click() {
        figura = "boton5";
    }

    @FXML
    private void handleButton6Click() {
        figura = "boton6";
    }
}