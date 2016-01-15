package ch3;

import ch1.Apple;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by SungHyun Park on 2016-01-14.
 */
public class Ch3Main {
    public static void main(String[] args) throws IOException {

        //String output = processFile((BufferedReader br) -> br.readLine() + br.readLine() );
        //System.out.println(output);

        List<Apple> inventory = new ArrayList<>();
        inventory.add(new Apple("green", 100));
        inventory.add(new Apple("red", 120));
        inventory.add(new Apple("red", 80));
        inventory.add(new Apple("yellow", 150));
        inventory.add(new Apple("blue", 100));

        //forEach(inventory, (Apple a) -> System.out.println(a.color));

        //List<Integer> weights = map(inventory, (Apple a) -> a.getWeight());
        //System.out.println(weights);

        //inventory.sort(comparing(Apple::getWeight).reversed());
        //System.out.println(inventory);

        lambdaReference();

        Function<String, String> transformationPipeline = Letter::addHeader;
        transformationPipeline.andThen(Letter::addFooter);

    }

    public static void lambdaReference() {
        List<String> str = Arrays.asList("a","b","A", "B");
        str.sort(String::compareToIgnoreCase);
        System.out.println(str);
    }

    public static <T,R> List<R> map(List<T> list, Function<T,R> f) {
        List<R> result = new ArrayList<>();
        for ( T t : list ) {
            result.add(f.apply(t));
        }
        return result;
    }

    public static <T> void forEach(List<T> list, Consumer<T> c) {
        for(T i : list) {
            c.accept(i);
        }
    }

    public static String processFile(BufferedReaderProcessor p) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("./src/ch2/data.txt")))  {
            return p.process(br);
        }
    }
}
