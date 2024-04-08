package com.example.proyectodeprogramacion;

import com.dlsc.formsfx.model.structure.Element;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Decision{

    public void DibujarDecision(GraphicsContext gc, GraphicsContext gc2, double x, double y) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Texto Decisión: ");
        String texto = scanner.nextLine();

        double tamanotexto = gc.getFont().getSize();
        while(tamanotexto * texto.length() > 140){
            tamanotexto -= 1;
        }

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

}
