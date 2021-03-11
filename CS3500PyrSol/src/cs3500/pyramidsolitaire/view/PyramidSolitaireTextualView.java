package cs3500.pyramidsolitaire.view;

import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;

import java.io.IOException;


/**
 * Represents the View of the model in order to display the model in String.
 */
public class PyramidSolitaireTextualView implements PyramidSolitaireView {
  private final PyramidSolitaireModel<?> model;
  // ... any other fields you need
  private Appendable in;

  public PyramidSolitaireTextualView(PyramidSolitaireModel<?> model) {
    this.model = model;
  }

  // constructor for model and Appendable
  public PyramidSolitaireTextualView(PyramidSolitaireModel<?> model, Appendable in) {
    this.model = model;
    this.in = in;
  }

  @Override
  public void render() throws IllegalArgumentException {
    try {
      this.in.append(this.toString());
    } catch (IOException e) {
      throw new IllegalArgumentException("Game is not over yet.");
    }
  }

  @Override
  public String toString() {
    if (model.getNumRows() == -1) {
      return "";
    }
    else if (model.getScore() == 0) {
      return "You win!";
    }
    else if (model.isGameOver()) {
      return "Game over. Score: " + model.getScore();
    }

    int numSpaces = (this.model.getNumRows() * 2) - 2;
    String indentSpaces = "                               ";
    String result = "";

    for (int i = 0; i < model.getNumRows(); i += 1) {
      // indents the proper number of spaces for each row when displayed
      result += indentSpaces.substring(0, numSpaces - (i * 2));

      for (int j = 0; j < model.getRowWidth(i); j += 1) {
        if (model.getCardAt(i, j) == null) {
          if (model.getRowWidth(i) - 1 == i) {
            result += ".  ";
          }
          else {
            result += ".   ";
          }
        }
        else {
          String toBeAppended = model.getCardAt(i, j).toString();

          if (model.getRowWidth(i) - 1 != j) {
            if (toBeAppended.substring(0, 2).equals("10")) {
              result += toBeAppended + " ";
            } else {
              result += toBeAppended + "  ";
            }
          } else {
            result += toBeAppended;
          }
        }
      }
      result += "\n"; // wack not slash
    }

    result += "Draw: ";

    for (int i = 0; i < model.getDrawCards().size(); i += 1) {
      result += model.getDrawCards().get(i).toString() + ", ";
    }
    result = result.substring(0, result.length() - 2) + "\n";

    return result.substring(0, result.length() - 1) + "\n";
  }
}
