package idacube;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player extends Group{
  private static Random random = new Random();


  private List<Tile> hand;
  private ArrayList<Tile> pile;
  private String name;

  public Player(String name, ArrayList<Tile> pile) throws InvalidGroupException{
    super();
    this.hand = tiles;
    this.name = name;
    this.pile = pile;
    for(int i = 0; i < 13; i++){
      pick();
    }
  }

  public void pick(){
    Tile tile = pile.get(random.nextInt(pile.size()));
    pile.remove(tile);
    add(tile);
  }

  public String getName(){
    return name;
  }

  @Override
  public boolean isValid(){
    return true;
  }



  /*public Tile getTile(int number, Color color){
    for(Tile tile : hand){
      if(tile.getNumber() == number && tile.getColor() == color){
        return tile;
      }
    }
    throw new Error("requested tile is not in the players hand");
  }*/

  public String toString(){
    String out = name+"'s Hand:\n";
    for(int i = 0; i < hand.size(); i++){
      out += i+": "+hand.get(i).toString() + "\n";
    }
    return out.substring(0, out.length() - 1);
  }
}
