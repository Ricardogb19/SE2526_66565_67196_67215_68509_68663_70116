# **Code Smells Analysis**

## *Speculative Generality*

### Location: `ore\src\mindustry\ui\Menus.java`

### Description: 
The whole class is not used.

### Analysis
The existence of this code smell, can lead to a generality that may not help, even risks "over-engenner". The best practive is just in time design.

### Ideal minimal refactoring:
The best refactoring its deleting the whole class.



## *Long method*

### Location: `Core/src/mindustry/maps/Generators/FieMapGenerator.java`

### Description: 
This class, has a method that it´s too big its 83 lines.

### Analysis
The existence of this code smell, makes it hard to understand the code.

### Ideal minimal refactoring:
The best refactoring it's to extract 5 methods and call them in the main method, leaving a cleaner and easier to understand conde.

Minimal sketch:

```java
private void loadMapData(Sector sector) {}
private void clearSpawnOverlays(Tiles tiles) {}
private boolean placeCoreWithOverride(Tiles tiles, Block coreTypeToUse, WorldParams params) {}
private boolean placeDefaultCore(Tiles tiles, Block coreTypeToUse) {}
private void placeCoreOrLoadout(Tile tile, Block coreTypeToUse) {}

```


## *message chain*

### Location: `core/src/mindustry/net/ArcNetProvider.java`

### Description: 
This class, has a message chain, calls multiple methods from a class, it can lead to thoughness and higher complexity.

### Analysis
The existence of this code smell, it leads to thoughness and higher complexity, make it hard to read and understtand every step.

### Ideal minimal refactoring:
The best refactoring it's to have a method on that class that does wha's needed, given that this message chain apeers at least tow times in this class i would say that a method on the class Connection would be best.

```java
//classe conection
public Connect addressTCP (){
 
  InetSocketAddress a = getRemoteAddressTCP();
  InetAddress aux = a.getAddress();
  String ad=  aux.getHostAdress();


}

