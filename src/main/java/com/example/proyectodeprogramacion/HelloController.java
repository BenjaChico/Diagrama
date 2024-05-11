package com.example.proyectodeprogramacion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
import java.util.Optional;
import java.util.ArrayList;
import javafx.scene.control.TextInputDialog;

public class HelloController {
    private final ArrayList<Figura> figurasarreglo = new ArrayList<>();

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
                            inicioX = x + 50;
                            inicioY = y;
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
                            //Lado Verdadero
                            /*if(!ListoPresionado){
                                inicioX = x - 150;
                                inicioY = y + 50;
                            }*/
                            //Lado Falso
                            /*else{
                                inicioX = x + 150;
                                inicioY = y + 50;
                            }*/
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
                            mientras.DibujarMientras(gc, x, y);
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
        private ArrayList Verdadero = new ArrayList();
        private ArrayList Falso = new ArrayList();


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


        public void agregarVerdadero(Figura figura) {
            Verdadero.add(figura);
        }

        public void agregarFalso(Figura figura) {
            Falso.add(figura);
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

        public void DibujarMientras(GraphicsContext gc, double x, double y) {
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
                //Flecha izquierda
                //gc.moveTo(x - 70, y + 50);
                //gc.lineTo(x - 150, y + 50);
                //Flecha derecha
                gc.moveTo(x + 70, y + 50);
                gc.lineTo(x + 100, y + 50);

                gc.setFont(new Font(tamanotexto + 5));
                gc.strokeText(texto, x - (texto.length() * tamanotexto / 4) - 10, y + 55);
                gc.stroke();
            });
        }

        public void DibujarCierre(GraphicsContext gc, double x, double y) {
            gc.beginPath();
            gc.lineTo(x, y - 40);
            gc.lineTo(x + 100, y);
            gc.lineTo(x + 100, y - 100);
            gc.lineTo(x, y + 40);
            gc.closePath();
            gc.stroke(); // Aquí se traza la figura
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
                //Flecha Derecha
                //gc.moveTo(x + 70, y + 50);
                //gc.lineTo(x + 150, y + 50);

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
    public void MostrarPseudocodigo() {
        for (Figura figura : figurasarreglo) {
            System.out.println(figura.generarPseudocodigo());
        }
    }


    public void CerrarMientras() {
        cerrar();
    }

    public void cerrar() {
        GraphicsContext gc = DibujoCanvas.getGraphicsContext2D();
        Figura ultimafigura = figurasarreglo.get(figurasarreglo.size() - 1);
        double xUltima = ultimafigura.getX();
        double yUltima = ultimafigura.getY();
        gc.beginPath();
        //FLECHA DE CICLO V
        gc.moveTo(xUltima, yUltima + 100);
        gc.lineTo(xUltima, yUltima + 140);
        gc.moveTo(xUltima, yUltima + 140); // Mover el punto de inicio de la nueva línea
        gc.lineTo(xUltima - 130, yUltima + 140); // Agregar línea hacia la izquierda
        gc.moveTo(xUltima - 130, yUltima + 140);
        gc.lineTo(xUltima - 130, yUltima - 50);
        gc.moveTo(xUltima - 130, yUltima - 50);
        gc.lineTo(xUltima, yUltima - 50);
        //FLECHA DE CICLO F
        gc.moveTo(xUltima + 100, yUltima + 50);
        gc.lineTo(xUltima + 100, yUltima + 200);
        gc.moveTo(xUltima + 100, yUltima + 200);
        gc.lineTo(xUltima, yUltima + 200);
        inicioX = xUltima;
        inicioY = yUltima + 200;

        gc.closePath();
        gc.stroke(); // Aquí se traza la figura
    }


    public void CerrarRepetir() {
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

    public void BotonListo() {
        for (Figura figura : figurasarreglo) {
            if (figura instanceof Decision) {
                Decision decision = (Decision) figura;
                double xDecision = decision.getX();
                double yDecision = decision.getY();
                inicioX = xDecision + 150;
                inicioY = yDecision + 50;
            }
        }
    }
}