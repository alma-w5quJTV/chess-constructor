package com.almasoft.chessconstructor.model.capture;

import java.util.LinkedList;
import java.util.List;

import com.almasoft.chessconstructor.model.Board;
import com.almasoft.chessconstructor.model.Point;

public class UnionPattern implements Pattern {
  
  private List<Pattern> cc = new LinkedList<Pattern> ();
  public UnionPattern plus(Pattern c){
    cc.add(c);
    return this;
  }
  @Override
  public boolean belongsToPattern(Point p, Point origin) {
    for (Pattern capture : cc) {
      if(capture.belongsToPattern(p, origin)) return true;
    }
    return false;
  }
  @Override
  public void visit(Board board, Point origin, Visitor v) {
    for (Pattern capture : cc) {
      capture.visit(board, origin, v);
    }
  }
}
