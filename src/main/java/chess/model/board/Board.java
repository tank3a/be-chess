package chess.model.board;

import chess.controller.Direction;
import chess.model.Position;
import chess.model.pieces.Piece;
import chess.model.pieces.PieceColor;
import chess.model.pieces.PieceCreator;
import chess.model.pieces.PieceType;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private List<Rank> rankList;
    public static final int BOARD_SIZE = 64;
    public static final int FILE_SIZE = 8;
    public static final int RANK_SIZE = 8;
    private final int BLACK_OTHER_LINE = 8;
    private final int BLACK_PAWN_LINE = 7;
    private final int WHITE_OTHER_LINE = 1;
    private final int WHITE_PAWN_LINE = 2;
    private final int MAX_PAWN_IN_LINE_FULL_SCORE = 1;

    public Board() {
        rankList = new ArrayList<>();
    }

    public void initialize() {
        rankList.clear();
        rankList.add(new Rank(BLACK_OTHER_LINE));
        rankList.add(new Rank(BLACK_PAWN_LINE));

        int indexFrom = WHITE_PAWN_LINE + 1;
        int indexTo = BLACK_PAWN_LINE;
        while (indexFrom < indexTo) {
            rankList.add(new Rank());
            indexFrom++;
        }

        rankList.add(new Rank(WHITE_PAWN_LINE));
        rankList.add(new Rank(WHITE_OTHER_LINE));

    }

    public void initializeEmpty() {
        rankList.clear();
        int index = 0;
        while (index < RANK_SIZE) {
            rankList.add(new Rank());
            index++;
        }
    }

    public List<Rank> getRankList() {
        return rankList;
    }

    public Rank getRank(int index) {
        return rankList.get(index);
    }

    public int countAll() {
        return BOARD_SIZE - getPieceCount(PieceCreator.createBlank());
    }

    public int getPieceCount(Piece pieceToCount) {
        int count = 0;
        for (Rank rank : rankList) {
            count += rank.countPiece(pieceToCount);
        }
        return count;
    }

    public Piece findPiece(String input) {
        Position position = new Position(input);
        return findPiece(position);
    }

    public Piece findPiece(Position position) {
        return rankList.get(position.getRank()).getPiece(position.getFile());
    }

    public void setPiece(Position position, Piece pieceToMove) {
        rankList.get(position.getRank()).setPiece(position.getFile(), pieceToMove);
    }

    public double calculatePoint(PieceColor color) {
        double point = 0;
        int countPawn;
        Piece piece;

        for(int column = 0; column < FILE_SIZE; column++) {
            countPawn = 0;
            for (int row = 0; row < RANK_SIZE; row++) {
                piece = rankList.get(row).getPiece(column);

                if (piece.compareColor(color)) {
                    point += piece.getPoint();
                    if (piece.isPawn()) {
                        countPawn++;
                    }
                }
            }
            if (countPawn > MAX_PAWN_IN_LINE_FULL_SCORE) {
                point -= (countPawn / 2.0);
            }
        }

        return point;
    }

    public List<Piece> getAllPieceByColor(PieceColor color) {
        List<Piece> pieceList = new ArrayList<>();

        rankList.stream().forEach(rank -> pieceList.addAll(rank.getAllPieceByColor(color)));

        return pieceList;
    }

    public boolean existPieceBetween(Position before, Position after, Direction direction) {
        Position positionToMove = before.getPositionAfterDirection(direction);

        if(positionToMove.equals(after)) {
            return false;
        }

        if(!findPiece(positionToMove).compareType(PieceType.NO_PIECE)) {
            return true;
        }

        return existPieceBetween(positionToMove, after, direction);
    }
}
