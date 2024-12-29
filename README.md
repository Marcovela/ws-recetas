# ws-recetas
# Web Scraper de Recetas

Este proyecto es una aplicación Java que utiliza la biblioteca Jsoup para realizar web scraping en el sitio web de AllRecipes. La aplicación extrae el título y el precio de las recetas y los muestra en la consola.

## Requisitos

- Java 8 o superior
- [Jsoup](https://jsoup.org/) biblioteca para realizar web scraping

## Instalación

1. Clona este repositorio en tu máquina local:
    ```sh
    git clone https://github.com/tu-usuario/tu-repositorio.git
    ```

2. Navega al directorio del proyecto:
    ```sh
    cd tu-repositorio
    ```

3. Asegúrate de tener la biblioteca Jsoup en tu classpath. Puedes descargarla desde [aquí](https://jsoup.org/download) y agregarla manualmente, o usar un sistema de construcción como Maven o Gradle.

## Uso

1. Compila el archivo [App.java](http://_vscodecontentref_/0):
    ```sh
    javac -cp .;jsoup-1.14.3.jar com/example/App.java
    ```

2. Ejecuta la aplicación:
    ```sh
    java -cp .;jsoup-1.14.3.jar com.example.App
    ```

## Código

El código principal se encuentra en el archivo [App.java](http://_vscodecontentref_/1) y realiza las siguientes tareas:

- Conecta al sitio web de AllRecipes.
- Extrae el título y el precio de las recetas.
- Muestra el título y el precio en la consola.

```java
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
