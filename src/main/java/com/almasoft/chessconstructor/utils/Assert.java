package com.almasoft.chessconstructor.utils;

public class Assert {
  public static void assertNotNull(Object o) throws AssertException {
    if(o == null){
      throw new AssertException("Expected non null value");
    }
  }
}
