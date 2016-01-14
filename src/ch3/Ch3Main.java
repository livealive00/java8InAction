package ch3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by SungHyun Park on 2016-01-14.
 */
public class Ch3Main {
    public static void main(String[] args) throws IOException {

        String output = processFile((BufferedReader br) -> br.readLine() + br.readLine() );
        System.out.println(output);
    }

    public static String processFile(BufferedReaderProcessor p) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("./src/ch2/data.txt")))  {
            return p.process(br);
        }
    }
}
