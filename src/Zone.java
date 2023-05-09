import java.util.stream.Stream;

public class Zone {
    private final Fuel highest;
    private final Fuel petrol, diesel, lpg;
    private boolean isPetrolHighest = false, isDieselHighest = false, isLpgHighest = false;
    private final Zone.Type type;

    public Zone(Zone.Type type, Petrol petrol, Diesel diesel, LPG lpg) {
        this.type = type;
        this.petrol = petrol;
        this.diesel = diesel;
        this.lpg = lpg;
        highest = Stream.of(petrol, diesel, lpg).max(Fuel::compare).orElse(null);
    }
    public void setPetrolIsHighest() {
        this.isPetrolHighest = true;
    }
    public void setDieselIsHighest() {
        this.isDieselHighest = true;
    }
    public void setLpgIsHighest() {
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
    public Type getType() {
        return type;
    }
    enum Type {
        NORTH, EAST, WEST, SOUTH
    }
}