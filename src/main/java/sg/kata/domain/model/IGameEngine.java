package sg.kata.domain.model;

public interface IGameEngine {

    void join(Player player);

    void winPoint(String name);

    void winTheGame(String name);

    void winTheSet(String name);

    void winTheMatch(String name);

    void startMatch();

    void resetGameScores();

    void resetSetScores();

    String getPlayerName(String name);

    int getPlayerGameScore(String name);

    void setPlayerGameScore(String name, int score);

    Player getGameWinner();

    Player getSetWinner();

    Player getPointWinner();

    String getOpponentName(String name);

    String serialize();

    boolean isReady();

    void clearPlayers();

    boolean isPlayerDeuce(String name);

    void setPlayerDeuce(String name, boolean deuce);

}
