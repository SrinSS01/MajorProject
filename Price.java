public record Price(double price, int year) implements Comparable<Price> {
    public static Price of(double price, int year) {
        return new Price(price, year);
    }
    @Override
    public int compareTo(Price o) {
        return Double.compare(price, o.price);
    }
}
