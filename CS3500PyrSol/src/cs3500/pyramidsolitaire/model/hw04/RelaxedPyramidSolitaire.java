package cs3500.pyramidsolitaire.model.hw04;

import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Initializes RelaxedPyramid using extended class.
 */
public class RelaxedPyramidSolitaire extends BasicPyramidSolitaire {

  // first thing to do in inheritance: call super constructor
  public RelaxedPyramidSolitaire() {
    super();
  }

  @Override
  public void remove(int row1, int card1, int row2, int card2)
          throws IllegalArgumentException, IllegalStateException {
    // checks that the game has started
    super.hasGameStarted();
    // checks that row1 is valid index-wise
    super.checksRow(row1);
    // checks that card1 is valid index-wise
    super.checksCard(row1, card1);
    // checks that row2 is valid index-wise
    super.checksRow(row2);
    // checks that card2 is valid index-wise
    super.checksCard(row2, card2);

    if (row1 < (pyramid.size() - 1) && row2 < (pyramid.size() - 1)) {
      // applies Relaxed game mode rules of removing two cards
      if (meetsRelaxedRules(row1 + 1, card1, row2, card2, row1 + 1, card1 + 1)
              || meetsRelaxedRules(row1 + 1, card1 + 1, row2, card2, row1 + 1, card1)
              || meetsRelaxedRules(row2 + 1, card2, row1, card1, row2 + 1, card2 + 1)
              || meetsRelaxedRules(row2 + 1, card2 + 1, row1, card1, row2 + 1, card2)) {
        if (pyramid.get(row1).get(card1).value + pyramid.get(row2).get(card2).value == 13) {
          // removes card and replaces that value with a null
          pyramid.get(row1).set(card1, null);
          pyramid.get(row2).set(card2, null);
        }
      }
    }

    // checks if card1 is covered
    isCardCovered(row1, card1);
    // checks if card2 is covered
    isCardCovered(row2, row2);
    // checks that the selected cards equal thirteen
    addsToThirteen(row1, card1, row2, card2);

    // removes card and replaces that value with a null
    pyramid.get(row1).set(card1, null);
    pyramid.get(row2).set(card2, null);
  }

  @Override
  public boolean isGameOver() throws IllegalStateException {
    // checks that the game has started
    super.hasGameStarted();

    List<Card> faceUpCards = new ArrayList<Card>();

    // loops through the rows
    for (int row = 0; row < pyramid.size(); row += 1) {

      // loops through the columns
      for (int card = 0; card < pyramid.get(row).size(); card += 1) {
        if (row == pyramid.size() - 1) {
          for (Card c : pyramid.get(row)) {
            if (c != null) {
              Card check = new Card(c.value, c.suit, c.name);
              faceUpCards.add(check);
            }
          }
        }

        // checks if game is over based on Relaxed rules
        else if (pyramid.get(row).get(card) != null) {
          if (pyramid.get(row + 1).get(card) == null) {
            return pyramid.get(row).get(card).value
                    + pyramid.get(row + 1).get(card + 1).value == 13;
          }
          else if (pyramid.get(row + 1).get(card + 1) == null) {
            return pyramid.get(row).get(card).value
                    + pyramid.get(row + 1).get(card).value == 13;
          }
          else if (pyramid.get(row + 1).get(card) == null
                  && pyramid.get(row + 1).get(card + 1) == null) {

            Card currentC = new Card(pyramid.get(row).get(card).value,
                    pyramid.get(row).get(card).suit,
                    pyramid.get(row).get(card).name);
            faceUpCards.add(currentC);
          }
        }
      }
    }

    // checks if any two cards add to thirteen
    for (Card c : faceUpCards) {
      for (int j = 0; j < faceUpCards.size(); j += 1) {
        if (c != null && c != faceUpCards.get(j)) {
          if (c.value + faceUpCards.get(j).value == 13) {
            return false;
          }
          else {

            for (int d = 0; d < drawCards.size(); d += 1) {
              if (c.value + drawCards.get(d).value == 13) {
                return false;
              }
            }
          }
        }
      }
    }

    return true;
  }

  /**
   * Makes sure that it meets the Relaxed rules of removal.
   * @param row1 row of card one.
   * @param card1 column of card one.
   * @param row1 row of card two.
   * @param row2 column of card two.
   * @param nextRow next row below.
   * @param rowCard the card of row below.
   */
  protected boolean meetsRelaxedRules(int row1, int card1, int row2, int card2,
                                    int nextRow, int rowCard) {
    return pyramid.get(row1).get(card1) == pyramid.get(row2).get(card2)
            && pyramid.get(nextRow).get(rowCard) == null;
  }
}
