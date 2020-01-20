package idacube;

import java.util.ArrayList;

public class Group{

  protected ArrayList<Tile> tiles = new ArrayList<Tile>(25);

  public Group(Tile...tiles) throws InvalidGroupException{
    for(int i = 0; i < tiles.length; i++){
      Tile tile = tiles[i];
      add(i, tile);
    }
    //if(!isValid()){
      //throw new InvalidGroupException();
    //}
  }

  public Group(Group group) throws InvalidGroupException{
    this(group.getTiles().toArray(new Tile[group.getTiles().size()]));
  }

  public ArrayList<Tile> getTiles(){
    return tiles;
  }

  public boolean add(int index, Tile tile){
    tiles.add(index, tile);
    if(tile.getParent() != null){
      ((Group)tile.getParent()).remove(tile);
    }
    tile.setParent(this);
    return true;
  }
  public boolean add(Tile tile){
    return this.add(tiles.size(), tile);
  }
  public boolean remove(Tile tile){
    tiles.remove(tile);
    tile.setToPreviousParent();
    if(tile.getParent() != null){
      tile.getParent().add(tile);
    }
    return true;
  }
  public boolean remove(int index){
    return remove(tiles.get(index));
  }
  public boolean removeAll(){
    for(int i =0; i < tiles.size(); i++){
      Tile tile = tiles.get(i);
      if(!((Group)this).remove(tile)){
        return false;
      }
    }
    return true;
  }

  /*public boolean addValidTile(int index, Tile tile){
    super.add(index, tile);
    if(!isValid()){
      super.remove(tile);
      return false;
    }
    return true;
  }

  public boolean addValidTile(Tile tile){
    return this.add(tiles.size(), tile);
  }


  public boolean removeValidTile(Tile tile){
    int index = -1;
    for(int i = 0; i < tiles.size(); i++){
      if(tile == tiles.get(i)){
        index = i;
        super.remove(index);
      }
    }
    if(!isValid()){
      super.add(index, tile);
      return false;
    }
    return true;
  }


  public boolean removeValidTile(int index){
    return this.remove(tiles.get(index));
  }*/

  public boolean isValid(){
    return isValidRun() || isValidThreeOfAKind();
  }

  public boolean isValidRun(){
    if(tiles.size() < 3){
      return false;
    }
    Tile prevTile = tiles.get(0);
    Color color = prevTile.getColor();
    for(int i = 1; i < tiles.size(); i++){
      Tile tile = tiles.get(i);
      if(tile.getColor() != color){
        return false;
      }
      if( (tile.getNumber() != prevTile.getNumber() + 1) && !(tile.getNumber() == 1 && prevTile.getNumber() == 13) ){
        return false;
      }
      try{
        if(tile.getNumber() == 2 && prevTile.getNumber() == 1 && tiles.get(i-2).getNumber() == 13){
          return false;
        }
      }
      catch(IndexOutOfBoundsException e){

      }
      prevTile = tiles.get(i);
    }
    return true;
  }

  public boolean isValidThreeOfAKind(){
    System.out.println(tiles.size());
    if(tiles.size() < 3){
      return false;
    }
    ArrayList<Color> colors = new ArrayList<Color>(4);

    int number = tiles.get(0).getNumber();
    for(int i = 0; i < tiles.size(); i++){
      Tile tile = tiles.get(i);
      System.out.println(tile.getColor());
      if(tile.getNumber() != number){
        return false;
      }
      if(colors.contains(tile.getColor())){
        return false;
      }
      colors.add(tile.getColor());
    }
    return tiles.size() <= 4;
  }

}
