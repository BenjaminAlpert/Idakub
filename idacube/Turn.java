package idacube;

import java.util.List;

public class Turn{
  private Game game;
  private Player player;
  private int initialNumberOfPlayersTiles;
  private boolean ended = false;
  public Turn(Game game){
    this.game = game;
    this.player = game.getPlayer();
    initialNumberOfPlayersTiles = player.getTiles().size();
  }

  public Player getPlayer(){
    return player;
  }
  public Game getGame(){
    return game;
  }

  public Group newGroup() throws TurnEndedException{
    checkEnded();
    try{
      Group group = new Group();
      game.getBoard().add(group);
      return group;
    }
    catch(InvalidGroupException e){
      throw new Error("Invalid Group Exception");
    }
  }
  public Group getGroup(int index) throws TurnEndedException{
    checkEnded();
    return game.getBoard().get(index);
  }
  public Tile getTile(int index, Group source) throws TurnEndedException{
    checkEnded();
    return source.getTiles().get(index);
  }
  public boolean move(Tile tile, Group destination, int destinationIndex) throws TurnEndedException{
    checkEnded();
    if(destination instanceof Group){
      if(tile.getParent() == destination){
        int sourceIndex = ((Group)destination).getTiles().indexOf(tile);
        ((Group)destination).getTiles().remove(tile);
        if(sourceIndex <= destinationIndex && destinationIndex != 0){
          ((Group)destination).getTiles().add(destinationIndex-1, tile);
        }
        else{
          ((Group)destination).getTiles().add(destinationIndex, tile);
        }


        //Collections.rotate(list.subList(j, k+1), -1);//move the element
        return true;
      }
      return ((Group)destination).add(destinationIndex, tile);
    }
    return false;
  }

  public boolean pick() throws TurnEndedException{
    checkEnded();
    if(player.getTiles().size() == initialNumberOfPlayersTiles){
      player.pick();
      ended = true;
      return true;
    }
    return false;
  }

  private void checkEnded() throws TurnEndedException{
    if(ended){
      throw new TurnEndedException();
    }
  }

  public boolean end() throws TurnEndedException{
    checkEnded();
    List<Group> groups = game.getBoard();
    for(int i = 0; i < groups.size(); i++){
      Group group = groups.get(i);
      //group = group.getGroup();
      //groups.set(i, group);
      if(!group.isValid()){
        group.removeAll();
        groups.remove(group);
        //return false;
      }
    }
    pick();//only picks if neccisary
    ended = true;
    return ended;
  }
}
