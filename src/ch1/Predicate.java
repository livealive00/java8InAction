package ch1;

/**
 * Created by SungHyun Park on 2016-01-14.
 */
@FunctionalInterface
public interface Predicate<T> {
    boolean test(T t);
}
