# **Code Smells Analysis**

## *Speculative Generality*

### Location: `core/src/mindustry/world/blocks/ItemSelection.java`

### Description:
ItemSelection defines seven overloaded buildTable() methods, all of which simply forward arguments to the final full parameter version. Many parrameters are rarely used or always passed with default values. This leads to excessive generality that is not currently needed.

### Analysis:
The class was prepared for future cases instead of current needs.
Too many overloads supporting parameter combinations that are rarely or never used.

### Code snippet:
```java
public static <T extends UnlockableContent> void buildTable(Table table, Seq<T> items, Prov<T> holder, Cons<T> consumer){
    buildTable(table, items, holder, consumer, true);
}

public static <T extends UnlockableContent> void buildTable(Table table, Seq<T> items, Prov<T> holder, Cons<T> consumer, boolean closeSelect){
    buildTable(null, table, items, holder, consumer, closeSelect, 5, 4);
}

public static <T extends UnlockableContent> void buildTable(Table table, Seq<T> items, Prov<T> holder, Cons<T> consumer, int columns){
    buildTable(null, table, items, holder, consumer, true, 5, columns);
}

// ... and several more overloads delegation to the same method...
```

### Ideal minimal refactoring:
Keep only one simple public method with default behavior.
Provide a single configuration object.

## *Shotgun Surgery*

### Location: `core/src/mindustry/world/Build.java`

### Description:
The method validPlaceIgnoreUnits() performs block placement validation using a massive set of rules, conditions and interactions with multiple systems (darkness, liquids, fog of war, etc.). Game-wide rule changes require updating multiple conditions inside this huge method.

### Analysis:
It becomes nearly impossible to change or expand placement rules without breaking others. Any new placement rule forces edits in several unrelated conditional branches.

### Code snippet:
```java
public static boolean validPlaceIgnoreUnits(Block type, Team team, int x, int y, int rotation, boolean checkVisible, boolean checkCoreRadius){
    if(
            check == null || //nothing there
                    (type.size == 2 && world.getDarkness(wx, wy) >= 3) ||
                    (state.rules.staticFog && state.rules.fog && !fogControl.isDiscovered(team, wx, wy)) ||
                    (check.floor().isDeep() && !type.floating && !type.requiresWater && !type.placeableLiquid) || //deep water
                    (!state.rules.derelictRepair && check.team() == Team.derelict && check.build != null) ||
                    (type == check.block() && check.build != null && rotation == check.build.rotation && type.rotate && !((type == check.block && team != Team.derelict && check.team() == Team.derelict))) || //same block, same rotation
                    !check.interactable(team) || //cannot interact
                    !check.floor().placeableOn && !type.ignoreBuildDarkness || //solid floor
                    //when you have a payload, you cannot place blocks on things, even if normal placement rules allow it. this is a hack that assumes checkVisible = true means it's coming from a payload
                    (!checkVisible && checkCoreRadius && !check.block().alwaysReplace) || //replacing a block that should be replaced (e.g. payload placement)
                    !(((type.canReplace(check.block()) || (check.build != null && check.build.canBeReplaced(type)) || (type == check.block && team != Team.derelict && check.team() == Team.derelict)) || //can replace type OR can replace derelict block of same type
                            (check.build instanceof ConstructBuild build && build.current == type && check.centerX() == tile.x && check.centerY() == tile.y)) && //same type in construction
                            type.bounds(tile.x, tile.y, Tmp.r1).grow(0.01f).contains(check.block.bounds(check.centerX(), check.centerY(), Tmp.r2))) || //no replacement
                    (type.requiresWater && check.floor().liquidDrop != Liquids.water) //requires water but none found
    ) return false;
}
```
### Ideal minimal refactoring:
Extract each logical rule into small validator classes. Compose them inside the main validation function. This isolates rule changes to small methods instead of one giant block.

## *Long Method*

### Location: `core/src/mindustry/ClientLauncher.java`

### Description:
Setup() is over 150+ lines long, performing dozens of unrelated tasks: configuring directories, loading assets, initializing game systems, and more.

### Analysis:
Difficult to test as it requires full game environment to run. Hard to maintain, changes to any subsystem require editing this one central method.

### Code snippet:
```java
//line 37

public void setup(){
    String dataDir = System.getProperty("mindustry.data.dir", OS.env("MINDUSTRY_DATA_DIR"));
    if(dataDir != null){
        Core.settings.setDataDirectory(files.absolute(dataDir));
    }
    //.
    //.
    //.

    assets.load(mods);
    assets.loadRun("mergeUI", PixmapPacker.class, () -> {}, () -> Fonts.mergeFontAtlas(atlas));

    add(logic = new Logic());
    add(control = new Control());
    add(renderer = new Renderer());
    add(ui = new UI());
    add(netServer = new NetServer());
    add(netClient = new NetClient());

    assets.load(schematics);

    assets.loadRun("contentinit", ContentLoader.class, () -> content.init(), () -> content.load());
    assets.loadRun("baseparts", BaseRegistry.class, () -> {}, () -> bases.load());
}
```

### Ideal minimal refactoring:
Split the setup into cohesive private methods, each handling one subsystem and then call them in order from setup().


