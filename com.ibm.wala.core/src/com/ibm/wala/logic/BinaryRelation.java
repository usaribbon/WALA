/*******************************************************************************
 * Copyright (c) 2006 IBM Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package com.ibm.wala.logic;

import java.util.Map;

import com.ibm.wala.util.collections.HashMapFactory;

public class BinaryRelation implements IRelation {

  public final static BinaryRelation EQUALS = new BinaryRelation("=");

  public final static BinaryRelation NE = new BinaryRelation("/=");

  public final static BinaryRelation LT = new BinaryRelation("<");

  public final static BinaryRelation LE = new BinaryRelation("<=");

  public final static BinaryRelation GT = new BinaryRelation(">");

  public final static BinaryRelation GE = new BinaryRelation(">=");

  private final static Map<BinaryRelation, BinaryRelation> negations = HashMapFactory.make();
  static {
    negations.put(EQUALS, NE);
    negations.put(NE, EQUALS);
    negations.put(LT, GE);
    negations.put(GE, LT);
    negations.put(GT, LE);
    negations.put(LE, GT);
  }

  private final static Map<BinaryRelation, BinaryRelation> swap = HashMapFactory.make();
  static {
    swap.put(LT, GT);
    swap.put(GE, LE);
    swap.put(GT, LT);
    swap.put(LE, GE);
  }

  private final String symbol;

  protected BinaryRelation(String symbol) {
    this.symbol = symbol;
  }

  public int getValence() {
    return 2;
  }

  @Override
  public String toString() {
    return getSymbol() + " : int x int";
  }

  @Override
  public int hashCode() {
    final int PRIME = 31;
    int result = 1;
    result = PRIME * result + ((symbol == null) ? 0 : symbol.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    final BinaryRelation other = (BinaryRelation) obj;
    if (symbol == null) {
      if (other.symbol != null)
        return false;
    } else if (!symbol.equals(other.symbol))
      return false;
    return true;
  }

  public String getSymbol() {
    return symbol;
  }

  public static BinaryRelation make(String symbol) {
    return new BinaryRelation(symbol);
  }

  /**
   * Attempt to negate a relation symbol. Return null if unsuccessful.
   */
  public static BinaryRelation negate(IRelation relation) {
    return negations.get(relation);
  }

  /**
   * Return the relation which is the "swap" of the original relation. Return
   * null if unsuccessful.
   */
  public static BinaryRelation swap(IRelation relation) {
    return swap.get(relation);
  }

}
