package com.almasoft.chessconstructor.game;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.almasoft.chessconstructor.model.PieceType;

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
}
