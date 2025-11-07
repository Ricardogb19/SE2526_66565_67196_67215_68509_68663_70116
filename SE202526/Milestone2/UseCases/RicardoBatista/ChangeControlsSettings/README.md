Use Case: Change Control Settings
ID:UC01

Specializes: None

Description: The player customizes input bindings and control sensitivity.

Main Actor: Player

Secondary Actors: None

Pre-conditions: The player is in the settings menu.

Main Flow:

The use case starts when the player opens "Controls" in settings.
The system displays current bindings and sensitivity sliders.
The player selects a control to rebind and presses the desired key/button.
The system updates the binding and shows the change.
The player adjusts sensitivity and applies changes.
The system saves the new control configuration to settings.
Post-conditions: The player's control bindings and sensitivity are updated and persist between sessions.

Alternative Flows: Conflict detected (binding already in use) — system warns and allows the player to confirm override or choose another input.