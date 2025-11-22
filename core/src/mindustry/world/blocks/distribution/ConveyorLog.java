package mindustry.world.blocks.distribution;


import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;

import static mindustry.Vars.ui;

public class ConveyorLog {
    private static final String IS_EMPTY = "There are currently no warnings in the log.";
    public static final String NO_CLEARED_WARNINGS = "There are no warnings to be cleared!";
    private static final String WARNING_INFO = "Type: %s\t   Location: %s\n\n";
    public static final String CLEAR_MESSAGE = "All fixed warnings cleared";
    private static final int MAX_WARNINGS = 15;
    private Set<Warning> warnings;


    public ConveyorLog() {
        this.warnings = new HashSet<>();
    }

    public void addWarning(Warning warning) {
        boolean canAdd = true;
        for(Warning w : warnings) {
            if((warning.getX() == w.getX() && warning.getY() - w.getY() == 8) || (warning.getY() == w.getY() && warning.getX() - w.getX() == 8) ||
                    (warning.getX() == w.getX() && warning.getY() - w.getY() == -8) || (warning.getY() == w.getY() && warning.getX() - w.getX() == -8)){
                canAdd = false;
            }
        }
        if (canAdd) {
            warnings.add(warning);
        }
    }

    public void removeFixed() {
        for (Warning w : warnings) {
            if(w.isFixed()) {
                warnings.remove(w);
            }
        }
    }

    public int getNumberOfWarnings() {
        return warnings.size();
    }

    public void removeAll() {
        warnings.clear();
    }

}

