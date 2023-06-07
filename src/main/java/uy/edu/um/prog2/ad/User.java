package uy.edu.um.prog2.ad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import uy.edu.um.prog2.ad.tads.linked_list.simple.LinkedList;
import uy.edu.um.prog2.ad.tads.linked_list.ListaConGenerics;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
public class User {
    private Long id;
    private String name;
    private ListaConGenerics<Tweet> tweets;
    private String userLocation;
    private String userDescription;
    private LocalDateTime userCreated;
    private Double userFollowers;
    private Double userFriends;
    private Double userFavourites;
    private Boolean isVerified;
    private Integer tweetCount;

    public User() {
        this.tweets = new LinkedList<>();
        tweetCount = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tweet=" + tweets.toString() +
                ", userLocation='" + userLocation + '\'' +
                ", userDescription='" + userDescription + '\'' +
                ", userCreated=" + userCreated +
                ", userFollowers=" + userFollowers +
                ", userFriends=" + userFriends +
                ", userFavourites=" + userFavourites +
                ", isVerified=" + isVerified +
                ", tweetCount=" + tweetCount +
                '}';
    }

    public void incrementTweetCount() {
        this.tweetCount++;
    }
}
