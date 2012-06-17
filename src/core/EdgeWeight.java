package core;

/**
 * Encapsulate a comparable which can also be added
 * and defines a zero weight
 * Should be used if the Graph Edge weights are not Integers
 */
public interface EdgeWeight extends Comparable {
    EdgeWeight add(EdgeWeight otherEw);

    EdgeWeight getZeroWeightI();
}
