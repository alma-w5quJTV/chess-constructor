package com.almasoft.chessconstructor.model;

import com.almasoft.chessconstructor.model.capture.Pattern;
import com.almasoft.chessconstructor.model.capture.PointsPattern;
import com.almasoft.chessconstructor.model.capture.UnionPattern;
import com.almasoft.chessconstructor.model.capture.VectorsPattern;

public enum PieceType {
  KING(new PointsPattern(
      Point.VECTOR_1_0,
      Point.VECTOR_1_1,
      Point.VECTOR_0_1,
      Point.VECTOR_m1_1,
      Point.VECTOR_m1_0,
      Point.VECTOR_m1_m1,
      Point.VECTOR_0_m1,
      Point.VECTOR_1_m1)), 
  /*KNIGHT(new PointsCapture(new Point(),Point.VECTOR_1_1,Point.VECTOR_0_1,Point.VECTOR_m1_1,Point.VECTOR_m1_0,Point.VECTOR_m1_m1,Point.VECTOR_0_m1,Point.VECTOR_1_m1)),*/
  BISSHOP(new VectorsPattern(
      Point.VECTOR_1_1,
      Point.VECTOR_m1_1)), 
  ROOK(new VectorsPattern(
      Point.VECTOR_1_0,
      Point.VECTOR_0_1)), 
  QUEEN(new UnionPattern()
      .plus(BISSHOP.getCapture())
      .plus(ROOK.getCapture()));
  PieceType(Pattern capture){
    this.capture = capture;
  }
  Pattern capture;
  
  Pattern getCapture(){
    return capture;
  }
}