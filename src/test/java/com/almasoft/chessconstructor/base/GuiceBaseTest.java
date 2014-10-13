package com.almasoft.chessconstructor.base;

import java.io.IOException;

import org.junit.Before;

import com.almasoft.chessconstructor.configuration.BruteForceModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class GuiceBaseTest {
  
  protected Injector injector;

  @Before
  public void setUp() throws IOException {
    injector = Guice.createInjector(new BruteForceModule());
    injector.injectMembers(this);
  }  
}
