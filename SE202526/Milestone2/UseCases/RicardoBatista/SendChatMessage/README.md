# Use Case: Send Chat Message
## ID:UC04

### Description:
- The player sends a chat message to other players.

### Actors:
- Main Actor: Player
- Secondary Actors: Other Players

### Pre-conditions:
- The player is in a lobby or in-game with chat enabled.

### Main Flow:
- The use case starts when the player opens the chat input.
- The player types a message and presses send/enter.
- The system transmits the message to the server (or local lobby) and displays it in the chat window.
- Other players receive and see the message in their chat UI. 

### Post-conditions:
- The message is visible to participants.

### Alternative Flows: 
- Message blocked — system notifies the player and discards the message.