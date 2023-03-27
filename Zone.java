import java.util.stream.Stream;

public class Zone {
    private final Fuel highest;
    private final Fuel petrol, diesel, lpg;
    private boolean isPetrolHighest = false, isDieselHighest = false, isLpgHighest = false;

    public Zone(Petrol petrol, Diesel diesel, LPG lpg) {
        this.petrol = petrol;
        this.diesel = diesel;
        this.lpg = lpg;
        highest = Stream.of(petrol, diesel, lpg).max((f1, f2) -> f1.highest.compareTo(f2.highest)).orElse(null);
    }

    static int petrolComparator(Zone a, Zone b) {
        return Double.compare(a.getPetrol().highest.price(), b.getPetrol().highest.price());
    }

    static int dieselComparator(Zone a, Zone b) {
        return Double.compare(a.getDiesel().highest.price(), b.getDiesel().highest.price());
    }

    static int lpgComparator(Zone a, Zone b) {
        return Double.compare(a.getLpg().highest.price(), b.getLpg().highest.price());
    }

    public void setPetrolHighest() {
        this.isPetrolHighest = true;
    }

    public void setDieselHighest() {
        this.isDieselHighest = true;
    }

    public void setLpgHighest() {
        this.isLpgHighest = true;
    }

    public boolean isPetrolHighest() {
        return isPetrolHighest;
    }

    public boolean isDieselHighest() {
        return isDieselHighest;
    }

    public boolean isLpgHighest() {
        return isLpgHighest;
    }

    public Fuel getHighest() {
        return highest;
    }

    public Fuel getPetrol() {
        return petrol;
    }

    public Fuel getDiesel() {
        return diesel;
    }

    public Fuel getLpg() {
        return lpg;
    }
}