package chess;

import chess.controller.ChessGame;
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
            switch (input[0]) {
                case "start":
                    chessGame.initializeBoard();
                    System.out.println(chessGame.showBoard());
                case "end":
                    System.exit(0);
                case "move":
                    try {
                        chessGame.move(new Position(input[1]), new Position(input[2]));
                        System.out.println(chessGame.showBoard());
                        break;
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                default:
                    System.out.println("입력이 잘못되었습니다. 다시 입력해주세요");
            }

            System.out.println("명령을 입력해주세요: start/move/end");

        }
    }
}
