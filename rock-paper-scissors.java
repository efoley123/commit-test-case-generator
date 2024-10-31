import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {
    
    // Function to get the user's choice
    public static String getUserChoice() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your choice (rock, paper, scissors): ");
        return scanner.nextLine().toLowerCase();
    }

    // Function to get the computer's choice
    public static String getComputerChoice() {
        String[] choices = {"rock", "paper", "scissors"};
        Random random = new Random();
        return choices[random.nextInt(choices.length)];
    }

    // Function to determine the winner
    public static String determineWinner(String userChoice, String computerChoice) {
        if (userChoice.equals(computerChoice)) {
            return "It's a tie!";
        } else if ((userChoice.equals("rock") && computerChoice.equals("scissors")) ||
                   (userChoice.equals("paper") && computerChoice.equals("rock")) ||
                   (userChoice.equals("scissors") && computerChoice.equals("paper"))) {
            return "You win!";
        } else {
            return "Computer wins!";
        }
    }

    public static void main(String[] args) {
        String userChoice = getUserChoice();
        String computerChoice = getComputerChoice();

        System.out.println("Your choice: " + userChoice);
        System.out.println("Computer's choice: " + computerChoice);
        
        String result = determineWinner(userChoice, computerChoice);
        System.out.println(result);
    }
}
