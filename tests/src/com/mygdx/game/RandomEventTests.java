package com.mygdx.game;
import com.badlogic.gdx.Game;
import com.mygdx.game.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * @author Gandhi-Inc.
 * @version Assessment 4
 *          An executable version of the game can be found at: http://gandhi-inc.me/downloads/assessment4.jar
 *          Our website is: www.gandhi-inc.me
 */

public class RandomEventTests extends TesterFile {

    private Game game;
    private GameScreen gameScreen;
    private GameEngine gameEngine;
    private PlayerSelectScreen testPlayerScreen;


    @Before
    public void setup() {
        game = new Main();
        gameEngine = new GameEngine(game, gameScreen);
        Player[] players = new Player[2];
        players[0] = new Player(0, "TEST");
        players[1] = new AiPlayer(1);
        gameEngine.setPlayers(players);
    }

    @Test
    public void testEarthquakeCutsProduction() throws Exception {
        Earthquake testEarthquake = new Earthquake(gameEngine, gameScreen);
        ArrayList<Tile> tilesDamagedBeforeQuake = testEarthquake.getTilesDamaged();
        int tileDamageValue = testEarthquake.getTileDamageValue();
        try {
            testEarthquake.eventHappen(true);
        }
        catch (Exception e) {

        }
        ArrayList<Tile> tileDamagedAfterQuake = testEarthquake.getTilesDamaged();


        for (int tile = 0; tile < tilesDamagedBeforeQuake.size(); tile++) {
            assertEquals(tilesDamagedBeforeQuake.get(tile).getOreCount() / tileDamageValue, tileDamagedAfterQuake.get(tile).getOreCount());
            assertEquals(tilesDamagedBeforeQuake.get(tile).getEnergyCount() / tileDamageValue, tileDamagedAfterQuake.get(tile).getEnergyCount());
            assertEquals(tilesDamagedBeforeQuake.get(tile).getFoodCount() / tileDamageValue, tileDamagedAfterQuake.get(tile).getFoodCount());
        }
    }

    @Test
    public void testEarthquakeResetsValuesAfterDuration() throws Exception {
        Earthquake testEarthquake = new Earthquake(gameEngine, gameScreen);
        ArrayList<Tile> tilesDamagedBeforeQuake = testEarthquake.getTilesDamaged();
        try {
            testEarthquake.eventHappen(true);
        }
        catch (Exception e) {}
        try {
            testEarthquake.eventHappen(false);
        }
        catch (Exception e) {}
        ArrayList<Tile> tilesDamagedAferRepair = testEarthquake.getTilesDamaged();

        for (int tile = 0; tile < tilesDamagedBeforeQuake.size(); tile++) {
            assertEquals(tilesDamagedBeforeQuake.get(tile).getOreCount(), tilesDamagedAferRepair.get(tile).getOreCount());
            assertEquals(tilesDamagedBeforeQuake.get(tile).getEnergyCount(), tilesDamagedAferRepair.get(tile).getEnergyCount());
            assertEquals(tilesDamagedBeforeQuake.get(tile).getFoodCount(), tilesDamagedAferRepair.get(tile).getFoodCount());
        }
    }

    @Test
    public void testRoboticonMalfunctionsSuccessfully() throws Exception {
        Tile testTile = new Tile(game, gameEngine, 0,0,0,0, true, new Runnable() {
            @Override
            public void run() {
            }
        });

        gameEngine.players()[0].assignTile(testTile);
        Roboticon testRoboticon = new Roboticon(1, gameEngine.players()[0], gameEngine.players()[0].getTileList().get(0));
        Malfunction testMalfunction = new Malfunction(gameEngine, gameScreen, 0);
        try {
            testMalfunction.eventHappen(true);
        }
        catch (Exception e) {}

        int[] roboticonLevelsAfterMalfunction = testMalfunction.getRoboticonToMalfunction().getLevel();
        int[] expectedRoboticonLevels = {0, 0, 0};
        assertArrayEquals(expectedRoboticonLevels, roboticonLevelsAfterMalfunction);
    }

    @Test
    public void testRoboticonRevertsAfterMalfunction() {
        Tile testTile = new Tile(game, gameEngine, 0,0,0,0, true, new Runnable() {
            @Override
            public void run() {
            }
        });

        gameEngine.players()[0].assignTile(testTile);
        Roboticon testRoboticon = new Roboticon(1, gameEngine.players()[0], gameEngine.players()[0].getTileList().get(0));
        Malfunction testMalfunction = new Malfunction(gameEngine, gameScreen, 0);
        int[] startingRoboticonLevels = testMalfunction.getStartingRoboticonLevels();
        try {
            testMalfunction.eventHappen(true);
        }
        catch (Exception e) {}
        try {
            testMalfunction.eventHappen(false);
        }
        catch (Exception e) {}

        int[] roboticonLevelsAfterFix = testMalfunction.getRoboticonToMalfunction().getLevel();
        assertArrayEquals(startingRoboticonLevels, roboticonLevelsAfterFix);

    }

}
