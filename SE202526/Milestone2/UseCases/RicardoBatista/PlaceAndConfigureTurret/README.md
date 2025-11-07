Use Case: Place and Configure Turret
ID:UC03

Specializes: None

Description: The player places a defensive turret on the map and configures its firing behavior.

Main Actor: Player

Secondary Actors: None

Pre-conditions: The player is in build mode (in-game or editor), has the turret unlocked, and possesses the required resources.

Main Flow:

The use case starts when the player opens the build menu.
The system displays available buildings and their resource costs.
The player selects the turret from the build menu.
The system shows a placement preview (ghost) and allowed placement indicators.
The player moves the preview to a valid tile and rotates the turret if desired.
The player confirms placement.
The system deducts resources, spawns the turret entity at the chosen location, and displays its configuration panel.
The player opens the configuration panel and sets targeting priorities (e.g., air/ground/all), firing mode, and ammo type if applicable.
The player confirms configuration.
The system applies settings and the turret becomes active.
Post-conditions: A turret is built at the chosen location, configured as specified, and active in the game world.

Alternative Flows:

Invalid placement (terrain blocked, too close to another structure): system prevents placement and indicates the reason; player repositions or cancels.
Insufficient resources: system blocks placement and prompts the player to gather resources or cancel.
Player cancels during placement or configuration: no turret is built or configuration changes are discarded.
Configuration unsupported (e.g., selected ammo not available): system warns and prevents applying unsupported option.