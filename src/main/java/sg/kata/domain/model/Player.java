package sg.kata.domain.model;

import lombok.*;

/**
 * Created by Nassim MOUALEK on 31/08/2018.
 */
@Getter
@RequiredArgsConstructor
@ToString
public class Player {

    @NonNull
    private String name;
    @Setter
    private int index;
    private PlayerGameScore gameScore = new PlayerGameScore();
    private PlayerSetScore setScore = new PlayerSetScore();

    final public void resetScores(){
        resetGameScore();
        resetSetScore();
    }

    final public void resetGameScore(){
        gameScore.clear();
    }

    final public void resetSetScore(){
        gameScore.clear();
    }
}
