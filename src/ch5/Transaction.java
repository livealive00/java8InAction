package ch5;

import lombok.Data;

/**
 * Created by SungHyun Park on 2016. 1. 17..
 */
@Data
public class Transaction {

    private final Trader trader;
    private final int year;
    private final int value;

    public Transaction(Trader trader, int year, int value) {
        this.trader = trader;
        this.year = year;
        this.value = value;
    }
}
