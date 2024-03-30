package com.example.proyectodeprogramacion;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.layout.AnchorPane;

import java.util.Scanner;

public class HelloController {
    @FXML
    public Canvas DibujoCanvas;

    private String figura = "";

    private double inicioX = -1;
    private double inicioY = -1;


    public void initialize() {
        DibujoCanvas.setOnMouseClicked(event ->{
            double x = event.getX();
            double y = event.getY();
            if(x <= 688){
                switch (figura){
                    case "boton1":
                        //Proximamente
                    case "boton2":
                        //Proximamente
                    case "boton3":
                        DibujarProceso(x,y);
                        break;
                    case "boton4":
                        DibujarDecision(x, y);
                        break;
                    case "boton5":
                        Dibujar_Entrada_Salida(x, y);
                        break;
                    case "boton6":
                        //Proximamente
                        break;
                }
                if (inicioX != -1 && inicioY != -1) {
                    DibujarFlecha(inicioX, inicioY, x, y);
                }
                inicioX = x;
                inicioY = y;
            }
            else{
                System.out.println("No se puede colocar aqui");
            }
        });
    }

    private void DibujarFlecha(double inicioX, double inicioY, double finalX, double finalY) {
        GraphicsContext gc = DibujoCanvas.getGraphicsContext2D();
        double arrowSize = 10;
        double angle = Math.atan2(finalY - inicioY, finalX - inicioX);

        if(inicioX < 688) {
            gc.strokeLine(inicioX, inicioY, finalX, finalY);
            gc.strokeLine(finalX, finalY, finalX - arrowSize * Math.cos(angle - Math.PI / 6), finalY - arrowSize * Math.sin(angle - Math.PI / 6));
            gc.strokeLine(finalX, finalY, finalX - arrowSize * Math.cos(angle + Math.PI / 6), finalY - arrowSize * Math.sin(angle + Math.PI / 6));
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

        gc.beginPath();
        gc.moveTo(x, y);
        gc.lineTo(x + 70, y + 50);
        gc.lineTo(x, y + 100);
        gc.lineTo(x - 70, y + 50);
        gc.closePath();

        gc.setFont(new Font(20)); // Tamaño de fuente 20
        gc.strokeText(texto, x - 35, y + 55); //escribir texto

        gc.stroke();
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