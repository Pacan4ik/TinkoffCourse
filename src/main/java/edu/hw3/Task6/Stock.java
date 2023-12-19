package edu.hw3.Task6;

public interface Stock {
    Company getCompany();

    double getPrice();

    void setPrice(double price);

    int getVolume();

    void setVolume(int volume);
}
