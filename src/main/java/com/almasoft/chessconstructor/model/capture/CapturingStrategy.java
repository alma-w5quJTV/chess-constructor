package com.almasoft.chessconstructor.model.capture;

import com.almasoft.chessconstructor.model.Piece;
import com.almasoft.chessconstructor.model.Point;

@Deprecated
public interface CapturingStrategy {
  public void capture(Piece p);
  public boolean isCaptured(Point p);
}
