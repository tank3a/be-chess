package chess.controller;

import chess.exception.ExceptionMessage;
import chess.exception.InvalidMoveException;
import chess.exception.InvalidPositionException;
import chess.model.Position;
import chess.model.pieces.*;
import chess.model.board.Board;
import chess.view.BoardView;

public class ChessGame {

    private final Board board;
    private final BoardView boardView;
    private boolean isWhiteTurn;

    public ChessGame() {
        board = new Board();
        boardView = new BoardView();
        isWhiteTurn = true;
    }

    public void initializeBoard() {
        board.initialize();
    }

    public void initializeEmptyBoard() {
        board.initializeEmpty();
    }

    public void showBoard() {
        boardView.showBoard(board.getRankList());
    }

    public boolean isInitialized() {
        return board.isInitialized();
    }

    public void move(Position before, Position after) {
        validateMove(before, after);

        isWhiteTurn = !isWhiteTurn;

        Piece piece = board.findPiece(before);

        board.getRank(after.getRank()).setPiece(after.getFile(), piece);
        board.getRank(before.getRank()).setPiece(before.getFile(), PieceCreator.createBlank());
    }

    private void validateMove(Position before, Position after) {
        Piece piece = board.findPiece(before);

        isTurn(piece);

        isBlank(piece);

        piece.verifyMovePosition(before, after);

        Direction direction = Direction.findDirection(before, after);
        validatePawnMove(before, piece, direction);
        board.existPieceBetween(before, after, direction);

        if (piece.compareColor(board.findPiece(after))) {
            throw new InvalidMoveException(ExceptionMessage.SAME_PIECE);
        }
    }

    private void validatePawnMove(Position before, Piece pieceBefore, Direction direction) {
        if (!pieceBefore.compareType(PieceType.PAWN)) {
            return;
        }

        Piece pieceAfter = board.findPiece(before.getPositionAfterDirection(direction));
        if ((Direction.linearDirection().contains(direction) && pieceAfter.compareType(PieceType.NO_PIECE))) {
            return;
        }

        if (Direction.diagonalDirection().contains(direction) && pieceBefore.isOppositeColor(pieceAfter)) {
            return;
        }
        throw new InvalidPositionException(ExceptionMessage.INVALID_PAWN_MOVE);
    }

    private void isBlank(Piece piece) {
        if (piece.compareType(PieceType.NO_PIECE)) {
            throw new InvalidPositionException(ExceptionMessage.PIECE_NOT_EXIST);
        }
    }

    private void isTurn(Piece piece) {
        if (piece.isWhite() != isWhiteTurn) {
            throw new InvalidMoveException(ExceptionMessage.NOT_YOUR_PIECE);
        }
    }

    public Piece getPieceInPosition(Position position) {
        return board.findPiece(position);
    }

    public void printScore() {
        boardView.printScore(calculatePoint(PieceColor.BLACK), calculatePoint(PieceColor.WHITE));
    }

    public double calculatePoint(PieceColor pieceColor) {
        return board.calculatePoint(pieceColor);
    }

    public void placePiece(String input, Piece piece) {
        board.setPiece(new Position(input), piece);
    }

    public void printErrorInput(Exception exception) {
        boardView.printErrorInput(exception.getMessage());
    }
}
