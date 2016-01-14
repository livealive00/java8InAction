package ch1;

/**
 * Created by SungHyun Park on 2016-01-14.
 */
public class AppleSimpleFormatter implements AppleFormatter {
    @Override
    public String accept(Apple apple) {
        return "An " + apple.getColor() + " apple of " + apple.getWeight() + "g";
    }
}
