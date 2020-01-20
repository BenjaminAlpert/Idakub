package idacube.gui;
import idacube.*;

import javafx.scene.layout.Pane;
import javafx.scene.control.Button;

public class PlayerPane extends Pane{
  private Player player;
  private GroupPane groupPane;
  private Button continueButton = new Button("Continue");
  public PlayerPane(Player player){
    super();
    this.player = player;
    update();
    continueButton.setOnAction(event->{
      this.getChildren().remove(this.continueButton);
      this.getChildren().add(this.groupPane);
    });
  }

  public GroupPane getGroup(){
    return groupPane;
  }

  public void update(Player player){
    if(this.player != player){
      this.player = player;
      update();
    }
    groupPane.update();
  }

  public void update(){
    this.getChildren().clear();
    groupPane = new GroupPane(player);
    this.getChildren().add(this.continueButton);
  }
}
