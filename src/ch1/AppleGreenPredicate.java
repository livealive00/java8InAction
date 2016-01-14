package ch1;

/**
 * Created by SungHyun Park on 2016-01-14.
 */
public class AppleGreenPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return "green".equals(apple.getColor());
    }

}
