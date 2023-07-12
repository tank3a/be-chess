package chess.model.board;

import chess.model.pieces.Piece;
import chess.model.pieces.PieceColor;
import chess.model.pieces.PieceCreator;

import java.util.ArrayList;
import java.util.List;

public class Rank {
    private List<Piece> rank;

    public Rank() {
        rank = new ArrayList<>();
        for (int repeat = 0; repeat < 8; repeat++) {
            rank.add(PieceCreator.createBlank());
        }
    }

    public Rank(int line) {
        if (line > 8) {
            throw new RuntimeException("체스판 크기를 초과하였습니다.");
        }

        rank = new ArrayList<>();

        switch (line) {
            case 8:
                setInitialBlackOther();
                break;
            case 7:
                setInitialBlackPawn();
                break;
            case 2:
                setInitialWhitePawn();
                break;
            case 1:
                setInitialWhiteOther();
                break;
            default:
                new Rank();
        }
    }

    private void setInitialBlackPawn() {
        for (int index = 0; index < Board.FILE_SIZE; index++) {
            rank.add(PieceCreator.createBlackPawn());
        }
    }

    private void setInitialWhitePawn() {
        for (int index = 0; index < Board.FILE_SIZE; index++) {
            rank.add(PieceCreator.createWhitePawn());
        }
    }

    private void setInitialBlackOther() {
        rank.add(PieceCreator.createBlackRook());
        rank.add(PieceCreator.createBlackKnight());
        rank.add(PieceCreator.createBlackBishop());
        rank.add(PieceCreator.createBlackQueen());
        rank.add(PieceCreator.createBlackKing());
        rank.add(PieceCreator.createBlackBishop());
        rank.add(PieceCreator.createBlackKnight());
        rank.add(PieceCreator.createBlackRook());
    }

    private void setInitialWhiteOther() {
        rank.add(PieceCreator.createWhiteRook());
        rank.add(PieceCreator.createWhiteKnight());
        rank.add(PieceCreator.createWhiteBishop());
        rank.add(PieceCreator.createWhiteQueen());
        rank.add(PieceCreator.createWhiteKing());
        rank.add(PieceCreator.createWhiteBishop());
        rank.add(PieceCreator.createWhiteKnight());
        rank.add(PieceCreator.createWhiteRook());
    }


    public List<Piece> getAllPieceByColor(PieceColor color) {
        List<Piece> pieceList = new ArrayList<>();
        rank.stream().forEach(piece -> {
            if (piece.compareColor(color)) {
                pieceList.add(piece);
            }
        });
        return pieceList;
    }

    public String getLinePrint() {
        StringBuilder stringBuilder = new StringBuilder();
        rank.stream().forEach(piece -> stringBuilder.append(piece.getTypeInCharacter()));

        return stringBuilder.toString();
    }

    public int countPiece(Piece pieceToCount) {
        return (int) rank.stream().filter(piece -> piece.equals(pieceToCount)).count();
    }

    public Piece getPiece(int index) {
        return rank.get(index);
    }

    public void setPiece(int index, Piece pieceToSet) {
        rank.set(index, pieceToSet);
    }
}
