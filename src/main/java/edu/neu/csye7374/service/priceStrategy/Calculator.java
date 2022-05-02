package edu.neu.csye7374.service.priceStrategy;

public class Calculator {
    Strategy strategy;
    double controllerPrice;
    double eldenringPrice;
    double monitorPrice;
    double persona5Price;
    double playstationPrice;
    int controllerNo;
    int eldenringNo;
    int monitorNo;
    int persona5No;
    int playstationNo;
    public Calculator(double controllerPrice, double eldenringPrice,
                      double monitorPrice, double persona5Price, double playstationPrice,
                      int controllerNo, int eldenringNo,
                      int monitorNo, int persona5No, int playstationNo){
        this.controllerPrice = controllerPrice;
        this.eldenringPrice = eldenringPrice;
        this.monitorPrice = monitorPrice;
        this.persona5Price = persona5Price;
        this.playstationPrice = playstationPrice;
        this.controllerNo = controllerNo;
        this.eldenringNo = eldenringNo;
        this.monitorNo = monitorNo;
        this.persona5No = persona5No;
        this.playstationNo = playstationNo;
    }
    public void setStrategy(Strategy strategy){
        this.strategy = strategy;
    }
    public double run(){
        return strategy.calculatePrice(controllerPrice, eldenringPrice,
         monitorPrice,  persona5Price,  playstationPrice,
        controllerNo, eldenringNo,
        monitorNo, persona5No, playstationNo);
    }


}
