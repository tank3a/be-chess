package chess;

import chess.pieces.Piece;
import chess.pieces.Piece.Color;
import chess.pieces.Rank;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static chess.StringUtils.appendNewLine;

public class Board {

    private List<Rank> rankList;
    private final int BOARD_SIZE = 64;
    private final int FILE_SIZE = 8;
    private final int RANK_SIZE = 8;
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

    public String showBoard() {
        StringBuilder board = new StringBuilder();
        rankList.stream().forEach(rank -> board.append(appendNewLine(rank.getLinePrint())));

        return board.toString();
    }

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

    public Piece findPiece(String input) {
        Position position = new Position(input);
        return findPiece(position);
    }

    public Piece findPiece(Position position) {
        return rankList.get(position.getRank()).getPiece(position.getFile());
    }

    public void move(Position position, Piece pieceToMove) {
        rankList.get(position.getRank()).setPiece(position.getFile(), pieceToMove);
    }

    public void move(Position before, Position after) {
        Piece piece = findPiece(before);
        rankList.get(after.getRank()).setPiece(after.getFile(), piece);
        rankList.get(before.getRank()).setPiece(before.getFile(), Piece.createBlank());
    }

    public double calculatePoint(Color color) {
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

    private List<Piece> getAllPieceByColor(Color color) {
        List<Piece> pieceList = new ArrayList<>();

        rankList.stream().forEach(rank -> pieceList.addAll(rank.getAllPieceByColor(color)));

        return pieceList;
    }

    private StringBuilder printSort(List<Piece> pieceList) {
        StringBuilder stringBuilder = new StringBuilder();

        pieceList.stream().sorted(Comparator.comparing(piece -> piece.getPoint()))
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
