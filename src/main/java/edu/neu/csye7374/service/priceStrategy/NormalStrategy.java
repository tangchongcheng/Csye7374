package edu.neu.csye7374.service.priceStrategy;

public class NormalStrategy implements Strategy{
    @Override
    public double calculatePrice(double controllerPrice, double eldenringPrice, double monitorPrice, double persona5Price, double playstationPrice, int controllerNo, int eldenringNo, int monitorNo, int persona5No, int playstationNo) {
        return controllerNo*controllerPrice + eldenringPrice*eldenringNo +
                persona5No * persona5Price + monitorPrice * monitorNo +
                playstationNo * playstationPrice;
    }
}
