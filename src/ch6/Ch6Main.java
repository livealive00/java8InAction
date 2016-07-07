package ch6;

import ch4.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

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

    static String str = "Laurie is lost in Userland. She knows where she is, or where she's going, but maybe not at the same time. The only way out is through Jargon-infested swamps, gates guarded by perfect logic, and the perils of breakfast time at the Philosopher's Diner. With just her wits and the help of a lizard who thinks he's a dinosaur, Laurie has to find her own way home.";

    public static void main(String[] args) {

        wordCountExample();

    }

    public static void wordCountExample() {
        Stream<Character> stream = IntStream.range(0, str.length()).mapToObj(i->str.charAt(i));
        int cnt = countWords(stream.parallel());
        System.out.println(cnt);

        System.out.println(countWordsIteratively(str));
    }

    private static int countWords(Stream<Character> stream) {
        return stream.reduce(new WordCounter(0, true), WordCounter::accumulate, WordCounter::combine).getCounter();
    }

    public static int countWordsIteratively(String s) {
        int counter = 0;
        boolean lastSpace = true;
        for(char c : s.toCharArray()) {
            if(Character.isWhitespace(c)) {
                lastSpace = true;
            } else {
                if (lastSpace) {
                    counter++;
                }
                lastSpace = false;
            }
        }
        return counter;
    }

    public static long forJoinSum(long n) {
        long[] numbers = LongStream.rangeClosed(1, n).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        return new ForkJoinPool().invoke(task);
    }

    public static long parallelSum(long n) {
        return LongStream.rangeClosed(1,n).parallel().reduce(0L, Long::sum);
    }

    public static long sequentialSum(long n) {
        return LongStream.rangeClosed(1,n).reduce(0L, Long::sum);
    }

    public static long iterativeSum(long n) {
        long result = 0;
        for(long i=1L; i<=n; i++) {
            result += 1;
        }
        return result;
    }

    public static long mesureSumPerf(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;
        for (int i=0; i<10; i++) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
            //System.out.println("Result: " + sum);
            if (duration < fastest) fastest = duration;
        }
        return fastest;
    }

    public static <A> List<A> takeWhile(List<A> list, Predicate<A> p) {
        int i = 0;
        for(A item : list) {
            if( !p.test(item) ) {
                return list.subList(0, i);
            }
            i++;
        }
        return list;
    }

    public static boolean isPrimeV2(List<Integer> primes, int candidate) {
        int candidateRoot = (int) Math.sqrt((double)candidate);
        return takeWhile(primes, i -> i <= candidateRoot).stream().noneMatch(i -> candidate%i == 0);
    }

    public boolean isPrimeV2(int candidate) {
        int candidateRoot = (int) Math.sqrt((double)candidate);
        return IntStream.rangeClosed(2, candidateRoot).noneMatch(i -> candidateRoot % i == 0);

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
