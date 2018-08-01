package tic_tac_toe.rest.tools;

import java.util.ArrayList;
import java.util.List;
import tic_tac_toe.entity.Move;
import tic_tac_toe.enums.GameSymbol;

@SuppressWarnings("checkstyle:magicnumber")
public class MoveHelper {

    /**
     * Helper for moves less than 7
     * @param size less than 7
     * @return moves
     */
    public static List<Move> createMoves(int size) {
        List<Move> moves = new ArrayList<>();
        int counter = 0;

        int maxSize = 3;
        A: for (int i = 0; i < maxSize; i++) {
            for (int j = 0; j < maxSize; j++) {
                Move move = new Move()
                        .setHorizontalPosition(i + 1)
                        .setVerticalPosition(j + 1)
                        .setGameSymbol(counter % 2 == 0 ? GameSymbol.CROSS : GameSymbol.NAUGHT)
                        .setMoveNumber(++counter);
                moves.add(move);

                if (counter >= size) {
                    break A;
                }
            }
        }

        return moves;
    }

    public static List<Move> createEightMovesForDraw() {
        List<Move> moves = new ArrayList<>();
        moves.add(new Move()
                .setGameSymbol(GameSymbol.CROSS)
                .setHorizontalPosition(2)
                .setVerticalPosition(1)
                .setMoveNumber(1));
        moves.add(new Move()
                .setGameSymbol(GameSymbol.NAUGHT)
                .setHorizontalPosition(1)
                .setVerticalPosition(1)
                .setMoveNumber(2));
        moves.add(new Move()
                .setGameSymbol(GameSymbol.CROSS)
                .setHorizontalPosition(2)
                .setVerticalPosition(2)
                .setMoveNumber(3));
        moves.add(new Move()
                .setGameSymbol(GameSymbol.NAUGHT)
                .setHorizontalPosition(1)
                .setVerticalPosition(2)
                .setMoveNumber(4));
        moves.add(new Move()
                .setGameSymbol(GameSymbol.CROSS)
                .setHorizontalPosition(3)
                .setVerticalPosition(2)
                .setMoveNumber(5));
        moves.add(new Move()
                .setGameSymbol(GameSymbol.NAUGHT)
                .setHorizontalPosition(2)
                .setVerticalPosition(3)
                .setMoveNumber(6));
        moves.add(new Move()
                .setGameSymbol(GameSymbol.CROSS)
                .setHorizontalPosition(3)
                .setVerticalPosition(3)
                .setMoveNumber(7));
        moves.add(new Move()
                .setGameSymbol(GameSymbol.NAUGHT)
                .setHorizontalPosition(3)
                .setVerticalPosition(1)
                .setMoveNumber(8));

        return moves;
    }

    public static List<Move> createFourMovesForWin() {
        List<Move> moves = new ArrayList<>();
        moves.add(new Move()
                .setGameSymbol(GameSymbol.CROSS)
                .setHorizontalPosition(1)
                .setVerticalPosition(1)
                .setMoveNumber(1));
        moves.add(new Move()
                .setGameSymbol(GameSymbol.NAUGHT)
                .setHorizontalPosition(2)
                .setVerticalPosition(1)
                .setMoveNumber(2));
        moves.add(new Move()
                .setGameSymbol(GameSymbol.CROSS)
                .setHorizontalPosition(1)
                .setVerticalPosition(2)
                .setMoveNumber(3));
        moves.add(new Move()
                .setGameSymbol(GameSymbol.NAUGHT)
                .setHorizontalPosition(2)
                .setVerticalPosition(2)
                .setMoveNumber(4));

        return moves;
    }

    public static List<Move> createFiveMovesForWin() {
        List<Move> moves = new ArrayList<>();
        moves.add(new Move()
                .setGameSymbol(GameSymbol.CROSS)
                .setHorizontalPosition(1)
                .setVerticalPosition(1)
                .setMoveNumber(1));
        moves.add(new Move()
                .setGameSymbol(GameSymbol.NAUGHT)
                .setHorizontalPosition(1)
                .setVerticalPosition(2)
                .setMoveNumber(2));
        moves.add(new Move()
                .setGameSymbol(GameSymbol.CROSS)
                .setHorizontalPosition(1)
                .setVerticalPosition(3)
                .setMoveNumber(3));
        moves.add(new Move()
                .setGameSymbol(GameSymbol.NAUGHT)
                .setHorizontalPosition(2)
                .setVerticalPosition(2)
                .setMoveNumber(4));
        moves.add(new Move()
                .setGameSymbol(GameSymbol.CROSS)
                .setHorizontalPosition(2)
                .setVerticalPosition(1)
                .setMoveNumber(5));

        return moves;
    }

    public static List<Move> createSixMovesForWin() {
        List<Move> moves = new ArrayList<>();
        moves.add(new Move()
                .setGameSymbol(GameSymbol.CROSS)
                .setHorizontalPosition(1)
                .setVerticalPosition(1)
                .setMoveNumber(1));
        moves.add(new Move()
                .setGameSymbol(GameSymbol.NAUGHT)
                .setHorizontalPosition(1)
                .setVerticalPosition(2)
                .setMoveNumber(2));
        moves.add(new Move()
                .setGameSymbol(GameSymbol.CROSS)
                .setHorizontalPosition(2)
                .setVerticalPosition(1)
                .setMoveNumber(3));
        moves.add(new Move()
                .setGameSymbol(GameSymbol.NAUGHT)
                .setHorizontalPosition(1)
                .setVerticalPosition(3)
                .setMoveNumber(4));
        moves.add(new Move()
                .setGameSymbol(GameSymbol.CROSS)
                .setHorizontalPosition(2)
                .setVerticalPosition(2)
                .setMoveNumber(5));
        moves.add(new Move()
                .setGameSymbol(GameSymbol.NAUGHT)
                .setHorizontalPosition(2)
                .setVerticalPosition(3)
                .setMoveNumber(6));

        return moves;
    }

    public static List<Move> createEightMovesForWin() {
        List<Move> moves = new ArrayList<>();
        moves.add(new Move()
                .setGameSymbol(GameSymbol.CROSS)
                .setHorizontalPosition(1)
                .setVerticalPosition(3)
                .setMoveNumber(1));
        moves.add(new Move()
                .setGameSymbol(GameSymbol.NAUGHT)
                .setHorizontalPosition(1)
                .setVerticalPosition(1)
                .setMoveNumber(2));
        moves.add(new Move()
                .setGameSymbol(GameSymbol.CROSS)
                .setHorizontalPosition(2)
                .setVerticalPosition(1)
                .setMoveNumber(3));
        moves.add(new Move()
                .setGameSymbol(GameSymbol.NAUGHT)
                .setHorizontalPosition(1)
                .setVerticalPosition(2)
                .setMoveNumber(4));
        moves.add(new Move()
                .setGameSymbol(GameSymbol.CROSS)
                .setHorizontalPosition(2)
                .setVerticalPosition(2)
                .setMoveNumber(5));
        moves.add(new Move()
                .setGameSymbol(GameSymbol.NAUGHT)
                .setHorizontalPosition(2)
                .setVerticalPosition(3)
                .setMoveNumber(6));
        moves.add(new Move()
                .setGameSymbol(GameSymbol.CROSS)
                .setHorizontalPosition(3)
                .setVerticalPosition(2)
                .setMoveNumber(7));
        moves.add(new Move()
                .setGameSymbol(GameSymbol.NAUGHT)
                .setHorizontalPosition(3)
                .setVerticalPosition(3)
                .setMoveNumber(8));

        return moves;
    }
}
