package com.almasoft.chessconstructor.strategy;

import org.junit.Assert;
import org.junit.Test;

import com.almasoft.chessconstructor.game.Game;
import com.almasoft.chessconstructor.game.SimpleGameStream;
import com.almasoft.chessconstructor.model.PieceType;
/**
 * 
 * @author andriy_kostin
 *
 *  Result for first iteration (wrong because of permutation was not took into consideration):
 *  [3 x 3], Number is 8, time = 9 ms
 *  [4 x 4], Number is 384, time = 35 ms
 *  [7 x 7], Number is 24510624, time = 16490 ms
 *
 */
public class StrategyTest {
  @Test public void test3x3(){
    SimpleGameStream out = startGame(3, 3, new Game().putPiece(PieceType.ROOK, 1).putPiece(PieceType.KING, 2));
    Assert.assertEquals(4, out.getCounter());
  }
  @Test public void test4x4(){
    SimpleGameStream out = startGame(4, 4, new Game().putPiece(PieceType.ROOK, 2).putPiece(PieceType.KNIGHT, 4));
    Assert.assertEquals(8, out.getCounter());
  }
  @Test(timeout = 5000) public void test7x7(){
    SimpleGameStream out = startGame(7, 7, new Game().putPiece(PieceType.KING, 2).putPiece(PieceType.QUEEN, 2).putPiece(PieceType.BISSHOP, 2).putPiece(PieceType.KNIGHT, 1));
    Assert.assertEquals(8, out.getCounter());
  }
  /**
   * 
   */
  @Test(timeout = 5000) public void test10x10(){
    SimpleGameStream out = startGame(10,10,new Game().putPiece(PieceType.KING, 2).putPiece(PieceType.QUEEN, 2).putPiece(PieceType.BISSHOP, 2).putPiece(PieceType.KNIGHT, 1));
    Assert.assertEquals(8, out.getCounter());
  }
  
  private SimpleGameStream startGame(int x, int y, Game game){
    long start = System.currentTimeMillis();
    SimpleGameStream out = new SimpleGameStream();
    game.startGame(x, y, out);
    long finish = System.currentTimeMillis();
    System.out.println(String.format("[%d x %d], Number is %d, time = %d ms",x, y, out.getCounter(), finish - start));
    return out;
  }
}
