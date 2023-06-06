package uy.edu.um.prog2.ad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import uy.edu.um.prog2.ad.tads.linked_list.simple.LinkedList;
import uy.edu.um.prog2.ad.tads.linked_list.ListaConGenerics;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Tweet {

    private ListaConGenerics<HashTag> hashTags;
    private Long id;
    private String content;
    private String source;
    private Boolean isRetweet;
    private LocalDateTime date;

    public Tweet() {
        this.hashTags = new LinkedList<>();
    }

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
