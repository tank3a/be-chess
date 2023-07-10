package chess.pieces;

import java.util.ArrayList;
import java.util.List;

public class Rank {
    private List<Piece> rank;

    public Rank() {
        rank = new ArrayList<>();
        for(int repeat = 0; repeat < 8; repeat++) {
            rank.add(Piece.createBlank());
        }
    }

    public Rank(int line) {
        if(line > 8) {
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
        for(int index = 0; index < 8; index++) {
            rank.add(Piece.createBlackPawn());
        }
    }

    private void setInitialWhitePawn() {
        for(int index = 0; index < 8; index++) {
            rank.add(Piece.createWhitePawn());
        }
    }

    private void setInitialBlackOther() {
        rank.add(Piece.createBlackRook());
        rank.add(Piece.createBlackKnight());
        rank.add(Piece.createBlackBishop());
        rank.add(Piece.createBlackQueen());
        rank.add(Piece.createBlackKing());
        rank.add(Piece.createBlackBishop());
        rank.add(Piece.createBlackKnight());
        rank.add(Piece.createBlackRook());
    }

    private void setInitialWhiteOther() {
        rank.add(Piece.createWhiteRook());
        rank.add(Piece.createWhiteKnight());
        rank.add(Piece.createWhiteBishop());
        rank.add(Piece.createWhiteQueen());
        rank.add(Piece.createWhiteKing());
        rank.add(Piece.createWhiteBishop());
        rank.add(Piece.createWhiteKnight());
        rank.add(Piece.createWhiteRook());
    }


    public List<Piece> getAllPieceByColor(PieceColor color) {
        List<Piece> pieceList = new ArrayList<>();
        rank.stream().forEach(piece -> {
            if(piece.compareColor(color)) {
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
