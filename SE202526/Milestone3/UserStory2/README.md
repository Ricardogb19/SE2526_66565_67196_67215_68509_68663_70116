# User story 2
New warning icon for misplaced conveyors and congestion.

## Author(s)
- Raksana Udagedara (67196)
- Guilherme Neto (68663)
## Reviewer(s)
- Clara Dias (67215)
- Ricardo Batista (68509)
## User Story:
**As a** player that likes to keep track of everything, **I want to** receive a text warning everytime I misplace a conveyor for whatever reason and obtain possible fixes, **so that** I can have everything under my watch and build solid structures quickly.
### Review
This addition has great potential, mainly because for new players, conveyor misplacements and poor assemblies may not be obvious at first. It’s important to be careful so that the game’s interface doesn’t become too cluttered with excessive information.
## Use case diagram
![alt text](UCD1.png)
![alt text](UCD2.png)
## Use case textual description

## Use Case: Place misplaced conveyor.

**ID:** 1

**Description:** The player places a conveyor with inconsistent rotation in relation to a previously placed conveyor.

**Primary actor:** player.

**Secondary actor:** none.

**Preconditions:**

1. The player is aligning conveyors.

**Main flow:**

1. The use case begins when the player places a conveyor with inconsistent rotation relative to a previously placed conveyor.
2. The system alerts the player by displaying a warning icon on the misplaced conveyor.

**Postconditions:**

1. The system displays the error icon on the misplaced conveyors.

**Alternative flows:** none.

## Use Case: Cause congestion.

**ID:** 2

**Description:** The player causes congestion in the circulation of items on the conveyors.

**Primary actor:** player.

**Secondary actor:** none.

**Preconditions:**

1. The player places a drill that puts ores into circulation on the conveyors.

**Main flow:**

1. The use case begins when the circulation of items on the conveyors stops.
2. The system alerts the player by displaying a congestion warning icon.

**Postconditions:**

1. The system displays the error icon at the location of the congestion.

**Alternative flows:** none.

## Use Case: Obtain error details.

**ID:** 3

**Description:** The player sees an error icon and checks its description.

**Primary actor:** player.

**Secondary actor:** none.

**Preconditions:**

1. The player has placed at least one conveyor with an inconsistent rotation relative to a preceding conveyor/there is congestion on the conveyors.
2. The system alerts the player by displaying an icon in the associated location.

**Main flow:**

1. The use case begins when the player clicks on the error icon.
2. The system displays a window identifying the error and suggesting a solution.

**Postconditions:**

1. The player is in the window displaying the reason for the error and the suggested solution.

**Alternative flows:** none.

## Use Case: Confirm error acknowledgment.

**ID:** 4

**Description:** The player clicks “OK” to exit the window that identifies the error/suggestion.

**Primary actor:** player.

**Secondary actor:** none.

**Preconditions:** 

1. The player has placed at least one conveyor with an inconsistent rotation relative to an immediately preceding conveyor/there is congestion on the conveyors.
2. The system alerts the player by displaying an icon in the associated location.
3. The player is in the window that identifies the error/suggestion.

**Main flow:** 

1. The use case begins when the player clicks “OK” to exit the window.
2. The system exits the error/suggestion identification window.

**Postconditions:**

1. The game resumes.

**Alternative flows:** none.



## Use Case: Error resolution.

**ID:** 5

**Description:** The player resolves incorrectly placed conveyors/congestion.

**Primary actor:** player.

**Secondary actor:** none.

**Preconditions:**

1. There is at least one error.

**Main flow:**

1. The use case begins when the player resolves the error by placing the associated conveyors in the correct position or resolving the congestion.
2. The system stops displaying the error icon.

**Postconditions:** 

1. The error icon disappears.

**Alternative flows:** none.

## Use Case: Consult log.

**ID:** 6

**Description:** The player clicks on the menu icon associated with the log.

**Primary actor:** player.

