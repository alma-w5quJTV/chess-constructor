package com.almasoft.chessconstructor.model;

import com.almasoft.chessconstructor.model.capture.Pattern;
import com.almasoft.chessconstructor.model.capture.PointsPattern;
import com.almasoft.chessconstructor.model.capture.UnionPattern;
import com.almasoft.chessconstructor.model.capture.VectorsPattern;

public enum PieceType {
  KING('K', 
      new PointsPattern(
        Point.POINT_0_0,
        Point.VECTOR_1_0,
        Point.VECTOR_1_1,
        Point.VECTOR_0_1,
        Point.VECTOR_m1_1,
        Point.VECTOR_m1_0,
        Point.VECTOR_m1_m1,
        Point.VECTOR_0_m1,
        Point.VECTOR_1_m1), false), 
  KNIGHT('N',
      new PointsPattern(
        Point.POINT_0_0,
        new Point(2,1),
        new Point(2,-1),
        new Point(1,2),
        new Point(-1,2),
        new Point(-2,1),
        new Point(-2,-1),
        new Point(1,-2),
        new Point(-1,-2)), false),
  BISHOP('B',
      new VectorsPattern(
        Point.VECTOR_1_1,
        Point.VECTOR_m1_1), true), 
  ROOK('R',
      new VectorsPattern(
        Point.VECTOR_1_0,
        Point.VECTOR_0_1), true), 
  QUEEN('Q',
      new UnionPattern()
        .plus(BISHOP.getCapture())
        .plus(ROOK.getCapture()), true);
  PieceType(char code, Pattern capture, boolean isLinear){
    this.capture = capture;
    this.code = code;
    this.isLinear = isLinear;
  }
  private Pattern capture;
  private char code;
  private boolean isLinear = false;
  
  public Pattern getCapture(){
    return capture;
  }
  public char getCode() {
    return code;
  }
  public boolean isLinear() {
    return isLinear;
  }
}