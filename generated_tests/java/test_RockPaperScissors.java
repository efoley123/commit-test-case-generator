import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RockPaperScissorsTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private Random mockRandom;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        mockRandom = mock(Random.class);
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    // Mocking System.in to simulate user input
    private void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    @Test
    public void testGetUserChoiceValidInput() {
        provideInput("rock\n");
        assertEquals("rock", RockPaperScissors.getUserChoice());
    }

    @Test
    public void testGetUserChoiceInvalidThenValidInput() {
        provideInput("invalid\npaper\n");
        assertEquals("paper", RockPaperScissors.getUserChoice());
    }

    @Test
    public void testGetComputerChoice() {
        // Mocking Random class to control the output of getComputerChoice
        when(mockRandom.nextInt(anyInt())).thenReturn(0); // Mocking choice "rock"
        assertEquals("rock", RockPaperScissors.getComputerChoice());
        verify(mockRandom, times(1)).nextInt(3);
    }

    @Test
    public void testDetermineWinnerUserWins() {
        assertEquals("You win!", RockPaperScissors.determineWinner("rock", "scissors"));
    }

    @Test
    public void testDetermineWinnerComputerWins() {
        assertEquals("Computer wins!", RockPaperScissors.determineWinner("paper", "scissors"));
    }

    @Test
    public void testDetermineWinnerTie() {
        assertEquals("It's a tie!", RockPaperScissors.determineWinner("rock", "rock"));
    }

    @Test
    public void testDisplayScore() {
        RockPaperScissors.determineWinner("rock", "scissors"); // User wins
        RockPaperScissors.determineWinner("scissors", "rock"); // Computer wins
        RockPaperScissors.determineWinner("paper", "paper"); // Tie
        RockPaperScissors.displayScore();
        assertTrue(outContent.toString().contains("You: 1"));
        assertTrue(outContent.toString().contains("Computer: 1"));
        assertTrue(outContent.toString().contains("Ties: 1"));
    }

    // Ensuring that scores are correctly updated in multiple rounds
    @Test
    public void testScoresAfterMultipleRounds() {
        RockPaperScissors.determineWinner("rock", "scissors"); // User wins
        RockPaperScissors.determineWinner("rock", "scissors"); // User wins again
        RockPaperScissors.determineWinner("scissors", "rock"); // Computer wins
        assertEquals("You win!", RockPaperScissors.determineWinner("rock", "scissors")); // User wins third time
        assertEquals("It's a tie!", RockPaperScissors.determineWinner("paper", "paper")); // Tie

        RockPaperScissors.displayScore();
        assertTrue(outContent.toString().contains("You: 3"));
        assertTrue(outContent.toString().contains("Computer: 1"));
        assertTrue(outContent.toString().contains("Ties: 1"));
    }
}
```
Note: In the provided code snippet, I've included tests for normal, edge, and error cases as required. However, mocking the `Random` class directly in `getComputerChoice` would require changes to the original code structure to inject the mock, which I've simulated by demonstrating how you would set it up. Similarly, user input is simulated for `getUserChoice` tests, but keep in mind this approach is somewhat simplified and assumes the method is decoupled enough to be tested in isolation.