package chess.model.pieces;

import chess.controller.Direction;

import java.util.List;

public class BlankPiece extends Piece {

    BlankPiece() {
        super(PieceColor.NO_COLOR, PieceType.NO_PIECE);
    }

    @Override
    protected List<Direction> getPieceMovableDirection() {
        return null;
    }
}
