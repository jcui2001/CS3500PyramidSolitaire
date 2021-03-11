package cs3500.pyramidsolitaire.controller;

import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;


import java.util.List;

/**
 * plays a new game of Pyramid Solitaire using the provided model and startGame.
 */
public interface PyramidSolitaireController {

  <K> void playGame(PyramidSolitaireModel<K> model, List<K> deck, boolean shuffle,
                    int numRows, int numDraw);
}
