# StatePattern 
### Location: 
- `/core/src/mindustry/game/GameState.java`
- `/core/src/mindustry/game/states/ (PlayState, MenuState, EditorState, LoadingState, etc.)`

## Class diagram (simplified)
![Class diagram (StatePattern)](data/ClassDiagramStatePattern.png)

## Illustrating code snippet

`GameState.java`:
```Java
// ...existing code...
public class GameState {
    private State current;

    public void setState(State s){
        if(current != null) current.exit();
        current = s;
        current.enter();
    }

    public void update(float delta){
        if(current != null) current.update(delta);
    }
}
// ...existing code...
```
`PlayState.java`:
```Java
public class PlayState implements State {
    @Override public void enter(){ /* init play */ }
    @Override public void update(float dt){ /* gameplay update */ }
    @Override public void exit(){ /* cleanup */ }
}
```
## Rationale of why Gamestate and states corresponds to a StatePattern:

- Context (GameState) keeps a references to States and delegates handling to them.
- Concrete states encapsulate mode-specific behavior (menu, play, editor).
- Switching behavior is achieved by swapping the current State object (no if/switch branching).