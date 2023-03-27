import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final Zone EAST = new Zone(
            new Petrol(84.68, 74.91, 90.34, 101.87, 106.03),
            new Diesel(75.97, 64.61, 80.51, 92.67, 92.76),
            new LPG(66.0, 44.0, 45.0, 64.0, 74.0)
    );
    private static final Zone WEST = new Zone(
            new Petrol(90.22, 78.57, 87.82, 107.47, 111.35),
            new Diesel(78.69, 66.35, 77.99, 94.17, 94.24),
            new LPG(55.98, 39.57, 41.83, 63.41, 75.24)
    );
    private static final Zone NORTH = new Zone(
            new Petrol(82.86, 72.96, 81.14, 101.39, 96.72),
            new Diesel(74.12, 62.88, 71.58, 89.57, 89.62),
            new LPG(57.74, 41.54, 41.83, 62.28, 74.15)
    );
    private static final Zone SOUTH = new Zone(
            new Petrol(86.13, 76.25, 84.22, 99.15, 102.63),
            new Diesel(78.36, 66.35, 77.99, 92.67, 92.76),
            new LPG(59.04, 42.71, 42.95, 63.41, 75.24)
    );

    public static void main(String[] args) {
        Fuel northHighest = NORTH.getHighest();
        Fuel southHighest = SOUTH.getHighest();
        Fuel eastHighest = EAST.getHighest();
        Fuel westHighest = WEST.getHighest();

        ArrayList<Zone> zones = new ArrayList<>(List.of(NORTH, EAST, WEST, SOUTH));
        zones.sort(Zone::petrolComparator);
        Zone zone = zones.get(0);
        zone.setPetrolHighest();
        Price petrolHighest = zone.getPetrol().highest;
        zones.sort(Zone::dieselComparator);
        zone = zones.get(0);
        zone.setDieselHighest();
        Price dieselHighest = zone.getDiesel().highest;
        zones.sort(Zone::lpgComparator);
        zone = zones.get(0);
        zone.setLpgHighest();
        Price lpgHighest = zone.getLpg().highest;

        System.out.printf(
                """
                            +------------------------------------------+--------+
                            |%7s  |%7s   |%7s   |%7s   |  year  |
                +-----------+------------------------------------------+--------+
                |   petrol  |%5s    |%5s     |%5s     |%5s     |%6s  |
                +-----------+------------------------------------------+--------+
                |   diesel  |%5s    |%5s     |%5s     |%5s     |%6s  |
                +-----------+------------------------------------------+--------+
                |    lpg    |%5s    |%5s     |%5s     |%5s     |%6s  |
                +-----------+------------------------------------------+--------+
                %n""",
                "EAST", "WEST", "NORTH", "SOUTH",
                EAST.isPetrolHighest() ? '*' : ' ',
                WEST.isPetrolHighest() ? '*' : ' ',
                NORTH.isPetrolHighest() ? '*' : ' ',
                SOUTH.isPetrolHighest() ? '*' : ' ',
                petrolHighest.year(),
                EAST.isDieselHighest() ? '*' : ' ',
                WEST.isDieselHighest() ? '*' : ' ',
                NORTH.isDieselHighest() ? '*' : ' ',
                SOUTH.isDieselHighest() ? '*' : ' ',
                dieselHighest.year(),
                EAST.isLpgHighest() ? '*' : ' ',
                WEST.isLpgHighest() ? '*' : ' ',
                NORTH.isLpgHighest() ? '*' : ' ',
                SOUTH.isLpgHighest() ? '*' : ' ',
                lpgHighest.year()
        );

        System.out.printf("In eastern zone the highest price was for %s (Rs. %s) in %d%n", eastHighest.getType(), eastHighest.highest.price(), eastHighest.highest.year());
        System.out.printf("In western zone the highest price was for %s (Rs. %s) in %d%n", westHighest.getType(), westHighest.highest.price(), westHighest.highest.year());
        System.out.printf("In northern zone the highest price was for %s (Rs. %s) in %d%n", northHighest.getType(), northHighest.highest.price(), northHighest.highest.year());
        System.out.printf("In southern zone the highest price was for %s (Rs. %s) in %d%n", southHighest.getType(), southHighest.highest.price(), southHighest.highest.year());
    }
}
