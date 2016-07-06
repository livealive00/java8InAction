package ch6;

import ch4.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

/**
 * Created by SungHyun Park on 2016-07-06.
 */
public class Ch6Main {

    static List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OHTER),
            new Dish("rice", true, 350, Dish.Type.OHTER),
            new Dish("season fruit", true, 120, Dish.Type.OHTER),
            new Dish("pizza", true, 550, Dish.Type.OHTER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH)
    );

    public static void main(String[] args) {
        IntStream.range(0,7).forEach(i -> System.out.println(i));
        quiz1();


    }

    public static void partitionByPrime() {
        Map<Boolean, List<Integer>> res = IntStream.rangeClosed(2, 100).boxed().collect(partitioningBy(c ->isPrime(c)));
        System.out.println(res);
    }

    public static boolean isPrime(int candidate) {
        return IntStream.range(2, candidate).noneMatch( i -> candidate % i == 0);
    }

    public static void quiz1() {
        //1.
        menu.stream().collect(Collectors.reducing( "", Dish::getName, (d1,d2)-> d1 + d2) );
        Map<Dish.Type, Dish> res = menu.stream()
                                    .collect( groupingBy(Dish::getType,
                                                        collectingAndThen(maxBy(comparingInt(Dish::getCalories)), Optional::get)) );

        //System.out.println(menu.stream().collect(groupingBy(Dish::getType, toSet())) );
        Map<Boolean, Dish> mostCaloricPartitionedByVegetarian =
                menu.stream().collect( partitioningBy(Dish::isVegetarian,
                                                      collectingAndThen( maxBy(comparingInt(Dish::getCalories)), Optional::get)) );
        System.out.println(mostCaloricPartitionedByVegetarian);
    }


}
