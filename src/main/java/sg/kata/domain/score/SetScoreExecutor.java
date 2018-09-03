package sg.kata.domain.score;
import lombok.extern.slf4j.Slf4j;
import sg.kata.domain.model.IGameEngine;

@Slf4j
public class SetScoreExecutor extends ScoreExecutor {

    public SetScoreExecutor(IGameEngine game){
        super(game);
    }

    @Override
    public void applyRules(String player){

        String opponent = game.getOpponentName(player);
        int currentScore = game.getPlayerSetScore(player);
        int newScore = nextScore(currentScore);
        if ( newScore >= 6 ) {
            if (game.getPlayerSetScore(opponent) <=4 ) {
                game.winTheSet(player);
                game.setPlayerSetScore( player, newScore );
            }else if (currentScore>=6 && game.getPlayerSetScore(opponent)>=6) {
                game.setPlayerTieBreakScore(player, game.getPlayerTieBreakScore(player) + 1);
                if (game.getPlayerTieBreakScore(player)>=7 && game.getPlayerTieBreakScore(player)-game.getPlayerTieBreakScore(opponent)>2){
                    game.winTheSet(player);
                    game.winTheMatch(player);
                }
            }else
                game.setPlayerSetScore( player, newScore );
        }else
            game.setPlayerSetScore( player, newScore );
        log.debug( "New Set Score " + game.serialize() );

    }

    @Override
    protected int nextScore(int score){
        return score+1;
    }

}