package com.almasoft.chessconstructor.strategy;

import org.junit.Assert;
import org.junit.Test;

import com.almasoft.chessconstructor.model.NMLineEncoder;
import com.almasoft.chessconstructor.model.Point;

public class NMLineEncoderTest {
  @Test public void testDiagonalEncoder(){
    NMLineEncoder e = new NMLineEncoder(new Point(10,5));
    
    e.markLine(0, 0, NMLineEncoder.DIAGONAL_LINE_MASK);
    e.markLine(1, 4, NMLineEncoder.DIAGONAL_LINE_MASK);
    
    e.markLine(5, 2, NMLineEncoder.DIAGONAL_LINE_MASK);
    
    e.markLine(7, 1, NMLineEncoder.CO_DIAGONAL_LINE_MASK);
    
    e.markLine(9, 2, NMLineEncoder.CO_DIAGONAL_LINE_MASK);
    e.markLine(9, 4, NMLineEncoder.CO_DIAGONAL_LINE_MASK);
    e.markLine(9, 0, NMLineEncoder.CO_DIAGONAL_LINE_MASK);
    
    Assert.assertTrue(e.isLineMarked(0, 3, NMLineEncoder.DIAGONAL_LINE_MASK));
    Assert.assertTrue(e.isLineMarked(7, 4, NMLineEncoder.DIAGONAL_LINE_MASK));
    Assert.assertTrue(e.isLineMarked(9, 4, NMLineEncoder.CO_DIAGONAL_LINE_MASK));
    Assert.assertTrue(e.isLineMarked(8, 3, NMLineEncoder.CO_DIAGONAL_LINE_MASK));
    Assert.assertTrue(e.isLineMarked(8, 0, NMLineEncoder.CO_DIAGONAL_LINE_MASK));
    
    e.unmarkLine(8, 0, NMLineEncoder.CO_DIAGONAL_LINE_MASK);
    Assert.assertFalse(e.isLineMarked(7, 1, NMLineEncoder.CO_DIAGONAL_LINE_MASK));
    
  } 
  @Test public void testHorisontalEncoder(){
    NMLineEncoder e = new NMLineEncoder(new Point(10,5));
    
    e.markLine(0, 0, NMLineEncoder.HORISONTAL_LINE_MASK);
    e.markLine(3, 3, NMLineEncoder.HORISONTAL_LINE_MASK);
    e.markLine(0, 1, NMLineEncoder.HORISONTAL_LINE_MASK);
    
    e.markLine(0, 0, NMLineEncoder.VERTICAL_LINE_MASK);
    e.markLine(3, 5, NMLineEncoder.VERTICAL_LINE_MASK);
    e.markLine(9, 5, NMLineEncoder.VERTICAL_LINE_MASK);
    
    Assert.assertTrue(e.isLineMarked(9, 0, NMLineEncoder.HORISONTAL_LINE_MASK));
    Assert.assertTrue(e.isLineMarked(1, 3, NMLineEncoder.HORISONTAL_LINE_MASK));
    Assert.assertTrue(e.isLineMarked(9, 1, NMLineEncoder.HORISONTAL_LINE_MASK));
    
    e.unmarkLine(0, 1, NMLineEncoder.HORISONTAL_LINE_MASK);
    Assert.assertFalse(e.isLineMarked(9, 1, NMLineEncoder.HORISONTAL_LINE_MASK));
    
    Assert.assertTrue(e.isLineMarked(0, 1, NMLineEncoder.VERTICAL_LINE_MASK));
    Assert.assertTrue(e.isLineMarked(3, 2, NMLineEncoder.VERTICAL_LINE_MASK));
    Assert.assertTrue(e.isLineMarked(0, 3, NMLineEncoder.VERTICAL_LINE_MASK));
  }
  //[B : 1,1][R : 1,3][R : 2,1]
  @Test public void testBRR(){
    NMLineEncoder e = new NMLineEncoder(new Point(3,3));
    
    e.markLine(0, 0, NMLineEncoder.VERTICAL_LINE_MASK);
    e.markLine(0, 0, NMLineEncoder.HORISONTAL_LINE_MASK);
    
    Assert.assertTrue(e.isLineMarked(0, 2, NMLineEncoder.VERTICAL_LINE_MASK));
  }
  
}
 