# **Code Smells Analysis**

## *Data Clumps*

### Location: `core\src\mindustry\world\Build.java`

### Description: 
In this class, we can see the variables int x, int y, and int rotation appearing together in numerous parts of the code.

### Analysis:
The existence of this code smell can lead to duplication and errors when calling methods, especially if the order of the parameters is reversed.

### Ideal minimal refactoring:
These variables are passed as method parameters, so we can apply the Introduce Parameter Object refactoring to group them into a single class, and then using an object of this class instead of passing the parameters together. When doing this, we need to be careful not to create a data class, as that would introduce a new code smell.

Minimal sketch:

```java

public final class Pos {
    public final int x;
    public final int y;
    public final int rotation;

    public Pos(int x, int y, int rotation){
        this.x = x;
        this.y = y;
        this.rotation = rotation;

    (…)
/* the class must have more methods not only the ones associated with the saving data*/
    }
}
```

## *Message Chains*

### Location: `core\src\mindustry\world\blocks\campaign\LandingPad.java`

### Description: 
The class has numerous methods with long message chains.

### Analysis:
This code smell can cause complexity in the design and make the code more difficult to test independently.

### Ideal minimal refactoring:
We can create new methods in the class that delegate the calls to the immediate neighbor. Then we can replace the message chains with calls to these methods.

```java

private Planet curPlanet(){ 
    return state.getPlanet(); 
}
private CampaignRules campRules(){ 
    return curPlanet().campaignRules; 
}

// before
if(state.getPlanet().campaignRules.legacyLaunchPads){ ... }
// after
if(campRules().legacyLaunchPads){ ... }
````

## *Speculative Generality*

### Location: `core\src\mindustry\world\blocks\payloads\PayloadBlock.java`

### Description: 
The class has methods that are not used. 

### Analysis:
In this class, there are some methods that are never used. This may be due to anticipated future features that were never implemented. However, keeping such methods can make the code harder to understand and maintain.

### Ideal minimal refactoring:
Remove the unused methods. Implement only what is currently needed (apply the Just-In-Time design principle).

```java

//unused methods examples
public void drawTeamTop(){
    carried = false;
}

 
public Payload takePayload(){
    T t = payload;
    payload = null;
    return t;
}


public void onDestroyed(){
    if(payload != null) payload.destroyed();
    super.onDestroyed();
}

````







