import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import cs3500.pyramidsolitaire.view.PyramidSolitaireTextualView;

import java.util.List;

/**
 * Do not modify this file. This file should compile correctly with your code!
 * You DO NOT need to submit this file.
 */
public class Hw02TypeChecks {

  /**
   * The contents of this method are meaningless.
   * They are only here to ensure that your code compiles properly.
   */
  public static void main(String[] args) {
    helper(new BasicPyramidSolitaire());
  }

  private static <K> void helper(
          PyramidSolitaireModel<K> model) {
    PyramidSolitaireTextualView view =
            new PyramidSolitaireTextualView(model);

    String ignored = view.toString();
    List<K> deck = model.getDeck();
    model.startGame(deck, true, 7, 3);
    model.remove(1, 2, 3, 4);
    model.remove(5, 6);
    model.removeUsingDraw(1, 2, 3);
    model.discardDraw(2);
    boolean go = model.isGameOver();
    model.getCardAt(1, 2);
    model.getScore();
  }
}
