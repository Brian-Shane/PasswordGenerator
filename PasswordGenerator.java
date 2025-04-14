import java.awt.*;
import java.security.SecureRandom;
import javax.swing.*;

public class PasswordGenerator {
    private static final String LETTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String HEXDIGITS = "0123456789abcdefABCDEF";
    private static final String PUNCTUATION = "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";
    private static final String CHAR_POOL = LETTERS + HEXDIGITS + PUNCTUATION;
    private static SecureRandom random = new SecureRandom();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Password Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding

        JLabel titleLabel = new JLabel("Choose an option:");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        frame.add(titleLabel, gbc);

        JButton randomBtn = new JButton("1. Random Password Generator");
        JButton usernameBtn = new JButton("2. Username Password Generator");

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        frame.add(randomBtn, gbc);

        gbc.gridx = 1;
        frame.add(usernameBtn, gbc);

        JLabel usernameLabel = new JLabel("Username (for option 2):");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        frame.add(usernameLabel, gbc);

        JTextField usernameField = new JTextField(20);
        gbc.gridx = 1;
        frame.add(usernameField, gbc);

        JLabel lengthLabel = new JLabel("Password Length:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        frame.add(lengthLabel, gbc);

        JTextField lengthField = new JTextField(20);
        gbc.gridx = 1;
        frame.add(lengthField, gbc);

        JTextArea output = new JTextArea(3, 30);
        output.setEditable(false);
        output.setLineWrap(true);
        output.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(output);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        frame.add(scrollPane, gbc);

        JButton exitBtn = new JButton("3. Exit");
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        frame.add(exitBtn, gbc);

      
        randomBtn.addActionListener(e -> {
            try {
                int length = Integer.parseInt(lengthField.getText().trim());
                output.setText("Random Password: " + generateRandomPassword(length));
            } catch (NumberFormatException ex) {
                output.setText("Please enter a valid number for length.");
            }
        });

        usernameBtn.addActionListener(e -> {
            try {
                String name = usernameField.getText().trim();
                int length = Integer.parseInt(lengthField.getText().trim());
                if (name.isEmpty()){
                    output.setText("Username is required.");
                    return;
                }
                output.setText("Username Password: " + generateUsernamePassword(name, length));
            } catch (NumberFormatException ex) {
                output.setText("Please enter a valid number for length.");
            }
        });

        exitBtn.addActionListener(e -> System.exit(0));

        frame.setVisible(true);
    }

    public static String generateRandomPassword(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(CHAR_POOL.charAt(random.nextInt(CHAR_POOL.length())));
        }
        return sb.toString();
    }

    public static String generateUsernamePassword(String name, int length) {
        StringBuilder part1 = new StringBuilder();
        StringBuilder part2 = new StringBuilder();
        for (int i = 0; i < length; i++) {
            part1.append(CHAR_POOL.charAt(random.nextInt(CHAR_POOL.length())));
            part2.append(CHAR_POOL.charAt(random.nextInt(CHAR_POOL.length())));
        }
        return part1.toString() + name + part2.toString();
    }
}
