package io.github.bobhostern.bellre.bell;

import com.badlogic.gdx.utils.Disposable;

import java.util.Map;

/**
 * This interface is to create GameCard instances.
 * This basically loads all cards that are available.
 *
 * @author Bobho_000
 */
public interface CardLibrary extends Disposable {
    /**
     * Loads the list of cards.
     */
    void loadCards();

    Map<String, GameCard> getCards();

    int getTotal();

    int getCurrent();
}
