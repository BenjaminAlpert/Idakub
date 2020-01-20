/*
javac -d ./bin $(find -name "*.java") && java -cp ./bin idacube.gui.GUIInterface
*/

package idacube.gui;
import idacube.*;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class GUIInterface extends Application{

  public static final int WIDTH = 1250;
  public static final int HEIGHT = 500;

  public void start(Stage primaryStage){
    VBox root = new VBox(10);
    Scene scene = new Scene(root, WIDTH, HEIGHT);
    primaryStage.setScene(scene);
    primaryStage.show();

    Game game = new Game("Benjamin", "Lily Claire", "Mother");
    GamePane gp = new GamePane(game);
    root.getChildren().add(gp);

  }


  public static void main(String[] args){
    launch(args);
  }
}
