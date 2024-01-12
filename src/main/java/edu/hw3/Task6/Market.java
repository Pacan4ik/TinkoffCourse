package edu.hw3.Task6;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Market implements StockMarket {

    Queue<Stock> queue = new PriorityQueue<>(Comparator.comparingDouble(Stock::getPrice).reversed());

    @Override
    public void add(Stock stock) {
        queue.add(stock);
    }

    @Override
    public void remove(Stock stock) {
        queue.remove(stock);
    }

    @Override
    public Stock mostValuableStock() {
        return queue.peek();
    }
}
