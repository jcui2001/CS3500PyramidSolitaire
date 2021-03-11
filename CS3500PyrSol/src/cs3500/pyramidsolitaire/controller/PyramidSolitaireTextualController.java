package cs3500.pyramidsolitaire.controller;

import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import cs3500.pyramidsolitaire.view.PyramidSolitaireTextualView;
import cs3500.pyramidsolitaire.view.PyramidSolitaireView;

import java.io.IOException;

import java.util.List;
import java.util.Scanner;

/**
 * Controller to manage inputs and outputs.
 */
public class PyramidSolitaireTextualController implements PyramidSolitaireController {

  final Readable rd;
  final Appendable ap;

  /**
   * Controls the input and output flow.
   * @param rd    the inflow of input.
   * @param ap  the appended outflow.
   * @throws IllegalArgumentException if the attempted remove is invalid.
   */
  public PyramidSolitaireTextualController(Readable rd, Appendable ap)
          throws IllegalArgumentException {

    // throws exception if arguments are null
    if (rd == null
        || ap == null) {
      throw new IllegalArgumentException("Values are null.");
    }
    this.rd = rd;
    this.ap = ap;
  }

  @Override
  public <K> void playGame(PyramidSolitaireModel<K> model, List<K> deck,
                           boolean shuffle, int numRows, int numDraw)
          throws IllegalArgumentException, IllegalStateException {
    // represents view to render model and to append to Appendable
    PyramidSolitaireView textView = new PyramidSolitaireTextualView(model, this.ap);
    // Scans the Readable
    Scanner scan = new Scanner(this.rd);

    // check if model is null
    if (model == null) {
      throw new IllegalArgumentException("Invalid game.");
    }
    // checks if the deck is empty
    if (deck.isEmpty()) {
      throw new IllegalArgumentException("Invalid deck.");
    }

    // tries starting the game and throws an error if game cannot start
    try {
      model.startGame(deck, shuffle, numRows, numDraw);
    }
    catch (IllegalArgumentException e) {
      throw new IllegalStateException("Could not start game."); // append to the appendable
    }
    // tries rendering the text
    try {
      textView.render();
    }
    catch (IOException e) {
      throw new IllegalStateException("Could not append."); // append to appendable
    }

    // makes sure there is another input
    while (scan.hasNext()) {
      // scans the next token in the Readable
      String nextMove = scan.next();
      switch (nextMove) {
        case "q":
        case "Q":
          try {
            ap.append("Game quit!\nState of game when quit:\n" + textView.toString()
                    + "Score: " + model.getScore());
          } catch (IOException e) {
            throw new IllegalStateException("Cannot perform.");
          }
          return;
        case "rm1":
          try {
            model.remove(readNext(scan, textView), readNext(scan, textView));
            appendToAp(textView.toString() + "\n", ap);
          } catch (IllegalArgumentException e) {
            appendToAp(e.getMessage() + "\n" + textView.toString(), ap);
          }

          break;
        case "rm2":
          try {
            model.remove(readNext(scan, textView), readNext(scan, textView),
                    readNext(scan, textView), readNext(scan, textView));
            appendToAp(textView.toString()  + "\n", ap);
          } catch (IllegalArgumentException e) {
            appendToAp(e.getMessage() + "\n" + textView.toString(), ap);
          }
          break;
        case "rmd":
          try {
            model.removeUsingDraw(readNext(scan, textView), readNext(scan, textView),
                    readNext(scan, textView));
            appendToAp(textView.toString()  + "\n", ap);
          } catch (IllegalArgumentException e) {
            appendToAp(e.getMessage() + "\n" + textView.toString(), ap);
          }
          break;
        case "dd":
          try {
            model.discardDraw(readNext(scan, textView));
            appendToAp(textView.toString()  + "\n", ap);
          } catch (IllegalArgumentException e) {
            appendToAp(e.getMessage() + "\n" + textView.toString(), ap);
          }
          break;
        default:
          try {
            ap.append("Invalid command." + "\n");
          }
          catch (IOException e) {
            throw new IllegalStateException("No command." + "\n");
          }
      }
    }
  }

  // appends model to appendable after performing move
  private void appendToAp(String value, Appendable ap) throws IllegalArgumentException {
    try {
      ap.append(value);
    } catch (IOException io) {
      throw new IllegalArgumentException(value);
    }
  }

  // reads the next item in the scanner
  private Integer readNext(Scanner s, PyramidSolitaireView view) {
    while (true) {
      if (s.hasNext()) {
        // stores next token
        if (s.hasNextInt()) {
          int value = s.nextInt() - 1;
          // returns a value
          return value;
        }
        else {
          String nextItem = s.next();
          if (nextItem.equals("q") || nextItem.equals("Q")) {
            try {
              ap.append("Game quit!\nState of game when quit: \n" + view.toString());
            } catch (IOException e) {
              throw new IllegalStateException("Cannot perform.");
            }
          }
          else {
            try {
              ap.append("Invalid input.");
            } catch (IOException e) {
              throw new IllegalArgumentException("Cannot perform.");
            }
          }
        }
      }
      else {
        throw new IllegalStateException("No inputs.");
      }
    }
  }
}
// should we be throwing these errors and ending the game?

