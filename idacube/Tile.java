package idacube;

import java.util.ArrayList;

public class Tile{

  private int number;
  private Color color;
  private Group parent = null;
  private ArrayList<Group> previousParents = new ArrayList<Group>();

  public Tile(int number, Color color){
    this.number = number;
    this.color = color;
  }

  public int getNumber(){
    return number;
  }
  public Color getColor(){
    return color;
  }

  public Group getParent(){
    return parent;
  }
  public void setParent(Group parent){
    this.parent = parent;
    previousParents.add(this.parent);
  }

  public boolean setToPreviousParent(){
    if(previousParents.size() == 0){
      this.parent = null;
      return false;
    }
    this.parent = previousParents.get(previousParents.size()-1);
    previousParents.remove(this.parent);
    return true;
  }

  @Override
  public String toString(){
    return color + " " + number;
  }

}
