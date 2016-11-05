# LastServerService
Responsible for keeping track of the latest gameId (and optionally the mapId and flavorId) the player has played on

##Endpoints
###/players/{uuid}/lastserver/{gameId}/{mapId}/{flavorId} [PUT]:
####Sets the gameId (and optionally the mapId and flavorId) as the last game the player has played on, and returns whether or not the operation was a success

**Arguments**:
- uuid (string): The unique id of the player
- gameId (string): The game identifier
- mapId (string)[OPTIONAL]: The map identifier
- flavorId (string)[OPTIONAL]: The flavor identifier

**Response**: {"success": true}
- success (boolean): Whether or not the last gameId of the player could be set.
- err (string)[OPTIONAL]: When an unexpected error occured (fe. database not accessable), this field contains the error message. This will never be added if success=true

###/players/{uuid}/lastserver/ [GET]:
####Gets the latest gameId (and optionally the mapId and flavorId) the player has played on 

**Arguments**:
- uuid (string): The unique id of the player

**Response**: {"gameId": "IW", "mapId": "greenForest", "flavorId": "solo"}
- gameId (string): The game identifier
- mapId (string)[OPTIONAL]: The map identifier
- flavorId (string)[OPTIONAL]: The flavor identifier
