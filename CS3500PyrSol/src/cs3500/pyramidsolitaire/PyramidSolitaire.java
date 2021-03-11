package cs3500.pyramidsolitaire;

import cs3500.pyramidsolitaire.controller.PyramidSolitaireController;
import cs3500.pyramidsolitaire.controller.PyramidSolitaireTextualController;

import cs3500.pyramidsolitaire.model.hw04.PyramidSolitaireCreator;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;

import java.io.StringReader;

/**
 * class to execute game.
 */
public class PyramidSolitaire {
  /**
   * main method to execute game.
   * @param args console inputs to play game.
   */
  public static void main(String[] args) {

    PyramidSolitaireCreator game = new PyramidSolitaireCreator();
    Readable rd = new StringReader("");
    Appendable ap = new StringBuilder();
    PyramidSolitaireModel model = null;
    PyramidSolitaireController controller = new PyramidSolitaireTextualController(rd, ap);
    String version = args[0];

    // checks for any given arguments validity
    int numRow;
    int numDraw;

    if (version.equals("basic")) {
      model = game.create(PyramidSolitaireCreator.GameType.BASIC);
    }
    else if (version.equals("relaxed")) {
      model = game.create(PyramidSolitaireCreator.GameType.RELAXED);
    }
    else if (version.equals("multipyramid")) {
      model = game.create(PyramidSolitaireCreator.GameType.MULTIPYRAMID);
    }

    if (args.length - 1 > 0) {

      try {
        // parses and gets the
        numRow = Integer.parseInt(args[1]);
        numDraw = Integer.parseInt(args[2]);
      } catch (NumberFormatException e) {
        return;
      }
    }
    else {
      // initializes row to seven rows
      numRow = 7;
      // initializes the draw cards to three
      numDraw = 3;
    }

    model.startGame(model.getDeck(), false, numRow, numDraw);
    /*

    PyramidSolitaireCreator game = new PyramidSolitaireCreator();
    Readable rd = new InputStreamReader(System.in);
    Appendable ap = System.out;
    PyramidSolitaireModel model = new RelaxedPyramidSolitaire();
    PyramidSolitaireController controller = new PyramidSolitaireTextualController(rd, ap);
    controller.playGame(model, model.getDeck(), true, 3, 3);



    PyramidSolitaireCreator game = new PyramidSolitaireCreator();
    Readable rd = new InputStreamReader(System.in);
    Appendable ap = System.out;
    PyramidSolitaireModel model = new RelaxedPyramidSolitaire();
    PyramidSolitaireController controller = new PyramidSolitaireTextualController(rd, ap);
    controller.playGame(model, model.getDeck(), false, 3, 3);


     */
  }
}
// handling drawing the score when game is quit?
// make a helper that just appends the score?
// if statement?