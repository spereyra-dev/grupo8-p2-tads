package uy.edu.um.prog2.ad;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import uy.edu.um.prog2.ad.entities.Driver;
import uy.edu.um.prog2.ad.entities.HashTag;
import uy.edu.um.prog2.ad.entities.Tweet;
import uy.edu.um.prog2.ad.entities.User;
import uy.edu.um.prog2.ad.exception.FileNotValidException;
import uy.edu.um.prog2.ad.tads.hash.table.MyHashTable;
import uy.edu.um.prog2.ad.tads.heap.array_heap.MyHeapImp;
import uy.edu.um.prog2.ad.tads.linked_list.ListaConGenerics;
import uy.edu.um.prog2.ad.tads.linked_list.simple.LinkedList;

/**
 * CSV utils for creating a validating csv files
 *
 * @author Santiago Pereyra
 */
public class CsvUtils {
    private static final String driversFile = "src/main/resources/drivers.txt";
    private static final String FILE_ERROR_FORMAT = "Wrong format in the file";
    private static final DateTimeFormatter FORMATTER_1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter FORMATTER_2 = DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm:ss");
    private final ListaConGenerics<User> userLinkedList = new LinkedList<>();
    private final ListaConGenerics<Tweet> tweetLinkedList = new LinkedList<>();
    private final ListaConGenerics<HashTag> hashTagLinkedList = new LinkedList<>();
    private final String csvFile;

    public CsvUtils(boolean isTest) {
        if (isTest) {
            csvFile = "src/main/resources/datasetSanti.csv";
        } else {
            csvFile = "src/main/resources/f1_dataset.csv";
        }
    }

    public CsvUtils() {
        this(false);
    }

    public ListaConGenerics<User> getUserLinkedList() {
        return userLinkedList;
    }

    public ListaConGenerics<Tweet> getTweetLinkedList() {
        return tweetLinkedList;
    }

    public ListaConGenerics<HashTag> getHashTagLinkedList() {
        return hashTagLinkedList;
    }

