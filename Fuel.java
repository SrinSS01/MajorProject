import java.util.ArrayList;
import java.util.List;

public abstract class Fuel {
    public final Price highest;
    private final Fuel.Type type;
    private static final int START_YEAR = 2018;
    public Fuel (Fuel.Type type, double... prices) {
        this.type = type;
        ArrayList<Price> priceList = new ArrayList<>(prices.length);
        int year = START_YEAR;
        for (double price : prices) {
            priceList.add(Price.of(price, year++));
        }
        highest = select(priceList);
    }

    Price select(ArrayList<Price> dataSet) {
        while (dataSet.size() > 1) {
            double fitnessValue = fitnessFunction(dataSet);
            dataSet.removeIf(data -> data.price() < fitnessValue);
        }
        return dataSet.get(0);
    }

    double fitnessFunction(List<Price> dataSet) {
        double sum = 0;
        int size = 0;
        for (var data : dataSet) {
            if (data.price() == 0) continue;
            sum += data.price();
            size++;
        }
        if (size == 0) return 0;
        return sum / size;
    }

    // a static comparator for sorting the prices
    public static int compare(Fuel f1, Fuel f2) {
        return f1.highest.compareTo(f2.highest);
    }

    public Type getType() {
        return type;
    }

    public enum Type {
        PETROL, DIESEL, LPG
    }    
}
