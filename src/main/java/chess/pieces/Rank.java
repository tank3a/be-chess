package chess.pieces;

import java.util.ArrayList;
import java.util.List;

public class Rank {
    public List<Piece> rank;

    public Rank() {
        rank = new ArrayList<>();
        for(int repeat = 0; repeat < 8; repeat++) {
            rank.add(Piece.createBlank());
        }
    }

    public void setInitialBlackPawn() {
        rank.clear();
        for(int index = 0; index < 8; index++) {
            rank.add(Piece.createBlackPawn());
        }
    }

    public void setInitialWhitePawn() {
        rank.clear();
        for(int index = 0; index < 8; index++) {
            rank.add(Piece.createWhitePawn());
        }
    }

    public void setInitialBlackOther() {
        rank.clear();
        rank.add(Piece.createBlackRook());
        rank.add(Piece.createBlackKnight());
        rank.add(Piece.createBlackBishop());
        rank.add(Piece.createBlackQueen());
        rank.add(Piece.createBlackKing());
        rank.add(Piece.createBlackBishop());
        rank.add(Piece.createBlackKnight());
        rank.add(Piece.createBlackRook());
    }

    public void setInitialWhiteOther() {
        rank.clear();
        rank.add(Piece.createWhiteRook());
        rank.add(Piece.createWhiteKnight());
        rank.add(Piece.createWhiteBishop());
        rank.add(Piece.createWhiteQueen());
        rank.add(Piece.createWhiteKing());
        rank.add(Piece.createWhiteBishop());
        rank.add(Piece.createWhiteKnight());
        rank.add(Piece.createWhiteRook());
    }

    @Override
    public String toString() {
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
