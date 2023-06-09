package uy.edu.um.prog2.ad.entities;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tweet {

    private HashTag hashTags;
    private Long id;
    private String content;
    private String source;
    private Boolean isRetweet;
    private LocalDateTime date;


    @Override
    public String toString() {
        return "Tweet{" +
                "hashTags=" + hashTags.toString() +
                ", id=" + id +
                ", content='" + content + '\'' +
                ", source='" + source + '\'' +
                ", isRetweet=" + isRetweet +
                ", date=" + date +
                '}';
    }
}
