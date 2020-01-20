package idacube.gui;
import idacube.*;

import javafx.scene.layout.HBox;

public class GroupPane extends HBox{
  private boolean isManipulationGroup = false;
  private Group group;
  public GroupPane(Group group){
    super(1);
    //this.setPrefHeight(750/4);
    this.setStyle("-fx-border:solid black 2px;");
    this.group = group;

    update();
  }

  public void update(Group group){
    this.group = group;
    update();
  }

  public void update(){
    this.getChildren().clear();
    int index = 0;
    if(this.group.getTiles().size() > 0){
      this.getChildren().add(new Index(index++, this));
    }
    for(Tile tile : this.group.getTiles()){
      this.getChildren().add(new TilePane(tile));
      this.getChildren().add(new Index(index++, this));
    }
    if(this.group.getTiles().size() == 0){
      this.getChildren().add(new Index(0, this));
    }
  }

  public Group getGroup(){
    return group;
  }
}
