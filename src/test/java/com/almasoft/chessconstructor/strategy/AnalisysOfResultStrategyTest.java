package com.almasoft.chessconstructor.strategy;

import org.junit.Assert;
import org.junit.Test;

import com.almasoft.chessconstructor.base.GuiceBaseTest;
import com.almasoft.chessconstructor.game.CapturingGameStream;
import com.almasoft.chessconstructor.game.Game;
import com.almasoft.chessconstructor.model.PieceType;
import com.google.inject.Inject;

public class AnalisysOfResultStrategyTest extends GuiceBaseTest{
  
  @Inject Game game;

  @Test public void test7x7(){
    CapturingGameStream out = startGame(7, 7, game.putPiece(PieceType.KING, 2).putPiece(PieceType.QUEEN, 2).putPiece(PieceType.BISSHOP, 2).putPiece(PieceType.KNIGHT, 1));
    Assert.assertEquals(3063828, out.getCounter());
    
    Assert.assertEquals(out.getCounter(), ((CapturingGameStream)out).getSolutions().size());
  }
  
  private CapturingGameStream startGame(int x, int y, Game game){
    long start = System.currentTimeMillis();
    CapturingGameStream out = new CapturingGameStream();
    game.startGame(x, y, out);
    long finish = System.currentTimeMillis();
    System.out.println(String.format("[%d x %d], Number is %d, time = %d ms",x, y, out.getCounter(), finish - start));
    System.out.println(out.getSolutions());
    return out;
  }
}