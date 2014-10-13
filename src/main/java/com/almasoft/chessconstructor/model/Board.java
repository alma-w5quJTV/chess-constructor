package com.almasoft.chessconstructor.model;

import java.util.Iterator;

import com.almasoft.chessconstructor.model.capture.Visitor;
import com.almasoft.chessconstructor.strategy.State;

/**
 * TODO extract interface, get rid from boolean arrays.
 * @author andriy_kostin
 *
 */
public class Board {
  /**
   * TODO, it might me int[][] to increment and decrement.
   */
  private char[][] capturingState;
  private final int dimentionX;
  private final int dimentionY;
  private final Point dimention; 
  
  public Board(Point dimention) {
    capturingState = new char[dimention.getY()][dimention.getX()];
    this.dimentionX = dimention.getX();
    this.dimentionY = dimention.getY();
    this.dimention = dimention;
  }
  private Visitor placeBlackVisitor = new PlaceBlackVisitor();
  private Visitor removeBlackVisitor = new RemoveBlackVisitor();
  
  public Point getDimention() {
    return dimention;
  }

  /**
   * Weather given piece captures some other figure placed in the board (belongs to Gray set) 
   * of is captured (belongs to Black set).
   * 
   * @param piece -- test piece.
   * @return true if piece captures
   */
  public boolean isPositionConflicting(Piece piece, State state) {
    return 
        capturingState[piece.getPosition().getY()][piece.getPosition().getX()] > 0  
        ||
        belongsToGraySet(piece, state);
  }
  
  private boolean belongsToGraySet(final Piece piece, State state) {
    
    for (Iterator<Piece> i = state.getPlacedPiecesIterator(); i.hasNext();) {
      Piece existing =  i.next();
      if(existing != piece && piece.doesCapture(existing.getPosition())){
        return true;
      }
    }
    return false;
  }

  public Iterator<Point> possiblePositionsIterator(State state){
    //TODO same type figures.
    return new BoardIterator();
  }
  /**
   * update BlackSet, Gray Set
   * @param p
   */
  public void placePiece(Piece piece) {
    piece.getType().getCapture().visit(this, piece.getPosition(), placeBlackVisitor);
  }
  /**
   * remove BlackSet, Gray Set
   * @param p
   */
  public void removePiece(Piece piece) {
    piece.getType().getCapture().visit(this, piece.getPosition(), removeBlackVisitor);
  }
  
  /**
   * Simple iterator, that visits all Board cell from (0,0), (1, 0), ... , (N, 0), (0, 1)...
   * Means that we iterate y=0 line, then y=1 line, ... y=dimensionY -1 line
   * @author andriy_kostin
   *
   */
  private class BoardIterator implements Iterator<Point>{
    int x = -1;
    int y = 0;
    @Override
    
    
    public boolean hasNext() {
      return y < dimentionY - 1  || x < dimentionX - 1;
    }

    @Override
    public Point next() {
      if(x == dimentionX - 1){
        x = 0;
        y++;
      }else{
        x ++;
      }
      return new Point(x,y);
    }

    @Override
    public void remove() {
      throw new RuntimeException("Not supported");
    }
  }
  private class PlaceBlackVisitor implements Visitor{
    @Override
    public void visit(int x, int y) {
      capturingState[y][x] ++; 
    }
  }
  private class RemoveBlackVisitor implements Visitor{
    @Override
    public void visit(int x, int y) {
      capturingState[y][x] --; 
    }
  }

}
