package com.almasoft.chessconstructor.strategy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.almasoft.chessconstructor.game.Game;
import com.almasoft.chessconstructor.model.Piece;
import com.almasoft.chessconstructor.model.PieceType;

public class State{

  private List<Piece> state = new ArrayList<Piece>();
  private int pointer = 0;
  
  public void init(Game g){
    PieceType pt = null;
    while ((pt = g.takeNextPiece()) != null){
      state.add(new Piece(pt));
    }
  }
  /**
   * 
   * @return Iterator that returns all already placed pieces 
   */
  public Iterator<Piece> getPlacedPiecesIterator() {
    return new Iterator<Piece>(){
      int p = 0;
      @Override
      public boolean hasNext() {
        return p < pointer;
      }
      @Override
      public Piece next() {
        return state.get(p ++);
      }
      @Override
      public void remove() {
        
      }};
  }
  public boolean hasMorePieces() {
    return pointer < state.size();
  }

  public Piece take(){
    return state.get(pointer ++);
  }
  public Piece getCurrent(){
    return state.get(pointer - 1);
  }
  public Piece getPrevious(){
    Piece retVal = null;
    int prev = pointer - 2; 
    if(prev >= 0){
      retVal =  state.get(prev);
    }
    return retVal;
  }
  public void offer(){
    pointer --;
  }
  public void reset(){
    pointer = 0;
  }
  @Override
  public String toString() {
    return "State [state=" + state + "]";
  }
  

}