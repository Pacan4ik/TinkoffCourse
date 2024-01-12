package edu.hw3;

import edu.hw3.Task6.Company;
import edu.hw3.Task6.Market;
import edu.hw3.Task6.Stock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task6Test {

    private class TestStock implements Stock {

        private double price;

        TestStock(double price) {
            this.price = price;
        }

        @Override
        public Company getCompany() {
            return null;
        }

        @Override
        public double getPrice() {
            return price;
        }

        @Override
        public void setPrice(double price) {
            this.price = price;
        }

        @Override
        public int getVolume() {
            return 0;
        }

        @Override
        public void setVolume(int volume) {

        }
    }

    @Test
    void shouldAddStock() {
        //given
        TestStock stock = new TestStock(10);
        Market stockMarket = new Market();

        //when
        stockMarket.add(stock);

        //then
        Assertions.assertEquals(stock, stockMarket.mostValuableStock());
    }

    @Test
    void shouldReturnMostValuableStock() {
        //given
        TestStock[] stocks = new TestStock[] {
            new TestStock(10),
            new TestStock(50),
            new TestStock(30),
            new TestStock(50),
            new TestStock(60),
            new TestStock(20),
        };
        Market stockMarket = new Market();

        //when
        for (var stock : stocks) {
            stockMarket.add(stock);
        }

        //then
        Assertions.assertEquals(stocks[4], stockMarket.mostValuableStock());
    }
}
