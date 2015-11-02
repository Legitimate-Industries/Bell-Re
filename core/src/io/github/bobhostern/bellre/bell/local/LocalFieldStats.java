package io.github.bobhostern.bellre.bell.local;

import io.github.bobhostern.bellre.bell.FieldStats;
import io.github.bobhostern.bellre.bell.PlayerTeam;

import java.util.Arrays;

/**
 * Created by Bobhostern on 10/16/2015.
 */
public class LocalFieldStats implements FieldStats {
    private int turnCount = 0;
    private PlayerTeam currentPlayer;
    private int[] playerLPs = new int[4];
    private double[] playerTimes = new double[4];
    private final int startingLP;
    private final double startingTime; // Note a startingTime of -1 means unlimited.
    private final PlayerTeam[] turnProgression;

    public LocalFieldStats(int slp, int st, PlayerTeam... playerProg) {
        assert playerProg.length > 0;
        startingLP = slp;
        startingTime = st;
        turnProgression = playerProg;
        currentPlayer = playerProg[0];
        for (int i = 0; i < 4; i++) {
            playerLPs[i] = startingLP;
            playerTimes[i] = startingTime;
        }
    }

    @Override
    public int getTurnCount() {
        return turnCount;
    }

    @Override
    public PlayerTeam getTurnPlayer() {
        return currentPlayer;
    }

    @Override
    public double getRemainingTime(PlayerTeam player) {
        return playerTimes[player.ordinal()];
    }

    @Override
    public int getRemainingLP(PlayerTeam player) {
        return playerLPs[player.ordinal()];
    }

    @Override
    public int getMaxLP() {
        return startingLP;
    }

    @Override
    public double getMaxTime() {
        return startingTime;
    }

    @Override
    public void turnOver() {
        for (int i = 0; i < 4; i++) {
            playerTimes[i] = startingTime;
        }
        int idx = Arrays.binarySearch(turnProgression, currentPlayer);
        idx = (++idx >= turnProgression.length ? 0 : idx);
        currentPlayer = turnProgression[idx];
        turnCount++;
    }

    @Override
    public void time(PlayerTeam pt, double t) {
        double d = playerTimes[pt.ordinal()];
        if (d - t < 0)
            playerTimes[pt.ordinal()] = 0;
        else
            playerTimes[pt.ordinal()] -= t;
    }
}
