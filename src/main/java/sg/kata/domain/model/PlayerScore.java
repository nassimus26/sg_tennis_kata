package sg.kata.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by Nassim MOUALEK on 31/08/2018.
 */
@Getter
@ToString
@Setter
public abstract class PlayerScore {

    private int score;

    public void clear() {
        score = 0;
    }
}