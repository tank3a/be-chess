package chess;

import java.util.Scanner;

public class MainChess {

    public static void run() {
        Scanner scanner = new Scanner(System.in);
        String command;

        Board board = new Board();

        while (true) {
            System.out.println("명령을 입력해주세요: start/end");
            command = scanner.nextLine();
            switch (command) {
                case "start":
                    board.initialize();
                    board.print();
                case "end":
                    break;
                default:
                    System.out.println("입력이 잘못되었습니다. 다시 입력해주세요");
            }

            if(command.equals("end"))
                break;
        }
    }
}
