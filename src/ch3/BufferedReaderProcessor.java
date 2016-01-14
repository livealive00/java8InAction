package ch3;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by SungHyun Park on 2016-01-14.
 */
@FunctionalInterface
public interface BufferedReaderProcessor {
    String process(BufferedReader br) throws IOException;
}
