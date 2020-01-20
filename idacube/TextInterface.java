package idacube;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class TextInterface{

  private static Scanner scanner = new Scanner(System.in);
  private Game game = new Game("Benjamin", "Benjamin2");
  private Player player;
  private Turn turn;

  public TextInterface() throws InvalidOptionException{
    while(true){

      for(int i = 0; i < 10; i++){
        System.out.println();
      }
      turn = game.getTurn();
      player = game.getPlayer();
      System.out.println(game.getBoard()+"\n");
      System.out.println(player.getName()+"'s Turn:");
      System.out.println(player+"\n");
      System.out.print("Select an option:\nPick Tile (p) (default), Move Tile (m), End Turn (e): ");
      String line = scanner.nextLine();
      if(line.equals("exit")){
        break;
      }
      try{
        switch(line.charAt(0)){
          case 'p':
            try{
              for(int i = 0; i < (Integer.parseInt(line.substring(1))-1)*game.getPlayers().size(); i++){
                turn = game.getNextTurn();
              }
            }
            catch(NumberFormatException e){
              turn = game.getNextTurn();
            }
            break;
          case 'm':
            move();
            break;
          case 'e':
            turn = game.getNextTurn();
            break;
          default:
            throw new InvalidOptionException();
        }
      } catch(TurnEndedException e){
        System.err.println(e);
      }
    }
  }


  public static void main(String[] args){
    try{
      new TextInterface();
    }
    catch(InvalidOptionException e){
      System.err.println(e);
    }
  }

  public void move() throws TurnEndedException, InvalidOptionException{
    Group source = selectGroup("Select a Source Group: ");
    Tile sourceTile = selectTile(source);
    Group destination = selectGroup("Select a Destination Group: ");
    int index = selectIndex(destination);
    destination.add(index, sourceTile);
  }

  public Group selectGroup(String message) throws TurnEndedException, InvalidOptionException{
    System.out.println("n: New Group");
    System.out.println("h: hand");
    System.out.println(game.getBoard());
    System.out.print(message);
    String line = scanner.nextLine();
    if(line.equals("n")){
      return turn.newGroup();
    }
    if(line.equals("h")){
      return player;
    }
    try{
      Group group = game.getBoard().get(Integer.parseInt(line));
      return group;
    }
    catch(NumberFormatException e){
      throw new InvalidOptionException();
    }

  }

  public Tile selectTile(Group group){
    System.out.println(group);
    System.out.print("Select Tile: ");
    int index = Integer.parseInt(scanner.nextLine());
    return group.getTiles().get(index);
  }

  public int selectIndex(Group group){
    System.out.println(group);
    System.out.print("\nSelect Index: ");
    return Integer.parseInt(scanner.nextLine());
  }
}
