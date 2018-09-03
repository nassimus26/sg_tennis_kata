package sg.kata.domain.score;

import lombok.extern.slf4j.Slf4j;
import sg.kata.domain.model.IGameEngine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Nassim MOUALEK on 31/08/2018.
 */
@Slf4j
public class GameScoreExecutor extends ScoreExecutor {

    public GameScoreExecutor(IGameEngine game){
        super( game );
    }

    public static List<Integer> STEPS = new ArrayList(Arrays.asList(new Integer[]{0, 15, 30, 40}));

    @Override
    public void applyRules(String player){
        String opponent = game.getOpponentName(player);
        int currentScore = game.getPlayerGameScore(player);
        int newScore = nextScore(currentScore);
        if (currentScore==40) {
            if (game.getPlayerGameScore(opponent)!=40){
                game.winTheGame(player);
            }
            else if (game.isPlayerDeuce(player)) {
                game.winTheGame(player);
            }else
            if (!game.isPlayerDeuce(opponent)) {
                game.setPlayerDeuce(player, true);
                game.setPlayerDeuce(opponent, false);
                log.debug("Deuce Case " +
                        game.getPlayerName(player) + " take the adventage");
            }else{
                game.setPlayerDeuce(opponent, false);
                log.debug("Deuce Case " + game.getPlayerName(opponent) + " loose the adventage");
            }
        }else
            game.setPlayerGameScore(player, newScore);

        log.debug("New Game Score : " + game.serialize());
    }

    @Override
    protected int nextScore(int score){
        int index = STEPS.indexOf(score);
        if ( index < STEPS.size()-1 )
            return STEPS.get(index+1);
        return STEPS.get(index);
    }

}