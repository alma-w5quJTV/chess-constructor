package com.almasoft.chessconstructor.model.capture;

import com.almasoft.chessconstructor.model.Board;
import com.almasoft.chessconstructor.model.Point;

public class PointsPattern implements Pattern {
  private Point[] pp;
  
  public PointsPattern(Point... pp) {
    this.pp = pp;
  }
  
  public Point[] getPoints() {
    return pp;
  }

  @Override
  public boolean belongsToPattern(Point p, Point origin) {
    Point normalized = new Point(p.getX() - origin.getX(), p.getY() - origin.getY());
    
    for (Point point : pp) {
      if(point.equals(normalized)) return true;
    }
    return false;
  }

  @Override
  public void visit(Board board, Point origin, Visitor v) {
    int dimX = board.getDimention().getX();
    int dimY = board.getDimention().getY();
    
    for (Point point : pp) {
      int x = point.getX() + origin.getX();
      if(x >= 0 && x < dimX){
        int y = point.getY() + origin.getY();
        if(y >= 0 && y < dimY){
          v.visit(x, y);
        }
      }
    }
  }
}
