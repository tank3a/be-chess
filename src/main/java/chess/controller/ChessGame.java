package chess.controller;

import chess.exception.ExceptionMessageHandler;
import chess.exception.InvalidMoveException;
import chess.exception.InvalidPositionException;
import chess.model.Position;
import chess.model.pieces.*;
import chess.model.board.Board;
import chess.view.BoardView;

import java.util.List;

public class ChessGame {

    private final Board board;
    private final BoardView boardView;
    private boolean isBlackTurn;

    public ChessGame() {
        board = new Board();
        boardView = new BoardView();
        isBlackTurn = true;
    }

    public void initializeBoard() {
        board.initialize();
    }

    public void initializeEmptyBoard() {
        board.initializeEmpty();
    }

    public String showBoard() {
        return boardView.showBoard(board.getRankList());
    }

    public void move(Position before, Position after) {
        validateMove(before, after);

        isBlackTurn = !isBlackTurn;

        Piece piece = board.findPiece(before);

        board.getRank(after.getRank()).setPiece(after.getFile(), piece);
        board.getRank(before.getRank()).setPiece(before.getFile(), PieceCreator.createBlank());
    }

    private void validateMove(Position before, Position after) {
        Piece piece = board.findPiece(before);

        if (piece.compareType(PieceType.NO_PIECE)) {
            throw new InvalidPositionException(ExceptionMessageHandler.PIECE_NOT_EXIST);
        }

        piece.verifyMovePosition(before, after);

        Direction direction = Direction.findDirection(before, after);
        if (piece.compareType(PieceType.PAWN)) {
            validPawnMove(piece, before, direction);
        }
        board.existPieceBetween(before, after, direction);

        if (piece.compareColor(board.findPiece(after))) {
            throw new InvalidMoveException(ExceptionMessageHandler.SAME_PIECE);
        }

        if(piece.isBlack() != isBlackTurn) {
            throw new InvalidMoveException(ExceptionMessageHandler.NOT_YOUR_PIECE);
        }
    }

    private void validPawnMove(Piece pawn, Position position, Direction direction) {
        Piece piece = board.findPiece(position.getPositionAfterDirection(direction));
        if ((direction.equals(Direction.NORTH) || direction.equals(Direction.SOUTH)
        && piece.compareType(PieceType.NO_PIECE))) {
            return;
        }

        if (Direction.diagonalDirection().contains(direction)) {
            if (pawn.isWhite() && piece.isBlack()) {
                return;
            }

            if (piece.isWhite() && pawn.isBlack()) {
                return;
            }
        }
        throw new InvalidPositionException(ExceptionMessageHandler.INVALID_PAWN_MOVE);
    }

    public Piece getPieceInPosition(Position position) {
        return board.findPiece(position);
    }

    public double calculatePoint(PieceColor pieceColor) {
        return board.calculatePoint(pieceColor);
    }

    public void placePiece(String input, Piece piece) {
        board.setPiece(new Position(input), piece);
    }

    public String sortPieceByColorAndPrint(PieceColor color, boolean desc) {
        List<Piece> pieceList = board.getAllPieceByColor(color);

        if (desc) {
            return boardView.printPieceDesc(pieceList);
        }

        return boardView.printPieceAsc(pieceList);
    }
}
