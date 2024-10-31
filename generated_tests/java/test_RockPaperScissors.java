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

class RockPaperScissorsUnitTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private Random mockRandom;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
        mockRandom = mock(Random.class);
        RockPaperScissors.setRandom(mockRandom); // Assume method to inject mock
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    private void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    @Test
    void testGetUserChoiceValidInput() {
        provideInput("rock\n");
        assertEquals("rock", RockPaperScissors.getUserChoice());
    }

    @Test
    void testGetUserChoiceInvalidThenValidInput() {
        provideInput("invalid\npaper\n");
        assertEquals("paper", RockPaperScissors.getUserChoice());
    }

    @Test
    void testGetComputerChoiceRock() {
        when(mockRandom.nextInt(anyInt())).thenReturn(0);
        assertEquals("rock", RockPaperScissors.getComputerChoice());
    }

    @Test
    void testGetComputerChoicePaper() {
        when(mockRandom.nextInt(anyInt())).thenReturn(1);
        assertEquals("paper", RockPaperScissors.getComputerChoice());
    }

    @Test
    void testGetComputerChoiceScissors() {
        when(mockRandom.nextInt(anyInt())).thenReturn(2);
        assertEquals("scissors", RockPaperScissors.getComputerChoice());
    }

    @Test
    void testDetermineWinnerUserWins() {
        assertEquals("You win!", RockPaperScissors.determineWinner("rock", "scissors"));
    }

    @Test
    void testDetermineWinnerComputerWins() {
        assertEquals("Computer wins!", RockPaperScissors.determineWinner("paper", "scissors"));
    }

    @Test
    void testDetermineWinnerTie() {
        assertEquals("It's a tie!", RockPaperScissors.determineWinner("rock", "rock"));
    }

    @Test
    void testDisplayScoreSingleRound() {
        RockPaperScissors.determineWinner("rock", "scissors");
        RockPaperScissors.displayScore();
        assertTrue(outContent.toString().contains("You: 1"));
        assertTrue(outContent.toString().contains("Computer: 0"));
        assertTrue(outContent.toString().contains("Ties: 0"));
    }

    @Test
    void testScoresAfterMultipleRounds() {
        RockPaperScissors.determineWinner("rock", "scissors");
        RockPaperScissors.determineWinner("rock", "scissors");
        RockPaperScissors.determineWinner("scissors", "rock");
        RockPaperScissors.determineWinner("rock", "scissors");
        RockPaperScissors.determineWinner("paper", "paper");
        RockPaperScissors.displayScore();
        assertTrue(outContent.toString().contains("You: 3"));
        assertTrue(outContent.toString().contains("Computer: 1"));
        assertTrue(outContent.toString().contains("Ties: 1"));
    }

    @Test
    void testInvalidInputFollowedByExit() {
        provideInput("invalid\nexit\n");
        assertThrows(Exception.class, RockPaperScissors::getUserChoice); // Assuming getUserChoice throws on "exit"
    }

    // Test to ensure the system handles unexpected high values from Random
    @Test
    void testGetComputerChoiceWithUnexpectedHighValue() {
        when(mockRandom.nextInt(anyInt())).thenReturn(999); // Unexpected high value
        assertThrows(Exception.class, RockPaperScissors::getComputerChoice); // Assuming getComputerChoice has a safeguard
    }

    // Test to ensure the system handles negative values from Random
    @Test
    void testGetComputerChoiceWithNegativeValue() {
        when(mockRandom.nextInt(anyInt())).thenReturn(-1); // Negative value
        assertThrows(Exception.class, RockPaperScissors::getComputerChoice); // Assuming getComputerChoice has a safeguard
    }

    // Ensure that providing no input (EOF) to getUserChoice properly throws or handles the situation
    @Test
    void testGetUserChoiceWithNoInput() {
        provideInput(""); // EOF
        assertThrows(Exception.class, RockPaperScissors::getUserChoice); // Assuming getUserChoice handles EOF appropriately
    }
}