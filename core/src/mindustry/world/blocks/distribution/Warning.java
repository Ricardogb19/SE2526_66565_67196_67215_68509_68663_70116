package mindustry.world.blocks.distribution;

public class Warning {

    protected static final String MISPLACED_TYPE = "misplaced";
    protected static final String MISPLACED_MESSAGE = "There appears to be a misplaced conveyor!\n\n Try changing your conveyor's direction or place a router.";
    protected static final String CONGESTED_TYPE = "congested";
    protected static final String CONGESTED_MESSAGE = "There appears to be clogging in your conveyor line!\n\n Try adding a destination, like a turret or your core.";

    //TODO might be a State

    private String message;
    private boolean isFixed;
    private String type;
    private float x;
    private float y;


    public Warning(String type, String message, float x, float y) {
        this.message = message;
        this.isFixed = false;
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public String getMessage() {
        return message;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public String getCoordinates() {
        return ("(x = " + this.x + " y = " + this.y + ")");
    }

    public void switchFixed() {
        isFixed = !isFixed;
    }

    public boolean isFixed() {
        return isFixed;
    }

    public String isFixedToString() {
        if (this.isFixed) {
            return "Fixed";
        }
        return "Not Fixed";
    }

    public String getType() {
        return type;
    }


    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Warning warning = (Warning) other;
        return this.getType().equals(warning.getType()) &&
                this.getCoordinates().equals(warning.getCoordinates());
    }

    @Override
    public int hashCode() {
        return (int)(this.getX() * this.getY() * 2);
    }
}
