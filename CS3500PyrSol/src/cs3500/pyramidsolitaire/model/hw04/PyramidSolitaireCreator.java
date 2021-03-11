package cs3500.pyramidsolitaire.model.hw04;

import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import cs3500.pyramidsolitaire.model.hw04.MultiPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw04.RelaxedPyramidSolitaire;

/**
 * Factory to instantiate the game based on the given input.
 */
public class PyramidSolitaireCreator {

  /**
   * Initializes enumerations of versions of the game.
   */
  public enum GameType {
   BASIC,
   RELAXED,
   MULTIPYRAMID
  }

  /**
   * Creates game type based on enumeration.
   * @param type Version of game.
   * @return instance of selected version.
   */
  public static PyramidSolitaireModel create(GameType type) {
    //
    if (type.equals(GameType.BASIC)) {
      return new BasicPyramidSolitaire();
    }
    if (type.equals(GameType.RELAXED)) {
      return new RelaxedPyramidSolitaire();
    }
    if (type.equals(GameType.MULTIPYRAMID)) {
      return new MultiPyramidSolitaire();
    }
    return null;
  }
}
