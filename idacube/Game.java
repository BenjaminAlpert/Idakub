package idacube;

import java.util.List;
import java.util.ArrayList;

public class Game{
  private ArrayList<Tile> tiles = new ArrayList<Tile>(52*2);//not including jokers, just yet
  private Board board = new Board();
  private Player currentPlayer;
  private ArrayList<Player> players = new ArrayList<Player>(4);
  private Turn turn;


  public Game(String...names){
    for(int j = 0; j < 2; j++){
      for(int i = 0; i < 52; i++){
        Color color = Color.getColorById(i / 13);
        tiles.add(new Tile(i % 13 + 1, color));
      }
    }

    for(String name : names){
      try{
        players.add(new Player(name, tiles));
      }
      catch(InvalidGroupException e){
        throw new Error("Invalid Group Exception");
      }

    }
    currentPlayer = players.get(0);
    turn = new Turn(this);
  }

  public Board getBoard(){
    return board;
  }

  public List<Player> getPlayers(){
    return players;
  }

  public Player getPlayer(String name){
    for(Player player : players){
      if(player.getName().equals(name)){
        return player;
      }
    }
    throw new Error("No player by that name!");
  }

  public Turn getTurn(){
    return turn;
  }

  private int currentPlayerCounter = 0;
  public Turn getNextTurn(){
    boolean ended = false;
    try{
      ended = turn.end();
    }
    catch(TurnEndedException e){
      ended = true;
    }
    if(ended){
      if(++currentPlayerCounter == players.size()){
        currentPlayerCounter = 0;
      }
      currentPlayer = players.get(currentPlayerCounter);
      turn = new Turn(this);
      return turn;
    }
    throw new Error("Invalid End of Turn");
  }


  public Player getPlayer(){
    return currentPlayer;
  }


}
