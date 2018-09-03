package sg.kata.domain.score;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import sg.kata.domain.model.IGameEngine;

/**
 * Created by Nassim MOUALEK on 31/08/2018.
 */
@RequiredArgsConstructor
abstract class ScoreExecutor {
    @NonNull
    protected IGameEngine game;

    public abstract void applyRules(String name);

    protected abstract int nextScore(int score);

}