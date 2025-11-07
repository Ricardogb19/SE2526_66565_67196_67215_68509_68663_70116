# **Code Smells Analysis**

## *Speculative Generality*

### Location: `core/src/mindustry/game/EventType.java`

### Description: 
There is a class, declared inside this class, that has no usages. It is called `ServerLoadEvent`. It is never used and therefore was declared inside `EventType` simply because it may be useful in the future.

### Analysis:
This code smell does not solve any problem regarding the proper function of the game; it simply worsens its source code's readability.

### Ideal minimal refactoring:

Since this does not seem to be a concern of the developer right now, the easiest refactor would be simply to delete the one line of code that defines said class; this way, the code becomes closer to having just what is necessary. Should something related to servers be a problem in the future, any of the contributors can go back to this functionality, thus defining it; as of right now, it does not seem to serve any purpose. 

Minimal sketch:

```java
    public class EventType {
    //Rest of the class
    public static class SaveWriteEvent{}
    public static class ClientCreateEvent{}
    //ServerLoadEvent used to be here but has since been removed.
    public static class DisposeEvent{}
    public static class PlayEvent{}
    //Rest of the class
    }
```


## *Duplicated Code*

### Location: `core/src/mindustry/game/FogControl.java`

### Description: 
There is code in this class, more specifically in the `stop()` method, that does the same verification for different instances of a `Thread` class; it also does the same operations, regardless of the thread type.

### Analysis:
This code smell makes the code harder to maintain: it makes it more confusing to read and understand. It also does not promote easy maintainability, as, if an error in one of these verifications for a specific `Thread` instance is found, it is highly likely that the error is in all of the duplicated code snippets, and thus requires several changes for just one error.

### Ideal minimal refactoring:

Creating a method that receives a `Thread` type and makes said verifications and operations inside the function makes it more concise, simpler and easier to maintain.

Minimal sketch:

```java
    private void stopThread(Thread t) {
        if (t != null) {
            t.interrupt();
            t = null;
        }
    }    
```



## *Primitive Obsession*

### Location: `core/src/mindustry/io/units/SaveMeta.java`

### Description: 
This class simply has a builder, which defines/sets values. It has no real funcionality besides that.

### Analysis:
This code smell ends up not being easy to read and making it so that this class is really just a container for all info. It needs more functionality besides that.

### Ideal minimal refactoring:

Creating a method called `saveFileInfo()`, which uses the information present in the class and returns it in the format of a string, allows for the player to receive useful info about its save file(s) and makes this class able to execute more tasks besides just storing data.

Minimal sketch:

```java
    public class SaveMeta{
        //Instance variables and builder
        public String saveFileInfo() {
            return ("Version:%d\n
                    Build:%d\n
                    Time Played:%ld\n
                    ", version, build, timePlayed);
        }
        //Rest of the class
    }  
```




