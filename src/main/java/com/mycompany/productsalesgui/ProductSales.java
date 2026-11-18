package com.mycompany.productsalesgui;

// question 2.4
public class ProductSales implements IProductSales {
    private int[][] salesData = {
        {300, 150, 700},   // Microphone, Speakers, Mixing Desk - Year 1
        {250, 200, 600}    // Microphone, Speakers, Mixing Desk - Year 2
    };
    private final int LIMIT = 500;

    @Override
    public int[][] GetProductSales() {
        return salesData;
    }

    @Override
    public int GetTotalSales() {
        int total = 0;
        for (int year = 0; year < salesData.length; year++) {
            for (int q = 0; q < salesData[year].length; q++) {
                total += salesData[year][q];
            }
        }
        return total;
    }

    @Override
    public double GetAverageSales() {
        int total = GetTotalSales();
        int count = salesData.length * salesData[0].length;
        return (double) total / count;
    }

    @Override
    public int GetSalesOverLimit() {
        int count = 0;
        for (int year = 0; year < salesData.length; year++) {
            for (int q = 0; q < salesData[year].length; q++) {
                if (salesData[year][q] > LIMIT) {
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    public int GetSalesUnderLimit() {
        int count = 0;
        for (int year = 0; year < salesData.length; year++) {
            for (int q = 0; q < salesData[year].length; q++) {
                if (salesData[year][q] < LIMIT) {
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    public int GetProductsProcessed() {
        return 2;
    }
}