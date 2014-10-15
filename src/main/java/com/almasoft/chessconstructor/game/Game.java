package com.almasoft.chessconstructor.game;

import com.almasoft.chessconstructor.model.Board;
import com.almasoft.chessconstructor.model.PieceType;
import com.almasoft.chessconstructor.model.Point;
import com.almasoft.chessconstructor.strategy.GameStream;
import com.almasoft.chessconstructor.strategy.PositionStrategy;
import com.google.inject.Inject;

public class Game {
  @Inject Board board; 
  
  @Inject OrderingPieceStrategy orderingPieceStrategy;
  
  public Game putPiece(PieceType type, int number){
    orderingPieceStrategy.put(type, Integer.valueOf(number));
    return this;
  }
  
  public PieceType takeNextPiece(){
    return orderingPieceStrategy.giveNextPiece();
  }
  
  public void startGame(int sizeX, int sizeY, GameStream out){
    board.init(new Point(sizeX, sizeY));
    
    PositionStrategy strategy = new PositionStrategy();
    strategy.setResultStream(out);
    
    strategy.layout(board, this);
  }

  public OrderingPieceStrategy getOrderingPieceStrategy() {
    return orderingPieceStrategy;
  }

  public void setOrderingPieceStrategy(OrderingPieceStrategy orderingPieceStrategy) {
    this.orderingPieceStrategy = orderingPieceStrategy;
  }
}