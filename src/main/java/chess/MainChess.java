package chess;

import java.util.Scanner;

public class MainChess {

    public static void run() {
        Scanner scanner = new Scanner(System.in);
        String command;

        Board board = new Board();

        while (true) {
            System.out.println("명령을 입력해주세요: start/end");
            command = scanner.nextLine().trim();
            String[] input = command.split(" ");
            switch (input[0]) {
                case "start":
                    board.initialize();
                    System.out.println(board.showBoard());
                case "end":
                    break;
                case "move":
                    board.move(input[1], input[2]);
                    System.out.println(board.showBoard());
                    break;
                default:
                    System.out.println("입력이 잘못되었습니다. 다시 입력해주세요");
            }

            if(command.equals("end"))
                break;
        }
    }
}
