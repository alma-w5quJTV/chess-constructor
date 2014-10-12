package com.almasoft.chessconstructor.model.capture;


import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.almasoft.chessconstructor.model.Board;
import com.almasoft.chessconstructor.model.Piece;
import com.almasoft.chessconstructor.model.PieceType;
import com.almasoft.chessconstructor.model.Point;

public class VisitorCapturingTest {
  
  @Test
  public void testVisitingQueen(){
    Board b = new Board(new Point(5, 5));
    Piece queen = new Piece(PieceType.QUEEN);
    queen.setPosition(new Point(1,1));
    
    Set<Point> captured = collect(queen, b);
    Assert.assertEquals(15, captured.size());
    Assert.assertTrue(captured.contains(new Point(3,3)));
    Assert.assertTrue(captured.contains(new Point(2,0)));
    Assert.assertFalse(captured.contains(new Point(3,2)));
  }
  @Test
  public void testVisitingRook(){
    Board b = new Board(new Point(5, 5));
    Piece roook = new Piece(PieceType.ROOK);
    roook.setPosition(new Point(1,1));
    Set<Point> captured = collect(roook, b);
    Assert.assertEquals(9, captured.size());
  }
  @Test
  public void testVisitingKing(){
    Board b = new Board(new Point(5, 5));
    Piece king = new Piece(PieceType.KING);
    king.setPosition(new Point(1,1));
    Set<Point> captured = collect(king, b);
    Assert.assertEquals(9, captured.size());
    
    king.setPosition(new Point(0,0));
    captured = collect(king, b);
    Assert.assertEquals(4, captured.size());
  }
  @Test
  public void testVisitingKnight(){
    Board b = new Board(new Point(5, 5));
    Piece knight = new Piece(PieceType.KNIGHT);
    knight.setPosition(new Point(0,0));
    Set<Point> captured = collect(knight, b);
    Assert.assertEquals(3, captured.size());
    
    Assert.assertTrue(captured.contains(new Point(2,1)));
    Assert.assertTrue(captured.contains(new Point(1,2)));
    Assert.assertTrue(captured.contains(new Point(0,0)));
  }
  

  private Set<Point> collect(Piece p, Board b){
    final Set<Point> captured = new HashSet<Point>();
    Visitor v = new Visitor() {
      @Override
      public void visit(int x, int y) {
        captured.add(new Point(x,y));
      }
    };
    p.getType().getCapture().visit(b, p.getPosition(), v);
    return captured;
  }
  
}
