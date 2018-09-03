package sg.kata.unit;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import sg.kata.domain.model.GameEngine;
import sg.kata.domain.model.Player;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by Nassim MOUALEK on 31/08/2018.
 */
public class TennisGameEngineTest {

    private GameEngine gameEngine;
    private Player player1;
    private Player player2;

    @Before
    public void setUp(){
        gameEngine = new GameEngine();
        player1 = new Player("player1");
        player2 = new Player("player2");
    }

    @Test
    public void testJoinGame() {
        gameEngine.join(player1);
        assertThat(gameEngine.isReady()).isFalse();
        gameEngine.join(player2);
        assertThat(gameEngine.isReady()).isTrue();
    }

    @Test
    public void testClearPlayers() {
        gameEngine.join(player1);
        gameEngine.join(player2);
        assertThat(gameEngine.isReady()).isTrue();
        gameEngine.clearPlayers();
        assertThat(gameEngine.isReady()).isFalse();
    }

    @Test
    public void testStartMatch_with_missed_player_should_throw_exception() {
        gameEngine.join(player1);
        Throwable throwable = catchThrowable(()->{
            gameEngine.startMatch();
        });
        assertThat(throwable).hasMessage(GameEngine.GAME_CANNOT_STARTED);
    }

    @Test
    public void testStartMatch_with_all_players() {
        gameEngine.join(player1);
        gameEngine.join(player2);
        gameEngine.startMatch();
        assertThat(gameEngine.getGameWinner()).isNull();
        assertThat(gameEngine.getSetWinner()).isNull();
        assertThat(gameEngine.getPointWinner()).isNull();
    }

    @Test
    public void testWinPoint() {
        gameEngine.join(player1);
        gameEngine.join(player2);
        gameEngine.startMatch();
        assertThat(gameEngine.getPlayerGameScore(player1.getName())).isEqualTo(0);
        assertThat(gameEngine.getPlayerGameScore(player2.getName())).isEqualTo(0);
        gameEngine.winPoint(player1.getName());

        assertThat(gameEngine.getPointWinner()).isEqualTo(player1);
        assertThat(gameEngine.getPlayerGameScore(player1.getName())).isEqualTo(15);
        assertThat(gameEngine.getPlayerGameScore(player2.getName())).isEqualTo(0);
    }

    private static int POINTS[] = new int[]{0, 15, 30, 40};

    @Test
    public void testWinGame() {
        gameEngine.join(player1);
        gameEngine.join(player2);
        gameEngine.startMatch();
        for (int i=0; i<POINTS.length; i++) {
            assertThat(gameEngine.getPlayerGameScore(player1.getName())).isEqualTo(POINTS[i]);
            assertThat(gameEngine.getPlayerGameScore(player2.getName())).isEqualTo(0);
            gameEngine.winPoint(player1.getName());
        }
        assertThat(gameEngine.getGameWinner()).isEqualTo(player1);
    }

    @Test
    public void testWinGameWithDeuce() {
        gameEngine.join(player1);
        gameEngine.join(player2);
        gameEngine.startMatch();
        for (int i=0; i<POINTS.length; i++) {
            assertThat(gameEngine.getPlayerGameScore(player1.getName())).isEqualTo(POINTS[i]);
            assertThat(gameEngine.getPlayerGameScore(player2.getName())).isEqualTo(POINTS[i]);
            gameEngine.winPoint(player1.getName());
            gameEngine.winPoint(player2.getName());
        }
        assertThat(gameEngine.getGameWinner()).isNull();
        assertThat(gameEngine.isPlayerDeuce(player1.getName())).isFalse();
        assertThat(gameEngine.isPlayerDeuce(player2.getName())).isFalse();
        gameEngine.winPoint(player1.getName());
        assertThat(gameEngine.getGameWinner()).isNull();
        assertThat(gameEngine.isPlayerDeuce(player1.getName())).isTrue();
        assertThat(gameEngine.isPlayerDeuce(player2.getName())).isFalse();
        gameEngine.winPoint(player2.getName());
        assertThat(gameEngine.getGameWinner()).isNull();
        assertThat(gameEngine.isPlayerDeuce(player1.getName())).isFalse();
        assertThat(gameEngine.isPlayerDeuce(player2.getName())).isFalse();
        gameEngine.winPoint(player2.getName());
        assertThat(gameEngine.getGameWinner()).isNull();
        assertThat(gameEngine.isPlayerDeuce(player1.getName())).isFalse();
        assertThat(gameEngine.isPlayerDeuce(player2.getName())).isTrue();
        gameEngine.winPoint(player2.getName());
        assertThat(gameEngine.getGameWinner()).isEqualTo(player2);
    }

