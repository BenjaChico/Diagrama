package com.example.proyectodeprogramacion;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;

import java.util.Scanner;

public class EntradaSalida {

    public void Dibujar_Entrada_Salida(GraphicsContext gc, double x, double y) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Texto Entrada/Salida: ");
        String texto = scanner.nextLine();
        int tamañoFuente = 20;

        gc.beginPath();
        gc.moveTo(x, y);
        gc.lineTo(x + 100, y);
        gc.lineTo(x + 100 - (50) / 4, y + 50);
        gc.lineTo(x - (50) / 4, y + 50);
        gc.closePath();

        gc.setFont(new Font(tamañoFuente));
        gc.strokeText(texto, x + 15, y + 30);

        gc.stroke();
    }

}
