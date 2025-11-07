# **Design Patterns Analysis**

## **Design Pattern 1: Memento**

### Location: `core/src/mindustry/editor/DrawOperation.java`

### Analysis:
    - MapEditor class alters current state by using addTileOp (Originator)
    - DrawOperation class acts as a "snapshot" of the current state (Memento)
    - OperationStack class keeps track of and saves changes to state, and "pops" the top of stack when an "undo" is requested (Caretaker)

### Code Snippet:
```java
public class MapEditor {
    public void addTileOp(long data) {
        if (loading) return;

        if (currentOp == null) currentOp = new DrawOperation();
        currentOp.addOperation(data);

        renderer.updateStatic(TileOp.x(data), TileOp.y(data));
    }
}
 

public class DrawOperation {
     private LongSeq array = new LongSeq();

     public boolean isEmpty(){
         return array.isEmpty();
     }

     public int size(){
         return array.size;
     }

     public void remove(int amount){
         array.setSize(Math.max(0, array.size - amount));
     }

     public void addOperation(long op){
         array.add(op);
     }

     public void undo(){
         for(int i = array.size - 1; i >= 0; i--){
             updateTile(i);
         }
     }

     public void redo(){
         for(int i = 0; i < array.size; i++){
             updateTile(i);
         }
     }
 }
 
public class OperationStack{
    
    public void add(DrawOperation action){
        stack.truncate(stack.size + index);
        index = 0;
        stack.add(action);

        if(stack.size > maxSize){
            stack.remove(0);
        }
    }
    public void undo(){
        if(!canUndo()) return;

        stack.get(stack.size - 1 + index).undo();
        index--;
    }

    public void redo(){
        if(!canRedo()) return;

        index++;
        stack.get(stack.size - 1 + index).redo();

    }
}
```

![Memento](memento.png)

## **Design Pattern 2: Template**

### Location: `core/src/mindustry/type/Weather.java`

### Analysis:
    - Weather class is used as a base for RainWeather and ParticleWeather, meaning that these do not need to reimplement other methods, only those where its algorithm differs

### Code Snippet:
```java
public class Weather extends UnlockableContent{
    public void drawOver(WeatherState state){

    }
}
public class RainWeather extends Weather {
    public void drawOver(WeatherState state) {
        drawRain(sizeMin, sizeMax, xspeed, yspeed, density, state.intensity, stroke, color);
    }

public class ParticleWeather extends Weather {
    public void drawOver(WeatherState state){

            float windx, windy;
            if(useWindVector){
                float speed = baseSpeed * state.intensity;
                windx = state.windVector.x * speed;
                windy = state.windVector.y * speed;
            }else{
                windx = this.xspeed;
                windy = this.yspeed;
            }

            if(drawNoise){
                if(noise == null){
                    noise = Core.assets.get("sprites/" + noisePath + ".png", Texture.class);
                    noise.setWrap(TextureWrap.repeat);
                    noise.setFilter(TextureFilter.linear);
                }

                float sspeed = 1f, sscl = 1f, salpha = 1f, offset = 0f;
                Color col = Tmp.c1.set(noiseColor);
                for(int i = 0; i < noiseLayers; i++){
                    drawNoise(noise, noiseColor, noiseScale * sscl, state.opacity * salpha * opacityMultiplier, sspeed * (useWindVector ? 1f : baseSpeed), state.intensity, windx, windy, offset);
                    sspeed *= noiseLayerSpeedM;
                    salpha *= noiseLayerAlphaM;
                    sscl *= noiseLayerSclM;
                    offset += 0.29f;
                    col.mul(noiseLayerColorM);
                }
            }

            if(drawParticles){
                drawParticles(region, color, sizeMin, sizeMax, density, state.intensity, state.opacity, windx, windy, minAlpha, maxAlpha, sinSclMin, sinSclMax, sinMagMin, sinMagMax, randomParticleRotation);
            }
        }
    }
}
```
![Template](template.png)

