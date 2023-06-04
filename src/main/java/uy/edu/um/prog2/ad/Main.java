package uy.edu.um.prog2.ad;


import static uy.edu.um.prog2.ad.CsvUtils.differentHashTagsForADay;
import static uy.edu.um.prog2.ad.CsvUtils.top15UsersWithMoreTweets;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        final var csvInfo = CsvUtils.getCsvInfo();
        CsvUtils.getCsvInfo();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            // Mostrar el menú
            System.out.println("==== Menú ====");
            System.out.println("1. Reporte 1");
            System.out.println("2. Reporte 2");
            System.out.println("3. Reporte 3");
            System.out.println("4. Reporte 4");
            System.out.println("5. Reporte 5");
            System.out.println("0. Salir");
            System.out.println("==============");

            // Leer la opción del usuario
            System.out.print("Ingrese el número de opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            // Realizar acción según la opción seleccionada
            switch (opcion) {
                case 1:
                    generarReporte1();
                    break;
                case 2:
                    generarReporte2();
                    break;
                case 3:
                    generarReporte3();
                    break;
                case 4:
                    generarReporte4();
                    break;
                case 5:
                    generarReporte5();
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, ingrese una opción válida.");
            }
        }

        System.out.println("¡Hasta luego!");
    }

    private static void generarReporte1() {
        // Código para generar el Reporte 1
        System.out.println("Generando Reporte 1...");
        top15UsersWithMoreTweets();
    }

    private static void generarReporte2() {
        // Código para generar el Reporte 2
        System.out.println("Generando Reporte 2...");
        LocalDate date = LocalDate.of(2021, 12, 12);
        differentHashTagsForADay(date);
    }


    private static void generarReporte3() {
        // Código para generar el Reporte 3
        System.out.println("Generando Reporte 3...");
    }

    private static void generarReporte4() {
        // Código para generar el Reporte 4
        System.out.println("Generando Reporte 4...");
    }

    private static void generarReporte5() {
        // Código para generar el Reporte 5
        System.out.println("Generando Reporte 5...");
    }

}



