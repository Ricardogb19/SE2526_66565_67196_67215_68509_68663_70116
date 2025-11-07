# Use Cases
## Use case: Encontrar objetos.
ID:UC11

Description: The player's current file is saved.

Main Actor: Player

Secondary Actors: None

Main Flow:
1. The use case begins when the player moves around the map searching for an object.

2. The player finds an object.

3. The player checks whether it is the object they intended to find.

Post-conditions: none.

Alternative Flows: The player doesn't find the object


## Use case: Remove objects
ID:UC12

Description: Remove a drill from the player’s map.

Primary Actor: Player

Secondary Actors: None

Preconditions: The player has found an object.

Main Flow:

1. The use case begins when the player selects a drill on their map.

2. The player emits “rays” toward the targeted drill.

3. The system removes the drill.

4. The system updates the player’s map.

Post conditions: The player’s catalog is updated, and the map reflects the change.

Alternative Flows: None.


## Use case: Change planet
ID:UC13

Description: Change planet.

Primary Actor: Player.

Secondary Actors: None.

Preconditions: Has more than one planet.

Main Flow:

1. The use case begins when the player selects the planet map.

2. The player selects a planet.

3. The system dynamically updates the player’s position and sectors.

4. The system updates the search tree.

Postconditions: The search tree is updated.

Alternative Flows: None.



## Use case: Build defenses
ID:UC14

Description: The player builds defenses to protect themselves from enemies.

Primary Actor: Player

Secondary Actors: None.

Preconditions: The player has sufficient resources.

Main Flow:

1. The use case begins when the player is in the location where they want to build defenses.

2. The player has the necessary resources to construct the defenses.

3. Before building, the system indicates the position and range of the turret.

4. The defenses are built.

Postconditions: The player has a turret placed at the chosen position, if the position is valid.

Alternative Flows: None.


## Use case: Energy criation
ID:UC15

Description: Energy.

Primary Actor: Player

Secondary Actors: None

Preconditions: Generators exist.

Main Flow:

1. The use case begins when the player collects enough resources.

2. The player moves to the machine/generator.

3. The player supplies the resources to the generator.

4. The generator produces energy.

5. The energy is transferred to the electrical network.

Postconditions:

Increased available energy.

Alternative Flows: None.