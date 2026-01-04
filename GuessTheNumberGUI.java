import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GuessTheNumberGUI extends JFrame implements ActionListener {

    private JTextField guessField;
    private JLabel messageLabel, scoreLabel, attemptsLabel;
    private JButton guessButton, newRoundButton;

    private int randomNumber;
    private int attemptsLeft;
    private int score;
    private final int MAX_ATTEMPTS = 5;

    public GuessTheNumberGUI() {
        setTitle("Guess The Number Game");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new GridLayout(6, 1));

        messageLabel = new JLabel("Guess a number between 1 and 100", JLabel.CENTER);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 14));

        guessField = new JTextField();
        guessField.setHorizontalAlignment(JTextField.CENTER);

        guessButton = new JButton("Guess");
        newRoundButton = new JButton("New Round");

        attemptsLabel = new JLabel("", JLabel.CENTER);
        scoreLabel = new JLabel("Score: 0", JLabel.CENTER);

        add(messageLabel);
        add(guessField);
        add(guessButton);
        add(attemptsLabel);
        add(scoreLabel);
        add(newRoundButton);

        guessButton.addActionListener(this);
        newRoundButton.addActionListener(this);

        startNewRound();
        setVisible(true);
    }

    private void startNewRound() {
        Random rand = new Random();
        randomNumber = rand.nextInt(100) + 1;
        attemptsLeft = MAX_ATTEMPTS;
        attemptsLabel.setText("Attempts Left: " + attemptsLeft);
        messageLabel.setText("Guess a number between 1 and 100");
        guessField.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == guessButton) {
            try {
                int guess = Integer.parseInt(guessField.getText());

                attemptsLeft--;

                if (guess == randomNumber) {
                    int points = attemptsLeft * 10;
                    score += points;
                    scoreLabel.setText("Score: " + score);
                    messageLabel.setText("🎉 Correct! You earned " + points + " points.");
                    guessButton.setEnabled(false);
                } 
                else if (guess < randomNumber) {
                    messageLabel.setText("📉 Too Low!");
                } 
                else {
                    messageLabel.setText("📈 Too High!");
                }

                attemptsLabel.setText("Attempts Left: " + attemptsLeft);

                if (attemptsLeft == 0 && guess != randomNumber) {
                    messageLabel.setText("❌ Game Over! Number was " + randomNumber);
                    guessButton.setEnabled(false);
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number!");
            }
        }

        if (e.getSource() == newRoundButton) {
            guessButton.setEnabled(true);
            startNewRound();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GuessTheNumberGUI());
    }
}
