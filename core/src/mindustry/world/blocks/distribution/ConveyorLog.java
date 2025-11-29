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
    private static final int TILE_SIZE = 8;
    private Set<Warning> warnings;


    public ConveyorLog() {
        this.warnings = new HashSet<>();
    }

    private boolean doesSimilarExistExist(Warning warning, Warning other) {
        return ((warning.getX() == other.getX() && warning.getY() - other.getY() == TILE_SIZE) || (warning.getY() == other.getY() && warning.getX() - other.getX() == TILE_SIZE) ||
                (warning.getX() == other.getX() && warning.getY() - other.getY() == -TILE_SIZE) || (warning.getY() == other.getY() && warning.getX() - other.getX() == -TILE_SIZE));
    }

    public void addWarning(Warning warning) {
        boolean canAdd = true;
        for(Warning w : warnings) {
            if(doesSimilarExistExist(warning, w)) {
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

    public void printLog(){
        if (warnings.isEmpty()) {
            ui.showConveyorLog(IS_EMPTY);
        } else {
            String allWarnings = "";
            int i = 0;
            Iterator<Warning> it = warnings.iterator();
            while(i++ < MAX_WARNINGS &&  it.hasNext()) {
                Warning w = it.next();
                allWarnings = allWarnings.concat(String.format(WARNING_INFO, w.getType(), w.getCoordinates(), w.isFixedToString()));
            }
            ui.showConveyorLog(allWarnings);

        }
    }

}

