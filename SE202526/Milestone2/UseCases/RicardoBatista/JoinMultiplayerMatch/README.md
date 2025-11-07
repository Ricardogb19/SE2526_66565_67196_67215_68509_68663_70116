Use Case: Join Multiplayer Match
ID:UC02

Specializes: None

Description: The player joins an existing multiplayer match.

Main Actor: Player

Secondary Actors: None

Pre-conditions: The player is in the multiplayer menu and knows a server address or sees a server in the list.

Main Flow:

The use case starts when the player selects a server from the list or enters an address.
The system attempts to connect and displays connection progress.
If required, the player enters a password or accepts game rules.
Upon successful connection, the system places the player in the lobby.
The player waits until the host starts the match; then the game launches on the client.
Post-conditions: The player is connected to the multiplayer match as a client.

Alternative Flows: Connection fails (timeout, incompatible version) — system displays error and returns to the server list.