package sg.kata.infrastructure.services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sg.kata.domain.model.IGameEngine;
import sg.kata.domain.model.Player;

@Service
@RequiredArgsConstructor
public class TennisGameService {
    @Getter
    private final IGameEngine game;

    public void joinTheGame(String name){
        game.join(new Player(name));
    }

    public void startMatch() {
        game.startMatch();
    }

    public void clearPlayers() {
        game.clearPlayers();
    }

    public Player getPointWinner() {
        return game.getPointWinner();
    }

    public Player getGameWinner() {
        return game.getGameWinner();
    }

    public Player getSetWinner() {
        return game.getSetWinner();
    }

    public int getPlayerGameScore(String name) {
        return game.getPlayerGameScore(name);
    }

    public int getPlayerSetScore(String name) {
        return game.getPlayerSetScore(name);
    }

    public boolean isPlayerDeuce(String name) {
        return game.isPlayerDeuce(name);
    }

    public void winPoint(String name) {
        game.winPoint(name);
    }

    public String getOpponentName(String name) {
        return game.getOpponentName(name);
    }

}