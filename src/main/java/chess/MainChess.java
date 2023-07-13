package chess;

import chess.controller.ChessGame;
import chess.exception.ExceptionMessage;
import chess.exception.InvalidBoardException;
import chess.exception.InvalidInputException;
import chess.model.Position;

import java.util.Scanner;

public class MainChess {

    public static void run() {
        Scanner scanner = new Scanner(System.in);
        String command;

        ChessGame chessGame = new ChessGame();

        System.out.println("명령을 입력해주세요: start/end");

        while (true) {
            command = scanner.nextLine().trim();
            String[] input = command.split(" ");
            try {
                switch (input[0]) {
                    case "start":
                        chessGame.initializeBoard();
                        chessGame.showBoard();
                        break;
                    case "end":
                        break;
                    case "move":
                        if(!chessGame.isInitialized()) {
                            throw new InvalidBoardException(ExceptionMessage.BOARD_NOT_EXIST);
                        }
                        chessGame.move(new Position(input[1]), new Position(input[2]));
                        chessGame.showBoard();
                        break;
                    case "score":
                        if(!chessGame.isInitialized()) {
                            throw new InvalidBoardException(ExceptionMessage.BOARD_NOT_EXIST);
                        }
                        chessGame.printScore();
                        break;
                    default:
                        chessGame.printErrorInput(new InvalidInputException(ExceptionMessage.INVALID_INPUT));
                }
            } catch (Exception exception) {
                chessGame.printErrorInput(exception);
            }
            System.out.println("명령을 입력해주세요: start/move/score/end");

            if(input[0].equals("end")) {
                break;
            }
        }
    }
}
