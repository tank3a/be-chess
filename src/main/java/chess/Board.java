package chess;

import chess.pieces.Piece;
import chess.pieces.Piece.Color;
import chess.pieces.Rank;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import static chess.Position.convertXIndex;
import static chess.Position.convertYIndex;
import static chess.StringUtils.appendNewLine;

public class Board {

    private List<Rank> rankList;
    private final int BOARD_SIZE = 64;
    private final int FILE_SIZE = 8;
    private final int RANK_SIZE = 8;
    private final int MAX_PAWN_IN_LINE_FULL_SCORE = 1;

    public Board() {}

    public void initialize() {
        initializeEmpty();
        rankList.get(0).setInitialBlackOther();
        rankList.get(1).setInitialBlackPawn();
        rankList.get(6).setInitialWhitePawn();
        rankList.get(7).setInitialWhiteOther();
    }

    public void initializeEmpty() {
        rankList = new ArrayList<>();
        for (int index = 0; index < 8; index++) {
            rankList.add(new Rank());
        }
    }

    public String showBoard() {
        return toString();
    }

    public int countAll() {
        return BOARD_SIZE - getPieceCount(Piece.createBlank());
    }

    @Override
    public String toString() {
        StringBuilder board = new StringBuilder();
        rankList.stream().forEach(rank -> board.append(appendNewLine(rank.toString())));

        return board.toString();
    }

    public int getPieceCount(Piece pieceToCount) {
        int count = 0;
        for (Rank rank : rankList) {
            count += rank.countPiece(pieceToCount);
        }
        return count;
    }

    public Piece findPiece(String position) {
        return rankList.get(convertYIndex(position)).getPiece(convertXIndex(position));
    }

    public void move(String position, Piece pieceToMove) {
        rankList.get(convertYIndex(position)).setPiece(convertXIndex(position), pieceToMove);
    }

    public double calculatePoint(Color color) {
        double point = 0;
        if(color.equals(Color.BLACK)) {

            point += Piece.Type.PAWN.getPoint() * checkPawnInLine(color);
            point += Piece.Type.ROOK.getPoint() * getPieceCount(Piece.createBlackRook());
            point += Piece.Type.BISHOP.getPoint() * getPieceCount(Piece.createBlackBishop());
            point += Piece.Type.KNIGHT.getPoint() * getPieceCount(Piece.createBlackKnight());
            point += Piece.Type.QUEEN.getPoint() * getPieceCount(Piece.createBlackQueen());

            return point;
        }

        if(color.equals(Color.WHITE)) {
            point += Piece.Type.PAWN.getPoint() * checkPawnInLine(color);
            point += Piece.Type.ROOK.getPoint() * getPieceCount(Piece.createWhiteRook());
            point += Piece.Type.BISHOP.getPoint() * getPieceCount(Piece.createWhiteBishop());
            point += Piece.Type.KNIGHT.getPoint() * getPieceCount(Piece.createWhiteKnight());
            point += Piece.Type.QUEEN.getPoint() * getPieceCount(Piece.createWhiteQueen());

            return point;
        }

        return -1;
    }

    private double checkPawnInLine(Color color) {
        int count = 0;
        double totalScore = 0;
        Piece piece = Piece.createBlackPawn();
        if (color.equals(Color.WHITE)) {
            piece = Piece.createWhitePawn();
        }

        for(int column = 0; column < FILE_SIZE; column++) {
            count = 0;
            for(int row = 0; row < RANK_SIZE; row++) {
                if(rankList.get(row).rank.get(column).equals(piece)) {
                    count++;
                }
            }
            if(count > MAX_PAWN_IN_LINE_FULL_SCORE) {
                totalScore += count / 2.0;
                continue;
            }
            totalScore += count;
        }
        return totalScore * Piece.Type.PAWN.getPoint();
    }

    private List<Piece> getAllPieceByColor(Color color) {
        List<Piece> pieceList = new ArrayList<>();
        for (int index = 0; index < RANK_SIZE; index++) {
            Iterator iterator = rankList.get(index).rank.listIterator();

            while (iterator.hasNext()) {
                Piece piece = (Piece) iterator.next();
                if(color.equals(Color.BLACK) && piece.isBlack()) {
                    pieceList.add(piece);
                }
                if(color.equals(Color.WHITE) && piece.isWhite()) {
                    pieceList.add(piece);
                }
            }
        }

        return pieceList;
    }

    private StringBuilder printSort(List<Piece> pieceList) {
        StringBuilder stringBuilder = new StringBuilder();

        pieceList.stream().sorted(Comparator.comparing(piece -> piece.getType().getPoint()))
                .forEach(piece -> stringBuilder.append(piece.getTypeInCharacter()));

        return stringBuilder;
    }

    public String sortPieceAndPrint(Color color, boolean desc) {

        List<Piece> pieceList = getAllPieceByColor(color);

        StringBuilder stringBuilder = printSort(pieceList);
        if(desc) {
            stringBuilder = stringBuilder.reverse();
        }

        return stringBuilder.toString();
    }
}