**Secondary actor:** none.

**Preconditions:**

1. Log icon available in the menu.

**Main flow:**

1. The use case begins when the player clicks on the log icon.
2. The system displays the warnings present in the log.
Extension Point: Clear fixed warnings.

**Postconditions:** 

1. The player is in the log window.

**Alternative flows:** none.

## Use Case: Clear fixed warnings.

**ID:** 7

**Description:** The player clicks on the button that removes warnings associated with fixed issues.

**Primary actor:** player.

**Secondary actor:** none.

**Preconditions:**

1. Log icon available in the menu.
2. There are errors that have already been fixed (error icon is no longer present).

**Main flow:**

1. The use case begins when the player selects the option to delete warnings associated with resolved errors from the log.
2. The system informs the player that the fixed warnings have been removed.
3. The system updates the log, leaving only warnings associated with unfixed errors.

**Postconditions:**

1. Log is updated.

**Alternative flows:** No fixed warnings.

## Alternative flow: No fixed warnings.

**ID:** 7.1

**Description:** The system informs the player that he has not fixed/has no warnings to fix in the log.

**Primary actor:** player.

**Secondary actor:** none.

**Preconditions:**

1. Log with no fixed warnings or warnings to fix.

**Main flow:**

1. The alternative scenario begins after step 1 of the main scenario.
2. The system informs the player that there are no fixed warnings to remove.

**Postconditions:** none.


## Use Case: Close log.

**ID:** 8

**Description:** The player clicks on the button to close the log.

**Primary actor:** player.

**Secondary actor:** none.

**Preconditions:** 

1. The player is on the window that displays the log information.

**Main flow:**

1. The use case begins when the player clicks the button to close the log.
2. The system closes the log.

**Postconditions:**

1. The game resumes.

**Alternative flows:** none.

### Review
*(Please add your use case review here)*
## Implementation documentation

For the implementation of user story 2, the subgroup held meetings to plan and develop the code associated with the proposed extension. During this process, we created two new classes: Warning and ConveyorLog, and added and/or changed methods in the classes: UI, DesktopInput, Conveyor.ConveyorBuild.

### Implementation summary
The first commit (792e508) made in the repository concerns the Warning class (implemented in core/src/mindustry/world/blocks/distribution/Warning.java). This class is used to represent a Warning type object that stores information about conveyor congestion/misplacing errors, namely the position where the error occurs.

The second commit (363bd59) refers to the ConveyorLog class (core/src/mindustry/world/blocks/distribution/ConveyorLog.java), which represents the object that stores warnings in the game i.e., errors not yet resolved by the player and the ones that were fixed but not cleared (it is like a browser history).

The third commit (ae81754) consists of an addition to the ConveyorLog class (printLog method) that allows us to display the warnings present in the log.

The fourth commit (0bd07d0) refers to the addition of a new button to the game interface, more precisely in the menu area, where we now have a button to access the Log. For this, we changed the buildPlacementUI method of the DesktopInput class (core/src/mindustry/input/DesktopInput.java).

The fifth commit (02ff140) is related to the third commit, which allows us to display the warnings present in the log. To achieve this goal, the showConveyorLog method was added to the UI class (core/src/mindustry/core/UI.java).

The sixth commit (94731b5) refers to the addition to the Conveyor class (core/src/mindustry/world/blocks/distribution/Conveyor.java), more precisely to the inner class ConveyorBuild of the showErrorSuggestion, didPlayerClick, and setFalse methods. These methods allow the player to click on the error and see a message identifying the cause of the error as well as a suggested solution.

The seventh commit (ab7f320) again refers to the UI class (core/src/mindustry/core/UI.java) and is associated with the previous commit. It consists of adding the showInfoWarning method, which allows the information to be displayed, as well as the “OK” button, which allows the player to exit the window showing the information/suggestion associated with the error.

