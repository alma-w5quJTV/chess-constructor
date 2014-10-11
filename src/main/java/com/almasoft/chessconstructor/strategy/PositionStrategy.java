package com.almasoft.chessconstructor.strategy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.almasoft.chessconstructor.game.Game;
import com.almasoft.chessconstructor.model.Board;
import com.almasoft.chessconstructor.model.Piece;
import com.almasoft.chessconstructor.model.PieceType;
import com.almasoft.chessconstructor.model.Point;

public class PositionStrategy {
  public void layout(Board b, Game game){
    State state = new State();
    state.init(game);
    PieceType pt = game.takeNextPiece();
    if(pt != null){
      
      layoutPiece(pt, b, state, game);
      
      game.returnPiece(pt);
    }else{
      emitState(state);
    }
  }
  private void emitState(State state) {
    System.out.println(state);
  }
  private void layoutPiece(Board b, State state) {
    Piece piece = state.take();
    
    for (Iterator<Point> iterator = b.createAllCellIterator(); iterator.hasNext();) {
      Point p = iterator.next();
      piece.setPosition(p);
      //verify corruption
      layoutPiece(b, state);
    }
    state.offer();
  }

  private class State{
    private List<Piece> state = new ArrayList<Piece>();
    private int pointer = 0;
    
    public void init(Game g){
      PieceType pt = null;
      while ((pt = g.takeNextPiece()) != null){
        state.add(new Piece(pt));
      }
    }
    
    public Piece take(){
      return state.get(pointer ++);
    }
    public void offer(){
      pointer --;
    }
  }
}
