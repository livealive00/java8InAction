package ch1;

import lombok.Data;

/**
 * Created by SungHyun Park on 2016-01-14.
 */
@Data
public class Apple {
    public String color;
    public Integer weight;

    public Apple(String color, Integer weight) {
        this.color = color;
        this.weight = weight;
    }

}
