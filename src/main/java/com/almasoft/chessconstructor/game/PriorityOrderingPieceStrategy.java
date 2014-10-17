package com.almasoft.chessconstructor.game;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.almasoft.chessconstructor.model.PieceType;
/**
 * Ordering strategy that based on heuristics that we have to start from 
 * putting pieces ordered by following order:
 * 
 *   QUEEN, BISHOP, ROOK, KNIGHT, KING
 * 
 * based on capturing set size
 * 
 * @author andriy_kostin
 *
 */
public class PriorityOrderingPieceStrategy implements OrderingPieceStrategy {
  private Map<PieceType, Integer> game = new LinkedHashMap<PieceType, Integer>();
  public PriorityOrderingPieceStrategy(){
    game.put(PieceType.QUEEN, Integer.valueOf(0));
    game.put(PieceType.BISHOP, Integer.valueOf(0));
    game.put(PieceType.ROOK, Integer.valueOf(0));
    game.put(PieceType.KNIGHT, Integer.valueOf(0));
    game.put(PieceType.KING, Integer.valueOf(0));
  } 
  
  
  
  @Override
  public void put(PieceType type, Integer number) {
    game.put(type, Integer.valueOf(number));
  }

  @Override
  public PieceType giveNextPiece() {
    PieceType retVal = null;
    for (Iterator<Map.Entry<PieceType,Integer>> i = game.entrySet().iterator(); i.hasNext();) {
      Entry<PieceType, Integer> pair = i.next();
      if(pair.getValue() > 0){
        game.put(pair.getKey(), pair.getValue() - 1);
        retVal = pair.getKey();
        break;
      }
    }
    return retVal;
  }
}
