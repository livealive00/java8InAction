package ch6;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * Created by SungHyun Park on 2016-07-07.
 */
public class PrimeNumbersCollector implements Collector<Integer, Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> {

    //누적자를 만드는 함수 반환
    @Override
    public Supplier<Map<Boolean, List<Integer>>> supplier() {
        return () -> new HashMap<Boolean, List<Integer>>() {
            {
                put(true, new ArrayList<>());
                put(false, new ArrayList<>());
            }
        };
    }

    //누적자에 누적하는 함수반환
    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
        return (Map<Boolean, List<Integer>> acc, Integer cand)
                -> acc.get(Ch6Main.isPrimeV2(acc.get(true), cand)).add(cand);
    }

    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        return (m1, m2) -> {
            m1.get(true).addAll(m2.get(true));
            m2.get(false).addAll(m2.get(false));
            return m1;
        };
    }

    @Override
    public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
    }
}
