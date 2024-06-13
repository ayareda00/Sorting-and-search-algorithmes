import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LinearSearchGUI extends JPanel {
    private JTextField arrayField;
    private JTextField searchField;
    private JButton searchButton;
    private JTextArea resultArea;

    public LinearSearchGUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);

        JPanel arrayInputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcArray = new GridBagConstraints();
        gbcArray.gridx = 0;
        gbcArray.gridy = 0;
        gbcArray.anchor = GridBagConstraints.CENTER;
        gbcArray.insets = new Insets(5, 5, 5, 5);
        Font laelFont = new Font("Arial", Font.BOLD, 50);
        JLabel arrayLabel = new JLabel("Enter array (comma-separated):");
        arrayLabel.setFont(laelFont);
        arrayField = new JTextField(50);
        arrayInputPanel.add(arrayLabel, gbcArray);
        gbcArray.gridy++;
        arrayInputPanel.add(arrayField, gbcArray);

        add(arrayInputPanel, gbc);

        JPanel searchPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcSearch = new GridBagConstraints();
        gbcSearch.gridx = 0;
        gbcSearch.gridy = 0;
        gbcSearch.anchor = GridBagConstraints.CENTER;
        gbcSearch.insets = new Insets(5, 5, 5, 5);
        Font elFont = new Font("Arial", Font.BOLD, 50);
        JLabel searchLabel = new JLabel("Enter number to search (Linear):");
        searchLabel.setFont(elFont);
        searchField = new JTextField(20);
        searchPanel.add(searchLabel, gbcSearch);
        gbcSearch.gridy++;
        searchPanel.add(searchField, gbcSearch);
        gbcSearch.gridy++;
        searchButton = new JButton("Search");
        searchButton.setBackground(Color.GRAY);
        searchButton.setForeground(Color.WHITE);
        searchButton.setPreferredSize(new Dimension(150, 40));
        searchPanel.add(searchButton, gbcSearch);

        gbc.gridy++;
        add(searchPanel, gbc);

        resultArea = new JTextArea(30, 50);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        gbc.gridy++;
        add(scrollPane, gbc);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performLinearSearch();
            }
        });
    }

    private void performLinearSearch() {
        try {
            String[] arrayStrings = arrayField.getText().split(",");
            int[] array = new int[arrayStrings.length];
            for (int i = 0; i < arrayStrings.length; i++) {
                array[i] = Integer.parseInt(arrayStrings[i].trim());
            }

            int numberToSearch = Integer.parseInt(searchField.getText());

            int resultIndex = linearSearch(array, numberToSearch);

            if (resultIndex != -1) {
                resultArea.setText("Number " + numberToSearch + " found at index " + resultIndex);
            } else {
                resultArea.setText("Number " + numberToSearch + " not found in the array.");
            }
        } catch (NumberFormatException ex) {
            resultArea.setText("Error: Invalid input. Please enter valid integers.");
        }
    }

    private int linearSearch(int[] array, int target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Linear Search");
        LinearSearchGUI linearSearchGUI = new LinearSearchGUI();
        frame.getContentPane().add(linearSearchGUI);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
