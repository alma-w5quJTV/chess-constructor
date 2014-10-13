package com.almasoft.chessconstructor.model;

import java.util.Iterator;

import com.almasoft.chessconstructor.strategy.State;

public interface Board {

  /**
   * Performs necessary initialization for Board. E.g. allocation some accessory structures, arrays etc. 
   * @param dimension
   */
  public void init(Point dimension);
  
  public Point getDimention();

  /**
   * Weather given piece captures some other figure placed in the board (belongs to Gray set) 
   * of is captured (belongs to Black set).
   * 
   * @param piece -- test piece.
   * @return true if piece captures
   */
  public boolean isPositionConflicting(Piece piece, State state);

  public Iterator<Point> possiblePositionsIterator(State state);

  /**
   * update BlackSet, Gray Set
   * @param p
   */
  public void placePiece(Piece piece);

  /**
   * remove BlackSet, Gray Set
   * @param p
   */
  public void removePiece(Piece piece);

}