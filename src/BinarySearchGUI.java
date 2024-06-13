import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BinarySearchGUI extends JPanel {
    private JTextField arrayField;
    private JTextField searchField;
    private JButton searchButton;
    private JTextArea resultArea;




    public BinarySearchGUI() {
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
        Font labelFont = new Font("Arial", Font.BOLD, 50);
        JLabel arrayLabel = new JLabel("Enter sorted array (comma-separated):");
        arrayLabel.setFont(labelFont);
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
        Font searchFont = new Font("Arial", Font.BOLD, 50);
        JLabel searchLabel = new JLabel("Enter number to search (Binary):");
        searchLabel.setFont(searchFont);
        searchField = new JTextField(20);
        searchPanel.add(searchLabel, gbcSearch);
        gbcSearch.gridy++;
        searchPanel.add(searchField, gbcSearch);
        gbcSearch.gridy++;
        searchButton = new JButton("Search");
        searchButton.setBackground(Color.gray);
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
                performBinarySearch();
            }
        });
    }
    private void performBinarySearch() {
        try {
            String[] arrayStrings = arrayField.getText().split(",");
            int[] sortedArray = new int[arrayStrings.length];
            for (int i = 0; i < arrayStrings.length; i++) {
                sortedArray[i] = Integer.parseInt(arrayStrings[i].trim());
            }

            int numberToSearch = Integer.parseInt(searchField.getText());

            int resultIndex = binarySearch(sortedArray, numberToSearch);

            if (resultIndex != -1) {
                resultArea.setText("Number " + numberToSearch + " found at index " + resultIndex);
            } else {
                resultArea.setText("Number " + numberToSearch + " not found in the array.");
            }
        } catch (NumberFormatException ex) {
            resultArea.setText("Error: Invalid input. Please enter valid integers.");
        }
    }

    private int binarySearch(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (array[mid] == target)
                return mid;

            if (array[mid] < target)
                left = mid + 1;
            else
                right = mid - 1;
        }

        return -1;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Binary Search");
        BinarySearchGUI binarySearchGUI = new BinarySearchGUI();
        frame.getContentPane().add(binarySearchGUI);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
