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
        priceList.sort((p1, p2) -> p1.compareTo(p2));
        highest = select(priceList);
    }

    Price select(ArrayList<Price> dataSet) {
        int i = 1;
        System.out.println("\n\tFuel type: " + type);
        while (dataSet.size() > 1) {
            System.out.println("\tIteration " + i++);
            int rank = 1;
            System.out.println("\t+---------------------------+");
            System.out.println("\t|  Rank  |  Price  |  Year  |");
            System.out.println("\t+---------------------------+");
            for (Price price : dataSet) {
                System.out.printf("\t| %6s | %7s | %6d |%n", rank++, price.price(), price.year());
            }
            System.out.println("\t+---------------------------+");
            double fitnessValue = fitnessFunction(dataSet);
            dataSet.removeIf(data -> data.price() < fitnessValue);
        }
        var select = dataSet.get(0);
        System.out.println("\tIteration " + i);
        System.out.println("\t+---------------------------+");
        System.out.println("\t|  Rank  |  Price  |  Year  |");
        System.out.println("\t+---------------------------+");
        System.out.printf("\t| %6s | %7s | %6d |%n", 1, select.price(), select.year());
        System.out.println("\t+---------------------------+");
        System.out.println("\n\tHeighest price: " + select.price() + " at year " + select.year());

        return select;
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
