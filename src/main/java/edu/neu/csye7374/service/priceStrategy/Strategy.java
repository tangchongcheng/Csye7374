package edu.neu.csye7374.service.priceStrategy;

public interface Strategy {
    double calculatePrice(double controllerPrice, double eldenringPrice,
                                 double monitorPrice, double persona5Price, double playstationPrice,
                                 int controllerNo, int eldenringNo,
                                 int monitorNo, int persona5No, int playstationNo);
}
