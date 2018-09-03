package sg.kata.domain.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Nassim MOUALEK on 31/08/2018.
 */
@Getter
@Setter
public class PlayerGameScore extends PlayerScore {
    private boolean deuce;

    @Override
    public void clear() {
        super.clear();
        this.deuce = false;
    }
}