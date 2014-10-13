package com.almasoft.chessconstructor.configuration;

import com.almasoft.chessconstructor.game.OrderingPieceStrategy;
import com.almasoft.chessconstructor.game.RundomOrderingPieceStrategy;
import com.almasoft.chessconstructor.model.BlackSetAwareArrayBoard;
import com.almasoft.chessconstructor.model.Board;
import com.google.inject.AbstractModule;


public class BruteForceModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(Board.class).to(BlackSetAwareArrayBoard.class);
    bind(OrderingPieceStrategy.class).to(RundomOrderingPieceStrategy.class);
  }
}
