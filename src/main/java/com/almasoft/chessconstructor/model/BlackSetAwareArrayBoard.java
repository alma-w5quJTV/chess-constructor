package com.almasoft.chessconstructor.model;

import java.util.Iterator;

import com.almasoft.chessconstructor.model.capture.Visitor;
import com.almasoft.chessconstructor.strategy.State;

/**
 * 
 * Board that keeps its state in 2-dimensions array. When Piece is placed on Board, its position and 
 * all positions that Piece captures are considered as Black set and marked as non-zero value in array. 
 * 
 * Each placing of new piece increments value in its location and captured locations.
 * 
 * When piece is removed the value of captured locations and its location is decremented.
 * 
 * Class uses primitive iterator, that simply covers all cells line by line, and not even considers Black set.
 * 
 * Do not use for production.
 * 
 * @author andriy_kostin
 *
 */
public class BlackSetAwareArrayBoard implements Board{
  
  private char[][] capturingState;
  private int dimentionX;
  private int dimentionY;
  private Point dimention; 
  
  @Override
  public void init(Point dimention) {
    capturingState = new char[dimention.getY()][dimention.getX()];
    this.dimentionX = dimention.getX();
    this.dimentionY = dimention.getY();
    this.dimention = dimention;
  }
  
  private Visitor placeBlackVisitor = new PlaceBlackVisitor();
  private Visitor removeBlackVisitor = new RemoveBlackVisitor();
  /* (non-Javadoc)
   * @see com.almasoft.chessconstructor.model.Board2#getDimention()
   */
  @Override
  public Point getDimention() {
    return dimention;
  }

  /* (non-Javadoc)
   * @see com.almasoft.chessconstructor.model.Board2#isPositionConflicting(com.almasoft.chessconstructor.model.Piece, com.almasoft.chessconstructor.strategy.State)
   */
  @Override
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

  /* (non-Javadoc)
   * @see com.almasoft.chessconstructor.model.Board2#possiblePositionsIterator(com.almasoft.chessconstructor.strategy.State)
   */
  @Override
  public Iterator<Point> possiblePositionsIterator(State state){
    return new BoardIterator(state);
  }
  /* (non-Javadoc)
   * @see com.almasoft.chessconstructor.model.Board2#placePiece(com.almasoft.chessconstructor.model.Piece)
   */
  @Override
  public void placePiece(Piece piece) {
    piece.getType().getCapture().visit(this, piece.getPosition(), placeBlackVisitor);
  }
  /* (non-Javadoc)
   * @see com.almasoft.chessconstructor.model.Board2#removePiece(com.almasoft.chessconstructor.model.Piece)
   */
  @Override
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
    private int x;
    private int y;
    BoardIterator(State state) {
      Piece current = state.getCurrent();
      Piece prev = state.getPrevious();
      if(current == null || prev == null || prev.getType() != current.getType()){
        x = -1;
        y = 0;
      }else{
        //put start of iterator to next to prev position
        Point prevPosition = prev.getPosition();
        x = prevPosition.getX();
        y = prevPosition.getY();
      }
    }

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
