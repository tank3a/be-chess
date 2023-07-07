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

    private List<Rank> boardList;
    private final int BOARD_SIZE = 64;

    public Board() {}

    public void initialize() {
        initializeEmpty();
        boardList.get(0).setInitialBlackOther();
        boardList.get(1).setInitialBlackPawn();
        boardList.get(6).setInitialWhitePawn();
        boardList.get(7).setInitialWhiteOther();
    }

    public void initializeEmpty() {
        boardList = new ArrayList<>();
        for (int index = 0; index < 8; index++) {
            boardList.add(new Rank());
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
        boardList.stream().forEach(rank -> board.append(appendNewLine(rank.toString())));

        return board.toString();
    }

    public int getPieceCount(Piece pieceToCount) {
        int count = 0;
        for (Rank rank : boardList) {
            count += rank.countPiece(pieceToCount);
        }
        return count;
    }

    public Piece findPiece(String position) {
        return boardList.get(convertYIndex(position)).getPiece(convertXIndex(position));
    }

    public void move(String position, Piece pieceToMove) {
        boardList.get(convertYIndex(position)).setPiece(convertXIndex(position), pieceToMove);
    }

    public double calculatePoint(Color color) {
        double point = 0;
        if(color.equals(Color.BLACK)) {

            point += Piece.createBlackPawn().getType().getPoint() * checkPawnInLine(color);
            point += Piece.createBlackRook().getType().getPoint() * getPieceCount(Piece.createBlackRook());
            point += Piece.createBlackBishop().getType().getPoint() * getPieceCount(Piece.createBlackBishop());
            point += Piece.createBlackKnight().getType().getPoint() * getPieceCount(Piece.createBlackKnight());
            point += Piece.createBlackQueen().getType().getPoint() * getPieceCount(Piece.createBlackQueen());

            return point;
        }

        if(color.equals(Color.WHITE)) {
            point += Piece.createWhitePawn().getType().getPoint() * checkPawnInLine(color);
            point += Piece.createWhiteRook().getType().getPoint() * getPieceCount(Piece.createWhiteRook());
            point += Piece.createWhiteBishop().getType().getPoint() * getPieceCount(Piece.createWhiteBishop());
            point += Piece.createWhiteKnight().getType().getPoint() * getPieceCount(Piece.createWhiteKnight());
            point += Piece.createWhiteQueen().getType().getPoint() * getPieceCount(Piece.createWhiteQueen());

            return point;
        }

        return -1;
    }

    private double checkPawnInLine(Color color) {
        boolean hasMore = false;
        int count = 0;
        double totalScore = 0;
        Piece piece = Piece.createBlackPawn();
        if (color.equals(Color.WHITE)) {
            piece = Piece.createWhitePawn();
        }

        for(int column = 0; column < 8; column++) {
            hasMore = false;
            count = 0;
            for(int row = 0; row < 8; row++) {
                if(boardList.get(row).rank.get(column).equals(piece)) {
                    count++;
                    hasMore = true;
                }
            }
            if(hasMore && count > 1) {
                totalScore += count / 2.0;
                continue;
            }
            totalScore += count;
        }
        return totalScore;
    }

    private List<Piece> getAllPieceByColor(Color color) {
        List<Piece> pieceList = new ArrayList<>();
        for (int index = 0; index < 8; index++) {
            Iterator iterator = boardList.get(index).rank.listIterator();

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
