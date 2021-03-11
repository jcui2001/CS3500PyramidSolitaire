package cs3500.pyramidsolitaire.model.hw02;

import java.util.Objects;

/**
 * Represents a Card with a value, a suit, and its name.
 */

public class Card {

  public String name;
  public Integer value;
  public String suit;

  /**
   * Constructs a {@code Card} object.
   *
   * @param value the card value.
   * @param suit  the suit of the card.
   * @param name  the name of the card.
   */
  public Card(Integer value, String suit, String name) {
    this.value = value;
    this.suit = suit;
    this.name = name;
  }

  @Override
  public String toString() {
    return this.name + this.suit;
  }

  @Override
  public boolean equals(Object that) {
    if (this == that) {
      return true;
    }

    if (!(that instanceof Card)) {
      return false;
    }

    return ((Card) that).value == this.value
            && ((Card) that).suit == this.suit;
  }

  @Override
  public int hashCode() {
    return Objects.hash(value, suit, name);
  }
}
