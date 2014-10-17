package com.almasoft.chessconstructor.strategy;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.almasoft.chessconstructor.base.GuiceBaseTest;
import com.almasoft.chessconstructor.configuration.GrayBruteForceOrderedPieceModule;
import com.almasoft.chessconstructor.game.CapturingGameStream;
import com.almasoft.chessconstructor.game.Game;
import com.almasoft.chessconstructor.model.PieceType;
import com.google.inject.Guice;
import com.google.inject.Inject;

public class AnalisysOfResultStrategyTest extends GuiceBaseTest{
  
  @Inject Game game;

  @Before
  @Override
  public void setUp() throws IOException {
    injector = Guice.createInjector(new GrayBruteForceOrderedPieceModule());
    injector.injectMembers(this);
  } 
  
  @Test public void test7x7(){
    CapturingGameStream out = startGame(7, 7, game.putPiece(PieceType.KING, 2).putPiece(PieceType.QUEEN, 2).putPiece(PieceType.BISHOP, 2).putPiece(PieceType.KNIGHT, 1));
    Assert.assertEquals(3063828, out.getCounter());
    
    Assert.assertEquals(out.getCounter(), ((CapturingGameStream)out).getSolutions().size());
  }
  
  @Test public void test3x3(){
    CapturingGameStream out = startGame(3, 3, game.putPiece(PieceType.ROOK, 2).putPiece(PieceType.BISHOP, 1));
    Assert.assertEquals(4, out.getCounter());
    Assert.assertEquals(out.getCounter(), ((CapturingGameStream)out).getSolutions().size());
  }
  private CapturingGameStream startGame(int x, int y, Game game){
    long start = System.currentTimeMillis();
    CapturingGameStream out = new CapturingGameStream();
    game.startGame(x, y, out);
    long finish = System.currentTimeMillis();
    System.out.println(String.format("[%d x %d], Number is %d, time = %d ms",x, y, out.getCounter(), finish - start));
//    System.out.println(out.getSolutions());
//    System.out.println(out.print(new Point(x,y)));
    return out;
  }
}
