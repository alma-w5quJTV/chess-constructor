package com.almasoft.chessconstructor.game;

import com.almasoft.chessconstructor.model.PieceType;

public interface OrderingPieceStrategy {

  void put(PieceType type, Integer number);

  PieceType giveNextPiece();

}
