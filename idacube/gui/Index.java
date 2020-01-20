package idacube.gui;
import idacube.*;

import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.Font;

public class Index extends FlowPane{

  private int index;
  private GroupPane groupPane;

  public Index(int index, GroupPane groupPane){
    super();
    this.index = index;
    this.groupPane = groupPane;
    this.setPrefWidth(10);
    this.setPrefHeight(80);
    this.setStyle("-fx-border-color: grey;");

    if(this.groupPane.getGroup().getTiles().size() == 0){
      Text text = new Text("+");
      text.setFont(new Font(30));
      this.getChildren().add(text);
    }

    this.setOnMouseEntered(event->{
      this.setPrefWidth(25);
      this.setStyle("-fx-border-background-color: lightblue;-fx-border-color: grey;");
    });
    this.setOnMouseExited(event->{
      this.setPrefWidth(10);
      this.setStyle("-fx-border-background-color: auto;-fx-border-color: grey;");
    });
    super.setOnMouseClicked(event->{
      Object source = event.getSource();
      if(source instanceof Index){
        GamePane.setSelectedIndex(((Index)source));
      }
    });
  }

  public int getIndex(){
    return index;
  }

  public GroupPane getGroupPane(){
    return groupPane;
  }
}
