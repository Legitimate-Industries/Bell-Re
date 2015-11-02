package io.github.bobhostern.bellre.bell;

/**
 * Created by Bobhostern on 10/16/2015.
 */
public interface FieldStats {
    int getTurnCount();

    PlayerTeam getTurnPlayer();

    double getRemainingTime(PlayerTeam player);

    int getRemainingLP(PlayerTeam player);

    int getMaxLP();

    double getMaxTime();

    void turnOver(); // Used to switch the turn player, reset the remaining time for both players, and increase the turn count.

    void time(PlayerTeam pt, double t); // Removes some time from that player's time
}
