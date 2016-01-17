package ch5;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

/**
 * Created by SungHyun Park on 2016. 1. 17..
 */
public class Ch5Main {
    public static void main(String[] args) {

        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 710),
                new Transaction(alan, 2012, 950)
        );

        //pythagoreanTriple();
        fibo();
    }

    public static void fibo() {
        Stream.iterate(new int[]{0,1}, n -> new int[]{n[1], n[0]+n[1]} )
                .limit(20)
                .forEach( a -> System.out.println(a[0] + ", " + a[1]));
    }

    public static void pythagoreanTriple() {
        IntStream.range(1,100).boxed()
                .flatMap(
                        a -> IntStream.range(a,100)
                            .mapToObj(b -> new double[]{a, b, Math.sqrt(a*a + b*b)})

                ).filter( tri -> tri[2] % 1 == 0 )
                .forEach( tri -> System.out.println(tri[0]+" "+ tri[1]+" "+tri[2]));
    }

    public static void problem1(List<Transaction> transactions) {
        List<Transaction> result = transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(comparing(Transaction::getValue))
                .collect(toList());
        System.out.println(result);
    }

    public static void problem2(List<Transaction> transactions) {
        List<String> result = transactions.stream()
                .map(t -> t.getTrader().getCity())
                .distinct()
                .collect(toList());
        System.out.println(result);
    }

    public static void problem3(List<Transaction> transactions) {
        List<Trader> result = transactions.stream()
                .map(t -> t.getTrader())
                .collect(toList());
        System.out.println(result);
    }

    public static void problem4(List<Transaction> transactions) {
        String result = transactions.stream()
                .map(t -> t.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", (n1,n2) -> n1 + n2);
        System.out.println(result);
    }

    public static void problem5(List<Transaction> transactions) {
        boolean result = transactions.stream()
                .anyMatch(t -> t.getTrader().getCity().equals("Milan"));
        System.out.println(result);
    }

    public static void problem6(List<Transaction> transactions) {
        transactions.stream()
                .filter(t -> t.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .forEach(System.out::println);
    }

    public static void problem7(List<Transaction> transactions) {
        Optional<Integer> result = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);
        System.out.println(result);
    }

    public static void problem8(List<Transaction> transactions) {
        Optional<Transaction> result = transactions.stream()
                .min(comparing(Transaction::getValue));
        System.out.println(result);
    }
}
