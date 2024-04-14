package com.example.proyectodeprogramacion;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;

import java.util.Scanner;

public class Documento {

    public void Dibujar_Documento(GraphicsContext gc, double x, double y) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Texto Entrada/Salida: ");
        String texto = scanner.nextLine();

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
    }

}
