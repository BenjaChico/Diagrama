package com.example.proyectodeprogramacion;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.layout.AnchorPane;

import java.util.Scanner;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

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

        if(inicioX < 660) {
            gc.strokeLine(inicioX, inicioY, finalX, finalY);
            gc.strokeLine(finalX, finalY, finalX - TamañoFlecha * Math.cos(angulo - Math.PI / 6), finalY - TamañoFlecha * Math.sin(angulo - Math.PI / 6));
            gc.strokeLine(finalX, finalY, finalX - TamañoFlecha * Math.cos(angulo + Math.PI / 6), finalY - TamañoFlecha * Math.sin(angulo + Math.PI / 6));
        }
    }


    public class Decision{
        public String texto;
        public String getTexto() {
            return texto;
        }

        public void setTexto(String texto) {
            this.texto = texto;
        }

        public void DibujarDecision(GraphicsContext gc, GraphicsContext gc2, double x, double y) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Texto Decisión");
            dialog.setHeaderText(null);
            dialog.setContentText("Texto Decisión:");

            dialog.showAndWait().ifPresent(texto -> {
                double tamanotexto = gc.getFont().getSize();
                while (tamanotexto * texto.length() > 140) {
                    tamanotexto -= 1;

                }
                this.texto = texto;
                System.out.println(texto);
                //Dibuja figura Decisión
                gc.beginPath();
                gc.moveTo(x, y);
                gc.lineTo(x + 70, y + 50);
                gc.lineTo(x, y + 100);
                gc.lineTo(x - 70, y + 50);
                gc.closePath();

                //Dibuja Lineas Verdadero y Falso de figura Decisión
                gc.moveTo(x - 70, y + 50);
                gc.lineTo(x - 150, y + 50);
                gc.moveTo(x + 70, y + 50);
                gc.lineTo(x + 150, y + 50);

                gc2.setFont(new Font(15));
                gc2.strokeText("V", x - 130, y + 45);
                gc2.strokeText("F", x + 120, y + 45);


                gc.setFont(new Font(tamanotexto + 5));
                gc.strokeText(texto, x - (texto.length() * tamanotexto / 4) - 10, y + 55);
                gc.stroke();

            });
        }
    }




    public class Documento {

        public void Dibujar_Documento(GraphicsContext gc, double x, double y) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Texto Decisión");
            dialog.setHeaderText(null);
            dialog.setContentText("Texto Decisión:");

            dialog.showAndWait().ifPresent(texto -> {
                double tamanotexto = gc.getFont().getSize();
                while (tamanotexto * texto.length() > 140) {
                    tamanotexto -=1;
                }
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
            });
        }

    }
    public class EntradaSalida {

        public void Dibujar_Entrada_Salida(GraphicsContext gc, double x, double y) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Texto Decisión");
            dialog.setHeaderText(null);
            dialog.setContentText("Texto Decisión:");
            dialog.showAndWait().ifPresent(texto -> {
                double tamanotexto = gc.getFont().getSize();
                while (tamanotexto * texto.length() > 140) {
                    tamanotexto -= 1;
                }
                gc.beginPath();
                gc.moveTo(x, y);
                gc.lineTo(x + 100, y);
                gc.lineTo(x + 100, y + 50);
                gc.lineTo(x, y + 50);
                gc.closePath();
                gc.setFont(new Font(20));
                gc.strokeText(texto, x + 20, y + 30);
                gc.stroke();
            });
        }

    }
    public class Proceso {

        public String getTextoo() {
            return textoo;
        }

        public void setTextoo(String textoo) {
            this.textoo = textoo;
        }

        private String textoo;


        public void DibujarProceso(GraphicsContext gc, double x, double y) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Texto Decisión");
            dialog.setHeaderText(null);
            dialog.setContentText("Texto Decisión:");

            gc.beginPath();
            gc.moveTo(x, y);
            gc.lineTo(x + 100, y);
            gc.lineTo(x + 100, y + 50);
            gc.lineTo(x, y + 50);
            gc.closePath();

            dialog.showAndWait().ifPresent(texto -> {
                double tamanotexto = gc.getFont().getSize();
                while (tamanotexto * texto.length() > 140) {
                    tamanotexto -= 1;
                }
                gc.beginPath();
                gc.moveTo(x, y);
                gc.lineTo(x + 100, y);
                gc.lineTo(x + 100 - (50) / 4, y + 50);
                gc.lineTo(x - (50) / 4, y + 50);
                gc.closePath();

            });
        }
    }



    private void DibujarLineaLimite(double x, double y) {
        GraphicsContext gc = DibujoCanvas.getGraphicsContext2D();
        gc.setStroke(javafx.scene.paint.Color.BLACK);
        gc.setLineWidth(2.0);
        gc.strokeLine(x, y, x, y+ 800);
    }


    @FXML
    private void borrarFigurasClick() {
        GraphicsContext gc = DibujoCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0,660, DibujoCanvas.getHeight());
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