package com.almasoft.chessconstructor.base;

import java.io.IOException;

import org.junit.Before;

import com.almasoft.chessconstructor.configuration.BruteForceOrderedPieceModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class GuiceBaseTest {
  
  protected Injector injector;

  @Before
  public void setUp() throws IOException {
//    injector = Guice.createInjector(new BruteForceModule());
    injector = Guice.createInjector(new BruteForceOrderedPieceModule());
    injector.injectMembers(this);
  }  
}
