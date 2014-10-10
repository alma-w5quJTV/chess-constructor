package com.almasoft.chessconstructor.model.capture;


import org.junit.Assert;
import org.junit.Test;

import com.almasoft.chessconstructor.model.Piece;
import com.almasoft.chessconstructor.model.PieceType;
import com.almasoft.chessconstructor.model.Point;

public class PieceCapturingTest {
  
  @Test
  public void testQueens(){
    Piece queen = new Piece(PieceType.QUEEN);
    queen.setPosition(new Point(3,3));
    
    Assert.assertTrue(queen.doesCapture(new Point(3,3)));
    Assert.assertTrue(queen.doesCapture(new Point(3,1)));
    Assert.assertFalse(queen.doesCapture(new Point(4,7)));
  }
  @Test
  public void testKing(){
    Piece queen = new Piece(PieceType.KING);
    
    queen.setPosition(new Point(0,0));
    Assert.assertTrue(queen.doesCapture(new Point(1,0)));
    Assert.assertTrue(queen.doesCapture(new Point(0,1)));
    Assert.assertFalse(queen.doesCapture(new Point(2,2)));
    
    queen.setPosition(new Point(1,1));
    Assert.assertTrue(queen.doesCapture(new Point(2,1)));
    Assert.assertTrue(queen.doesCapture(new Point(1,2)));
    Assert.assertFalse(queen.doesCapture(new Point(3,5)));
  }
}