    private void winAGame(Player player){
        for (int j = 0; j < POINTS.length; j++)
            gameEngine.winPoint(player.getName());
    }

    @Test
    public void testWinSet() {
        gameEngine.join(player1);
        gameEngine.join(player2);
        gameEngine.startMatch();
        for (int i=1; i<=6; i++) {
            for (int j = 0; j < POINTS.length; j++) {
                assertThat(gameEngine.getPlayerGameScore(player1.getName())).isEqualTo(POINTS[j]);
                assertThat(gameEngine.getPlayerGameScore(player2.getName())).isEqualTo(0);
                gameEngine.winPoint(player1.getName());
            }
            assertThat(gameEngine.getGameWinner()).isEqualTo(player1);
            assertThat(gameEngine.getPlayerSetScore(player1.getName())).isEqualTo(i);
            if (i<6)
                assertThat(gameEngine.getSetWinner()).isNull();
        }
        assertThat(gameEngine.getSetWinner()).isEqualTo(player1);
    }

    @Test
    public void testPlayer1_reach_6_and_the_other_has_4_player1_win() {
        gameEngine.join(player1);
        gameEngine.join(player2);
        gameEngine.startMatch();
        for (int i=1; i<=5; i++)
            winAGame(player1);
        for (int i=1; i<=4; i++)
            winAGame(player2);

        assertThat(gameEngine.getSetWinner()).isNull();
        assertThat(gameEngine.getPlayerSetScore(player1.getName())).isEqualTo(5);
        assertThat(gameEngine.getPlayerSetScore(player2.getName())).isEqualTo(4);
        winAGame(player1);
        assertThat(gameEngine.getPlayerSetScore(player1.getName())).isEqualTo(6);
        assertThat(gameEngine.getSetWinner()).isEqualTo(player1);
    }

    @Ignore // thz User story 1 of sprint 2 is not compatible with the User story 2
    @Test
    public void testPlayer1_reach_6_and_the_other_has_5_player1_must_reach_7_points() {
        gameEngine.join(player1);
        gameEngine.join(player2);
        gameEngine.startMatch();
        for (int i=1; i<=5; i++)
            winAGame(player1);
        for (int i=1; i<=5; i++)
            winAGame(player2);

        assertThat(gameEngine.getSetWinner()).isNull();
        assertThat(gameEngine.getPlayerSetScore(player1.getName())).isEqualTo(5);
        assertThat(gameEngine.getPlayerSetScore(player2.getName())).isEqualTo(5);
        winAGame(player1);
        assertThat(gameEngine.getPlayerSetScore(player1.getName())).isEqualTo(6);
        assertThat(gameEngine.getSetWinner()).isNull();
        winAGame(player1);
        assertThat(gameEngine.getPlayerSetScore(player1.getName())).isEqualTo(7);
        assertThat(gameEngine.getSetWinner()).isEqualTo(player1);
    }

    @Test
    public void testPlayers_reach_6_tieBreak_must_be_applied() {
        gameEngine.join(player1);
        gameEngine.join(player2);
        gameEngine.startMatch();
        for (int i=1; i<=6; i++)
            winAGame(player1);
        for (int i=1; i<=6; i++)
            winAGame(player2);

        assertThat(gameEngine.getPlayerTieBreakScore(player1.getName())).isEqualTo(0);
        assertThat(gameEngine.getSetWinner()).isNull();
        assertThat(gameEngine.getPlayerSetScore(player1.getName())).isEqualTo(6);
        assertThat(gameEngine.getPlayerSetScore(player2.getName())).isEqualTo(6);

        for (int tieBreakScore=1;tieBreakScore<=7;tieBreakScore++) {
            winAGame(player1);
            assertThat(gameEngine.getPlayerSetScore(player1.getName())).isEqualTo(6);
            if (tieBreakScore<7) {
                assertThat(gameEngine.getSetWinner()).isNull();
                assertThat(gameEngine.getPlayerTieBreakScore(player1.getName())).isEqualTo(tieBreakScore);
                assertThat(gameEngine.getMatchWinner()).isNull();
            }
        }
        assertThat(gameEngine.getSetWinner()).isEqualTo(player1);
        assertThat(gameEngine.getMatchWinner()).isEqualTo(player1);
    }

}
