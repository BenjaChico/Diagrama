package com.example.proyectodeprogramacion;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;

import java.util.Scanner;

public class Proceso{

    public void DibujarProceso(GraphicsContext gc, double x, double y) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Texto Proceso: ");
        String texto = scanner.nextLine();

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

}
