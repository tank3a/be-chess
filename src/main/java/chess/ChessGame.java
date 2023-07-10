package chess;

import chess.pieces.Piece;
import chess.pieces.PieceColor;
import chess.pieces.PieceCreator;

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
        board.getRank(after.getRank()).setPiece(after.getFile(), piece);
        board.getRank(before.getRank()).setPiece(before.getFile(), PieceCreator.createBlank());
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

        if(desc) {
            return boardView.printPieceDesc(pieceList);
        }

        return boardView.printPieceAsc(pieceList);
    }
}
