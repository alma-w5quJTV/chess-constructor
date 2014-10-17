package com.almasoft.chessconstructor.model;

import java.util.Iterator;

import com.almasoft.chessconstructor.model.capture.Visitor;
import com.almasoft.chessconstructor.strategy.State;

/**
 * Board that avoids iterations for linear figures, but keeps lines in MNLineEncoder instead of marking it in matrix.
 * 
 * Also precalculates GraySet(PieceType) - is the set of cells in the board, that will be captured for piece of type PieceType. 
 * This Board precalculates all greay sets for all types, in future we can precalculate only for PT that are in game.
 * 
 * TODO Refactor captured state to 2 sets of Set<Point> to avoid N x M iteration.
 * 
 * @author andriy_kostin
 */
public class BlackGraySetAwareArrayBoard implements Board{
  
  private char[][] capturingState;
  private NMLineEncoder blackLines;
  
  /**
   * Diagonals that is captured by piece. 
   */
  private Visitor placeBlackVisitor;
  private Visitor removeBlackVisitor;
  
  /**
   * Gray structures
   */
  private NMLineEncoder grayLines;
  private char[][] kingGrayState;
  private char[][] knightGrayState;
  
  private int dimentionX;
  private int dimentionY;
  private Point dimention;
  
  @Override
  public void init(Point dimention) {
    capturingState = new char[dimention.getY()][dimention.getX()]; 
    placeBlackVisitor = new PointMarkingVisitor(capturingState, 1);
    removeBlackVisitor = new PointMarkingVisitor(capturingState, -1);
    
    grayLines = new NMLineEncoder(dimention);
    blackLines = new NMLineEncoder(dimention);
    kingGrayState = new char[dimention.getY()][dimention.getX()]; 
    knightGrayState = new char[dimention.getY()][dimention.getX()]; 
    
    this.dimentionX = dimention.getX();
    this.dimentionY = dimention.getY();
    this.dimention = dimention;
  }
  
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
    return belongsToBlackSet(piece, state, capturingState, blackLines) || belongsToSet(piece, state, kingGrayState, knightGrayState, grayLines);
  }
  private boolean belongsToBlackSet(final Piece piece, State state, char[][] capturingState, NMLineEncoder lines){
    int x = piece.getPosition().getX();
    int y = piece.getPosition().getY();
      
    return capturingState[y][x] > 0 || 
        lines.isLineMarked(x, y, NMLineEncoder.DIAGONAL_LINE_MASK) || 
        lines.isLineMarked(x, y, NMLineEncoder.CO_DIAGONAL_LINE_MASK) ||
        lines.isLineMarked(x, y, NMLineEncoder.VERTICAL_LINE_MASK) || 
        lines.isLineMarked(x, y, NMLineEncoder.HORISONTAL_LINE_MASK);
  }
  private boolean belongsToSet(final Piece piece, State state, char[][] kingState, char[][] knightState, NMLineEncoder lines){
    PieceType type = piece.getType();
    boolean retVal = false;
    int x = piece.getPosition().getX();
    int y = piece.getPosition().getY();
    if(type.isLinear()){
      if(type != PieceType.ROOK){//BISHOP + QUEEN
        retVal = lines.isLineMarked(x, y, NMLineEncoder.DIAGONAL_LINE_MASK) || lines.isLineMarked(x, y, NMLineEncoder.CO_DIAGONAL_LINE_MASK);
        if(retVal) return true;
      }
      if(type != PieceType.BISSHOP){//ROOK + QUEEN
        retVal = lines.isLineMarked(x, y, NMLineEncoder.VERTICAL_LINE_MASK) || lines.isLineMarked(x, y, NMLineEncoder.HORISONTAL_LINE_MASK);
      }
      
    }else if(type == PieceType.KING || type == PieceType.KNIGHT){
      char[][] pointGrayState = (type == PieceType.KING) ? kingState : knightState;
      retVal = pointGrayState[y][x] > 0;
    }else{
      throw new IllegalStateException("Given type "+type + " is not supported for GrayStrategy");
    }
    return retVal;
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
    if(piece.getType().isLinear()){
      markBlackSet(piece,true);
    }else{
      piece.getType().getCapture().visit(this, piece.getPosition(), placeBlackVisitor);
    }
    markGraySet(piece, true);
  }
  
  /* (non-Javadoc)
   * @see com.almasoft.chessconstructor.model.Board2#removePiece(com.almasoft.chessconstructor.model.Piece)
   */
  @Override
  public void removePiece(Piece piece) {
    if(piece.getType().isLinear()){
      markBlackSet(piece, false);
    }else{
      piece.getType().getCapture().visit(this, piece.getPosition(), removeBlackVisitor);
    }
    markGraySet(piece, false);
  }
  private void markBlackSet(Piece piece, boolean isMark){
    PieceType type = piece.getType();
    int x = piece.getPosition().getX();
    int y = piece.getPosition().getY();
    if(type != PieceType.ROOK){//BISHOP + QUEEN
      blackLines.markLine(x, y, NMLineEncoder.DIAGONAL_LINE_MASK,isMark);
      blackLines.markLine(x, y, NMLineEncoder.CO_DIAGONAL_LINE_MASK,isMark);
    }
    if(type != PieceType.BISSHOP){//ROOK + QUEEN
      blackLines.markLine(x, y, NMLineEncoder.VERTICAL_LINE_MASK,isMark);
      blackLines.markLine(x, y, NMLineEncoder.HORISONTAL_LINE_MASK,isMark);
    }
  }
  private void markGraySet(Piece piece, boolean isMark) {
      int x = piece.getPosition().getX();
      int y = piece.getPosition().getY();
      
      grayLines.markLine(x, y, NMLineEncoder.DIAGONAL_LINE_MASK, isMark);
      grayLines.markLine(x, y, NMLineEncoder.CO_DIAGONAL_LINE_MASK, isMark);
      grayLines.markLine(x, y, NMLineEncoder.VERTICAL_LINE_MASK, isMark);
      grayLines.markLine(x, y, NMLineEncoder.HORISONTAL_LINE_MASK, isMark);
      
      PieceType.KING.getCapture().visit(this, piece.getPosition(), new PointMarkingVisitor(kingGrayState, isMark ? 1 : -1));
      PieceType.KNIGHT.getCapture().visit(this, piece.getPosition(), new PointMarkingVisitor(knightGrayState, isMark ? 1 : -1));
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
    private Piece current;
    private Piece prev;
    BoardIterator(State state) {
      current = state.getCurrent();
      prev = state.getPrevious();
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
      return  y < dimentionY - 1  || x < dimentionX - 1;
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
  
  private static class PointMarkingVisitor implements Visitor{
    private char[][] array;
    private int delta;
    
    PointMarkingVisitor(char[][] array, int delta){
      this.array = array;
      this.delta = delta;
    }
    @Override
    public void visit(int x, int y) {
      array[y][x] += delta; 
    }
  }
}
