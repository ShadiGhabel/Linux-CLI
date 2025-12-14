import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FileManager fileManager = new FileManager();
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.print("fs> ");
            String input = scanner.nextLine();
            if(input.equals("exit")) break;
            fileManager.executeCommand(input);
        }
        scanner.close();
    }
}

