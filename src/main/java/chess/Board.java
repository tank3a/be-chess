package chess;

import chess.pieces.Piece;
import chess.pieces.Piece.Color;
import chess.pieces.Rank;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static chess.Position.convertXIndex;
import static chess.Position.convertYIndex;
import static chess.StringUtils.appendNewLine;

public class Board {

    private List<Rank> boardList;
    private final int BOARD_SIZE = 64;


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
        if(color.equals(Color.WHITE)) {
            for(int i = 0; i < 8; i++) {
                hasMore = false;
                count = 0;
                for(int j = 0; j < 8; j++) {
                    if(boardList.get(j).rank.get(i).equals(Piece.createWhitePawn())) {
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

        if(color.equals(Color.BLACK)) {
            for(int i = 0; i < 8; i++) {
                hasMore = false;
                count = 0;
                for(int j = 0; j < 8; j++) {
                    if(boardList.get(j).rank.get(i).equals(Piece.createBlackPawn())) {
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

        //예외처리 필요
        return -1;
    }

    public String sortPieceAscAndPrint(Color color) {
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

        StringBuilder stringBuilder = new StringBuilder();

        pieceList.stream().sorted(Comparator.comparing(piece -> piece.getType().getPoint()))
                .forEach(piece -> stringBuilder.append(piece.getTypeInCharacter()));

        return stringBuilder.toString();
    }

    public String sortPieceDescAndPrint(Color color) {
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

        StringBuilder stringBuilder = new StringBuilder();

        pieceList.stream().sorted(Comparator.comparing(Piece::getScore).reversed())
                .forEach(piece -> stringBuilder.append(piece.getTypeInCharacter()));

        return stringBuilder.toString();
    }

}
