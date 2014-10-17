package com.almasoft.chessconstructor.strategy;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.almasoft.chessconstructor.base.GuiceBaseTest;
import com.almasoft.chessconstructor.configuration.GrayBruteForceOrderedPieceModule;
import com.almasoft.chessconstructor.game.CapturingGameStream;
import com.almasoft.chessconstructor.game.Game;
import com.almasoft.chessconstructor.game.SimpleGameStream;
import com.almasoft.chessconstructor.model.PieceType;
import com.google.inject.Guice;
import com.google.inject.Inject;
/**
 * 
 * @author andriy_kostin
 *
 *  Result for first iteration (wrong because of permutation was not took into consideration):
 *  [3 x 3], Number is 8, time = 9 ms
 *  [4 x 4], Number is 384, time = 35 ms
 *  [7 x 7], Number is 24510624, time = 16490 ms
 *
 *  Second iteration (permutations fix)
 *  [3 x 3], Number is 4, time = 7 ms
 *  [4 x 4], Number is 8, time = 35 ms (8 = 384 / (2! 4!) )
 *  [5 x 5], Number is 1, time = 47 ms
 *  [7 x 7], Number is 3063828, time = 4963 ms (e.g. 3063828 = 24510624 / (2! 2! 2!))
 *
 *  Third iteration ordered Piece Strategy 
 *  [3 x 3], Number is 4, time = 3 ms
 *  [4 x 4], Number is 8, time = 6 ms
 *  [5 x 5], Number is 1, time = 34 ms
 *  [7 x 7], Number is 3063828, time = 2284 ms
 *  
 *  Forth iteration (BruteForce optimized)
 *  [25 x 33], Number is 75292800, time = 28653 ms
 *  [3 x 3], Number is 4, time = 0 ms
 *  [4 x 4], Number is 8, time = 10 ms
 *  [5 x 5], Number is 1, time = 19 ms
 *  [7 x 7], Number is 3063828, time = 2030 ms
 *  [10 x 17], Number is 1195840, time = 261 ms
 *  [5 x 5], Number is 1388, time = 0 ms
 *  [3 x 3], Number is 4, time = 20 ms
 *  [25 x 33], Number is 316800, time = 150 ms
 *  [50 x 60], Number is 4393150, time = 3841 ms
 *
 *  Fifth iteration (GrayBruteForce)
 *  [25 x 33], Number is 75292800, time = 20458 ms
 *  [3 x 3], Number is 4, time = 0 ms
 *  [4 x 4], Number is 8, time = 0 ms
 *  [5 x 5], Number is 1, time = 10 ms
 *  [7 x 7], Number is 3063828, time = 3008 ms
 *  [10 x 17], Number is 1195840, time = 340 ms
 *  [5 x 5], Number is 1388, time = 0 ms
 *  [3 x 3], Number is 4, time = 20 ms
 *  [25 x 33], Number is 316800, time = 100 ms
 *  [50 x 60], Number is 4393150, time = 1244 ms
 *
 */
public class StrategyTest extends GuiceBaseTest{
  
  @Inject Game game;
  
  @Before
  @Override
  public void setUp() throws IOException {
    injector = Guice.createInjector(new GrayBruteForceOrderedPieceModule());
//    injector = Guice.createInjector(new BruteForceOrderedPieceModule());
    injector.injectMembers(this);
  } 
  
  @Test public void test3x3(){
    GameStream out = startGame(3, 3, game.putPiece(PieceType.ROOK, 1).putPiece(PieceType.KING, 2),null);
    Assert.assertEquals(4, out.getCounter());
  }
  @Test public void test4x4(){
    GameStream out = startGame(4, 4, game.putPiece(PieceType.ROOK, 2).putPiece(PieceType.KNIGHT, 4),null);
    Assert.assertEquals(8, out.getCounter());
  }
  @Test public void test5x5(){
    GameStream out = startGame(5, 5, game.putPiece(PieceType.KING, 9),null);
    Assert.assertEquals(1, out.getCounter());
  }

  @Test public void testLinear5x5(){
    GameStream out = startGame(5, 5, game.putPiece(PieceType.ROOK, 2).putPiece(PieceType.BISSHOP, 2),null);
    Assert.assertEquals(1388, out.getCounter());
  }

  @Test public void test3RookNxM(){
    int x = 25;
    int y = 33;
    GameStream out = startGame(x, y, game.putPiece(PieceType.ROOK, 3),null);
    Assert.assertEquals((x*y* (x-1)*(y-1) * (x-2)*(y-2))/6 , out.getCounter());
  }
  @Test public void testRR_NxM(){
    int x = 25;
    int y = 33;
    GameStream out = startGame(x, y, game.putPiece(PieceType.ROOK, 2),null);
    Assert.assertEquals((x*y* (x-1)*(y-1))/2 , out.getCounter());
}
  
  @Test public void test2BishopNxM(){
    int x = 50;
    int y = 60;
    GameStream out = startGame(x, y, game.putPiece(PieceType.BISSHOP, 2),null);
    Assert.assertEquals(4393150 , out.getCounter());
  }
  
  @Test public void testRRB_3x3(){
    CapturingGameStream out = new CapturingGameStream();
    startGame(3, 3, game.putPiece(PieceType.ROOK, 2).putPiece(PieceType.BISSHOP, 1), out);
    Assert.assertEquals(4 , out.getCounter());
  }
  @Test public void testRRB_10x17(){
    GameStream out = startGame(10, 17, game.putPiece(PieceType.ROOK, 2).putPiece(PieceType.BISSHOP, 1), null);
    Assert.assertEquals(1195840 , out.getCounter());
  }
  @Test(timeout = 5000) public void test7x7(){
    GameStream out = startGame(7, 7, game.putPiece(PieceType.KING, 2).putPiece(PieceType.QUEEN, 2).putPiece(PieceType.BISSHOP, 2).putPiece(PieceType.KNIGHT, 1),null);
    Assert.assertEquals(3063828, out.getCounter());
  }
  
  @Test/*(timeout = 5000)*/ @Ignore public void test10x10(){
    GameStream out = startGame(10,10, game.putPiece(PieceType.KING, 2).putPiece(PieceType.QUEEN, 2).putPiece(PieceType.BISSHOP, 2).putPiece(PieceType.KNIGHT, 1),null);
    Assert.assertEquals(8, out.getCounter());
  }
  
  private GameStream startGame(int x, int y, Game game, GameStream out){
    long start = System.currentTimeMillis();
    if(out == null){
      out = new SimpleGameStream();
    }
    game.startGame(x, y, out);
    long finish = System.currentTimeMillis();
    System.out.println(String.format("[%d x %d], Number is %d, time = %d ms",x, y, out.getCounter(), finish - start));
    return out;
  }
}
