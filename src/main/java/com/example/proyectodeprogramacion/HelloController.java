package com.example.proyectodeprogramacion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
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
    private boolean ListoPresionado = false;

    public void initialize() {
        DibujoCanvas.setOnMouseClicked(event ->{
            double x = event.getX();
            double y = event.getY();

            GraphicsContext gc = DibujoCanvas.getGraphicsContext2D();
            GraphicsContext gc2 = DibujoCanvas.getGraphicsContext2D();
            if(x <= DibujoCanvas.getWidth() - 140.0){
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
                        if(!ListoPresionado){
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
                        EntradaSalida entradaSalida = new EntradaSalida();
                        entradaSalida.Dibujar_Entrada_Salida(gc, x, y);
                        if (inicioX != -1 && inicioY != -1) {
                            DibujarFlecha(inicioX, inicioY, x + 50, y);
                        }
                        inicioX = x + 50;
                        inicioY = y + 50;
                        break;
                    case "boton6":
                        Documento documento = new Documento();
                        documento.Dibujar_Documento(gc, x, y);
                        if (inicioX != -1 && inicioY != -1) {
                            DibujarFlecha(inicioX, inicioY, x + 50, y);
                        }
                        inicioX = x + 50;
                        inicioY = y + 50;
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

        if(inicioX < DibujoCanvas.getWidth() - 140.0) {
            gc.strokeLine(inicioX, inicioY, finalX, finalY);
            gc.strokeLine(finalX, finalY, finalX - TamañoFlecha * Math.cos(angulo - Math.PI / 6), finalY - TamañoFlecha * Math.sin(angulo - Math.PI / 6));
            gc.strokeLine(finalX, finalY, finalX - TamañoFlecha * Math.cos(angulo + Math.PI / 6), finalY - TamañoFlecha * Math.sin(angulo + Math.PI / 6));
        }
    }

    @FXML
    private void borrarFigurasClick() {
        GraphicsContext gc = DibujoCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0,DibujoCanvas.getWidth(), DibujoCanvas.getHeight());
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

    public void BotonListo() {
        ListoPresionado = true;
    }
}