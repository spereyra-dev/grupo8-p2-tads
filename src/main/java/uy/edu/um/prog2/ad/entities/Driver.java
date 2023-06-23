package uy.edu.um.prog2.ad.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Driver implements Comparable<Driver> {
    private String name;
    private Integer mentions;

    public Driver() {
        this.mentions = 0;
    }

    public Driver(String driverLowerCase) {
        this.name = driverLowerCase;
        this.mentions = 0;
    }

    @Override
    public int compareTo(Driver o) {
        return this.mentions.compareTo(o.mentions);
    }
}
