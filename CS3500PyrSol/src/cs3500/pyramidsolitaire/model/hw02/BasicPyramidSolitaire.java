package cs3500.pyramidsolitaire.model.hw02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;

/**
 * Represents an unparametized deck.
 */
public class BasicPyramidSolitaire implements PyramidSolitaireModel<Card> {
  // should we store the deck of cards as a
  protected List<Card> stock = new ArrayList<Card>();

  protected List<ArrayList<Card>> pyramid = new ArrayList<ArrayList<Card>>();

  protected List<Card> drawCards = new ArrayList<Card>();

  @Override
  public List<Card> getDeck() {

    ArrayList<Integer> vals = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5,
            6, 7, 8, 9, 10, 11, 12, 13));
    ArrayList<String> names = new ArrayList<String>(Arrays.asList("A", "2", "3", "4", "5", "6",
            "7", "8", "9", "10", "J", "Q", "K"));
    ArrayList<String> suits = new ArrayList<String>(Arrays.asList("♣", "♦", "♥", "♠"));

    List<Card> clientDeck = new ArrayList<Card>();
    // instantiates card
    Card card;
    // loops through values
    for (int i = 0; i < vals.size(); i += 1) {
      // loops through suits and creates 4, 1 for each suit for each value
      for (int j = 0; j < suits.size(); j += 1) {
        card = new Card(vals.get(i), suits.get(j), names.get(i));

        clientDeck.add(card);
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
    // checks the deck size
    if (deck.size() != 52) {
      throw new IllegalArgumentException("Not a valid deck.");
    }

    // checks that the number of rows is valid.
    if (numRows < 1) {
      throw new IllegalArgumentException("Invalid number of rows.");
    }

    int cardsDealt = 0;
    // makes sure there are enough cards to make rows
    for (int n = numRows; n > 0; n -= 1) {
      cardsDealt = cardsDealt + n;
    }
    if (cardsDealt + numDraw > deck.size()) {
      throw new IllegalArgumentException("Invalid number of rows.");
    }
    // checks that the number of draws is non-negative
    if (numDraw < 0) {
      throw new IllegalArgumentException("Invalid number of draws.");
    }
    //TODO: comment out when locally testing
    // checks for any duplicate cards.
    if (anyDuplicate(deck)) {
      throw new IllegalArgumentException("Not a valid deck.");
    }

    this.stock = new ArrayList<Card>(deck);

    if (shuffle) {
      Collections.shuffle(stock);
    }

    // keeps track of current card being moved to the pyramid

    // builds the pyramid
    for (int i = 1; i < numRows + 1; i += 1) {
      ArrayList<Card> row = new ArrayList<Card>();

      for (int j = 0; j < i; j += 1) {
        if (!(stock.isEmpty())) {
          row.add(stock.remove(0));
        }
      }
      pyramid.add(row);
    }

    if (stock.size() >= numDraw) {
      // initializes the face up draw cards
      for (int i = 0; i < numDraw; i += 1) {

        // removes card from beginning of stockpile
        drawCards.add(stock.remove(0));
      }
    }
  }

  @Override
  public void remove(int row1, int card1, int row2, int card2)
          throws IllegalArgumentException, IllegalStateException {
    // checks that the game has started
    hasGameStarted();
    // checks that row1 is valid index-wise
    checksRow(row1);
    // checks that card1 is valid index-wise
    checksCard(row1, card1);
    // checks that row2 is valid index-wise
    checksRow(row2);
    // checks that card2 is valid index-wise
    checksCard(row2, card2);
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
  public void remove(int row, int card) throws IllegalArgumentException,
          IllegalStateException {
    // checks that the game has started
    hasGameStarted();
    // checks that row1 is valid index-wise
    checksRow(row);
    // checks that card is valid index-wise
    checksCard(row, card);
    // checks that card is not covered
    isCardCovered(row, card);
    // checks that removed card equals thirteen
    equatesThirteen(row, card);

    pyramid.get(row).set(card, null);
  }

  @Override
  public void removeUsingDraw(int drawIndex, int row, int card)
          throws IllegalArgumentException, IllegalStateException {
    // checks that the game has started
    hasGameStarted();
    // checks that row1 is valid index-wise
    checksRow(row);
    // checks that card is valid index-wise
    checksCard(row, card);
    // checks that card is not covered
    isCardCovered(row, card);
    // checks the draw index validity
    drawIndexCheck(drawIndex);
    // checks that removed card equals thirteen
    addsThirteenIndex(drawIndex, row, card);

    pyramid.get(row).set(card, null);
    if (stock.isEmpty()) {
      drawCards.remove(drawIndex);
    }
    else {
      drawCards.set(drawIndex, stock.get(0));
    }

  }

  @Override
  public void discardDraw(int drawIndex) throws IllegalArgumentException, IllegalStateException {
    // checks that the game has started
    hasGameStarted();
    // checks the draw index validity
    drawIndexCheck(drawIndex);

    // checks to replace with card from stock
    if (stock.isEmpty()) {
      drawCards.remove(drawIndex);
    }
    else {
      drawCards.set(drawIndex, stock.remove(0));
    }
  }

  @Override
  public int getNumRows() {
    if (pyramid.isEmpty()) {
      return -1;
    }
    return pyramid.size();
  }

  @Override
  public int getNumDraw() {
    if (pyramid.isEmpty()) {
      return -1;
    }
    return drawCards.size();
  }

  @Override
  public int getRowWidth(int row) throws IllegalArgumentException, IllegalStateException {
    // checks that the game has started
    hasGameStarted();
    // checks that row1 is valid index-wise
    checksRow(row);

    return pyramid.get(row).size();
  }

  @Override
  public boolean isGameOver() throws IllegalStateException {
    // checks that the game has started
    hasGameStarted();

    List<Card> faceUpCards = new ArrayList<Card>();

    // loops through the rows
    for (int row = 0; row < pyramid.size(); row += 1) {

      // loops through the columns
      for (int card = 0; card < pyramid.get(row).size(); card += 1) {
        if (row == pyramid.size() - 1) {
          for (Card c : pyramid.get(row)) {
            if (c != null) {
              Card currentC = new Card(c.value, c.suit, c.name);
              faceUpCards.add(currentC);
            }
          }
        }
        else if (pyramid.get(row).get(card) != null) {
          if (pyramid.get(row + 1).get(card) == null
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
          if (c.value == 13) {
            return false;
          }
          else if (c.value + faceUpCards.get(j).value == 13) {
            return false;
          }
          else {

            // checks if any cards in the draw pile can be removed with an exposed
            // card
            for (int dr = 0; dr < drawCards.size(); dr += 1) {
              if (c.value + drawCards.get(dr).value == 13) {
                return false;
              }
            }

            // checks if any cards in the stock be removed
            for (int d = 0; d < stock.size(); d += 1) {
              if (c.value + stock.get(d).value == 13) {
                return false;
              }
            }
          }
        }
      }
    }

    return true;
  }

  @Override
  public int getScore() throws IllegalStateException {
    // checks that the game has started
    hasGameStarted();
    // represents the score
    Integer score = 0;

    for (int i = 0; i < pyramid.size(); i += 1) {
      for (Card c : pyramid.get(i)) {
        if (c != null) {
          score = score + c.value;
        }
      }
    }

    return score;
  }

  @Override
  public Card getCardAt(int row, int card) throws IllegalArgumentException, IllegalStateException {
    // checks that the game has started
    hasGameStarted();
    // checks that row1 is valid index-wise
    checksRow(row);
    // checks that card is valid index-wise
    checksCard(row, card);
    // checks that card is valid index-wise
    checksCard(row, card);

    return pyramid.get(row).get(card);
  }

  @Override
  public List<Card> getDrawCards() throws IllegalStateException {
    // checks that the game has started
    hasGameStarted();

    return new ArrayList<Card>(drawCards);
  }

  /**
   * constraint for checking that the game has started.
   * @throws IllegalArgumentException if game has not been started.
   */
  protected void hasGameStarted() throws IllegalStateException {
    if (pyramid.isEmpty()) {
      throw new IllegalStateException("Game has not started.");
    }
  }

  /**
   * checks that the row and card are not out of index.
   * @param row row of the desired card position, numbered from 0 from the top of the pyramid.
   * @throws IllegalArgumentException if the row is out of bounds.
   */
  protected void checksRow(int row) throws IllegalArgumentException {
    if (row > pyramid.size() - 1
            || row < 0) {
      throw new IllegalArgumentException("Invalid row number.");
    }
  }

  /**
   * checks that the card position is valid.
   * @param row row of the desired card position, numbered from 0 from the top of the pyramid.
   * @param card card of the desired card position, numbered from 0 from left.
   * @throws IllegalArgumentException is card is out of bounds of the pyramid.
   */
  protected void checksCard(int row, int card) throws IllegalArgumentException {
    if (card > pyramid.get(row).size() - 1
            || card < 0) {
      throw new IllegalArgumentException("Invalid card position.");
    }
  }

  /**
   * makes sure that the cards are uncovered.
   * @param row row of the desired card position, numbered from 0 from the top of the pyramid.
   * @param card card of the desired card position, numbered from 0 from left.
   * @throws IllegalArgumentException if card is covered.
   */
  protected void isCardCovered(int row, int card) throws IllegalArgumentException {
    if (row < pyramid.size() - 1) { // if I give it 7 shouldn't it just skip over this code?
      if (pyramid.get(row + 1).get(card) != null
              && pyramid.get(row + 1).get(card + 1) != null) {
        throw new IllegalArgumentException("Card is covered.");
      }
    }
  }

  /**
   * makes sure that the selected cards add to thirteen.
   * @param row1 row of the desired card position, numbered from 0 from the top of the pyramid.
   * @param card1 card of the desired card position, numbered from 0 from left.
   * @param row2 row of the desired card position, numbered from 0 from the top of the pyramid.
   * @param card2 card of the desired card position, numbered from 0 from left.
   * @throws IllegalArgumentException if does not add to thirteen.
   */
  protected void addsToThirteen(int row1, int card1, int row2, int card2)
          throws IllegalArgumentException {
    if (pyramid.get(row1).get(card1).value +
            pyramid.get(row2).get(card2).value != 13) {
      throw new IllegalArgumentException("Cannot remove selected cards.");
    }
  }

  /**
   * makes sure the selected card is equal to thirteen.
   * @param row row of the desired card position, numbered from 0 from the top of the pyramid.
   * @param card card of the desired card position, numbered from 0 from left.
   */
  protected void equatesThirteen(int row, int card) throws IllegalArgumentException {
    if (pyramid.get(row).get(card).value != 13) {
      throw new IllegalArgumentException("Cannot remove selected cards.");
    }
  }

  /**
   * makes sure that the drawIndex is valid.
   * @param drawIndex the card from the draw pile to be discarded.
   */
  protected void drawIndexCheck(int drawIndex) throws IllegalArgumentException {
    if (drawCards.isEmpty()) {
      throw new IllegalArgumentException("Draw pile empty.");
    }
    if (drawIndex >= drawCards.size() ||
            drawIndex < 0) {
      throw new IllegalArgumentException("Index out of draw cards' bounds.");
    }
  }

  /**
   * makes sure the cards selected from the pyramid and the draw adds to thirteen.
   * @param row row of the desired card position, numbered from 0 from the top of the pyramid.
   * @param card card of the desired card position, numbered from 0 from left.
   * @param drawIndex the card from the draw pile to be discarded.
   * @throws IllegalArgumentException if does not add to thirteen.
   */
  protected void addsThirteenIndex(int drawIndex, int row, int card)
          throws IllegalArgumentException {

    if (pyramid.get(row).get(card).value + drawCards.get(drawIndex).value != 13) {
      throw new IllegalArgumentException("Cannot remove selected cards.");
    }
  }

  /**
   * makes sure that the selected draw card equals thirteen.
   * @param drawIndex the card from the draw pile to be discarded.
   * @throws IllegalArgumentException if card does not equate thirteen.
   */
  protected void drawEquatesThirteen(int drawIndex) throws IllegalArgumentException {
    if (drawCards.get(drawIndex).value != 13) {
      throw new IllegalArgumentException("Cannot remove selected card.");
    }
  }


  /**
   * Checks for any duplicates.
   * @param stock decks of cards to check
   * @return whether the deck has any duplicates
   */
  protected boolean anyDuplicate(List<Card> stock) {
    final Set<Card> set1 = new HashSet<>();
    // checks that there are no duplicates in the deck
    for (Card card : stock) {
      if (!set1.add(card)) {
        return true;
      }
    }
    return false;
  }
}


