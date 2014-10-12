package com.almasoft.chessconstructor.game;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.almasoft.chessconstructor.model.Board;
import com.almasoft.chessconstructor.model.PieceType;
import com.almasoft.chessconstructor.model.Point;
import com.almasoft.chessconstructor.strategy.PositionStrategy;

public class Game {
  private Map<PieceType, Integer> game = new HashMap<PieceType, Integer>();
  
  public Game putPiece(PieceType type, int number){
    game.put(type, Integer.valueOf(number));
    return this;
  }
  
  public PieceType takeNextPiece(){
    //TODO make strategy
    Set<PieceType> keys = game.keySet();
    if(keys != null && keys.size() > 0){
      PieceType key = keys.iterator().next();
      int number = game.get(key);
      if(--number == 0){
        game.remove(key);
      }else{
        game.put(key, Integer.valueOf(number));
      }
      return key;
    }
    return null;
  }
  
  
  public void startGame(int sizeX, int sizeY){
    long start = System.currentTimeMillis();
    
    Board board = new Board(new Point(sizeX, sizeY));
    PositionStrategy strategy = new PositionStrategy();
    
    strategy.layout(board, this);
    long finish = System.currentTimeMillis();
    System.out.println(String.format("Number is %d, time = %d ms", strategy.getCounter(), finish - start));
  }
  
  public static void main(String[] args) {
    Game game = new Game().putPiece(PieceType.KING, 2).putPiece(PieceType.QUEEN, 2).putPiece(PieceType.BISSHOP, 2).putPiece(PieceType.KNIGHT, 1);
    game.startGame(7, 7);
  }
}