The last commit (ce79037) refers to the Conveyor class, more specifically ConveyorBuild, and consists of adding the detectError method, used in the draw method of the same class, which allows us to draw and display the error icon in the associated locations. This method uses others previously mentioned, maintaining a logical alignment of the identification process and associated display.

The commits done later were associated with the code smells identified in ConveyorBuild class and the existence of a singleton pattern in our ConveyorLog class.

## Code snippets

## Warning.java
### Location: `core/src/mindustry/world/blocks/distribution/Warning.java`

````java

package mindustry.world.blocks.distribution;

public class Warning {

    protected static final String MISPLACED_TYPE = "misplaced";
    protected static final String MISPLACED_MESSAGE = "There appears to be a misplaced conveyor!\n\n Try changing your conveyor's direction or place a router.";
    protected static final String CONGESTED_TYPE = "congested";
    protected static final String CONGESTED_MESSAGE = "There appears to be clogging in your conveyor line!\n\n Try adding a destination, like a turret or your core.";

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

````

## ConveyorLog.java
### Location: `core/src/mindustry/world/blocks/distribution/ConveyorLog.java`
````java
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
    private static ConveyorLog instance = null;


    public ConveyorLog() {
        this.warnings = new HashSet<>();
    }

    public static ConveyorLog getInstance() {
        if (instance == null) {
            instance = new ConveyorLog();
        }
        return instance;
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

````

## ConveyorBuild.java
### Location: `core/src/mindustry/world/blocks/distribution/Conveyor.java`

````java

        public void draw(){
		//(…) 
            detectError();
        }

       private void showErrorSuggestion(Float x, Float y, Warning warning){
            if (didPlayerClick(x,y) && !openMenu) {
                openMenu = true;
                state.set(GameState.State.paused);
                switch(warning.getType()){
                    case (Warning.CONGESTED_TYPE):
                        ui.showInfoWarning(Warning.CONGESTED_MESSAGE, this);
                        break;
                    case (Warning.MISPLACED_TYPE):
                        ui.showInfoWarning(Warning.MISPLACED_MESSAGE, this);
                        break;
                }
            }
        }

        public void setFalse(){
            openMenu = false;
        }

        private void detectErrorAux(TextureRegion error, float x, float y, Warning warning) {
            showErrorSuggestion(x, y, warning);
            Draw.rect(error, x, y, tilesize, tilesize);
            log.addWarning(warning);
        }

        private void detectError(){
            Warning warning = new Warning(Warning.MISPLACED_TYPE, Warning.MISPLACED_MESSAGE, x, y);
            TextureRegion error = Core.atlas.find("error");
            if(!aligned && (nextc != null && !nextc.aligned)){
                if(rotation != nextc.rotation){
                    int orientation = rotation - nextc.rotation;
                    if(orientation % 2 == 0 ){
                        float errorX =(x + nextc.x)/2;
                        float errorY = (y + nextc.y)/2;
                        detectErrorAux(error, errorX, errorY, warning);
                    }
                }
                return;
            }
            if(nextc == null && !shouldAmbientSound()){
                warning = new Warning(Warning.CONGESTED_TYPE, Warning.CONGESTED_MESSAGE, x, y);
                detectErrorAux(error, x, y, warning);
            }
        }

        private boolean didPlayerClick(float x , float y) {
            return (
                    Core.input.mouseWorldX() >= x - tilesize/2.0f
                            && Core.input.mouseWorldX() <= x + tilesize/2.0f
            )
                    && (
                    Core.input.mouseWorldY() >= y - tilesize/2.0f
                            && Core.input.mouseWorldY() <= y + tilesize/2.0f
            )
                    && Core.input.keyTap(KeyCode.mouseLeft);
        }


````

## DesktopInput.java
### Location: `core/src/mindustry/input/DesktopInput.java`

````java

public void buildPlacementUI(Table table){
	//(…)

        table.button(Icon.file, Styles.clearNonei, () -> {
            ui.conveyorLog.printLog();
        }).tooltip("Warning Log");

	//(…)
    }

````

## UI.java
### Location: `core/src/mindustry/core/UI.java`

````java

    public void showInfoWarning(String info, Conveyor.ConveyorBuild conveyor){
        new Dialog(""){{
            getCell(cont).growX();
            cont.margin(15).add(info).width(400f).wrap().get().setAlignment(Align.center, Align.center);
            buttons.button("@ok", () -> {
                player.shooting = false;
                state.set(GameState.State.playing);
                this.hide();
                conveyor.setFalse();

            }).size(110, 50).pad(4);

        }}.show();
    }

    public void showConveyorLog(String info) {
        new Dialog("                      Conveyor Log\n"){{
            getCell(cont).growX();
            cont.margin(0).add(info).width(400f).get().setAlignment(Align.top, Align.center);
            buttons.button("Exit Log", this::hide).size(110, 50).pad(4);
            buttons.button("Clear Fixed", () ->{
                ui.showInfoFade(conveyorLog.getNumberOfWarnings() == 0 ? ConveyorLog.NO_CLEARED_WARNINGS : ConveyorLog.CLEAR_MESSAGE);
                conveyorLog.removeAll();
                this.hide();
            }).size(110, 50).pad(4);
            keyDown(KeyCode.enter, this::hide);
            closeOnBack();
        }}.show();
    }

````

#### Review
*(Please add your implementation summary review here)*

### Class diagram

The class diagram represents the implementation done in the source code, summarizing it and illustrating the classes where the changes were made, as well as the ones that were important for the implementation.

![alt text](US_classDiagram.png)

### Review
*(Please add your class diagram review here)*

### Sequence diagrams

![Sequence Diagram #1: Misplace a Conveyor](sequence_diagram_1.png)

#### The sequence diagram above describes the user misplaying a conveyor: the player places the conveyor, specifically a misplaced one, as labeled by the condition, which creates a warning and draws the error. The latter cannot be interrupted.


![Sequence Diagram #2: Cause Congestion in a Conveyor](sequence_diagram_2.png)

#### The sequence diagram above describes the user misplaying a conveyor: the player places the conveyor, specifically a misplaced one, as labeled by the condition, which creates a warning and draws the error. The latter cannot be interrupted.


![Sequence Diagram #3: Obtain Error Details](sequence_diagram_3.png)

#### The sequence diagram above describes the user clicking on a warning's icon to obtain more information about it: by doing so, the player interacts with the `ConveyorBuild`, which in turn makes a call to `UI`. The action cannot be interrupted.


![Sequence Diagram #4: Confirm Error Acknowledgement](sequence_diagram_4.png)

#### The sequence diagram above describes the user clicking on the OK button while on the warning screen: The player directly interacts with the UI, which hides the menu and changes a flag that represents whether the menu is open.


![Sequence Diagram #5: Solve an Error](sequence_diagram_5.png)

#### The sequence diagram above describes the user fixing an existing error/warning in the conveyors, either by following the suggested solutions or by simply removing the conveyor. The Conveyor is always destroyed or updated.


![Sequence Diagram #6: Consult Log](sequence_diagram_6.png)

#### The sequence diagram above describes the user clicking on the log button in the menu in order to consult it. This consists in a simple showing action from the UI.


![Sequence Diagram #7: Clear Fixed Warnings](sequence_diagram_7.png)

#### The sequence diagram above describes the user clearing all the existing warnings in the Conveyor Log. The message shown depends on whether the player has any warnings in the log at the time the button is pressed.


![Sequence Diagram #8: Close Log](sequence_diagram_8.png)

#### The sequence diagram above describes the user exiting the Conveyor Log. It simply consists in the UI being hidden due to an interaction from a the player.

#### Review
*(Please add your sequence diagram review here)*
## Test specifications
(*Test cases specification and pointers to their implementation, where adequate.*)
### Review
*(Please add your test specification review here)*
