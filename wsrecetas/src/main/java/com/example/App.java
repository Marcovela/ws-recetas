package com.example;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class App {
    public static void main(String[] args) {
        String url = "https://www.allrecipes.com/";
        try {
            Document doc = Jsoup.connect(url).get();
            for (int cont = 21; cont < 27; cont++) {
                Elements recetas = doc.select("a#mntl-card-list-items_" + cont + "-0");
                for (Element receta : recetas) {
                    String titulo = receta.select("span.card__title").text();
                    String precio = receta.select("div.mm-recipes-card-meta__rating-count-number.mntl-recipe-card-meta__rating-count-number").text();
                    System.out.println(titulo + " - " + precio);
                    System.out.println("====================================");

                    if (precio != null && !precio.isEmpty()) {
                        String pr = "";
                        if (precio.length() >= 7) {
                            pr = precio.substring(0, precio.length() - 7); // omite los últimos 7 caracteres
                            try {
                                Double actual_precio = Double.parseDouble(pr); // convierto string en double
                                if (actual_precio < 11) {
                                    System.out.println(titulo + " - " + precio);
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Precio no válido para: " + titulo);
                            }
                        } else {
                            System.out.println("Precio no válido para: " + titulo);
                        }
                    } else {
                        System.out.println("Precio no válido para: " + titulo);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
