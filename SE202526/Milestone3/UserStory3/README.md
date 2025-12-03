# User story 3
New anti-air defense turret.
## Author(s)
- Afonso Rodriguez (66565)
- Rafael Soares (70116)
## Reviewer(s)
- Raksana Udagedara (67196)
- Guilherme Neto (68663)
## User Story:
**As a** veteran player, **I want to** orchestrate a new defensive strategy by pulling enemy air units to the ground, **so that I can** play the game in a more creative way.
### Review
The user story contributes to the strategic component of the game, proposing a creative feature to balance and improve defense in possible combat situations, aligning with the logic of the game.
This concept is quite outside the box and well thought out, allowing for changes to existing strategies and even the creation of new tactics. It promotes diversity in gameplay and a higher skill ceiling for more advanced players.
However, this user story could be clearer in the concept of veteran player: it is difficult to say whether it is a player who has been playing the game for a long time, or a player who has reached a certain level/milestone in the game.
## Use case diagram
![use_case.png](use_case_diagram.png)
## Use case textual description
## Use Case: PlaceVinesTurret
**ID:** 1

**Description:** Player builds a new vines turret.

**Primary Actor:** Player.

**Secondary Actor:** None.

**Precondition:**
1. The user has enough materials to build a new turret.
2. The user has build mode enabled.

**Main Flow:**
1. This use case starts when the player selects the vines turret in the turrets' sub-menu.
2. The system provides a blueprint that follows the mouse around, indicating where the turret will be placed.
3. The player clicks the left mouse button.
4. The system places a new instance of a vines turret.

**Postconditions:**
1. A new turret is built in place of the blueprint.
2. The required materials to build a vines turret are consumed from the player's core.

**Alternative flows:**
1. CancelPlacemnt
2. OverlappingBuild

### Alternative flow: PlaceVinesTurret:CancelPlacement
**ID:** 1.1

**Description:** Player decides not to build a new turret.

**Primary Actor:** Player.

**Secondary Actor:** None.

**Alternative Flow:**
1. The alternative flow begins after step 2 of the main flow.
2. The player presses the cancel building button.

**PostCondition:**
1. A new VinesTurret has not been built.
2. No resources were consumed.

### Alternative flow: PlaceVinesTurret:OverlappingBuild
**ID:** 1.2

**Description:** Player tries to build a new turret on an invalid tile.

**Primary Actor:** Player.

**Secondary Actor:** None.

**Alternative flow:**
1. The alternative flow begins after step 2 of the main flow.
2. If the turret's blueprint is on top of an already built block or terrain

   2.1 The blueprint turns red, indicating it is an invalid position.
3. If the player clicks to build, while the blueprint is red

   3.1 The system does not built the turret.

   3.2 The system does not consume the building materials.
4. The alternative flow returns to step 2

**PostCondition:**
1. A new VinesTurret has not been built.
2. No resources were consumed.

## Use Case: ManuallyLoadAmmo
**ID:** 2

**Description:** Player loads the placed vines turret with ammo.

**Primary Actor:** Player.

**Secondary Actor:** None.

**Precondition:**
1. There is a vines turret placed without full ammo.
2. The player has at least 1 copper or lead.
3. The game isn't paused.

**Main Flow:**
1. This use case starts when the player starts dragging a specific ammo type into the turret.
2. Include (PlaceVinesTurret).
3. The player drops the materials onto the turret to load it.

**Postconditions:**
1. The selected turret's ammo amount has been increased.
2. The materials used for loading ammo have been removed from the player's inventory.

**Alternative flows:**
1. MisplacedAmmoDrop

### Alternative flow: ManuallyLoadAmmo:MisplacedAmmoDrop
**ID:** 2.1

**Description:** Player dropped the ammo somewhere wrong.

**Primary Actor:** Player.

**Secondary Actor:** None.

**Alternative flow:**
1. The alternative flow begins after step 1 of the main flow.
2. The system doesn't consume any materials, due to wrong placement.
3. The alternative flow returns to step 1 of the main flow.

