package idacube.gui;
import idacube.*;

import javafx.scene.layout.BorderPane;

public class GamePane extends BorderPane{
  private Game game;

  private BoardPane boardPane;
  private TurnPane turnPane;
  private PlayerPane playerPane;

  private static Tile selectedTile;
  private static Index selectedIndex;

  public GamePane(Game game){
    super();
    this.game = game;
    super.setPrefWidth(GUIInterface.WIDTH);
    super.setPrefHeight(GUIInterface.HEIGHT);

    boardPane = new BoardPane(this);
    turnPane = new TurnPane(this);
    playerPane = new PlayerPane(this.game.getPlayer());

    super.setTop(boardPane);
    super.setCenter(turnPane);
    super.setBottom(playerPane);

    super.setOnMouseClicked(event->{
      turnPane.move();
    });

    //setSelectListeners();
  }

  public BoardPane getBoardPane(){
    return boardPane;
  }

  /*public void setSelectListeners(){
    super.setOnMouseClicked(event->{
      Object source = event.getSource();
      System.out.println(source.getClass());
      if(source instanceof TilePane){
        selectedTile = ((TilePane)source).getTile();
      }
      if(source instanceof Index){
        selectedIndex = ((Index)source);
      }
      System.out.println(selectedTile);
      System.out.println(selectedIndex);
    });
  }*/

  public static void setSelectedTile(Tile tile){
    selectedTile = tile;
  }
  public static void setSelectedIndex(Index index){
    selectedIndex = index;
  }

  public void update(){
    boardPane.update();
    playerPane.update(game.getPlayer());
    //setSelectListeners();
  }

  public Game getGame(){
    return game;
  }


  public Tile getSelectedTile(){
    return selectedTile;
  }
  public Index getSelectedIndex(){
    return selectedIndex;
  }
  public void clearSelected(){
    selectedTile = null;
    selectedIndex = null;
  }
}
