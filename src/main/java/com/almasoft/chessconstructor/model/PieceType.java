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
        Point.VECTOR_1_m1)), 
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
        new Point(-1,-2))),
  BISSHOP('B',
      new VectorsPattern(
        Point.VECTOR_1_1,
        Point.VECTOR_m1_1)), 
  ROOK('R',
      new VectorsPattern(
        Point.VECTOR_1_0,
        Point.VECTOR_0_1)), 
  QUEEN('Q',
      new UnionPattern()
        .plus(BISSHOP.getCapture())
        .plus(ROOK.getCapture()));
  PieceType(char code, Pattern capture){
    this.capture = capture;
    this.code = code;
  }
  Pattern capture;
  char code;
  public Pattern getCapture(){
    return capture;
  }
  public char getCode() {
    return code;
  }
}