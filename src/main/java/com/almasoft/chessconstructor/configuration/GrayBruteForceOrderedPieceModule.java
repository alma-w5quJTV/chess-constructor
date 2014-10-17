package com.almasoft.chessconstructor.configuration;

import com.almasoft.chessconstructor.game.OrderingPieceStrategy;
import com.almasoft.chessconstructor.game.PriorityOrderingPieceStrategy;
import com.almasoft.chessconstructor.model.BlackGraySetAwareArrayBoard;
import com.almasoft.chessconstructor.model.Board;
import com.google.inject.AbstractModule;


public class GrayBruteForceOrderedPieceModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(Board.class).to(BlackGraySetAwareArrayBoard.class);
    bind(OrderingPieceStrategy.class).to(PriorityOrderingPieceStrategy.class);
  }
}
