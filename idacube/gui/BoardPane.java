package idacube.gui;
import idacube.*;

import javafx.scene.layout.GridPane;

public class BoardPane extends GridPane{
  private Board board;
  private GamePane gamePane;
  public BoardPane(GamePane gamePane){
    super();

    this.gamePane = gamePane;
    this.board = this.gamePane.getGame().getBoard();

    this.setPrefHeight(GUIInterface.HEIGHT/2);

    update();
  }

  public void update(){
    this.getChildren().clear();
    int i = 0;
    for(Group group : board){
      this.add(new GroupPane(group), i/5, i%5);
      i++;
    }

  }
}
