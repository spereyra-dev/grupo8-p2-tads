package uy.edu.um.prog2.ad.entities;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import uy.edu.um.prog2.ad.tads.linked_list.simple.LinkedList;

@Getter
@Setter
@AllArgsConstructor
public class Tweet {

    private LinkedList<HashTag> hashTags;
    private Long id;
    private String content;
    private String source;
    private Boolean isRetweet;
    private LocalDateTime date;

    @Override
    public String toString() {
        return "Tweet{" +
                "hashTags=" + hashTags +
                ", id=" + id +
                ", content='" + content + '\'' +
                ", source='" + source + '\'' +
                ", isRetweet=" + isRetweet +
                ", date=" + date +
                '}';
    }

    public Tweet() {
        this.hashTags = new LinkedList<>();
    }
}
