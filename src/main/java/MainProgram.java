import java.util.Arrays;
import java.util.Scanner;

public class MainProgram {
    public static void main(String[] args) {
        System.out.println("Hello");
        System.out.println("Enter \"start\" to Start");
        while (true) {
            Scanner scanner = new Scanner(System. in);
            String inputStringStart = scanner.nextLine();
            if (inputStringStart.equals("start")) {
                StartProgram();
                break;
            }
            System.out.println("This word is not \"start\", try again.");
        }
        System.out.println("Enter \"stop\" to Escape");
        while (true) {
            Scanner scanner = new Scanner(System. in);
            String inputStringStop = scanner.nextLine();
            if (inputStringStop.equals("stop")) {
                System.exit(0);
            }
            System.out.println("This word is not \"stop\", try again.");
        }
    }

    public static void StartProgram() {
        WorkToFile workToFile = new WorkToFile();
        WorkToHtml workToHtml;
        WorkToWord workToWord;
        String[] urlData = workToFile.readFile();
        System.out.println(Arrays.toString(urlData));
        workToHtml = new WorkToHtml(urlData);
        workToHtml.saveHTMLPages();
        workToWord = new WorkToWord(urlData);
        workToWord.work();
    }
}
