# Tic tac toe
Backend for tic-tac-toe game. Contains api for game;

## Getting started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Dependencies

* Java 1.8
* Maven 3.3.9
* MySQL 5.7

### Modules

* **common** Contains common data
* **data-access** DB-migration, orm-entities, jpa-repositories
* **service** Contains interfaces of services, dto's and exceptions
* **service-impl** Contains implementations of service's interfaces, mappers, internal services
* **web** Application

### Spring-profiles

* **dev** 
* **it** For integration tests. Not need implicit indication

### Create DB

```sql
   CREATE DATABASE tic-tac-toe;
```

### API for starting game

```
* [POST] http://module's address:8080/start
```

This command starts registration first player and creates game. Also it registers the second player and starts the game.
 
 Request's example:
 ```json
{
  "login": "first_player",
  "gameId": "null"
}
```

Response's example:
```json
{
    "gameId": "1",
    "crossPlayer": {
        "playerId": 1,
        "login": "first_player"
    },
    "naughtPlayer": "null",
    "isStarted": "false"
}
```

For starting game needs the second player:
```json
{
  "login": "second_player",
  "gameId": 1
}
```

Response's example:
```json
{
  "gameId": 1,
  "crossPlayer": {
    "playerId": 1,
    "login": "first_player"
  },
  "naughtPlayer": {
    "playerId": 2,
    "login": "second_player"
  },
  "isStarted": true
}
```

### API for making move

```
* [POST] http://module's address:8080/game/{id}
```

This command initiative game's move.

Request's example:
```json
{
  "playerId": 1,
  "horizontalPosition": 2,
  "verticalPosition": 2
}
```

Response's example:
```json
{
  "status": "success",
  "isGameFinished": false
}
```

### API for getting field state

```
* [GET] http://module's address:8080/game/{id}/field
```

This command getting field state. Parameter {id} - gameId

Response's example:
```json
{
  "field": [
    {
      "horizontalPosition": 2,
      "verticalPosition": 2,
      "symbol": "cross"
    }
  ]
}
```

### API for getting result of the game

```
* [GET] http://module's address:8080/game/{id}/status
```

This command getting game result. Parameter {id} - gameId

Response's example:
```json
{
  "result": "crossWin"
}
```
