package com.almasoft.chessconstructor.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.almasoft.chessconstructor.base.GuiceBaseTest;
import com.almasoft.chessconstructor.model.Piece;
import com.almasoft.chessconstructor.model.PieceType;
import com.almasoft.chessconstructor.strategy.State;
import com.google.inject.Inject;

public class StateTest extends GuiceBaseTest {
  
  @Inject Game game;
  
  @Test public void testStateOperations(){
    game  
      .putPiece(PieceType.KING, 1)
      .putPiece(PieceType.QUEEN, 3)
      .putPiece(PieceType.ROOK, 2);
    State state = new State();
    state.init(game);
    
    Piece p = state.take();
    Assert.assertSame(state.getCurrent(), p);
    Assert.assertSame(state.getCurrent(), p);
    
    Assert.assertNull(state.getPrevious());
    
    List<Piece> pp = collectPlaced(state);
    Assert.assertEquals(1, pp.size());
    Assert.assertSame(state.getCurrent(), pp.get(0));
    
    Piece prev = state.take();
    Assert.assertSame(state.getCurrent(), prev);
    Assert.assertSame(state.getPrevious(), p);
    
    pp = collectPlaced(state);
    
    Assert.assertEquals(2, pp.size());
    Assert.assertSame(state.getCurrent(), pp.get(1));
    Assert.assertSame(state.getPrevious(), pp.get(0));
    
    state.offer();
    
    Assert.assertSame(state.getCurrent(), p);
    Assert.assertNull(state.getPrevious());
    
  }
  private List<Piece> collectPlaced(State state){
    List<Piece> pp = new ArrayList<Piece>();
    for (Iterator<Piece> i = state.getPlacedPiecesIterator(); i.hasNext();) {
      pp.add(i.next());
    }
    return pp;
  }
}
