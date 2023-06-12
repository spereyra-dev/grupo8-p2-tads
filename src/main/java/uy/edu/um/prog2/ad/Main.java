package uy.edu.um.prog2.ad;


import static uy.edu.um.prog2.ad.CsvUtils.differentHashTagsForADay;
import static uy.edu.um.prog2.ad.CsvUtils.getTopTenPilots;
import static uy.edu.um.prog2.ad.CsvUtils.mostUsedHashTagForADay;
import static uy.edu.um.prog2.ad.CsvUtils.top15UsersWithMoreTweets;
import static uy.edu.um.prog2.ad.CsvUtils.top7UsersWithMoreFavourites;
import static uy.edu.um.prog2.ad.CsvUtils.tweetsWithSpecificWordOrPhrase;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        long loadStartTime = System.currentTimeMillis();
        CsvUtils.getCsvInfo();
        long loadEndTime = System.currentTimeMillis();
        long loadTime = loadEndTime - loadStartTime;
        System.out.println("Tiempo de carga csv: " + loadTime + " ms");
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            // Mostrar el menú
            System.out.println("==== Menú ====");
            System.out.println("1. Reporte 10 pilotos activos en la temporada 2023 más mencionados en los tweets\n" +
                    "en un mes");
            System.out.println("2. Reporte top 15 usuarios con más tweets.");
            System.out.println("3. Reporte cantidad de hashtags distintos para un día dado");
            System.out.println("4. Reporte hashtag más usado para un día dado");
            System.out.println("5. Reporte top 7 cuentas con más favoritos");
            System.out.println("6. Reporte cantidad de tweets con una palabra o frase específicos ");
            System.out.println("0. Salir");
            System.out.println("==============");

            // Leer la opción del usuario
            System.out.print("Ingrese el número de opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            // Realizar acción según la opción seleccionada
            switch (opcion) {
                case 1 -> generarReporte1();
                case 2 -> generarReporte2();
                case 3 -> generarReporte3();
                case 4 -> generarReporte4();
                case 5 -> generarReporte5();
                case 6 -> generarReporte6();
                case 0 -> exit = true;
                default -> System.out.println("Opción inválida. Por favor, ingrese una opción válida.");
            }
        }

        System.out.println("¡Hasta luego!");
    }

    private static void generarReporte1() {
        // Código para generar el Reporte 1
        System.out.println("Generando Reporte 1...");
        int month = 11;
        int year = 2021;

        long loadStartTime = System.currentTimeMillis();
        getTopTenPilots(month, year);
        long loadEndTime = System.currentTimeMillis();
        long loadTime = loadEndTime - loadStartTime;
        System.out.println("Tiempo de carga: " + loadTime + " ms");
    }

    private static void generarReporte2() {
        // Código para generar el Reporte 2
        System.out.println("Generando Reporte 2...");
        long loadStartTime = System.currentTimeMillis();
        top15UsersWithMoreTweets();
        long loadEndTime = System.currentTimeMillis();
        long loadTime = loadEndTime - loadStartTime;
        System.out.println("Tiempo de carga: " + loadTime + " ms");
    }


    private static void generarReporte3() {
        // Código para generar el Reporte 3
        System.out.println("Generando Reporte 3...");
        LocalDate date = LocalDate.of(2021, 12, 12);
        long loadStartTime = System.currentTimeMillis();
        differentHashTagsForADay(date);
        long loadEndTime = System.currentTimeMillis();
        long loadTime = loadEndTime - loadStartTime;
        System.out.println("Tiempo de carga: " + loadTime + " ms");
    }

    private static void generarReporte4() {
        // Código para generar el Reporte 4
        System.out.println("Generando Reporte 4...");
        LocalDate date = LocalDate.of(2021, 12, 12);
        long loadStartTime = System.currentTimeMillis();
        mostUsedHashTagForADay(date);
        long loadEndTime = System.currentTimeMillis();
        long loadTime = loadEndTime - loadStartTime;
        System.out.println("Tiempo de carga: " + loadTime + " ms");
    }

    private static void generarReporte5() {
        // Código para generar el Reporte 5
        System.out.println("Generando Reporte 5...");
        long loadStartTime = System.currentTimeMillis();
        top7UsersWithMoreFavourites();
        long loadEndTime = System.currentTimeMillis();
        long loadTime = loadEndTime - loadStartTime;
        System.out.println("Tiempo de carga: " + loadTime + " ms");
    }

    private static void generarReporte6() {
        // Código para generar el Reporte 6
        System.out.println("Generando Reporte 6...");
        String frase = "MexicoGP";
        long loadStartTime = System.currentTimeMillis();
        tweetsWithSpecificWordOrPhrase(frase);
        long loadEndTime = System.currentTimeMillis();
        long loadTime = loadEndTime - loadStartTime;
        System.out.println("Tiempo de carga: " + loadTime + " ms");
    }

}



