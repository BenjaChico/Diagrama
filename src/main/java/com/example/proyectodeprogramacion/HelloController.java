package com.example.proyectodeprogramacion;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
import java.util.ArrayList;
import javafx.scene.control.TextInputDialog;

public class HelloController {
    private final ArrayList<Figura> figurasarreglo = new ArrayList<>();

    public abstract class Figura {
        public abstract boolean contienePunto(double x, double y);
        public abstract String getTexto();
        public abstract void setTexto(String texto);
        public double getX() {
            return x;
        }

        public void setX(double x) {
            this.x = x;
        }

        public double getY() {
            return y;
        }

        public void setY(double y) {
            this.y = y;
        }

        private double x;
        private double y;

        public Figura(double x, double y) {
            this.x = x;
            this.y = y;
        }
        public Figura() {
        }
    }
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
        Figura figuraSeleccionada = obtenerFiguraClicada(x,y);
        if(figuraSeleccionada != null){
            TextInputDialog dialog = new TextInputDialog(figuraSeleccionada.getTexto());
            dialog.setTitle("Modificar Texto");
            dialog.setHeaderText(null);
            dialog.setContentText("Nuevo Texto");

            dialog.showAndWait().ifPresent(newText -> {
                figuraSeleccionada.setTexto(newText);
                redibujarFiguras();
            });
        }
        else{
            if(x <= DibujoCanvas.getWidth() - 140.0){
                switch (figura){
                    case "boton1":
                        //Proximamente
                    case "boton2":
                        //Proximamente
                    case "boton3":
                        Proceso proceso = new Proceso(x, y);
                        proceso.DibujarProceso(gc, x,y);
                        figurasarreglo.add(proceso);
                        if (inicioX != -1 && inicioY != -1) {
                            DibujarFlecha(inicioX, inicioY, x + 50, y);
                        }
                        inicioX = x + 50;
                        inicioY = y + 50;
                        break;
                    case "boton4":
                        Decision decision = new Decision(x, y);
                        decision.DibujarDecision(gc, gc2, x, y);
                        figurasarreglo.add(decision);
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
                        EntradaSalida entradaSalida = new EntradaSalida(x, y);
                        entradaSalida.Dibujar_Entrada_Salida(gc, x, y);
                        figurasarreglo.add(entradaSalida);
                        if (inicioX != -1 && inicioY != -1) {
                            DibujarFlecha(inicioX, inicioY, x + 50, y);
                        }
                        inicioX = x + 50;
                        inicioY = y + 50;
                        break;
                    case "boton6":
                        Documento documento = new Documento(x, y);
                        documento.Dibujar_Documento(gc, x, y);
                        figurasarreglo.add(documento);
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

    public class Decision extends Figura{
        public String textoo;

        public Decision(double x, double y) {
            super(x, y);
        }
        public Decision() {
            super();
        }

        public String getTexto() {
            return textoo;
        }

        public void setTexto(String texto) {
            this.textoo = texto;
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
                setTexto(texto);
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
        @Override
        public boolean contienePunto(double x, double y) {
            double ancho = 140; // Ancho de la figura de decisión
            double alto = 100; // Alto de la figura de decisión

            double x1 = this.getX();
            double y1 = this.getY();
            double x2 = x1 + ancho;
            double y2 = y1 + alto;

            return x >= x1 && x <= x2 && y >= y1 && y <= y2;
        }
    }




    public class Documento extends Figura {

        public Documento(double x, double y) {
            super(x, y);
        }

        public Documento() {
            super();
        }

        public String textoo;

        public String getTexto() {
            return textoo;
        }

        public void setTexto(String texto) {
            this.textoo = texto;
        }

        public void Dibujar_Documento(GraphicsContext gc, double x, double y) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Texto Documento");
            dialog.setHeaderText(null);
            dialog.setContentText("Texto Documento:");

            dialog.showAndWait().ifPresent(texto -> {
                double tamanotexto = gc.getFont().getSize();
                while (tamanotexto * texto.length() > 140) {
                    tamanotexto -= 1;
                }
                setTexto(texto);
                gc.beginPath();
                gc.moveTo(x, y);
                gc.lineTo(x + 100, y);
                gc.lineTo(x + 100, y + 50);
                gc.bezierCurveTo(x + 100, y + 50, x + 150, y + 200, x + 150, y + 200);
                gc.lineTo(x, y + 50);
                gc.closePath();
                gc.setFont(new Font(20)); // Tamaño de fuente 20
                gc.strokeText(texto, x + 15, y + 30); //escribir texto
                gc.stroke();
            });
        }

        @Override
        public boolean contienePunto(double x, double y) {
            double ancho = 100;
            double alto = 50;

            double x1 = this.getX();
            double y1 = this.getY();
            double x2 = x1 + ancho;
            double y2 = y1 + alto;

            return x >= x1 && x <= x2 && y >= y1 && y <= y2;
        }
    }


    public class EntradaSalida extends Figura{
        public EntradaSalida(double x, double y) {
            super(x, y);
        }
        public EntradaSalida() {
            super();
        }

        public String textoo;
        public String getTexto() {
            return textoo;
        }

        public void setTexto(String texto) {
            this.textoo = texto;
        }

        public void Dibujar_Entrada_Salida(GraphicsContext gc, double x, double y) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Texto Entrada-Salida");
            dialog.setHeaderText(null);
            dialog.setContentText("Texto Entrada-Salida:");
            dialog.showAndWait().ifPresent(texto -> {
                double tamanotexto = gc.getFont().getSize();
                while (tamanotexto * texto.length() > 140) {
                    tamanotexto -= 1;
                }
                setTexto(texto);
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
        @Override
        public boolean contienePunto(double x, double y) {
            double ancho = 100;
            double alto = 50;


            double x1 = this.getX();
            double y1 = this.getY();
            double x2 = x1 + ancho;
            double y2 = y1 + alto;


            return x >= x1 && x <= x2 && y >= y1 && y <= y2;
        }

    }


    public class Proceso extends Figura{
        public Proceso(double x, double y) {
            super(x, y);
        }
        public Proceso() {
            super();
        }

        public String textoo;
        public String getTexto() {
            return textoo;
        }

        public void setTexto(String texto) {
            this.textoo = texto;
        }


        public void DibujarProceso(GraphicsContext gc, double x, double y) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Texto Proceso");
            dialog.setHeaderText(null);
            dialog.setContentText("Texto Proceso:");

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
                setTexto(texto);
                gc.beginPath();
                gc.moveTo(x, y);
                gc.lineTo(x + 100, y);
                gc.lineTo(x + 100 - (50) / 4, y + 50);
                gc.lineTo(x - (50) / 4, y + 50);
                gc.closePath();

            });
        }
        public boolean contienePunto(double x, double y) {
            double ancho = 100;
            double alto = 50;

            double x1 = this.getX();
            double y1 = this.getY();
            double x2 = x1 + ancho;
            double y2 = y1 + alto;

            return x >= x1 && x <= x2 && y >= y1 && y <= y2;
        }
    }


    @FXML
    private void borrarFigurasClick() {
        GraphicsContext gc = DibujoCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0,DibujoCanvas.getWidth(), DibujoCanvas.getHeight());
    }

    private Figura obtenerFiguraClicada(double x, double y) {
        for (Figura figura : figurasarreglo) {
            if (figura.contienePunto(x, y)) {
                return figura;
            }
        }
        return null;
    }


    private void redibujarFiguras() {
        GraphicsContext gc = DibujoCanvas.getGraphicsContext2D();
        // Limpiar el lienzo
        gc.clearRect(0, 0, DibujoCanvas.getWidth(), DibujoCanvas.getHeight());
        // Redibujar todas las figuras con el nuevo texto
        for (Figura figura : figurasarreglo) {
            if (figura instanceof Proceso) {
                Proceso proceso = (Proceso) figura;
                proceso.DibujarProceso(gc, figura.getX(), figura.getY());
            } else if (figura instanceof Decision) {
                Decision decision = (Decision) figura;
                decision.DibujarDecision(gc, gc, figura.getX(), figura.getY());
            } else if (figura instanceof EntradaSalida) {
                EntradaSalida entradaSalida = (EntradaSalida) figura;
                entradaSalida.Dibujar_Entrada_Salida(gc, figura.getX(), figura.getY());
            } else if (figura instanceof Documento) {
                Documento documento = (Documento) figura;
                documento.Dibujar_Documento(gc, figura.getX(), figura.getY());
            }
        }
    }


    @FXML
    private void imprimirFiguras() {
        System.out.println("Contenido del arreglo de figuras:");
        for (Figura figura : figurasarreglo) {
            System.out.println(figura.getX()); //
        }
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