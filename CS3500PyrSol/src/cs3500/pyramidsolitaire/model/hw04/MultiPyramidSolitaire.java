package cs3500.pyramidsolitaire.model.hw04;

import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.Card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * To implement the MultiPyramid version of the game.
 */
public class MultiPyramidSolitaire extends BasicPyramidSolitaire {

  // first thing to do in inheritance: call super constructor
  public MultiPyramidSolitaire() {
    super();
  }

  @Override
  public List<Card> getDeck() {

    ArrayList<Integer> vals = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5,
            6, 7, 8, 9, 10, 11, 12, 13));
    ArrayList<String> names = new ArrayList<String>(Arrays.asList("A", "2", "3", "4", "5", "6",
            "7", "8", "9", "10", "J", "Q", "K"));
    ArrayList<String> suits = new ArrayList<String>(Arrays.asList("♣", "♦", "♥", "♠"));

    List<Card> clientDeck = new ArrayList<Card>();
    // instantiates card
    Card card1;
    Card card2;
    // loops through values
    for (int i = 0; i < vals.size(); i += 1) {
      // loops through suits and creates 4, 1 for each suit for each value
      for (int j = 0; j < suits.size(); j += 1) {
        card1 = new Card(vals.get(i), suits.get(j), names.get(i));
        card2 = new Card(vals.get(i), suits.get(j), names.get(i));

        clientDeck.add(card1);
        clientDeck.add(card2);
      }
    }
    return clientDeck;
  }

  @Override
  public void startGame(List<Card> deck, boolean shuffle, int numRows, int numDraw)
          throws IllegalArgumentException {
    // checks for a valid deck

    if (deck == null) {
      throw new IllegalArgumentException("Not a valid deck.");
    }
    if (deck.size() != 104) {
      throw new IllegalArgumentException("Not a valid deck.");
    }
    // checks that the number of draws is non-negative
    if (numDraw < 0) {
      throw new IllegalArgumentException("Invalid number of draws.");
    }
    // checks that the number of rows is valid.
    if (numRows < 1) {
      throw new IllegalArgumentException("Invalid number of rows.");
    }

    int cardsDealt = 0;
    for (int ch = 0; ch < numRows; ch += 1) {
      if (ch < java.lang.Math.floor(numRows / 2)) {
        cardsDealt += (ch + 1) * 3;
      }
      else {
        cardsDealt += (java.lang.Math.floor(numRows / 2) * 3)
                + (ch + 1 - java.lang.Math.floor(numRows / 2));
      }
    }
    if (cardsDealt + numDraw > deck.size()) {
      throw new IllegalArgumentException("Invalid number of rows.");
    }

    // adds given deck to the stock to avoid mutating the original deck
    this.stock = new ArrayList<Card>(deck);

    if (shuffle) {
      Collections.shuffle(stock);
    }

    // builds the pyramid
    if (numRows == 1) {
      ArrayList<Card> row = new ArrayList<Card>();

      // only adds one card to the pyramid
      row.add(stock.remove(0));
      pyramid.add(row);
    } else {
      for (int i = 0; i < numRows; i += 1) {
        ArrayList<Card> row = new ArrayList<Card>();

        // checks if this row is non-overlapping
        if (i < java.lang.Math.floor(numRows / 2)) {

          // loops the three pyramids when building the non-overlapping rows
          for (int peak = 0; peak < 3; peak += 1) {
            // adds the appropriate block of cards for each peak
            for (int perPeak = 0; perPeak < i + 1; perPeak += 1) {
              row.add(stock.remove(0));
            }

            // adds the spaces between peaks
            if (peak != 2) {
              for (int spaces = java.lang.Math.floorDiv(numRows, 2) - (i + 1); // 2
                   spaces > 0; spaces -= 1) {
                row.add(null);
              }
            }
          }
          pyramid.add(row);
        }

        else {
          for (int j = 0; j < (java.lang.Math.floorDiv(numRows, 2) * 3)
                  + (i + 1 - java.lang.Math.floorDiv(numRows, 2)); j += 1) {
            if (!(stock.isEmpty())) {
              row.add(stock.remove(0));
            }
          }
          pyramid.add(row);
        }
      }
    }

    if (stock.size() >= numDraw) {
      // initializes the face up draw cards
      for (int i = 0; i < numDraw; i += 1) {

        // removes card from beginning of stockpile
        drawCards.add(stock.remove(0));
      }
    }
  }
}
