package com.almasoft.chessconstructor.game;

import org.junit.Assert;
import org.junit.Test;

import com.almasoft.chessconstructor.model.Piece;
import com.almasoft.chessconstructor.model.PieceType;
import com.almasoft.chessconstructor.model.Point;
import com.almasoft.chessconstructor.strategy.State;

public class CapturedStreamTest {
  @Test public void testCapturedStream(){
    CapturingGameStream out = new CapturingGameStream();
    
    Game game = new Game();
    game.setOrderingPieceStrategy(new PriorityOrderingPieceStrategy());
    
    game
      .putPiece(PieceType.QUEEN, 2)
      .putPiece(PieceType.KING, 2);
    
    State state = new State();
    state.init(game);
    
    Piece p = state.take();
    p.setPosition(new Point(1,2));
    p = state.take();
    p.setPosition(new Point(2,1));
    p = state.take();
    p.setPosition(new Point(3,1));
    p = state.take();
    p.setPosition(new Point(1,3));
    
    out.onGameReady(state);

    state.reset();

    p = state.take();
    p.setPosition(new Point(2,1));
    p = state.take();
    p.setPosition(new Point(1,2));
    p = state.take();
    p.setPosition(new Point(1,3));
    p = state.take();
    p.setPosition(new Point(3,1));

    out.onGameReady(state);
    
    Assert.assertEquals(2, out.getCounter());
    Assert.assertEquals(1, out.getSolutions().size());
  }
}