    public ListaConGenerics<Driver> getDriversFromFile() {
        ListaConGenerics<Driver> driversLinkedList = new LinkedList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(driversFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                driversLinkedList.add(new Driver(line.toLowerCase()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return driversLinkedList;
    }

    public void getCsvInfo() {
        MyHashTable<String, User> userMap = new MyHashTable<>();
        MyHashTable<String, HashTag> hashtagMap = new MyHashTable<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {
            csvParser.iterator().next();
            var userIdGenerated = 1L;
            var hashTagIdGenerated = 1L;
            for (CSVRecord csvRecord : csvParser) {
                Tweet tweet = new Tweet();
                try {
                    var userName = csvRecord.get(1);
                    var userInMap = userMap.get(userName);

                    LocalDateTime date = parseDateTime(csvRecord.get(9));
                    tweet.setDate(date);
                    tweet.setContent(csvRecord.get(10).toLowerCase());
                    tweet.setId(Long.parseLong(csvRecord.get(0)));
                    tweet.setSource(csvRecord.get(12));
                    tweet.setIsRetweet(Boolean.parseBoolean(csvRecord.get(13)));
                    String hashTags = csvRecord.get(11).toLowerCase();
                    String[] hashTagSplited = hashTags.replaceAll("[\\[\\]' ]", "").split(",");
                    for (String hashTagName : hashTagSplited) {
                        HashTag hashTag = new HashTag(hashTagIdGenerated, hashTagName);
                        if (hashtagMap.get(hashTagName) == null) {
                            hashtagMap.put(hashTagName, hashTag);
                            hashTagLinkedList.add(hashTag);
                            hashTagIdGenerated++;
                        }
                        tweet.getHashTags().add(hashTag);
                    }
                    tweetLinkedList.add(tweet);
                    if (userInMap != null) {
                        userInMap.getTweets().add(tweet);
                        userInMap.incrementTweetCount();
                        if (date.isAfter(userInMap.getLastTweet())) {
                            userInMap.setLastTweet(date);
                            userInMap.setIsVerified(Boolean.parseBoolean(csvRecord.get(
                                    8))); // Actualizar el estado verificado si se encuentra un tweet más reciente
                        }
                    } else {
                        User user = new User();
                        user.setName(userName);
                        user.setId(userIdGenerated);
                        user.setUserFavourites(Double.parseDouble(csvRecord.get(7)));
                        user.setIsVerified(Boolean.parseBoolean(csvRecord.get(8)));
                        user.getTweets().add(tweet);
                        user.incrementTweetCount();
                        user.setLastTweet(date);
                        userLinkedList.add(user);
                        userMap.put(userName, user);
                        userIdGenerated++;
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
    public void top15UsersWithMoreTweets() {
        MyHeapImp<User> heapTop = new MyHeapImp<>(userLinkedList.size(), false);
        for (int i = 0; i < userLinkedList.size(); i++) {
            heapTop.insert(userLinkedList.get(i));
        }
        for (int j = 1; j <= 15; j++) {
            User user = heapTop.delete();
            String verifiedStatus = user.getIsVerified() ? "Verificado" : "No verificado";
            System.out.println(
                    j + " - " + user.getName() + " - Tweets: " + user.getTweetCount() + " - " + verifiedStatus);
        }
    }

    /*
    Cantidad de hashtags distintos para un día dado. El día será ingresado en el formato
    YYYY-MM-DD.
     */
    public void differentHashTagsForADay(LocalDate date) {
        var differentHashTagsMap = new MyHashTable<String, String>();
        for (int i = 0; i < tweetLinkedList.size(); i++) {
            Tweet tweet = tweetLinkedList.get(i);
            var tweetDate = tweet.getDate().toLocalDate();
            var hashTags = tweet.getHashTags();
            if (tweetDate.equals(date)) {
                for (int j = 0; j < hashTags.size(); j++) {
                    String hashTag = hashTags.get(j).getText();
                    if (differentHashTagsMap.get(hashTag) == null) {
                        differentHashTagsMap.put(hashTag, hashTag);
                    }
                }
            }
        }
        System.out.println("Cantidad de hashtags distintos para el día " + date + ": " + differentHashTagsMap.size());
    }


    /*
    Hashtag más usado para un día dado, sin tener en cuenta #f1. El día será ingresado
    en el formato YYYY-MM-DD
     */
    public void mostUsedHashTagForADay(LocalDate date) {
        MyHashTable<String, Integer> hashTagHashTable = new MyHashTable<>();
        var maxHashTag = "";
        var maxCount = 0;
        for (int i = 0; i < tweetLinkedList.size(); i++) {
            var tweet = tweetLinkedList.get(i);
            var tweetDate = tweet.getDate().toLocalDate();
            var hashTags = tweet.getHashTags();
            if (tweetDate.equals(date) && hashTags != null) {
                for (int j = 0; j < hashTags.size(); j++) {
                    var hashTag = hashTags.get(j).getText();
                    if (!hashTag.equalsIgnoreCase("f1")) {
                        var count = hashTagHashTable.getOrDefault(hashTag, 0);
                        hashTagHashTable.put(hashTag, count + 1);
                        if (count > maxCount) {
                            maxHashTag = hashTag;
                            maxCount = count;
                        }
                    }
                }
            }
        }
        System.out.println("Hashtag más utilizado el día " + date + ": " + maxHashTag);
    }

    /*
    Top 7 cuentas con más favoritos. Para este listado se deberá retornar el nombre del
    usuario, junto con la cantidad de favoritos.
     */
    public void top7UsersWithMoreFavourites() {
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
    public void tweetsWithSpecificWordOrPhrase(String wordOrPhrase) {
        var count = 0;
        for (int i = 0; i < tweetLinkedList.size(); i++) {
            String tweetContent = tweetLinkedList.get(i).getContent();
            if (tweetContent != null && tweetContent.contains(wordOrPhrase.toLowerCase())) {
                count++;
            }
        }
        System.out.println("La cantidad de tweets con la palabra" + wordOrPhrase + " son: " + count);
    }

    /*
    Listar los 10 pilotos activos en la temporada 2023 más mencionados en los tweets
    en un mes (este mes será ingresado como 2 parámetros separados, mes y año, por
    consola). Este listado debe incluir el nombre de los pilotos y la cantidad de
    ocurrencias para cada uno de manera ordenada. Se espera que esta operación sea
    de orden n en notación Big O
     */
    public void getTopTenPilots(int month, int year) {
        MyHeapImp<Driver> driverMyHeapImp = new MyHeapImp<>(false);
        var driversList = getDriversFromFile();
        for (int i = 0; i < tweetLinkedList.size(); i++) {
            Tweet tweet = tweetLinkedList.get(i);
            LocalDateTime tweetDate = tweet.getDate();
            int dateMonthValue = tweetDate.getMonthValue();
            int dateYear = tweetDate.getYear();

            if (dateMonthValue == month && dateYear == year) {
                for (int j = 0; j < driversList.size(); j++) {
                    Driver driver = driversList.get(j);
                    String pilot = driver.getName();
                    if (tweet.getContent().contains(pilot)) {
                        driver.setMentions(driver.getMentions() + 1);
                    }
                }
            }
        }

        for (int i = 0; i < driversList.size(); i++) {
            Driver driver = driversList.get(i);
            driverMyHeapImp.insert(driver);
        }

        int count = 1;
        while (count <= 10 && driverMyHeapImp.size() != 0) {
            Driver topDriver = driverMyHeapImp.delete();
            System.out.println(count + "- " + topDriver.getName() + ": con " + topDriver.getMentions() + " menciones");
            count++;
        }
    }

    public LocalDateTime parseDateTime(String dateString) {
        try {
            return LocalDateTime.parse(dateString, FORMATTER_1);
        } catch (DateTimeParseException e1) {
            try {
                return LocalDateTime.parse(dateString, FORMATTER_2);
            } catch (DateTimeParseException e2) {
                throw new IllegalArgumentException("Invalid date format: " + dateString);
            }
        }
    }
}