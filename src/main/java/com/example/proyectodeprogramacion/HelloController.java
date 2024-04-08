package com.example.proyectodeprogramacion;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.layout.AnchorPane;

import java.util.List;
import java.util.Scanner;

public class HelloController {

    @FXML
    public Canvas DibujoCanvas;

    private String figura = "";

    private double inicioX = -1;
    private double inicioY = -1;

    public void initialize() {
        DibujarLineaLimite(660, 0);
        DibujoCanvas.setOnMouseClicked(event ->{
            double x = event.getX();
            double y = event.getY();

            GraphicsContext gc = DibujoCanvas.getGraphicsContext2D();
            GraphicsContext gc2 = DibujoCanvas.getGraphicsContext2D();
            if(x <= 660){
                switch (figura){
                    case "boton1":
                        //Proximamente
                    case "boton2":
                        //Proximamente
                    case "boton3":
                        Proceso proceso = new Proceso();
                        proceso.DibujarProceso(gc, x,y);
                        if (inicioX != -1 && inicioY != -1) {
                            DibujarFlecha(inicioX, inicioY, x + 50, y);
                        }
                        inicioX = x + 50;
                        inicioY = y + 50;
                        break;
                    case "boton4":
                        Decision decision = new Decision();
                        decision.DibujarDecision(gc, gc2, x, y);
                        if (inicioX != -1 && inicioY != -1) {
                            DibujarFlecha(inicioX, inicioY, x, y);
                        }
                        //Lado Verdadero
                        if(x > inicioX){
                            inicioX = x - 150;
                            inicioY = y + 50;
                        }
                        //Lado Falso
                        else{
                            inicioX = x + 150;
                            inicioY = y + 50;
                        }
                        break;
                    case "boton5":
                        EntradaSalida entradasalida = new EntradaSalida();
                        entradasalida.Dibujar_Entrada_Salida(gc, x, y);
                        if (inicioX != -1 && inicioY != -1) {
                            DibujarFlecha(inicioX, inicioY, x + 50, y);
                        }
                        inicioX = x + 50;
                        inicioY = y + 50;
                        break;
                    case "boton6":
                        //Proximamente
                        break;
                }
            }
            else{
                System.out.println("No se puede colocar aqui");
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