**Postconditions:**
1. The materials used for loading ammo didn't get removed from the player's inventory.

## Use Case: UpgradeVinesTurret
**ID:** 3

**Description:** Player upgrades a specific vines turret.

**Primary Actor:** Player.

**Secondary Actor:** None.

**Precondition:**
1. There is a vines turret placed that isn't max level.
2. The player has the sufficient materials for the next upgrade.

**Main Flow:**
1. This use case starts when the player presses the upgrade key.
2. Include (PlaceVinesTurret).
3. The turret gets upgraded to the next level.

**Postconditions:**
1. The player gets the materials needed for the upgraded removed from his core.

**Alternative flows:**
1. WrongTile.

### Alternative flow: UpgradeVinesTurret:WrongTile
**ID:** 3.1

**Description:** Tile selected isn't a vines turret.

**Primary Actor:** Player.

**Secondary Actor:** None.

**Alternative flow:**
1. The alternative flow begins after step 1 of the main flow.
2. The player receives the notice that he tried upgrading something unsuccessfully.
3. The alternative flow returns to step 1 of the main flow.

**Postconditions:**
1. The turret doesn't get upgraded.
2. The player doesn't lose any materials.
### Review
*(Please add your use case review here)*
## Implementation documentation
### Relevant classes to the implementation of User Story 3
![code_evolution_diagram.png](simple_class_diagram.png)
The main challenges were figuring out how to create a new turret block that could be placed by the players, and how to make it target only air units and force them into the ground. To overcome this problem we had to create a new type of bullet, and make it so that if any of the bullets fired collides with air units, they become grounded.
### Relevant code snippets
`core/src/mindustry/content.Blocks.java`
```java
vines = new ItemTurret("vines"){{
    requirements(Category.turret, with(Items.copper, 1600, Items.lead, 550, Items.graphite, 600, Items.surgeAlloy, 750, Items.silicon, 400));
    targetGround = false;     
    .
    .
    .
    ammo(Items.copper,  new BasicBulletType(2, 0){{
            instantDisappear = true;
            shootSound = empZap;
            fragBullet = new VineEmpBulletType() {{
                collidesGround = false;
            .   
            .
            .
            }};
    }};
    .
    .
    .
}}
```
`core/src/mindustry/entities/bullet.VineEmpBulletType.java`
```java
if(unit.isFlying()){
    unit.apply(StatusEffects.unmoving, 500f);
    unit.apply(StatusEffects.disarmed, 500f);
    unit.elevation = 0.0f;
}
```
### Implementation summary
Our implementation began by incorporating a custom turret sprite obtained from the official Mindustry spriting community discord. Additionally, a custom firing sound (empZap) was introduced and linked to the turret's shooting behavior. 
A new turret block, vines, was added inside Blocks.java using ItemTurret.java as the base class. Most of its core parameters were configured to our desired goal. The turret supports two ammo types, both mapped to fragmentation bullets. 
These fragments are the ones that generate the actual effect using a custom bullet type we created. The key feature of the bullet is the ability to force airborne units to the ground, disabling their mobility for a specific duration. This combination temporarily suppresses and disarms flying units, making them vulnerable to ground-based defenses.
### Review
*(Please add your implementation summary review here)*
### Class diagrams
(*Class diagrams and their discussion in natural language.*)
### Review
*(Please add your class diagram review here)*
### Sequence diagrams
(*Sequence diagrams and their discussion in natural language.*)
#### Review
*(Please add your sequence diagram review here)*
## Test specifications

Each test image has an embedded link to a video example
[![test1](test_case_1.png)](https://youtu.be/xJNpCtmNFtg)
[![test2](test_case_3.png)](https://youtu.be/kn7uTUjkd-I)
[![test3](test_case_2.png)](https://youtu.be/Rk-FimCNTfs)
### Review
*(Please add your test specification review here)*
