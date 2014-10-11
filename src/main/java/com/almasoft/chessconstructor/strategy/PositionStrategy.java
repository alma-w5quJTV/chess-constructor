package com.almasoft.chessconstructor.strategy;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.almasoft.chessconstructor.game.Game;
import com.almasoft.chessconstructor.model.Board;
import com.almasoft.chessconstructor.model.Piece;
import com.almasoft.chessconstructor.model.PieceType;
import com.almasoft.chessconstructor.model.Point;

public class PositionStrategy {
  public void layout(Board b, Game game){
    State state = new State();
    PieceType pt = game.takeNextPiece();
    if(pt != null){
      
      layoutPiece(pt, b, state, game);
      
    }else{
      emitState(state);
    }
  }
  private void emitState(State state) {
    System.out.println(state);
  }
  private void layoutPiece(PieceType pt, Board b, State state, Game game) {
    layout(b,game);
    
  }

  private class State{
    List<Piece> state = new LinkedList<Piece>();
    
  }
}
