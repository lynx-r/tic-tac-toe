var host = "localhost";
var port = 8080;
var pause = 2000;
var redirectPage = "http://localhost:63342/tic-tac-toe/web/static/game.html";


class Player {
  constructor(login, id) {
    this.login = login;
    this.id = id;
  }
}

class Main {
  constructor() {
     this.crossPlayer = null;
     this.naughtPlayer = null;
     this.gameId = null;
     this.isStarted = false;
  }
}

var main = new Main();

function createGame() {
    var login = $('#login').val();

    var data = {
        "login": login,
        "gameId": null
    };

    $.ajax({
      url: "http://" + host + ":" + port + "/start",
      dataType: 'json',
      type: 'post',
      async: false,
      contentType: 'application/json',
      data: JSON.stringify(data),
      processData: false,
      success: function(response){
          main.crossPlayer = new Player(login, response.crossPlayer.id);
          main.gameId = response.gameId;
      },
      error: function( jqXhr, textStatus, errorThrown ){
          console.log( errorThrown );
      }
    });

    $(".invisibleResult").val(main.gameId);
    $(".invisibleResult").css("display", "inline-block");
    $(".cssload-container").css("display","block");
    $("#black-background").css("display","block");
    var timerId = setInterval(function() {
            waitGame();
            if (main.isStarted) {
                clearInterval(timerId);
            }
        }
        , pause);
}

function connectGame() {
    var login = $('#naughtLogin').val();
    var gameId = $('#gameId').val();

      var data = {
        "login": login,
        "gameId": gameId
      };

      $.ajax({
          url: "http://" + host + ":" + port + "/start",
          dataType: 'json',
          type: 'post',
          contentType: 'application/json',
          data: JSON.stringify(data),
          processData: false,
          success: function(response){
              main.crossPlayer = new Player(response.crossPlayer.login, response.crossPlayer.id);
              main.naughtPlayer = new Player(login, response.naughtPlayer.id);
              main.gameId = response.gameId;
              main.isStarted = true;
              $(".cssload-container").css("display","none");
              document.location.href = redirectPage+"&gameId="+main.gameId;
          },
          error: function( jqXhr, textStatus, errorThrown ){
              console.log( errorThrown );
          }
      });
}

function waitGame() {
    $.ajax({
          url: "http://" + host + ":" + port + "/start/?gameId=" + main.gameId,
          type: 'get',
          dataType: 'json',
          success: function(response){
              console.log(response.isStarted);
              if (response.isStarted == true) {
                  main.isStarted = true;
                  $(".cssload-container").css("display","none");
                  document.location.href = redirectPage;
              }
          },
          error: function( jqXhr, textStatus, errorThrown ){
              console.log( errorThrown );
          }
    });
}

$(".gameContainer button").click(function() {
    var id = $(this).attr("id");
    $(this).text('X');
});


