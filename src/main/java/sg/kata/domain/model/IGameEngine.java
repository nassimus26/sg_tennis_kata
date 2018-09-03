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

    int getPlayerSetScore(String name);

    boolean isPlayerDeuce(String name);

    void setPlayerDeuce(String name, boolean deuce);

    void setPlayerGameScore(String name, int score);

    void setPlayerSetScore(String name, int score);

    Player getGameWinner();

    Player getSetWinner();

    Player getPointWinner();

    String getOpponentName(String name);

    String serialize();

    boolean isReady();

    void clearPlayers();

    int getPlayerTieBreakScore(String name);

    void setPlayerTieBreakScore(String name, int score);
}
