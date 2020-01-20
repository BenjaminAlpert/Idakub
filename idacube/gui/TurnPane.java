package idacube.gui;
import idacube.*;

import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class TurnPane extends GridPane{
  private Turn turn;
  private Game game;
  private GamePane gamePane;
  private Text messageText;
  public TurnPane(GamePane gamePane){
    super();
    this.gamePane = gamePane;
    this.game = this.gamePane.getGame();
    this.turn = this.game.getTurn();
    this.game = this.turn.getGame();
    this.messageText = new Text(this.turn.getPlayer().getName()+"'s Turn:");
    this.messageText.setFont(new Font(20));
    Button pickButton = new Button("Pick");
    Button placeButton = new Button("Place");
    Button endButton = new Button("End");
    super.add(messageText, 0, 0, 3, 1);
    super.add(pickButton, 0, 1);
    super.add(placeButton, 1, 1);
    super.add(endButton, 2, 1);

    pickButton.setOnAction(event->{
      pickOrEnd();
    });
    endButton.setOnAction(event->{
      pickOrEnd();
    });

    placeButton.setOnAction(event->{
      try{
        this.turn.newGroup();
        this.gamePane.update();
      }
      catch(TurnEndedException e){
        setMessageText("An Error occured. It is not your turn.");
        System.err.println(e);
      }
    });
  }

  private void pickOrEnd(){
    this.turn = this.game.getNextTurn();
    setMessageText(this.turn.getPlayer().getName()+"'s Turn:");
    this.gamePane.update();
  }

  public boolean move(){
    Tile sourceTile = gamePane.getSelectedTile();
    Index destination = gamePane.getSelectedIndex();
    if(sourceTile != null && destination != null){
      Group destinationGroup = destination.getGroupPane().getGroup();
      int destinationIndex = destination.getIndex();
      gamePane.clearSelected();
      try{
        turn.move(sourceTile, destinationGroup, destinationIndex);
        this.gamePane.update();
        return true;
      }
      catch(TurnEndedException e){
        setMessageText("An Error occured. It is not your turn.");
        return false;
      }
    }
    return false;
  }

  public void setMessageText(String text){
    messageText.setText(text);
  }
}
