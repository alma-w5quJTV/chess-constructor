package com.almasoft.chessconstructor.cli;

import java.io.IOException;
import java.util.Scanner;

import com.almasoft.chessconstructor.configuration.GrayBruteForceOrderedPieceModule;
import com.almasoft.chessconstructor.game.CapturingGameStream;
import com.almasoft.chessconstructor.game.Game;
import com.almasoft.chessconstructor.game.SimpleGameStream;
import com.almasoft.chessconstructor.model.PieceType;
import com.almasoft.chessconstructor.model.Point;
import com.almasoft.chessconstructor.strategy.GameStream;
import com.almasoft.chessconstructor.strategy.State;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main {
  private String command = null;
  public Main(String[] args) {
    if(args != null && args.length > 1){
      command = args[0];
    }
  }
  public static void main(String[] args) {
    new Main(args).executeCommand();
  }
  private int readPositiveNumber(Scanner sc){
    int retVal = 0;
    while(true){
      if(sc.hasNextInt()){
        retVal = sc.nextInt();
      }
      if(retVal < 0){
        System.out.print("\nPlease enter non-negative number: ");
      }else{
        break;
      }
    }
    return retVal;
  }
  private boolean readBoolean(Scanner sc){
    boolean retVal = true;
    while(true){
      String str = sc.next();
      if(str == null || str.isEmpty()){
        break;
      }else if("yn".indexOf(str.toLowerCase()) >=0){
        retVal = str.toLowerCase().equals("y");
        break;
      }else{
        System.out.print("\nPlease enter Y/N: ");
      }
    }
    return retVal;
  }
  
  private Game createNewGame(){
    Injector injector = Guice.createInjector(new GrayBruteForceOrderedPieceModule());
    return injector.getInstance(Game.class);
  }
  private void executeCommand() {
    if(command == null){
      Scanner sc = new Scanner(System.in);
      
      Game game = createNewGame();
      
      System.out.print("\nPlease enter width (X) of board: ");
      final int dimX = readPositiveNumber(sc);
      
      System.out.print("\nPlease enter height (Y) of board: ");
      final int dimY = readPositiveNumber(sc);
      
      System.out.print("\nPlease enter number (>=0) of Kings (K): ");
      game.putPiece(PieceType.KING, readPositiveNumber(sc));
      
      System.out.print("\nPlease enter number (>=0) of Queens (Q): ");
      game.putPiece(PieceType.QUEEN, readPositiveNumber(sc));
      
      System.out.print("\nPlease enter number (>=0) of Knights (N): ");
      game.putPiece(PieceType.KNIGHT, readPositiveNumber(sc));
      
      System.out.print("\nPlease enter number (>=0) of Rooks (R): ");
      game.putPiece(PieceType.ROOK, readPositiveNumber(sc));
      
      System.out.print("\nPlease enter number (>=0) of Bishop (B): ");
      game.putPiece(PieceType.BISSHOP, readPositiveNumber(sc));
      
      System.out.print("\nPrint all possible cases? (Y/N) [Y]: ");
      boolean print = readBoolean(sc);
      
      GameStream out = null;
      if(print){
        out = new CapturingGameStream(){
          @Override
          public void onGameReady(State state) {
            try {
              counter ++;
              print(System.out, new Solution(state), new Point(dimX, dimY));
            } catch (IOException e) {
              throw new RuntimeException("Unable to print result: "+ e.getMessage(), e);
            }
          }
        };
      }else{
        out = new SimpleGameStream();
      }
      long start = System.currentTimeMillis();
      game.startGame(dimX, dimY, out);
      
      if(print){
        System.out.println(String.format("[%d x %d] Find %d cases", dimX, dimY, out.getCounter()));
      }else{
        System.out.println(String.format("[%d x %d] Find %d cases by %d ms", dimX, dimY, out.getCounter(), System.currentTimeMillis() - start));
      }
    }
  }
}
