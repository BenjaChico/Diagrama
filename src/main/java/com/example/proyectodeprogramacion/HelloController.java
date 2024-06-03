package com.example.proyectodeprogramacion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.Stack;

public class HelloController {
    private final ArrayList<Figura> figurasarreglo = new ArrayList<>();
    private Stack<double[]> decisionStack = new Stack<>();
    private boolean MientrasCerrado = false;



    public abstract class Figura {
        public abstract boolean contienePunto(double x, double y);

        public abstract String getTexto();

        public abstract void setTexto(String texto);

        public abstract String generarPseudocodigo();

        public double getX() {
            return x;
        }

        private double inicioFlechaX;
        private double inicioFlechaY;
        private double finFlechaX;
        private double finFlechaY;

        public double getInicioFlechaX() {
            return inicioFlechaX;
        }

        public void setInicioFlechaX(double inicioFlechaX) {
            this.inicioFlechaX = inicioFlechaX;
        }

        public double getInicioFlechaY() {
            return inicioFlechaY;
        }

        public void setInicioFlechaY(double inicioFlechaY) {
            this.inicioFlechaY = inicioFlechaY;
        }

        public double getFinFlechaX() {
            return finFlechaX;
        }

        public void setFinFlechaX(double finFlechaX) {
            this.finFlechaX = finFlechaX;
        }

        public double getFinFlechaY() {
            return finFlechaY;
        }

