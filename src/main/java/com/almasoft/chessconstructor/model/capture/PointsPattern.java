package com.almasoft.chessconstructor.model.capture;

import com.almasoft.chessconstructor.model.Point;

public class PointsPattern implements Pattern {
  private Point[] pp;
  
  public PointsPattern(Point... pp) {
    this.pp = pp;
  }

  @Override
  public boolean belongsToPattern(Point p, Point origin) {
    for (Point point : pp) {
      if(point.equals(p)) return true;
    }
    return false;
  }
}
