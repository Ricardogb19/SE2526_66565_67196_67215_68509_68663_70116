## Code smell 1 : long parameter list


### Snippet
```java
 public void drawArcLine(VertexBatch3D batch, Vec3 a, Vec3 b, Color from, Color to, float length, float timeScale, int pointCount, float stroke); 
```
### Path
    core/src/mindustry/type/Planet.java
    
    In methods drawArcLine(608) and drawArc(586)

### Refactoring
    Since this parameter list is used more than once, it would be a good idea to creat an "arc" object, making it much easier to understand the code and promoting reusability 
## Code smell 2: data clumps
### Snippet
```java
private static void completeDamage(Team team, float x, float y, float radius, float damage);
public static void damage(Team team, float x, float y, float radius, float damage);
public static void tileDamage(Team team, int tx, int ty, float baseRadius, float damage, @Nullable Bullet source);
```
### Path
    core/src/mindustry/entities/Damage.java
### Refactoring
    Since most of the methods of this class use the same parameters, it would be wise to refactor these into a single Object that can be reused and perchance inherit some of these method to avoid creating a data class code smell
## Code smell 3: data class
```java
public class TargetPriority{
    public static final float
    //nobody cares about walls
    wall = -3f,
    //anything that has underBullets gets this priority (it's probably still more important than a wall)
    under = -2f,
    //transport infrastructure isn't as important as factories
    transport = -1f,
    //most blocks
    base = 0f,
    //turrets deal damage so they are more important
    turret = 1f,
    //core is always the most important thing to destroy
    core = 2f;
}

```
### Path
    core/src/mindustry/entities/TargetPriority.java

Found through code metric NOM = 0.

### Refactoring
    This class has no methods whatsoever, making it a poor use of abstraction. A workaround this problem would be to move it to the classes that use it.