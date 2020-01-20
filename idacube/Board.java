package idacube;

import java.util.ArrayList;

public class Board extends ArrayList<Group>{
  public Board(){
    super(25);
  }
  @Override
  public String toString(){
    String out = "Board:\n";
    for(int i = 0; i < this.size(); i++){
      out += i+": "+this.get(i) + "\n";
    }
    return out.substring(0, out.length()-1);
  }
}
