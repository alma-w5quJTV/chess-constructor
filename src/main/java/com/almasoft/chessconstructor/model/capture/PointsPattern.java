package com.almasoft.chessconstructor.model.capture;

import com.almasoft.chessconstructor.model.Point;

public class PointsPattern implements Pattern {
  private Point[] pp;
  
  public PointsPattern(Point... pp) {
    this.pp = pp;
  }

  @Override
  public boolean belongsToPattern(Point p, Point origin) {
    Point normalized = new Point(p.getX() - origin.getX(), p.getY() - origin.getY());
    
    for (Point point : pp) {
      if(point.equals(normalized)) return true;
    }
    return false;
  }
}
