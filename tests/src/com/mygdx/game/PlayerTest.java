package com.mygdx.game;
import com.badlogic.gdx.Game;
import com.mygdx.game.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * @author Gandhi-Inc.
 * @version Assessment 4
 *          An executable version of the game can be found at: http://gandhi-inc.me/downloads/assessment4.jar
 *          Our website is: www.gandhi-inc.me
 */

public class PlayerTest extends TesterFile {

    private Game game = new Main();
    private Player TestPlayer = new Player(1, "player1");
    private GameEngine engine = new GameEngine(game, null);
    private Tile TestTile = new Tile(game, engine, 0,0,0,0, true, new Runnable() {
        @Override
        public void run() {

        }
    });
    private Roboticon TestRoboticon = new Roboticon(0, TestPlayer, TestTile);
    private College TestCollege = new College ("Derwent");

    @Test
    public void testAssignCollege(){
        TestPlayer.assignCollege(TestCollege);
        assertEquals(TestPlayer.getCollege(),TestCollege);
    }

    @Test
    public void testAssignTile() {
        List<Tile> TileList = new ArrayList<Tile>();
        assertEquals(TestPlayer.getTileList(),TileList);
        TestPlayer.assignTile(TestTile);
        assertNotEquals(TestPlayer.getTileList(), TileList);
    }

    @Test
    public void testVaryResource() {
        int ore = TestPlayer.getOreCount();
        int food = TestPlayer.getFoodCount();
        int energy = TestPlayer.getEnergyCount();
        int money = TestPlayer.getMoney();
        for (int i = 1; i < 200; i++){
            ore += i;
            food += i;
            energy += i;
            money += i;
            TestPlayer.varyResource("Ore", i);
            TestPlayer.varyResource("Food", i);
            TestPlayer.varyResource("Energy", i);
            TestPlayer.varyResource("Money", i);
            assertEquals(ore,TestPlayer.getOreCount());
            assertEquals(food,TestPlayer.getFoodCount());
            assertEquals(energy,TestPlayer.getEnergyCount());
            assertEquals(money,TestPlayer.getMoney());
        }


    }

    @Test
    public void testcalculateScore(){
        int ore = TestPlayer.getOreCount();
        int food = TestPlayer.getFoodCount();
        int energy = TestPlayer.getEnergyCount();
        int score = ore + food + energy;
        assertEquals(score, TestPlayer.calculateScore());
        //assertEquals((TestPlayer.getOreCount() + TestPlayer.getFoodCount() + TestPlayer.getEnergyCount()), TestPlayer.calculateScore());
    }

    @Test
    public void testIncreaseRoboticonInventory(){
        int count = TestPlayer.getInventoryRoboticons();
        count += 1;
        TestPlayer.increaseRoboticonInventory();
        assertEquals(count,TestPlayer.getInventoryRoboticons());
    }

    @Test
    public void testDecreaseRoboticonInventory(){
        int count = TestPlayer.getInventoryRoboticons();
        count -= 1;
        TestPlayer.decreaseRoboticonInventory();
        assertEquals(count,TestPlayer.getInventoryRoboticons());
    }




















}




