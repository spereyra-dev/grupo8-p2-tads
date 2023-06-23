package uy.edu.um.prog2.ad;


import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    private static final CsvUtils csvUtils = new CsvUtils();
    public static void main(String[] args) {
        Runtime rt = Runtime.getRuntime();
        long total_mem = rt.totalMemory();
        long loadStartTime = System.currentTimeMillis();
        csvUtils.getCsvInfo();
        measureMemoryAndTime(rt, total_mem, loadStartTime);
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
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

            System.out.print("Ingrese el número de opción: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
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
        System.out.println("Generando Reporte 1...");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el año en formato yyyy:");
        int year = scanner.nextInt();
        System.out.println("Ingrese el mes en formato mm:");
        int month = scanner.nextInt();

        Runtime rt = Runtime.getRuntime();
        long total_mem = rt.totalMemory();
        long loadStartTime = System.currentTimeMillis();
        csvUtils.getTopTenPilots(month, year);
        measureMemoryAndTime(rt, total_mem, loadStartTime);
    }

    private static void generarReporte2() {
        System.out.println("Generando Reporte 2...");
        Runtime rt = Runtime.getRuntime();
        long total_mem = rt.totalMemory();
        long loadStartTime = System.currentTimeMillis();
        csvUtils.top15UsersWithMoreTweets();
        measureMemoryAndTime(rt, total_mem, loadStartTime);
    }


    private static void generarReporte3() {
        System.out.println("Generando Reporte 3...");
        System.out.println("Ingrese la fecha en formato yyyy-mm-dd:");
        Scanner scanner = new Scanner(System.in);
        String dateString = scanner.nextLine();
        LocalDate date = LocalDate.parse(dateString);
        Runtime rt = Runtime.getRuntime();
        long total_mem = rt.totalMemory();
        long loadStartTime = System.currentTimeMillis();
        csvUtils.differentHashTagsForADay(date);
        measureMemoryAndTime(rt, total_mem, loadStartTime);
    }

    private static void generarReporte4() {
        System.out.println("Generando Reporte 4...");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese la fecha en formato yyyy-mm-dd:");
        String dateString = scanner.nextLine();
        LocalDate date = LocalDate.parse(dateString);
        Runtime rt = Runtime.getRuntime();
        long total_mem = rt.totalMemory();
        long loadStartTime = System.currentTimeMillis();
        csvUtils.mostUsedHashTagForADay(date);
        measureMemoryAndTime(rt, total_mem, loadStartTime);
    }

    private static void generarReporte5() {
        System.out.println("Generando Reporte 5...");
        Runtime rt = Runtime.getRuntime();
        long total_mem = rt.totalMemory();
        long loadStartTime = System.currentTimeMillis();
        csvUtils.top7UsersWithMoreFavourites();
        measureMemoryAndTime(rt, total_mem, loadStartTime);
    }

    private static void generarReporte6() {
        System.out.println("Generando Reporte 6...");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese la palabra o frase a buscar: ");
        String frase = scanner.nextLine();
        Runtime rt = Runtime.getRuntime();
        long total_mem = rt.totalMemory();
        long loadStartTime = System.currentTimeMillis();
        csvUtils.tweetsWithSpecificWordOrPhrase(frase);
        measureMemoryAndTime(rt, total_mem, loadStartTime);
    }

    private static void measureMemoryAndTime(Runtime rt, long total_mem, long loadStartTime) {
        long free_mem = rt.freeMemory();
        long used_mem = total_mem - free_mem;
        long loadEndTime = System.currentTimeMillis();
        long loadTime = loadEndTime - loadStartTime;
        System.out.println("Tiempo de carga: " + loadTime + " ms");
        System.out.println("Memoria usada: " + used_mem / 1000000 + " megabytes");
    }

}



