package chess.controller;

import chess.model.Position;
import chess.model.pieces.*;
import chess.model.board.Board;
import chess.view.BoardView;

import java.util.List;

public class ChessGame {

    private final Board board;
    private final BoardView boardView;

    public ChessGame() {
        board = new Board();
        boardView = new BoardView();
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
        Piece piece = board.findPiece(before);

        if (piece.compareType(PieceType.NO_PIECE)) {
            throw new RuntimeException("기물이 존재하지 않습니다.");
        }

        if (!piece.verifyMovePosition(before, after)) {
            throw new RuntimeException("이동할 수 없는 위치입니다.");
        }

        Direction direction = Direction.findDirection(before, after);

        if (piece.compareType(PieceType.PAWN)) {
            if (!validPawnMove(piece, before, direction)) {
                throw new RuntimeException("폰이 이동할 수 없는 위치입니다.");
            }
        }

        if (board.existPieceBetween(before, after, direction)) {
            throw new RuntimeException("중간에 기물이 있어 이동할 수 없습니다.");
        }

        Piece pieceAtPosition = board.findPiece(after);
        if (piece.compareColor(pieceAtPosition)) {
            throw new RuntimeException("같은 색의 기물은 잡을 수 없습니다.");
        }

        board.getRank(after.getRank()).setPiece(after.getFile(), piece);
        board.getRank(before.getRank()).setPiece(before.getFile(), PieceCreator.createBlank());
    }

    private boolean validPawnMove(Piece pawn, Position position, Direction direction) {
        if (direction.equals(Direction.NORTH) || direction.equals(Direction.SOUTH)) {
            if (board.findPiece(position.getPositionAfterDirection(direction)).compareType(PieceType.NO_PIECE)) {
                return true;
            }
        }

        if (direction.equals(Direction.NORTHEAST) || direction.equals(Direction.NORTHWEST)) {
            if (pawn.isBlack() && board.findPiece(position.getPositionAfterDirection(direction)).compareColor(PieceColor.WHITE)) {
                return true;
            }
        }

        if (direction.equals(Direction.SOUTHEAST) || direction.equals(Direction.SOUTHWEST)) {
            if (pawn.isWhite() && board.findPiece(position.getPositionAfterDirection(direction)).compareColor(PieceColor.BLACK)) {
                return true;
            }
        }

        return false;
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
