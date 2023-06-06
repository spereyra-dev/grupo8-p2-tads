package uy.edu.um.prog2.ad;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import uy.edu.um.prog2.ad.tads.LinkedList.LinkedList.LinkedList;
import uy.edu.um.prog2.ad.tads.LinkedList.ListaConGenerics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 * CSV utils for creating a validating csv files
 *
 * @author Santiago Pereyra
 */
public class CsvUtils {

    private static final String FILE_ERROR_FORMAT = "Wrong format in the file";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final String csvFile = "src/main/resources/datasetSanti.csv";
    private static final String driversFile = "src/main/resources/drivers.txt";
    private static final ListaConGenerics<User> userLinkedList = new LinkedList<>();
    private static final ListaConGenerics<Tweet> tweetLinkedList = new LinkedList<>();
    private static final ListaConGenerics<HashTag> hashTagLinkedList = new LinkedList<>();
    private static final ListaConGenerics<String> driversLinkedLit = new LinkedList<>();

    public static void getDriversFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(driversFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                driversLinkedLit.add(line.toLowerCase());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    Listar los 10 pilotos activos en la temporada 2023 más mencionados en los tweets
    en un mes (este mes será ingresado como 2 parámetros separados, mes y año, por
    consola). Este listado debe incluir el nombre de los pilotos y la cantidad de
    ocurrencias para cada uno de manera ordenada. Se espera que esta operación sea
    de orden n en notación Big O
     */
    public static void getTopTenPilots(int month, int year) {
        getDriversFromFile();
        for (int i = 0; i < tweetLinkedList.size(); i++) {
            if (tweetLinkedList.get(i).getDate().getMonthValue() == month && tweetLinkedList.get(i).getDate().getYear() == year) {
                for (int j = 0; j < driversLinkedLit.size(); j++) {
                    if (tweetLinkedList.get(i).getContent().contains(driversLinkedLit.get(j))) {
                        //debería usar hash para agregar la cantidad de ocurrencias
                        //el key debería ser el nombre del piloto y el value la cantidad de ocurrencias
                    }
                }
            }
        }
    }


    public static void getCsvInfo() {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile));
             CSVParser csvParser = new CSVParser(br, CSVFormat.DEFAULT)) {
            csvParser.iterator().next();
            int i = -1;
            for (CSVRecord csvRecord : csvParser) {
                User user = new User();
                Tweet tweet = new Tweet();
                HashTag hashTagValue = new HashTag();

                try {

                    var hashTagsFromCsv = csvRecord.get(11).toLowerCase().replaceAll("[\\[\\]']", "").split(",");
                    hashTagValue.setText(Arrays.toString(hashTagsFromCsv));
                    hashTagValue.setId(0L);


                    LocalDateTime date = LocalDateTime.parse(csvRecord.get(9), formatter);
                    tweet.setDate(date);
                    tweet.setContent(csvRecord.get(10).toLowerCase());

                    //falta el 11 son los hashtags
                    tweet.getHashTags().add(hashTagValue);
                    tweet.setId(Long.parseLong(csvRecord.get(0)));
                    tweet.setSource(csvRecord.get(12));
                    tweet.setIsRetweet(Boolean.parseBoolean(csvRecord.get(13)));


                    user.setId(0L); //todo: fix this
                    user.setName(csvRecord.get(1));
                    user.setUserLocation(csvRecord.get(2));
                    user.setUserDescription(csvRecord.get(3).toLowerCase());
                    LocalDateTime userCreated = LocalDateTime.parse(csvRecord.get(4), formatter);
                    user.setUserCreated(userCreated);
                    user.setUserFollowers(Double.parseDouble(csvRecord.get(5)));
                    user.setUserFriends(Double.parseDouble(csvRecord.get(6)));
                    user.setUserFavourites(Double.parseDouble(csvRecord.get(7)));
                    user.setIsVerified(Boolean.parseBoolean(csvRecord.get(8)));
                    user.getTweets().add(tweet);
                    if (!userLinkedList.contains(user)) {
                        hashTagLinkedList.add(hashTagValue);
                        tweetLinkedList.add(tweet);
                        userLinkedList.add(user);
                        user.setTweetCount(1); // Actualizar la cantidad de tweets del usuario
                        i++;
                    } else {
                        userLinkedList.get(i).getTweets().add(tweet);
                        userLinkedList.get(i).incrementTweetCount(); // Incrementar la cantidad de tweets del usuario existente
                    }

                } catch (Exception ignored) {
                }
            }
        } catch (IOException e) {
            throw new FileNotValidException(FILE_ERROR_FORMAT, e);
        }
    }

    /*
    Top 15 usuarios con más tweets. Este listado debe incluir el nombre de usuario, la
    cantidad de tweets, y si el usuario está verificado o no, ordenado por cantidad de
    tweets en orden descendente. Se espera que esta operación sea de orden n en
    notación Big O
     */
    public static void top15UsersWithMoreTweets() {
        // Ordenar la lista de usuarios por cantidad de tweets en orden descendente
        userLinkedList.sort((user1, user2) -> Integer.compare(user2.getTweetCount(), user1.getTweetCount()));

        // Obtener el límite de usuarios a mostrar (máximo 15)
        int limit = Math.min(15, userLinkedList.size());

        // Mostrar el top 15 de usuarios con más tweets
        for (int i = 0; i < limit; i++) {
            User user = userLinkedList.get(i);
            String verifiedStatus = user.getIsVerified() ? "Verificado" : "No verificado";
            System.out.println(user.getName() + " - Tweets: " + user.getTweetCount() + " - " + verifiedStatus);
        }
    }

    /*
    Cantidad de hashtags distintos para un día dado. El día será ingresado en el formato
    YYYY-MM-DD.
     */
    public static void differentHashTagsForADay(LocalDate date) {
        var differentHashTags = new LinkedList<String>();
        for (int i = 0; i < tweetLinkedList.size(); i++) {
            Tweet tweet = tweetLinkedList.get(i);
            var tweetDate = tweet.getDate().toLocalDate();
            HashTag hashTagObj = hashTagLinkedList.get(i);
            String[] hashTagsSplit = hashTagObj.getText().replaceAll("[\\[\\]']", "").split(",");
            if (tweetDate.equals(date)) {
                for (String hashTag : hashTagsSplit) {
                    if (!differentHashTags.contains(hashTag)) {
                        differentHashTags.add(hashTag);
                    }
                }

            }
        }
        System.out.println("Cantidad de hashtags distintos para el día " + date + ": " + differentHashTags.size());
    }


    /*
    Hashtag más usado para un día dado, sin tener en cuenta #f1. El día será ingresado
    en el formato YYYY-MM-DD
     */
    public static void mostUsedHashTagForADay(LocalDate date) {
        for (int i = 0; i < tweetLinkedList.size(); i++) {
            Tweet tweet = tweetLinkedList.get(i);
            var count = 0;
            var tweetDate = tweet.getDate().toLocalDate();
            if (tweetDate.equals(date)) {
                if (tweet.getHashTags() != null) {
                    //todo: implementar hashmap
                    //se puede hacer un hashmap con el hashtag y la cantidad de veces que aparece
                }
            }
            System.out.println(count);
        }
    }

    /*
    Top 7 cuentas con más favoritos. Para este listado se deberá retornar el nombre del
    usuario, junto con la cantidad de favoritos.
     */
    public static void top7UsersWithMoreFavourites() {
        userLinkedList.sort((o1, o2) -> o2.getUserFavourites().compareTo(o1.getUserFavourites()));
        int limit = Math.min(7, userLinkedList.size());
        for (int i = 0; i < limit; i++) {
            System.out.println(userLinkedList.get(i).getName() + " " + userLinkedList.get(i).getUserFavourites());
        }
    }

    /*
    Cantidad de tweets con una palabra o frase específica (que será ingresado como
    parámetro).
     */
    public static void tweetsWithSpecificWordOrPhrase(String wordOrPhrase) {
        var count = 0;
        for (int i = 0; i < tweetLinkedList.size(); i++) {
            Tweet tweet = tweetLinkedList.get(i);
            if (tweet.getContent().contains(wordOrPhrase.toLowerCase())) {
                count++;
            }
        }
        System.out.println(count);
    }

}
