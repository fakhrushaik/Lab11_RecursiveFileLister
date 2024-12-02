import javax.swing.*;
import java.awt.*;
import java.io.File;

public class RecursiveListerGUI extends JFrame {
    private JButton startButton, quitButton;
    private JTextArea textArea;
    private JScrollPane scrollPane;
    private JLabel titleLabel;

    public RecursiveListerGUI() {
        setTitle("Recursive File Lister");
        setSize(420, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Title
        titleLabel = new JLabel("Recursive File Lister", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(titleLabel, BorderLayout.NORTH);

        // Text Area with ScrollPane
        textArea = new JTextArea();
        textArea.setEditable(false);
        scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        startButton = new JButton("Start");
        quitButton = new JButton("Quit");
        buttonPanel.add(startButton);
        buttonPanel.add(quitButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Button Listeners
        startButton.addActionListener(e -> openDirectoryChooser());
        quitButton.addActionListener(e -> System.exit(0));
    }

    private void openDirectoryChooser() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedDirectory = fileChooser.getSelectedFile();
            textArea.setText(""); // Clear the text area
            textArea.append("Selected Directory: " + selectedDirectory.getAbsolutePath() + "\n");
            textArea.append("---------------------------- Files in the Directory ---------------------------------------\n");
            listFilesRecursively(selectedDirectory, "");
        }
    }

    private void listFilesRecursively(File directory, String indent) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                textArea.append(indent + file.getName() + "\n");
                if (file.isDirectory()) {
                    listFilesRecursively(file, indent + "    ");
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RecursiveListerGUI app = new RecursiveListerGUI();
            app.setVisible(true);
        });
    }
}