        public void setFinFlechaY(double finFlechaY) {
            this.finFlechaY = finFlechaY;
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
    private boolean MientrasPresionado = false;

    public void initialize() {
        DibujoCanvas.setOnMouseClicked(event -> {
            double x = event.getX();
            double y = event.getY();

            GraphicsContext gc = DibujoCanvas.getGraphicsContext2D();
            GraphicsContext gc2 = DibujoCanvas.getGraphicsContext2D();
            Figura figuraSeleccionada = obtenerFiguraClicada(x, y);
            if (figuraSeleccionada != null) {
                TextInputDialog dialog = new TextInputDialog(figuraSeleccionada.getTexto());
                dialog.setTitle("Modificar Texto");
                dialog.setHeaderText(null);
                dialog.setContentText("Nuevo Texto");

                dialog.showAndWait().ifPresent(newText -> {
                    figuraSeleccionada.setTexto(newText);
                    redibujarFiguras();
                });
            } else {
                if (x <= DibujoCanvas.getWidth() - 140.0) {
                    switch (figura) {
                        case "boton6":
                            Repetir repetir = new Repetir(x, y);
                            repetir.DibujarRepetir(gc, x, y);
                            figurasarreglo.add(repetir);
                            if (inicioX != -1 && inicioY != -1) {
                                DibujarFlecha(inicioX, inicioY, x, y);
                                repetir.setInicioFlechaX(inicioX);
                                repetir.setInicioFlechaY(inicioY);
                                repetir.setFinFlechaX(x);
                                repetir.setFinFlechaY(y);
                            }
                            inicioX = x;
                            inicioY = y + 100;
                            break;
                        case "boton1":
                            InicioFin inicioFin = new InicioFin(x, y);
                            inicioFin.DibujarInicioFin(gc, x, y + 25);
                            figurasarreglo.add(inicioFin);
                            if (inicioX != -1 && inicioY != -1) {
                                DibujarFlecha(inicioX, inicioY, x + 50, y);
                                inicioFin.setInicioFlechaX(inicioX);
                                inicioFin.setInicioFlechaY(inicioY);
                                inicioFin.setFinFlechaX(x + 50);
                                inicioFin.setFinFlechaY(y);
                            }
                            inicioX = x + 50;
                            inicioY = y + 50;
                            break;
                        case "boton2":
                            Proceso proceso = new Proceso(x, y);
                            proceso.DibujarProceso(gc, x, y);
                            figurasarreglo.add(proceso);
                            if (inicioX != -1 && inicioY != -1) {
                                DibujarFlecha(inicioX, inicioY, x + 50, y);
                                proceso.setInicioFlechaX(inicioX);
                                proceso.setInicioFlechaY(inicioY);
                                proceso.setFinFlechaX(x + 50);
                                proceso.setFinFlechaY(y);
                            }
                            inicioX = x + 50;
                            inicioY = y + 50;
                            break;
                        case "boton3":
                            Decision decision = new Decision(x, y);
                            decision.DibujarDecision(gc, gc2, x, y);
                            figurasarreglo.add(decision);

                            if (inicioX != -1 && inicioY != -1) {
                                DibujarFlecha(inicioX, inicioY, x, y);
                                decision.setInicioFlechaX(inicioX);
                                decision.setInicioFlechaY(inicioY);
                                decision.setFinFlechaX(x);
                                decision.setFinFlechaY(y);
                            }
                            inicioX = x - 150;
                            inicioY = y + 50;
                            break;
                        case "boton4":
                            EntradaSalida entradaSalida = new EntradaSalida(x, y);
                            entradaSalida.Dibujar_Entrada_Salida(gc, x, y);
                            figurasarreglo.add(entradaSalida);
                            if (inicioX != -1 && inicioY != -1) {
                                DibujarFlecha(inicioX, inicioY, x + 50, y);
                                entradaSalida.setInicioFlechaX(inicioX);
                                entradaSalida.setInicioFlechaY(inicioY);
                                entradaSalida.setFinFlechaX(x + 50);
                                entradaSalida.setFinFlechaY(y);
                            }
                            inicioX = x + 50;
                            inicioY = y + 50;
                            break;
                        case "boton5":
                            Documento documento = new Documento(x, y);
                            documento.Dibujar_Documento(gc, x, y);
                            figurasarreglo.add(documento);
                            if (inicioX != -1 && inicioY != -1) {
                                DibujarFlecha(inicioX, inicioY, x + 50, y);
                                documento.setInicioFlechaX(inicioX);
                                documento.setInicioFlechaY(inicioY);
                                documento.setFinFlechaX(x + 50);
                                documento.setFinFlechaY(y);
                            }
                            inicioX = x + 50;
                            inicioY = y + 55;
                            break;
                        case "boton7":
                            Mientras mientras = new Mientras(x, y);
                            mientras.DibujarMientras(gc, gc2, x, y);
                            figurasarreglo.add(mientras);
                            if (inicioX != -1 && inicioY != -1) {
                                DibujarFlecha(inicioX, inicioY, x, y);
                                mientras.setInicioFlechaX(inicioX);
                                mientras.setInicioFlechaY(inicioY);
                                mientras.setFinFlechaX(x);
                                mientras.setFinFlechaY(y);
                            }
                            inicioX = x;
                            inicioY = y + 100;
                            break;
                        case "boton8":
                            Para para = new Para(x, y);
                            para.DibujarPara(gc);
                            figurasarreglo.add(para);
                            if(inicioX != -1 && inicioY != -1){
                                para.setInicioFlechaX(inicioX);
                                para.setInicioFlechaY(inicioY);
                                para.setFinFlechaX(x);
                                para.setFinFlechaY(y);
                            }
                    }
                } else {
                    System.out.println("No se puede colocar aqui");
                }
            }
        });
    }

    private void DibujarFlecha(double inicioX, double inicioY, double finalX, double finalY) {
        GraphicsContext gc = DibujoCanvas.getGraphicsContext2D();
        double TamañoFlecha = 10;
        double angulo = Math.atan2(finalY - inicioY, finalX - inicioX);

        if (inicioX < DibujoCanvas.getWidth() - 140.0) {
            gc.strokeLine(inicioX, inicioY, finalX, finalY);
            gc.strokeLine(finalX, finalY, finalX - TamañoFlecha * Math.cos(angulo - Math.PI / 6), finalY - TamañoFlecha * Math.sin(angulo - Math.PI / 6));
            gc.strokeLine(finalX, finalY, finalX - TamañoFlecha * Math.cos(angulo + Math.PI / 6), finalY - TamañoFlecha * Math.sin(angulo + Math.PI / 6));
        }
    }

    public class InicioFin extends Figura {
        public String textoo;

        public InicioFin(double x, double y) {
            super(x, y);
        }

        public InicioFin() {
            super();
        }

        public String getTexto() {
            return textoo;
        }

        public void setTexto(String texto) {
            this.textoo = texto;
        }

        @Override
        public String generarPseudocodigo() {
            return null;
        }

        public void DibujarInicioFin_Denuevo(GraphicsContext gc, double x, double y) {
            // Obtener el texto del objeto directamente
            String texto = getTexto();

            double tamanotexto = gc.getFont().getSize();
            while (tamanotexto * texto.length() > 140) {
                tamanotexto -= 1;
            }
            setTexto(texto);
            //Dibuja figura Decisión
            double radius = 25;
            double startAngle = 90;
            double extentAngle = 180;

            // Dibujar el semicírculo
            gc.strokeArc(x - radius, y - radius, radius * 2, radius * 2, startAngle, extentAngle, javafx.scene.shape.ArcType.OPEN);

            // Calcular las coordenadas finales del arco
            double cirx = x + radius * Math.cos(Math.toRadians(90)); // Coordenada x del punto final del arco
            double ciry = y + radius * Math.sin(Math.toRadians(90)); // Coordenada y del punto final del arco

            // Dibujar una línea desde el punto final del arco
            gc.beginPath();
            gc.moveTo(cirx, ciry);
            gc.lineTo(cirx + 100, ciry); // Dibujar una línea horizontal de 100 píxeles
            gc.stroke();

            double x2 = cirx + 100;
            double y2 = ciry - 25;
            double startAngle2 = 270; // Ángulo de inicio para el segundo semicírculo (mirando hacia la izquierda)

            // Dibujar el segundo semicírculo
            gc.strokeArc(x2 - radius, y2 - radius, radius * 2, radius * 2, startAngle2, extentAngle, javafx.scene.shape.ArcType.OPEN);

            // Calcular las coordenadas finales del segundo arco
            double cirx2 = x2 + radius * Math.cos(Math.toRadians(270)); // Coordenada x del punto final del arco
            double ciry2 = y2 + radius * Math.sin(Math.toRadians(270)); // Coordenada y del punto final del arco

            // Dibujar una línea desde el punto final del segundo arco
            gc.beginPath();
            gc.moveTo(cirx, ciry - 50);
            gc.lineTo(cirx2, ciry2); // Dibujar una línea horizontal de 100 píxeles hacia la izquierda
            gc.stroke();
            gc.closePath();

            gc.setFont(new Font(tamanotexto + 5));
            gc.strokeText(texto, x - (texto.length() * tamanotexto / 4) + 10, y);
            gc.stroke();
        }

        public void DibujarInicioFin(GraphicsContext gc, double x, double y) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Texto InicioFin");
            dialog.setHeaderText(null);
            dialog.setContentText("Texto InicioFin:");

            dialog.showAndWait().ifPresent(texto -> {
                textoo = texto;
                double tamanotexto = gc.getFont().getSize();
                while (tamanotexto * texto.length() > 140) {
                    tamanotexto -= 1;

                }
                setTexto(texto);
                //Dibuja figura Decisión
                double radius = 25;
                double startAngle = 90;
                double extentAngle = 180;

                // Dibujar el semicírculo
                gc.strokeArc(x - radius, y - radius, radius * 2, radius * 2, startAngle, extentAngle, javafx.scene.shape.ArcType.OPEN);

                // Calcular las coordenadas finales del arco
                double cirx = x + radius * Math.cos(Math.toRadians(90)); // Coordenada x del punto final del arco
                double ciry = y + radius * Math.sin(Math.toRadians(90)); // Coordenada y del punto final del arco

                // Dibujar una línea desde el punto final del arco
                gc.beginPath();
                gc.moveTo(cirx, ciry);
                gc.lineTo(cirx + 100, ciry); // Dibujar una línea horizontal de 100 píxeles
                gc.stroke();

                double x2 = cirx + 100;
                double y2 = ciry - 25;
                double startAngle2 = 270; // Ángulo de inicio para el segundo semicírculo (mirando hacia la izquierda)

                // Dibujar el segundo semicírculo
                gc.strokeArc(x2 - radius, y2 - radius, radius * 2, radius * 2, startAngle2, extentAngle, javafx.scene.shape.ArcType.OPEN);

                // Calcular las coordenadas finales del segundo arco
                double cirx2 = x2 + radius * Math.cos(Math.toRadians(270)); // Coordenada x del punto final del arco
                double ciry2 = y2 + radius * Math.sin(Math.toRadians(270)); // Coordenada y del punto final del arco

                // Dibujar una línea desde el punto final del segundo arco
                gc.beginPath();
                gc.moveTo(cirx, ciry - 50);
                gc.lineTo(cirx2, ciry2); // Dibujar una línea horizontal de 100 píxeles hacia la izquierda
                gc.stroke();
                gc.closePath();


                gc.setFont(new Font(tamanotexto + 5));
                gc.strokeText(texto, x - (texto.length() * tamanotexto / 4) + 10, y);
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

    public class Decision extends Figura {
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

        @Override
        public String generarPseudocodigo() {
            return "si " + getTexto() + "entonces";
        }

        public void DibujarDecision(GraphicsContext gc, GraphicsContext gc2, double x, double y) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Texto Decisión");
            dialog.setHeaderText(null);
            dialog.setContentText("Texto Decisión:");

            dialog.showAndWait().ifPresent(texto -> {
                textoo = texto;
                double tamanotexto = gc.getFont().getSize();
                while (tamanotexto * texto.length() > 140) {
                    tamanotexto -= 1;

                }
                setTexto(texto);
                gc.beginPath();
                gc.moveTo(x, y);
                gc.lineTo(x + 70, y + 50);
                gc.lineTo(x, y + 100);
                gc.lineTo(x - 70, y + 50);
                gc.closePath();
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

        public void DibujarDecision_Denuevo(GraphicsContext gc, GraphicsContext gc2, double x, double y) {
            // Obtener el texto del objeto directamente
            String texto = getTexto();

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

        @Override
        public String generarPseudocodigo() {
            return null;
        }

        public void Dibujar_Documento_Denuevo(GraphicsContext gc, double x, double y) {
            String texto = getTexto();

            double tamanotexto = gc.getFont().getSize();
            while (tamanotexto * texto.length() > 140) {
                tamanotexto -= 1;
            }
            setTexto(texto);
            gc.strokeLine(x, y, x + 100, y); // Línea Superior
            gc.strokeLine(x, y, x, y + 50); // Línea Izquierda
            gc.strokeLine(x + 100, y, x + 100, y + 40); // Línea Derecha
            gc.beginPath();
            gc.moveTo(x, y + 50); // Mover al punto de inicio de la curva
            gc.bezierCurveTo(x + 40, y + 80, x + 50, y + 35, x + 100, y + 40);
            gc.stroke(); // Trazar la curva
            gc.setFont(new Font(20)); // Tamaño de fuente 20
            gc.strokeText(texto, x + 15, y + 30); // Escribir texto
            gc.stroke();
        }

        public void Dibujar_Documento(GraphicsContext gc, double x, double y) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Texto Documento");
            dialog.setHeaderText(null);
            dialog.setContentText("Texto Documento:");

            dialog.showAndWait().ifPresent(texto -> {
                textoo = texto;
                double tamanotexto = gc.getFont().getSize();
                while (tamanotexto * texto.length() > 140) {
                    tamanotexto -= 1;
                }
                setTexto(texto);
                gc.strokeLine(x, y, x + 100, y); // Línea Superior
                gc.strokeLine(x, y, x, y + 50); // Línea Izquierda
                gc.strokeLine(x + 100, y, x + 100, y + 40); // Línea Derecha
                gc.beginPath();
                gc.moveTo(x, y + 50); // Mover al punto de inicio de la curva
                gc.bezierCurveTo(x + 40, y + 80, x + 50, y + 35, x + 100, y + 40);
                gc.stroke(); // Trazar la curva
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


    public class EntradaSalida extends Figura {
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

        @Override
        public String generarPseudocodigo() {
            return "leer " + getTexto();
        }

        public void Dibujar_Entrada_Salida_Denuevo(GraphicsContext gc, double x, double y) {
            String texto = getTexto();

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
            gc.setFont(new Font(20));
            gc.strokeText(texto, x + 20, y + 30);
            gc.stroke();
        }

        public void Dibujar_Entrada_Salida(GraphicsContext gc, double x, double y) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Texto Entrada-Salida");
            dialog.setHeaderText(null);
            dialog.setContentText("Texto Entrada-Salida:");
            dialog.showAndWait().ifPresent(texto -> {
                textoo = texto;
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

    public class Mientras extends Figura {
        public Mientras(double x, double y) {
            super(x, y);
        }

        public Mientras() {
            super();
        }

        public String textoo;

        @Override
        public String getTexto() {
            return textoo;
        }

        @Override
        public boolean contienePunto(double x, double y) {
            return false;
        }

        @Override
        public void setTexto(String texto) {

        }

        public void DibujarMientras(GraphicsContext gc, GraphicsContext gc2, double x, double y) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Texto Mientras");
            dialog.setHeaderText(null);
            dialog.setContentText("Texto Mientras:");

            dialog.showAndWait().ifPresent(texto -> {
                textoo = texto;
                double tamanotexto = gc.getFont().getSize();
                while (tamanotexto * texto.length() > 140) {
                    tamanotexto -= 1;

                }
                setTexto(texto);
                gc.beginPath();
                gc.moveTo(x, y);
                gc.lineTo(x + 70, y + 50);
                gc.lineTo(x, y + 100);
                gc.lineTo(x - 70, y + 50);
                gc.closePath();

                //Flecha derecha
                gc.moveTo(x + 70, y + 50);
                gc.lineTo(x + 120, y + 50);
                gc2.strokeText("F", x + 100, y + 45);

                gc.setFont(new Font(tamanotexto + 5));
                gc.strokeText(texto, x - (texto.length() * tamanotexto / 4) - 10, y + 55);
                gc.stroke();
            });
        }

        @Override
        public String generarPseudocodigo() {
            return null;
        }
    }

    public class Repetir extends Figura {
        public Repetir(double x, double y) {
            super(x, y);
        }

        public Repetir() {
            super();
        }

        public String textoo;

        @Override
        public String getTexto() {
            return textoo;
        }

        @Override
        public void setTexto(String texto) {
            this.textoo = texto;
        }

        @Override
        public String generarPseudocodigo() {
            return "Repetir" + "/n" + "Hasta Que" + getTexto();
        }

        public void DibujarRepetir(GraphicsContext gc, double x, double y) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Texto Repetir");
            dialog.setHeaderText(null);
            dialog.setContentText("Texto Repetir:");

            dialog.showAndWait().ifPresent(texto -> {
                textoo = texto;
                double tamanotexto = gc.getFont().getSize();
                while (tamanotexto * texto.length() > 140) {
                    tamanotexto -= 1;

                }
                setTexto(texto);
                gc.beginPath();
                gc.moveTo(x, y);
                gc.lineTo(x + 70, y + 50);
                gc.lineTo(x, y + 100);
                gc.lineTo(x - 70, y + 50);
                gc.closePath();
                //Flecha izquierda
                gc.moveTo(x - 70, y + 50);
                gc.lineTo(x - 100, y + 50);


                gc.setFont(new Font(tamanotexto + 5));
                gc.strokeText(texto, x - (texto.length() * tamanotexto / 4) - 10, y + 55);
                gc.stroke();
            });
        }

        @Override
        public boolean contienePunto(double x, double y) {
            double ancho = 140;
            double alto = 100;

            double x1 = this.getX();
            double y1 = this.getY();
            double x2 = x1 + ancho;
            double y2 = y1 + alto;

            return x >= x1 && x <= x2 && y >= y1 && y <= y2;
        }

    }

    public class Proceso extends Figura {
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

        @Override
        public String generarPseudocodigo() {
            return "proceso " + getTexto();
        }


        public void DibujarProceso(GraphicsContext gc, double x, double y) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Texto Proceso");
            dialog.setHeaderText(null);
            dialog.setContentText("Texto Proceso:");

            dialog.showAndWait().ifPresent(texto -> {
                textoo = texto;
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

        public void DibujarProceso_Denuevo(GraphicsContext gc, double x, double y) {
            // Obtener el texto del objeto directamente
            String texto = getTexto();

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
    
    public class Para extends Figura{

        private String texto1;
        private String texto2;
        private String texto3;
        private String texto4;

        public Para(double x, double y) {
            super(x, y);
        }

        public Para() {
            super();
        }

        public String getTexto1() {
            return texto1;
        }

        public String getTexto2() {
            return texto2;
        }

        public String getTexto3() {
            return texto3;
        }

        public String getTexto4() {
            return texto4;
        }
        @Override
        public String getTexto() {
            return String.format("%s <- %s hasta %s con paso %s", texto1, texto2, texto3, texto4);
        }

        @Override
        public void setTexto(String _texto) {
            this.texto1 = _texto;
        }

        public void setTexto2(String _texto){
            this.texto2 = _texto;
        }

        public void setTexto3(String _texto){
            this.texto3 = _texto;
        }

        public void setTexto4(String _texto){
            this.texto3 = _texto;
        }

        @Override
        public String generarPseudocodigo() {
            return null;
        }

        public void DibujarPara(GraphicsContext gc) {
            int size = figurasarreglo.size();
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Posición de cierre");
            dialog.setHeaderText(null);
            dialog.setContentText("Número de posición (hay " + size + " elementos en la lista):");

            dialog.showAndWait().ifPresent(textoo -> {
                try {
                    int posicion = Integer.parseInt(textoo);
                    if (posicion > 0 && posicion <= size) {
                        int index = posicion - 1;

                        Figura figuraCierre = figurasarreglo.get(index);
                        double xCerrar = figuraCierre.getX();
                        double yCerrar = figuraCierre.getY();
                        Figura ultimaFigura = figurasarreglo.get(figurasarreglo.size() - 1);
                        double xUltima = ultimaFigura.getX();
                        double yUltima = ultimaFigura.getY();

                        // Calcular el punto medio entre xCerrar, yCerrar y xUltima, yUltima
                        double xMedio = (xCerrar + xUltima) / 2;
                        double yMedio = (yCerrar + yUltima) / 2;
                        double tamanoFlecha = 10.0;

                        // Primer diálogo adicional
                        TextInputDialog dialog1 = new TextInputDialog();
                        dialog1.setTitle("Primer texto");
                        dialog1.setHeaderText(null);
                        dialog1.setContentText("Ingrese el primer texto:");

                        dialog1.showAndWait().ifPresent(texto1 -> {
                            // Segundo diálogo adicional
                            TextInputDialog dialog2 = new TextInputDialog();
                            dialog2.setTitle("Segundo texto");
                            dialog2.setHeaderText(null);
                            dialog2.setContentText("Ingrese el segundo texto:");

                            dialog2.showAndWait().ifPresent(texto2 -> {
                                // Tercer diálogo adicional
                                TextInputDialog dialog3 = new TextInputDialog();
                                dialog3.setTitle("Tercer texto");
                                dialog3.setHeaderText(null);
                                dialog3.setContentText("Ingrese el tercer texto:");

                                dialog3.showAndWait().ifPresent(texto3 -> {
                                    // Diálogo para el texto principal
                                    TextInputDialog dialog4 = new TextInputDialog();
                                    dialog4.setTitle("Cuarto texto");
                                    dialog4.setHeaderText(null);
                                    dialog4.setContentText("Ingrese el cuarto texto:");

                                    dialog4.showAndWait().ifPresent(texto -> {
                                        double tamanotexto = gc.getFont().getSize();
                                        while (tamanotexto * texto.length() > 140) {
                                            tamanotexto -= 1;
                                        }

                                        // Dibujar el círculo en el punto medio y flechas de cierre
                                        gc.beginPath();
                                        gc.strokeOval(xMedio - 200, yMedio - 30, 150, 150); // Ajusta para que el círculo esté centrado
                                        //Dibujo Linea de abajo
                                        gc.moveTo(xUltima + 50, yUltima + 70);
                                        gc.lineTo(xUltima - 143, yUltima + 70);
                                        gc.moveTo(xUltima - 143, yUltima + 70);
                                        gc.lineTo(xUltima - 143, yMedio + 115);

                                        //Dibujo Linea de arriba
                                        gc.moveTo(xMedio - 143, yMedio - 30);
                                        gc.lineTo(xMedio - 143, yCerrar - 20);
                                        gc.moveTo(xMedio - 143, yCerrar - 20);
                                        gc.lineTo(xCerrar + 10, yCerrar - 20);

                                        //Dibujo de la flecha superior
                                        gc.strokeLine(xCerrar + 10, yCerrar - 20, xCerrar - tamanoFlecha * Math.cos(Math.PI / 6), yCerrar - 20 + tamanoFlecha * Math.sin(Math.PI / 6));
                                        gc.strokeLine(xCerrar + 10, yCerrar - 20, xCerrar - tamanoFlecha * Math.cos(Math.PI / 6), yCerrar - 20 - tamanoFlecha * Math.sin(Math.PI / 6));

                                        //Dibujo de Lineas dentro de figura ciclo for
                                        //Linea horizontal
                                        gc.moveTo(xMedio - 50, yMedio + 30);
                                        gc.lineTo(xMedio - 200, yMedio + 30);
                                        //Primera linea vertical
                                        gc.moveTo(xMedio - 150 , yMedio + 30);
                                        gc.lineTo(xMedio - 150, yMedio + 115);
                                        //Segunda Linea Vertical
                                        gc.moveTo(xMedio - 100, yMedio + 30);
                                        gc.lineTo(xMedio - 100, yMedio + 115);
                                        gc.closePath();

                                        // Dibujar los textos adicionales y el texto principal
                                        gc.setFont(new Font(20));
                                        gc.strokeText(texto1, xMedio - 130 - (texto1.length() * tamanotexto / 4), yMedio + 10);
                                        gc.strokeText(texto2, xMedio - 180 - (texto2.length() * tamanotexto / 4), yMedio + 60);
                                        gc.strokeText(texto3, xMedio - 130 - (texto3.length() * tamanotexto / 4), yMedio + 60);
                                        gc.strokeText(texto, xMedio - 80 - (texto.length() * tamanotexto / 4), yMedio + 60);
                                        gc.stroke();
                                    });
                                });
                            });
                        });

                    } else {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Ingrese un número válido");
                    alert.showAndWait();
                }
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


    @FXML
    private void borrarFigurasClick() {
        GraphicsContext gc = DibujoCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, DibujoCanvas.getWidth(), DibujoCanvas.getHeight());
        inicioX = -1;
        inicioY = -1;
        figurasarreglo.clear();
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
        for (Figura figura : figurasarreglo) {
            if (figura instanceof Proceso) {
                Proceso proceso = (Proceso) figura;
                proceso.DibujarProceso_Denuevo(gc, figura.getX(), figura.getY());
                DibujarFlecha(proceso.getInicioFlechaX(), proceso.getInicioFlechaY(), proceso.getFinFlechaX(), proceso.getFinFlechaY());
            } else if (figura instanceof Decision) {
                Decision decision = (Decision) figura;
                decision.DibujarDecision_Denuevo(gc, gc, figura.getX(), figura.getY());
                DibujarFlecha(decision.getInicioFlechaX(), decision.getInicioFlechaY(), decision.getFinFlechaX(), decision.getFinFlechaY());
            } else if (figura instanceof EntradaSalida) {
                EntradaSalida entradaSalida = (EntradaSalida) figura;
                entradaSalida.Dibujar_Entrada_Salida_Denuevo(gc, figura.getX(), figura.getY());
                DibujarFlecha(entradaSalida.getInicioFlechaX(), entradaSalida.getInicioFlechaY(), entradaSalida.getFinFlechaX(), entradaSalida.getFinFlechaY());
            } else if (figura instanceof Documento) {
                Documento documento = (Documento) figura;
                documento.Dibujar_Documento_Denuevo(gc, figura.getX(), figura.getY());
                DibujarFlecha(documento.getInicioFlechaX(), documento.getInicioFlechaY(), documento.getFinFlechaX(), documento.getFinFlechaY());
            } else if (figura instanceof InicioFin) {
                InicioFin inicioFin = (InicioFin) figura;
                inicioFin.DibujarInicioFin_Denuevo(gc, figura.getX(), figura.getY());
                DibujarFlecha(inicioFin.getInicioFlechaX(), inicioFin.getInicioFlechaY(), inicioFin.getFinFlechaX(), inicioFin.getFinFlechaY());
            }
        }
    }

    @FXML
    private void borrarFiguraX() {
        int size = figurasarreglo.size();
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Borrar última figura");
        dialog.setHeaderText(null);
        dialog.setContentText("Número de posición a borrar (hay " + size + " elementos en la lista):");

        dialog.showAndWait().ifPresent(texto -> {
            try {
                int posicion = Integer.parseInt(texto);
                if (posicion > 0 && posicion <= size) { // Ajuste para que sea de 1 a size
                    // Ajuste de índice para que coincida con el índice de la lista (base 0)
                    int index = posicion -1 ;

                    // Guardar las coordenadas del objeto a eliminar
                    Figura figuraAEliminar = figurasarreglo.get(index);
                    double xEliminar = figuraAEliminar.getX();
                    double yEliminar = figuraAEliminar.getY();

                    // Eliminar el objeto en la posición especificada
                    figurasarreglo.remove(index);

                    // Desplazar los elementos hacia atrás y actualizar sus coordenadas
                    for (int i = index; i < figurasarreglo.size(); i++) {
                        Figura figuraActual = figurasarreglo.get(i);
                        System.out.println(figurasarreglo.get(i).getTexto());
                        // Guardar las coordenadas actuales de la figura
                        double xActual = figuraActual.getX();
                        double yActual = figuraActual.getY();

                        // Asignar las coordenadas de la figura anterior a la actual
                        figuraActual.setX(xEliminar);
                        figuraActual.setY(yEliminar);

                        // Actualizar las coordenadas para la siguiente iteración
                        xEliminar = xActual;
                        yEliminar = yActual;
                    }

                    // Redibujar las figuras después de la eliminación y desplazamiento
                    redibujarFigurasBorradas();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Por favor, ingresa un número que esté dentro del arreglo.");
                    alert.showAndWait();
                }
            } catch (NumberFormatException e) {
                // Maneja el caso en que el usuario no ingrese un número válido
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Por favor, ingresa un número válido.");
                alert.showAndWait();
            }
        });
        Figura ultimafigura = figurasarreglo.get(figurasarreglo.size() - 1);
    }


    private void redibujarFigurasBorradas() {
        GraphicsContext gc = DibujoCanvas.getGraphicsContext2D();
        GraphicsContext gc2 = DibujoCanvas.getGraphicsContext2D();
        // Limpiar el lienzo
        gc.clearRect(0, 0, DibujoCanvas.getWidth(), DibujoCanvas.getHeight());

        // Dibujar todas las figuras
        for (Figura figura : figurasarreglo) {
            if (figura instanceof Proceso) {
                Proceso proceso = (Proceso) figura;
                proceso.DibujarProceso_Denuevo(gc, figura.getX(), figura.getY());
            } else if (figura instanceof Decision) {
                Decision decision = (Decision) figura;
                decision.DibujarDecision_Denuevo(gc,gc2,figura.getX(), figura.getY());
            } else if (figura instanceof EntradaSalida) {
                EntradaSalida entradaSalida = (EntradaSalida) figura;
                entradaSalida.Dibujar_Entrada_Salida_Denuevo(gc, figura.getX(), figura.getY());
            } else if (figura instanceof Documento) {
                Documento documento = (Documento) figura;
                documento.Dibujar_Documento_Denuevo(gc, figura.getX(), figura.getY());
            } else if (figura instanceof InicioFin) {
                InicioFin inicioFin = (InicioFin) figura;
                inicioFin.DibujarInicioFin_Denuevo(gc, figura.getX(), figura.getY());
            }
        }

        // Dibujar las flechas después de dibujar todas las figuras
        for (int i = 0; i < figurasarreglo.size() - 1; i++) {
            Figura figuraActual = figurasarreglo.get(i);
            Figura figuraSiguiente = figurasarreglo.get(i + 1);

            // Actualizar las coordenadas de la flecha de figuraActual a figuraSiguiente
            figuraActual.setFinFlechaX(figuraSiguiente.getX());
            figuraActual.setFinFlechaY(figuraSiguiente.getY());
            figuraSiguiente.setInicioFlechaX(figuraActual.getX());
            figuraSiguiente.setInicioFlechaY(figuraActual.getY());

            DibujarFlecha(figuraActual.getInicioFlechaX(), figuraActual.getInicioFlechaY(),
                    figuraActual.getFinFlechaX(), figuraActual.getFinFlechaY());
        }
    }
    @FXML
    private void MostrarPseudocodigo() {
        int nivelIndentacion = 0;
        Stack<String> bloques = new Stack<>();
        boolean enSi = false;
        StringBuilder pseudocodigo = new StringBuilder();

        for (Figura figura : figurasarreglo) {
            // Verifica si se debe cerrar un bloque de "Si"
            if (enSi && !(figura instanceof Decision)) {
                nivelIndentacion--;
                pseudocodigo.append(generarIndentacion(nivelIndentacion)).append("SiNo\n");
                nivelIndentacion++;
                enSi = false;
            }

            // Verifica si un bloque "Mientras" ha sido cerrado
            if (MientrasCerrado && !(figura instanceof Mientras)) {
                while (!bloques.isEmpty() && "FinMientras".equals(bloques.peek())) {
                    nivelIndentacion--;
                    pseudocodigo.append(generarIndentacion(nivelIndentacion)).append(bloques.pop()).append("\n");
                }
                MientrasCerrado = false; // Reinicia el estado después de cerrar el bloque "Mientras"
            }

            if (figura instanceof Proceso) {
                pseudocodigo.append(generarIndentacion(nivelIndentacion)).append("Escribir: ").append(figura.getTexto()).append("\n");
            } else if (figura instanceof Decision) {
                if (enSi) {
                    nivelIndentacion--;
                    pseudocodigo.append(generarIndentacion(nivelIndentacion)).append("SiNo\n");
                    nivelIndentacion++;
                    enSi = false;
                } else {
                    pseudocodigo.append(generarIndentacion(nivelIndentacion)).append("Si ").append(figura.getTexto()).append(" Entonces\n");
                    nivelIndentacion++;
                    bloques.push("FinSi");
                    enSi = true;
                }
            } else if (figura instanceof EntradaSalida) {
                pseudocodigo.append(generarIndentacion(nivelIndentacion)).append("Leer: ").append(((EntradaSalida) figura).getTexto()).append("\n");
            } else if (figura instanceof Documento) {
                pseudocodigo.append(generarIndentacion(nivelIndentacion)).append("Leer: ").append(((Documento) figura).getTexto()).append("\n");
            } else if (figura instanceof Mientras) {
                pseudocodigo.append(generarIndentacion(nivelIndentacion)).append("Mientras ").append(figura.getTexto()).append(" Hacer\n");
                nivelIndentacion++;
                bloques.push("FinMientras");
            } else if (figura instanceof Repetir) {
                pseudocodigo.append(generarIndentacion(nivelIndentacion)).append("Repetir\n");
                nivelIndentacion++;
                bloques.push("Hasta Que " + figura.getTexto());
            } else if (figura instanceof InicioFin) {
                pseudocodigo.append(generarIndentacion(nivelIndentacion)).append("Algoritmo ").append(figura.getTexto()).append("\n");
                nivelIndentacion++;
                bloques.push("FinAlgoritmo");
            } else if (figura instanceof Para) {
                Para paraFigura = (Para) figura;
                pseudocodigo.append(generarIndentacion(nivelIndentacion)).append("Para ").append(paraFigura.getTexto())
                        .append(" <- ").append(paraFigura.getTexto2()).append(" hasta ").append(paraFigura.getTexto3())
                        .append(" con paso ").append(paraFigura.getTexto4()).append(" Hacer\n");
                nivelIndentacion++;
                bloques.push("FinPara");
            }
        }

        // Asegurarse de cerrar todos los bloques al final
        while (!bloques.isEmpty()) {
            nivelIndentacion--;
            pseudocodigo.append(generarIndentacion(nivelIndentacion)).append(bloques.pop()).append("\n");
        }

        mostrarPseudocodigoEnDialog(pseudocodigo.toString());
    }



    private String generarIndentacion(int nivel) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nivel; i++) {
            sb.append("\t"); // Usa tabulaciones para la indentación
        }
        return sb.toString();
    }

    private void mostrarPseudocodigoEnDialog(String pseudocodigo) {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Pseudocódigo");
        dialog.setHeaderText(null);

        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.getButtonTypes().addAll(ButtonType.CLOSE);

        TextArea textArea = new TextArea(pseudocodigo);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        VBox content = new VBox();
        content.getChildren().add(textArea);
        dialogPane.setContent(content);

        dialog.showAndWait();
    }

    public void CerrarMientras() {
        cerrar();
    }

    public void cerrar() {
        if (!figurasarreglo.isEmpty()) {
            // Recorre el arreglo de figuras al revés para encontrar el "Mientras" más reciente
            for (int i = figurasarreglo.size() - 1; i >= 0; i--) {
                Figura figura = figurasarreglo.get(i);
                if (figura instanceof Mientras) {
                    Mientras mientras = (Mientras) figura;
                    double xMientras = mientras.getX();
                    double yMientras = mientras.getY();

                    Figura ultimafigura = figurasarreglo.get(figurasarreglo.size() - 1);
                    double xUltima = ultimafigura.getX();
                    double yUltima = ultimafigura.getY();
                    double tamanoFlecha = 10.0;

                    GraphicsContext gc = DibujoCanvas.getGraphicsContext2D();
                    GraphicsContext gc2 = DibujoCanvas.getGraphicsContext2D();
                    gc.beginPath();

                    // Dibuja la flecha de ciclo V
                    gc.moveTo(xUltima + 50, yUltima + 50); // Ajusta las coordenadas según sea necesario
                    gc.lineTo(xUltima + 50, yUltima + 100); // Ajusta las coordenadas según sea necesario
                    gc.moveTo(xUltima + 50, yUltima + 100);
                    gc.lineTo(xUltima - 120, yUltima + 100);
                    gc.moveTo(xUltima - 120, yUltima + 100);
                    gc.lineTo(xMientras - 120, yMientras - 50);
                    gc.moveTo(xMientras - 120, yMientras - 50);
                    gc.lineTo(xMientras, yMientras - 50);
                    gc2.moveTo(xMientras + 15, yMientras + 110);
                    gc2.strokeText("V", xMientras + 15, yMientras + 110);

                    // Dibuja la punta de la flecha
                    gc.stroke();
                    gc.strokeLine(xMientras, yMientras - 50, xMientras - tamanoFlecha * Math.cos(Math.PI / 6), yMientras - 50 + tamanoFlecha * Math.sin(Math.PI / 6));
                    gc.strokeLine(xMientras, yMientras - 50, xMientras - tamanoFlecha * Math.cos(Math.PI / 6), yMientras - 50 - tamanoFlecha * Math.sin(Math.PI / 6));

                    // Dibuja la flecha de ciclo F
                    gc.moveTo(xMientras + 120, yMientras + 50); // Ajusta las coordenadas según sea necesario
                    gc.lineTo(xMientras + 120, yUltima + 170); // Ajusta las coordenadas según sea necesario
                    gc.moveTo(xMientras + 120, yUltima + 170);
                    gc.lineTo(xUltima, yUltima + 170);

                    gc.closePath();
                    gc.stroke();

                    // Actualiza las coordenadas de inicio
                    inicioX = xUltima;
                    inicioY = yUltima + 170;
                    MientrasCerrado = true;

                    break; // Solo queremos cerrar el primer "Mientras" encontrado desde el final
                }
            }
        }
    }


    public void BotonListo() {
        for (Figura figura : figurasarreglo) {
            if (figura instanceof Decision) {
                Decision decision = (Decision) figura;
                double xDecision = decision.getX();
                double yDecision = decision.getY();

                //Guarda en la pila las coordenadas de la figura antes de cambiar
                decisionStack.push(new double[]{inicioX, inicioY});

                inicioX = xDecision + 150;
                inicioY = yDecision + 50;
            }
        }
    }

    public void CerrarRepetir() {
        int size = figurasarreglo.size();
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Posición de cierre");
        dialog.setHeaderText(null);
        dialog.setContentText("Número de posición(hay " + (size - 1) + " elementos en la lista):");

        dialog.showAndWait().ifPresent(texto ->{
            try {
                int posicion = Integer.parseInt(texto);
                if(posicion > 0 && posicion <= size){
                    int index = posicion - 1;

                    Figura figuraCierre = figurasarreglo.get(index);
                    double xCerrar = figuraCierre.getX();
                    double yCerrar = figuraCierre.getY();
                    if(!figurasarreglo.isEmpty()){
                        for(int i = figurasarreglo.size() - 1; i >= 0; i--){
                            Figura figura = figurasarreglo.get(i);
                            if(figura instanceof Repetir){
                                Repetir repetir = (Repetir) figura;
                                double xRepetir = repetir.getX();
                                double yRepetir = repetir.getY();
                                GraphicsContext gc = DibujoCanvas.getGraphicsContext2D();
                                GraphicsContext gc2 = DibujoCanvas.getGraphicsContext2D();
                                double tamanoFlecha = 10.0;

                                gc.beginPath();

                                //Dibujo Linea lado F de ciclo Mientras
                                gc.moveTo(xRepetir - 100, yCerrar - 30);
                                gc.lineTo(xRepetir - 100, yRepetir + 50);
                                gc.moveTo(xRepetir - 100, yCerrar - 30);
                                gc.lineTo(xRepetir, yCerrar - 30);
                                gc2.moveTo(xRepetir - 90, yRepetir + 40);
                                gc2.strokeText("F", xRepetir - 90, yRepetir + 40, 10);
                                gc2.moveTo(xRepetir + 10, yRepetir + 120);
                                gc2.strokeText("V", xRepetir + 10, yRepetir + 120, 10);


                                //Dibujo punta de flecha
                                gc.strokeLine(xRepetir, yCerrar - 30, xRepetir - tamanoFlecha * Math.cos(Math.PI / 6), yCerrar - 30 + tamanoFlecha * Math.sin(Math.PI / 6));
                                gc.strokeLine(xRepetir, yCerrar - 30, xRepetir - tamanoFlecha * Math.cos(Math.PI / 6), yCerrar - 30 - tamanoFlecha * Math.sin(Math.PI / 6));


                                gc.closePath();
                                gc.stroke();
                            }
                        }
                    }
                }
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Ingrese un número valido");
                alert.showAndWait();
            }
        });
    }


    public void CerrarCondicional() {
        if(!decisionStack.isEmpty()){
            double[] coordenadaAnterior = decisionStack.pop();
            inicioX = coordenadaAnterior[0];
            inicioY = coordenadaAnterior[1];

        }
    }


    @FXML
    private void handleButton1Click() {
        figura = "boton6";
    }

    @FXML
    private void handleButton2Click() {
        figura = "boton1";
    }

    @FXML
    private void handleButton3Click() {
        figura = "boton2";
    }

    @FXML
    private void handleButton4Click() {
        figura = "boton3";
    }

    @FXML
    private void handleButton5Click() {
        figura = "boton4";
    }

    @FXML
    private void handleButton6Click() {
        figura = "boton5";
    }

    @FXML
    public void handleButton7Click() {
        figura = "boton7";
    }

    public void handleButton8Click() {
        figura = "boton8";
    }
}