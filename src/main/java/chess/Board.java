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
        for (int index = 0; index < RANK_SIZE; index++) {
            rankList.add(new Rank());
        }
    }

    public String showBoard() {
        StringBuilder board = new StringBuilder();
        rankList.stream().forEach(rank -> board.append(appendNewLine(rank.toString())));

        return board.toString();    }

    public int countAll() {
        return BOARD_SIZE - getPieceCount(Piece.createBlank());
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

    public void move(String before, String after) {
        Piece piece = findPiece(before);
        rankList.get(convertYIndex(after)).setPiece(convertXIndex(after), piece);
        rankList.get(convertYIndex(before)).setPiece(convertXIndex(before), Piece.createBlank());
    }

    public double calculatePoint(Color color) {
        double point = 0;
        int countPawn;
        Piece piece;

        for(int column = 0; column < FILE_SIZE; column++) {
            countPawn = 0;
            for (int row = 0; row < RANK_SIZE; row++) {
                piece = rankList.get(row).getPiece(column);

                if (piece.getColor() == color) {
                    point += piece.getScore();
                    if (piece.getType() == Piece.Type.PAWN) {
                        countPawn++;
                    }
                }
            }
            if (countPawn > 1) {
                point -= (countPawn / 2.0);
            }
        }

        return point;
    }

    private List<Piece> getAllPieceByColor(Color color) {
        List<Piece> pieceList = new ArrayList<>();
        for (int index = 0; index < RANK_SIZE; index++) {
            Iterator iterator = rankList.get(index).getRank().listIterator();

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
