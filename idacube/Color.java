package idacube;

public enum Color{
  BLACK,
  BLUE,
  RED,
  ORANGE;

  public static Color getColorById(int id){
    switch(id){
      case 0:
        return BLACK;
      case 1:
        return BLUE;
      case 2:
        return RED;
      case 3:
        return ORANGE;
      default:
        throw new Error("Invlaid Color ID");
    }
  }


  public static String getString(Color color){
    switch(color){
      case BLACK:
        return "black";
      case BLUE:
        return "blue";
      case RED:
        return "red";
      case ORANGE:
        return "orange";
      default:
        throw new Error("invalid color");
    }
  }
}
