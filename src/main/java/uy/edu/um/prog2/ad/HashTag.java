package uy.edu.um.prog2.ad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HashTag {
    private Long id;
    private String text;

    @Override
    public String toString() {
        return "HashTag{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
