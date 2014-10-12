package com.almasoft.chessconstructor.model.capture;


import org.junit.Assert;
import org.junit.Test;

import com.almasoft.chessconstructor.model.Piece;
import com.almasoft.chessconstructor.model.PieceType;
import com.almasoft.chessconstructor.model.Point;

public class PieceCapturingTest {
  
  @Test
  public void testQueen(){
    Piece queen = new Piece(PieceType.QUEEN);
    queen.setPosition(new Point(3,3));
    
    Assert.assertTrue(queen.doesCapture(new Point(3,3)));
    Assert.assertTrue(queen.doesCapture(new Point(3,1)));
    Assert.assertFalse(queen.doesCapture(new Point(4,7)));
  }
  @Test
  public void testKing(){
    Piece king = new Piece(PieceType.KING);
    
    king.setPosition(new Point(0,0));
    Assert.assertTrue(king.doesCapture(new Point(1,0)));
    Assert.assertTrue(king.doesCapture(new Point(0,1)));
    Assert.assertFalse(king.doesCapture(new Point(2,2)));
    
    king.setPosition(new Point(1,1));
    Assert.assertTrue(king.doesCapture(new Point(2,1)));
    Assert.assertTrue(king.doesCapture(new Point(1,2)));
    Assert.assertFalse(king.doesCapture(new Point(3,5)));
  }
  
  @Test
  public void testBisshop(){
    Piece bisshop = new Piece(PieceType.BISSHOP);
    bisshop.setPosition(new Point(3,3));
    
    Assert.assertTrue(bisshop.doesCapture(new Point(3,3)));
    Assert.assertTrue(bisshop.doesCapture(new Point(6,6)));
    Assert.assertTrue(bisshop.doesCapture(new Point(6,0)));
    Assert.assertFalse(bisshop.doesCapture(new Point(6,1)));
  }
  @Test
  public void testKnight(){
    Piece knight = new Piece(PieceType.KNIGHT);
    
    knight.setPosition(new Point(0,0));
    Assert.assertTrue(knight.doesCapture(new Point(0,0)));
    Assert.assertTrue(knight.doesCapture(new Point(2,1)));
    Assert.assertTrue(knight.doesCapture(new Point(1,2)));
    
  }
}
