import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

public class Main {
    private static final Zone EAST = new Zone(
            Zone.Type.EAST,
            new Petrol(84.68, 74.91, 90.34, 101.87, 106.03),
            new Diesel(75.97, 64.61, 80.51, 92.67, 92.76),
            new LPG(66.0, 44.0, 45.0, 64.0, 74.0)
    );
    private static final Zone WEST = new Zone(
            Zone.Type.WEST,
            new Petrol(90.22, 78.57, 87.82, 107.47, 111.35),
            new Diesel(78.69, 66.35, 77.99, 94.17, 94.24),
            new LPG(55.98, 39.57, 41.83, 63.41, 75.24)
    );                                        
    private static final Zone NORTH = new Zone(
            Zone.Type.NORTH,
            new Petrol(82.86, 72.96, 81.14, 101.39, 96.72),
            new Diesel(74.12, 62.88, 71.58, 89.57, 89.62),
            new LPG(57.74, 41.54, 41.83, 62.28, 74.15)
    );
    private static final Zone SOUTH = new Zone(
            Zone.Type.SOUTH,
            new Petrol(86.13, 76.25, 84.22, 99.15, 102.63),
            new Diesel(78.36, 66.35, 77.99, 94.17, 94.24),
            new LPG(59.04, 42.71, 42.95, 63.41, 75.24)
    );

    public static void main(String[] args) {
        Stream<Zone> zones = Stream.of(NORTH, EAST, WEST, SOUTH);
        AtomicReference<Price> petrolHighest = new AtomicReference<>(NORTH.getPetrol().highest);
        AtomicReference<Price> dieselHighest = new AtomicReference<>(NORTH.getDiesel().highest);
        AtomicReference<Price> lpgHighest = new AtomicReference<>(NORTH.getLpg().highest);
        AtomicReference<Zone> zoneHighest = new AtomicReference<>(NORTH);

        zones.forEach(zone -> {
                if (zone.getPetrol().highest.price() > petrolHighest.get().price()) {
                        petrolHighest.set(zone.getPetrol().highest);
                }
                if (zone.getDiesel().highest.price() > dieselHighest.get().price()) {
                        dieselHighest.set(zone.getDiesel().highest);
                }
                if (zone.getLpg().highest.price() > lpgHighest.get().price()) {
                        lpgHighest.set(zone.getLpg().highest);
                }
                if (zone.getHighest().highest.price() > zoneHighest.get().getHighest().highest.price()) {
                        zoneHighest.set(zone);
                }
        });
        zones.filter(it -> it.getPetrol().highest.price() == petrolHighest.get().price()).forEach(it -> it.setPetrolHighest());
        zones.filter(it -> it.getDiesel().highest.price() == dieselHighest.get().price()).forEach(it -> it.setDieselHighest());
        zones.filter(it -> it.getLpg().highest.price() == lpgHighest.get().price()).forEach(it -> it.setLpgHighest());

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
                petrolHighest.get().year(),
                EAST.isDieselHighest() ? '*' : ' ',
                WEST.isDieselHighest() ? '*' : ' ',
                NORTH.isDieselHighest() ? '*' : ' ',
                SOUTH.isDieselHighest() ? '*' : ' ',
                dieselHighest.get().year(),
                EAST.isLpgHighest() ? '*' : ' ',
                WEST.isLpgHighest() ? '*' : ' ',
                NORTH.isLpgHighest() ? '*' : ' ',
                SOUTH.isLpgHighest() ? '*' : ' ',
                lpgHighest.get().year()
        );

        Zone zone = zoneHighest.get();
        System.out.printf(
                "Amongst all the zones highest price was in the %sERN zone for %s with a price of Rs. %s in the year %d%n%n", 
                zone.getType(),
                zone.getHighest().getType(), 
                zone.getHighest().highest.price(), 
                zone.getHighest().highest.year()
        );
    }
}
