package sg.kata.domain.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Nassim MOUALEK on 31/08/2018.
 */
public class PlayerSetScore extends PlayerScore {
    @Getter
    @Setter
    private int tieBreak;
}