package com.almasoft.chessconstructor.game;

import com.almasoft.chessconstructor.model.PieceType;
/**
 * Strategy that is used for supplying Pieces for PositionStrategy. 
 * 
 * Different implementations can use specific order and this reflects to performance 
 *  
 * @author Andriy_Kostin
 *
 */
public interface OrderingPieceStrategy {

  /**
   * Register type and its number that will take place in game.
   * @param type
   * @param number
   */
  void put(PieceType type, Integer number);
  /**
   * @return next PieceType according order.
   */
  PieceType giveNextPiece();

}
