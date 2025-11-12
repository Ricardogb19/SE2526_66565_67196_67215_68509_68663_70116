# **Code Smells Analysis**

## *Long Method / God class (Blocks.load)*

### Location: `core/src/mindustry/content/Blocks.java`

### Description:

The method `public static void load()` in `Blocks.java` has hundreds of block, drawer, and effect definitions in a single method. This makes it extremely long and it obliges it to take on many responsibilities: defining, configuring and wiring game content all at once.

### Analysis:

- Violates Single Responsibility Principle:
    - A function or method should do one thing and do it well. In this case, it clearly does not do near to only one thing.
- Insanely hard to read and maintain:
    - Contributers will have a very hard time trying to understand and find the code they need.
- Hard to test or reuse:
    - Individual pieces can't be exercised in isolation.

### Ideal minimal refactoring:

- Organize loader methods by catrgory and extract them from the `load()` method, for example `loadEnvironmentBlocks()`, `loadProductionBlocks()`, `loadPowerBlocks()`, `loadTurretBlocks()` and call them from `load()`.


Minimal sketch:

```java
public static void load(){
		loadEnvironmentBlocks();
		loadPowerBlocks();
        loadProductionBlocks();
		loadTurretBlocks();
}

private static void loadEnvironmentBlocks(){
	// moved Environment block definitions from the method load()
}
```

## *Long Parameter List*

### Location: `/core/src/mindustry/net/NetworkIO.java`

### Description:

The method `public static Host readServerData()` in `NetworkIO.java` makes a call to a constructor with a large amount of fields (`new Host(ping, host, hostAddress, hostPort, map, wave, players, version, vertype, gamemode, limit, description, modeName)`)

### Analysis:

- The usage of `new Host(...)`is hard and the order of the parameters is difficult to remember.
- Arguments can be easily passed in the wrong order.
- It's a sign that an object parameter should exist.


### Ideal minimal refactoring:

Create new class HostInfo():

```java
package mindustry.net;

public class HostInfo {
    public int ping;
    public String host;
    public String hostAddress;
    public int hostPort;
    public String map;
    public int wave;
    public int players;
    public String version;
    public String vertype;
    public String gamemode;
    public int limit;
    public String description;
    public String modeName;

    private HostInfo() {}

    public static class Builder {
        private final HostInfo info = new HostInfo();

        public Builder ping(int ping){ info.ping = ping; return this; }
        public Builder host(String host){ info.host = host; return this; }
        public Builder hostAddress(String hostAddress){ info.hostAddress = hostAddress; return this; }
        public Builder hostPort(int hostPort){ info.hostPort = hostPort; return this; }
        public Builder map(String map){ info.map = map; return this; }
        public Builder wave(int wave){ info.wave = wave; return this; }
        public Builder players(int players){ info.players = players; return this; }
        public Builder version(String version){ info.version = version; return this; }
        public Builder vertype(String vertype){ info.vertype = vertype; return this; }
        public Builder gamemode(String gamemode){ info.gamemode = gamemode; return this; }
        public Builder limit(int limit){ info.limit = limit; return this; }
        public Builder description(String description){ info.description = description; return this; }
        public Builder modeName(String modeName){ info.modeName = modeName; return this; }

        public HostInfo build(){ return info; }
    }
}
```

Change Host constructor to accept HostInfo:

```Java
public Host(HostInfo info){
        this.ping = info.ping;
        this.host = info.host;
        this.hostAddress = info.hostAddress;
        this.hostPort = info.hostPort;
        this.map = info.map;
        this.wave = info.wave;
        this.players = info.players;
        this.version = info.version;
        this.vertype = info.vertype;
        this.gamemode = info.gamemode;
        this.limit = info.limit;
        this.description = info.description;
        this.modeName = info.modeName;
    }
```

Replace the call at `NetworkIO.readServerData()`:

```Java
HostInfo info = new HostInfo.Builder()
    .ping(ping)
    .host(host)
    .hostAddress(hostAddress)
    .hostPort(hostPort)
    .map(map)
    .wave(wave)
    .players(players)
    .version(version)
    .vertype(vertype)
    .gamemode(gamemode)
    .limit(limit)
    .description(description)
    .modeName(modeName)
    .build();

Host hostObj = new Host(info);
```

## *Shotgun Surgery*

### Location: 
- `/core/src/mindustry/type/UnitType.java`
- `/core/src/mindustry/type/Weapons.java`
- `/core/src/mindustry/content/Blocks.java`

### Description:
Many content definitions duplicate the same weapon/bullet/property assignments. A single conceptual change 
(e.g. rename or default change) forces edits in many files.

### Analysis:
- High coupling across content definitions.
- Content duplication increases maintenance cost and risk of inconsistent changes.

### Ideal minimal refactoring:

- Introduce small presets for common elements (bullets, weapon defaults, effect ids). Update content files to reference
presets rather that repeating values in different places.
- Do this incrementally: add a preset and migrate a few definitions to avoid breaking the program, as the coupling is 
very tight.

##### Minimal example of implementation:
```java
package mindustry.content;

/** Imports... */

public final class ContentPresets {
    private ContentPresets(){}

    public static Weapon simpleMount(String name, float reload, float x, float y){
        (...)
    }

    public static BasicBulletType defaultSmallBullet(float speed, float damage){
        (...)
    }
}
```