import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final Zone EAST;
    private static final Zone WEST;                                        
    private static final Zone NORTH;
    private static final Zone SOUTH;

    static {
        System.out.println("Eastern Zone");
        EAST = new Zone(
                Zone.Type.EAST,
                new Petrol(84.68, 74.91, 90.34, 101.87, 106.03),
                new Diesel(75.97, 64.61, 80.51, 92.67, 92.76),
                new LPG(66.0, 44.0, 45.0, 64.0, 74.0)
        );
        System.out.println("\nWestern Zone");
        WEST = new Zone(
                Zone.Type.WEST,
                new Petrol(90.22, 78.57, 87.82, 107.47, 111.35),
                new Diesel(78.69, 66.35, 77.99, 94.17, 94.24),
                new LPG(55.98, 39.57, 41.83, 63.41, 75.24)
        );
        System.out.println("\nNorthern Zone");
        NORTH = new Zone(
                Zone.Type.NORTH,
                new Petrol(82.86, 72.96, 81.14, 101.39, 96.72),
                new Diesel(74.12, 62.88, 71.58, 89.57, 89.62),
                new LPG(57.74, 41.54, 41.83, 62.28, 74.15)
        );
        System.out.println("\nSouthern Zone");
        SOUTH = new Zone(
                Zone.Type.SOUTH,
                new Petrol(86.13, 76.25, 84.22, 99.15, 102.63),
                new Diesel(78.36, 66.35, 77.99, 94.17, 94.24),
                new LPG(59.04, 42.71, 42.95, 63.41, 75.24)
        );
    }

    public static void main(String[] args) {
        ArrayList<Zone> zones = new ArrayList<>(List.of(NORTH, EAST, WEST, SOUTH));
        Price petrolHighest = NORTH.getPetrol().highest;
        Price dieselHighest = NORTH.getDiesel().highest;
        Price lpgHighest = NORTH.getLpg().highest;
        Zone zoneHighest = NORTH;
        for (Zone z : zones) {
                if (z.getPetrol().highest.price() > petrolHighest.price()) {
                        petrolHighest = z.getPetrol().highest;
                }
                if (z.getDiesel().highest.price() > dieselHighest.price()) {
                        dieselHighest = z.getDiesel().highest;
                }
                if (z.getLpg().highest.price() > lpgHighest.price()) {
                        lpgHighest = z.getLpg().highest;
                }
                if (z.getHighest().highest.price() > zoneHighest.getHighest().highest.price()) {
                        zoneHighest = z;
                }
        }
        final Price petrolHighestFinal = petrolHighest;
        final Price dieselHighestFinal = dieselHighest;
        final Price lpgHighestFinal = lpgHighest;
        zones.stream().filter(it -> it.getPetrol().highest.price() == petrolHighestFinal.price()).forEach(Zone::setPetrolIsHighest);
        zones.stream().filter(it -> it.getDiesel().highest.price() == dieselHighestFinal.price()).forEach(Zone::setDieselIsHighest);
        zones.stream().filter(it -> it.getLpg().highest.price() == lpgHighestFinal.price()).forEach(Zone::setLpgIsHighest);

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

        System.out.printf(
                "Amongst all the zones highest price was in the %sERN zone for %s with a price of Rs. %s in the year %d%n%n", 
                zoneHighest.getType(),
                zoneHighest.getHighest().getType(), 
                zoneHighest.getHighest().highest.price(), 
                zoneHighest.getHighest().highest.year()
        );
    }
}
