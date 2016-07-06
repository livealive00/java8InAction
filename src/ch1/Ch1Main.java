package ch1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by SungHyun Park on 2016-01-14.
 */
public class Ch1Main {
    public static void main(String[] args) {
        List<String> list = Arrays.asList( new String[]{"a", "a", "b", "b", "b", "c"} );

        List<Apple> inventory = new ArrayList<>();
        inventory.add(new Apple("green", 100));
        inventory.add(new Apple("red", 120));
        inventory.add(new Apple("red", 80));
        inventory.add(new Apple("yellow", 150));
        inventory.add(new Apple("blue", 100));

        // Strategy Pattern
        List<Apple> greenApples = filterApples(inventory, new AppleGreenPredicate());
        System.out.println(greenApples);

        // using lambda
        List<Apple> redApples = filter(inventory, (Apple apple) -> "red".equals(apple.getColor()));
        System.out.println(redApples);

        List<Integer> evenNumbers = filter(Arrays.asList(new Integer[]{1,2,3,4,5})
                , (Integer i) -> i%2==0 );
        System.out.println(evenNumbers);

        //prettyPrintApple(inventory, new AppleSimpleFormatter());
        inventory.stream().filter((Apple a) -> a.getColor().equals("red")).collect(toList());
        inventory.forEach( (Apple a) -> System.out.println(a.getColor()) );

        String[] strArr = new String[]{"ab", "bccc", "c"};


    }



    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<T>();
        for(T e : list) {
            if(p.test(e)) {
                result.add(e);
            }
        }
        return result;
    }


    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate predicate) {
        List<Apple> result = new ArrayList<Apple>();
        for(Apple apple : inventory) {
            if(predicate.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    public static void prettyPrintApple(List<Apple> inventory, AppleFormatter formatter) {
        List<Apple> result = new ArrayList<Apple>();
        for(Apple apple : inventory) {
            String output = formatter.accept(apple);
            System.out.println(output);
        }
    }

}
