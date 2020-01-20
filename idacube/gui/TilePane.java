package idacube.gui;
import idacube.*;

import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;

public class TilePane extends FlowPane{
  private Tile tile;
  private Text text;
  public TilePane(Tile tile){
    super();
    this.tile = tile;

    this.text = new Text(tile.getNumber()+"");
    this.text.setFont(new Font(30));
    this.getChildren().add(this.text);
    this.setAlignment(Pos.TOP_CENTER);
    this.setPrefWidth(50);
    this.setPrefHeight(80);
    this.setStyle("-fx-border-color: black;");

    this.text.setFill(javafx.scene.paint.Color.web(idacube.Color.getString(this.tile.getColor())));

    this.setOnMouseEntered(event->{
      this.setPrefWidth(60);
      this.setStyle("-fx-border-background-color: lightblue;-fx-border-color: black;");
    });
    this.setOnMouseExited(event->{
      this.setPrefWidth(50);
      this.setStyle("-fx-border-background-color: auto;-fx-border-color: black;");
    });
    super.setOnMouseClicked(event->{
      Object source = event.getSource();
      if(source instanceof TilePane){
        GamePane.setSelectedTile(((TilePane)source).getTile());
      }
    });
  }

  public Tile getTile(){
    return tile;
  }
}
