package uy.edu.um.prog2.ad;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import uy.edu.um.prog2.ad.entities.Driver;
import uy.edu.um.prog2.ad.entities.HashTag;
import uy.edu.um.prog2.ad.entities.Tweet;
import uy.edu.um.prog2.ad.entities.User;
import uy.edu.um.prog2.ad.tads.linked_list.ListaConGenerics;

class CsvUtilsTest {

    private final CsvUtils csvUtils = new CsvUtils(true);

    @Test
    void testGetDriversFromFile() {

        // Mock drivers.txt file content
        List<String> driverNames = Arrays.asList("max verstappen", "sergio pérez", "charles leclerc", "carlos sainz", "lewis hamilton", "george russell", "fernando alonso", "lance stroll", "lando norris", "oscar piastri", "pierre gasly", "esteban ocon", "nyck de vries", "yuki tsunoda", "alexander albon", "logan sargeant", "guanyu zhou", "valtteri bottas", "nico hülkenberg", "kevin magnussen");

        // Call the method
        ListaConGenerics<Driver> drivers = csvUtils.getDriversFromFile();

        Driver driver1 = drivers.get(0);
        assertEquals("max verstappen", driver1.getName());

        Driver driver2 = drivers.get(1);
        assertEquals("sergio pérez", driver2.getName());

        Driver driver3 = drivers.get(2);
        assertEquals("charles leclerc", driver3.getName());

        // Check the result
        assertEquals(20, drivers.size());
        for (int i = 0; i < drivers.size(); i++) {
            assertEquals(driverNames.get(i), drivers.get(i).getName());
        }
    }

    @Test
    void testGetCsvInfo() {
        assertEquals(LocalDateTime.of(2023, 6, 23, 12, 0, 0), csvUtils.parseDateTime("2023-06-23 12:00:00"));
        assertEquals(LocalDateTime.of(2023, 6, 23, 2, 0, 0), csvUtils.parseDateTime("2023-06-23 02:00:00"));

        csvUtils.getCsvInfo();

        assertEquals(141, csvUtils.getUserLinkedList().size());
        assertEquals(151, csvUtils.getTweetLinkedList().size());
        assertEquals(141, csvUtils.getHashTagLinkedList().size());

        User user1 = csvUtils.getUserLinkedList().get(0);
        assertEquals(1, user1.getId());
        assertEquals("HIDE ღ❤ღ꒰･‿･๑꒱㌰㌰official", user1.getName());
        assertEquals(4, user1.getTweets().size());
        assertEquals(LocalDateTime.of(2021, 11, 12, 19, 36, 38), user1.getLastTweet());
        assertFalse(user1.getIsVerified());

        User user2 = csvUtils.getUserLinkedList().get(1);
        assertEquals(2, user2.getId());
        assertEquals("Sol Saltiel", user2.getName());
        assertEquals(1, user2.getTweets().size());
        assertEquals(LocalDateTime.of(2021, 11, 21, 17, 40, 45), user2.getLastTweet());
        assertFalse(user2.getIsVerified());

        User user3 = csvUtils.getUserLinkedList().get(2);
        assertEquals(3, user3.getId());
        assertEquals("Motorsport.com", user3.getName());
        assertEquals(1, user3.getTweets().size());
        assertEquals(LocalDateTime.of(2021, 12, 12, 14, 23, 36), user3.getLastTweet());
        assertTrue(user3.getIsVerified());

        Tweet tweet1 = csvUtils.getTweetLinkedList().get(0);
        assertEquals(208738, tweet1.getId());
        assertEquals(LocalDateTime.of(2021, 11, 7, 19, 28, 43), tweet1.getDate());
        assertEquals("#f1 #formula1 #mexicogp\uD83C\uDDF2\uD83C\uDDFD final. https://t.co/wiyudpoh8r", tweet1.getContent());
        assertEquals("Twitter Web App", tweet1.getSource());
        assertFalse(tweet1.getIsRetweet());
        assertEquals(3, tweet1.getHashTags().size());

        Tweet tweet2 = csvUtils.getTweetLinkedList().get(1);
        assertEquals(988889, tweet2.getId());
        assertEquals(LocalDateTime.of(2021, 11, 7, 19, 28, 43), tweet1.getDate());
        assertEquals("#f1 #formula1 #mexicogp\uD83C\uDDF2\uD83C\uDDFD final. https://t.co/wiyudpoh8r", tweet1.getContent());
        assertEquals("Twitter Web App", tweet1.getSource());
        assertFalse(tweet1.getIsRetweet());
        assertEquals(3, tweet1.getHashTags().size());

        Tweet tweet3 = csvUtils.getTweetLinkedList().get(2);
        assertEquals(988989, tweet3.getId());
        assertEquals(LocalDateTime.of(2021, 11, 7, 19, 28, 43), tweet1.getDate());
        assertEquals("#f1 #formula1 #mexicogp\uD83C\uDDF2\uD83C\uDDFD final. https://t.co/wiyudpoh8r", tweet1.getContent());
        assertEquals("Twitter Web App", tweet1.getSource());
        assertFalse(tweet1.getIsRetweet());
        assertEquals(3, tweet1.getHashTags().size());

        HashTag hashTag1 = csvUtils.getHashTagLinkedList().get(0);
        assertEquals(1, hashTag1.getId());
        assertEquals("f1", hashTag1.getText());

        HashTag hashTag2 = csvUtils.getHashTagLinkedList().get(1);
        assertEquals(2, hashTag2.getId());
        assertEquals("formula1", hashTag2.getText());

        HashTag hashTag3 = csvUtils.getHashTagLinkedList().get(2);
        assertEquals(3, hashTag3.getId());
        assertEquals("mexicogp", hashTag3.getText());

        HashTag hashTag4 = csvUtils.getHashTagLinkedList().get(3);
        assertEquals(4, hashTag4.getId());
        assertEquals("qatargp", hashTag4.getText());

        HashTag hashTag5 = csvUtils.getHashTagLinkedList().get(4);
        assertEquals(5, hashTag5.getId());
        assertEquals("qatargrandprix", hashTag5.getText());

        HashTag hashTag6 = csvUtils.getHashTagLinkedList().get(5);
        assertEquals(6, hashTag6.getId());
        assertEquals("abudabhigp", hashTag6.getText());
    }

}
