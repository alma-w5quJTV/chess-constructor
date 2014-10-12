package com.almasoft.chessconstructor.strategy;

import java.util.Iterator;

import com.almasoft.chessconstructor.game.Game;
import com.almasoft.chessconstructor.model.Board;
import com.almasoft.chessconstructor.model.Piece;
import com.almasoft.chessconstructor.model.Point;

public class PositionStrategy {
  int counter = 0;
  public void layout(Board board, Game game){
    State state = new State();
    state.init(game);
      
    layoutPiece(board, state);
  }
  
  private void emitState(State state) {
    counter ++;
//    System.out.println(state);
  }
  
  private void layoutPiece(Board board, State state) {
    Piece piece = state.take();
    
    for (Iterator<Point> iterator = board.possiblePositionsIterator(state); iterator.hasNext();) {
      Point p = iterator.next();
      piece.setPosition(p);
      
      //verify capturing
      if(!board.isPositionConflicting(piece, state)){
        
        board.placePiece(piece);
        
        if(state.hasMorePieces()){
          layoutPiece(board, state);
        }
        else{
          emitState(state);
        }
        board.removePiece(piece);
      }
    }
    
    state.offer();
  }

  public int getCounter() {
    return counter;
  }
}
