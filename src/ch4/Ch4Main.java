package ch4;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * Created by SungHyun Park on 2016-01-15.
 */
public class Ch4Main {

    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
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

        List<String> threeHighestCaloricDishNames =
                menu.stream()
                .filter(d -> d.getCalories() > 300)
                .skip(2)
                .map(Dish::getName)
                .limit(3)
                .collect(toList());
        //System.out.println(threeHighestCaloricDishNames);

        //System.out.println(wordsToChars(Arrays.asList("Hello", "World")));

        // mapping examples
        //System.out.println(mappingExample1(Arrays.asList(1,2,3,4,5)));
        //System.out.println(mappingExample2(Arrays.asList(1,2,3), Arrays.asList(4,5)));

        // reduce
        //int sum = Arrays.asList(1,2,3).stream().reduce(0, Integer::sum);
        //System.out.println(sum);
    }

    public static List<int[]> mappingExample2(List<Integer> numbers1, List<Integer> numbers2) {
        return numbers1.stream()
                .flatMap(i -> numbers2.stream().map(j -> new int[]{i,j}))
                .filter(a -> (a[0] + a[1])% 3 == 0)
                .collect(toList());
    }

    public static List<Integer> mappingExample1(List<Integer> input) {
        return input.stream()
                .map(a -> (int)Math.pow((double)a, 2))
                .collect(toList());
    }

    public static List<String> wordsToChars(List<String> words){
        return words.stream().map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
    }
}
