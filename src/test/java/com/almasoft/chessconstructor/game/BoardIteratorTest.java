package com.almasoft.chessconstructor.game;

import java.util.Iterator;

import org.junit.Assert;
import org.junit.Test;

import com.almasoft.chessconstructor.model.Board;
import com.almasoft.chessconstructor.model.Point;

public class BoardIteratorTest {
  @Test public void testIterator(){
    Board board = new Board(new Point(3,5));
    int count = 0;
    int maxX = 0;
    int maxY = 0;
    for (Iterator<Point> iterator = board.createAllCellIterator(); iterator.hasNext();) {
      Point p = iterator.next();
      maxX = Math.max(maxX, p.getX());
      maxY = Math.max(maxY, p.getY());
      count ++;
    }
    Assert.assertEquals(3*5, count);
    Assert.assertEquals(2, maxX);
    Assert.assertEquals(4, maxY);
  }
}
