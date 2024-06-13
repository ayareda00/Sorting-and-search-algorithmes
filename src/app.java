import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class app extends JFrame{
    private static final int WIDTH = 620;
    private static final int HEIGHT = 800;

    JPanel homePanel;
    JPanel bubbleSortPanel;
    JPanel selectionSortPanel;
    JPanel quickSortPanel;
    JPanel heapSortPanel;
    JPanel insertionSortPanel;
    JPanel binarySearchPanel;
    JPanel linearSearchPanel;


    private class BubbleSortListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            homePanel.setVisible(false);
            bubbleSortPanel.setVisible(true);
            selectionSortPanel.setVisible(false);
            quickSortPanel.setVisible(false);
            heapSortPanel.setVisible(false);
            insertionSortPanel.setVisible(false);
            binarySearchPanel.setVisible(false);
            linearSearchPanel.setVisible(false);


        }
    }
    private class HomePanelListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            homePanel.setVisible(true);
            bubbleSortPanel.setVisible(false);
            selectionSortPanel.setVisible(false);
            quickSortPanel.setVisible(false);
            heapSortPanel.setVisible(false);
            insertionSortPanel.setVisible(false);
            binarySearchPanel.setVisible(false);
            linearSearchPanel.setVisible(false);


        }
    }
    private class SelectionSortPanelListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            homePanel.setVisible(false);
            bubbleSortPanel.setVisible(false);
            selectionSortPanel.setVisible(true);
            quickSortPanel.setVisible(false);
            heapSortPanel.setVisible(false);
            insertionSortPanel.setVisible(false);
            binarySearchPanel.setVisible(false);
            linearSearchPanel.setVisible(false);


        }
    }
    private class QuickSortPanelListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            homePanel.setVisible(false);
            bubbleSortPanel.setVisible(false);
            selectionSortPanel.setVisible(false);
            quickSortPanel.setVisible(true);
            heapSortPanel.setVisible(false);
            insertionSortPanel.setVisible(false);
            binarySearchPanel.setVisible(false);
            linearSearchPanel.setVisible(false);


        }
    }
    private class HeapSortPanelListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            homePanel.setVisible(false);
            bubbleSortPanel.setVisible(false);
            selectionSortPanel.setVisible(false);
            quickSortPanel.setVisible(false);
            heapSortPanel.setVisible(true);
            insertionSortPanel.setVisible(false);
            binarySearchPanel.setVisible(false);
            linearSearchPanel.setVisible(false);


        }
    }

    private class InsertionSortPanelListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            homePanel.setVisible(false);
            bubbleSortPanel.setVisible(false);
            selectionSortPanel.setVisible(false);
            quickSortPanel.setVisible(false);
            heapSortPanel.setVisible(false);
            insertionSortPanel.setVisible(true);
            binarySearchPanel.setVisible(false);
            linearSearchPanel.setVisible(false);


        }
    }
    private class BinarySearchPanelListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            homePanel.setVisible(false);
            bubbleSortPanel.setVisible(false);
            selectionSortPanel.setVisible(false);
            quickSortPanel.setVisible(false);
            heapSortPanel.setVisible(false);
            insertionSortPanel.setVisible(false);
            binarySearchPanel.setVisible(true);
            linearSearchPanel.setVisible(false);


        }
    }
    private class LinearSearchPanelListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            homePanel.setVisible(false);
            bubbleSortPanel.setVisible(false);
            selectionSortPanel.setVisible(false);
            quickSortPanel.setVisible(false);
            heapSortPanel.setVisible(false);
            insertionSortPanel.setVisible(false);
            binarySearchPanel.setVisible(false);
            linearSearchPanel.setVisible(true);


        }
    }

    public app(){

        super("Sorting Algorithms");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));

        homePanel = new JPanel();
        homePanel.setLayout(new BorderLayout());

        homePanel = new ImagePanel("C:\\Users\\ayare\\OneDrive\\Desktop\\sort2.gif");
        homePanel.setLayout(new BorderLayout());

        JLabel welcomeInfo = new JLabel("WELCOME! ABA Sorting System  ^_^ ");
        welcomeInfo.setFont(new Font("Guard script", Font.BOLD, 24));
        welcomeInfo.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeInfo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        homePanel.add(welcomeInfo, BorderLayout.NORTH);

        add(homePanel, BorderLayout.CENTER);



        bubbleSortPanel = BubbleSorter.runBubbleSort();
        bubbleSortPanel.setVisible(false);
        add(bubbleSortPanel);

        insertionSortPanel = InsertionSorter.runInsertionSort();
        insertionSortPanel.setVisible(false);
        add(insertionSortPanel);

        selectionSortPanel = SelectionSorter.runSelectionSort();
        selectionSortPanel.setVisible(false);
        add(selectionSortPanel);

        quickSortPanel = QuickSorter.runQuickSort();
        quickSortPanel.setVisible(false);
        add(quickSortPanel);

        heapSortPanel = HeapSorter.runHeapSort();
        heapSortPanel.setVisible(false);
        add(heapSortPanel);


        binarySearchPanel = new BinarySearchGUI();
        binarySearchPanel.setVisible(false);
        add(binarySearchPanel);

        linearSearchPanel = new LinearSearchGUI();
        linearSearchPanel.setVisible(false);
        add(linearSearchPanel);


        JMenu menu = new JMenu("Menu");
        JMenuItem homeChoice = new JMenuItem("Home");
        homeChoice.addActionListener(new HomePanelListener());
        menu.add(homeChoice);
        JMenuItem bubbleChoice = new JMenuItem("Bubble Sort");
        bubbleChoice.addActionListener(new BubbleSortListener());
        menu.add(bubbleChoice);
        JMenuItem selectionChoice = new JMenuItem("Selection Sort");
        selectionChoice.addActionListener(new SelectionSortPanelListener());
        menu.add(selectionChoice);
        JMenuItem quickChoice = new JMenuItem("Quick Sort");
        quickChoice.addActionListener(new QuickSortPanelListener());
        menu.add(quickChoice);
        JMenuItem heapChoice = new JMenuItem("Heap Sort");
        heapChoice.addActionListener(new HeapSortPanelListener());
        menu.add(heapChoice);
        JMenuItem insertionChoice = new JMenuItem("Insertion Sort");
        insertionChoice.addActionListener(new InsertionSortPanelListener());
        menu.add(insertionChoice);
        JMenuItem binaryChoice = new JMenuItem("Binary Search");
        binaryChoice.addActionListener(new BinarySearchPanelListener());
        menu.add(binaryChoice);
        JMenuItem linearChoice = new JMenuItem("Linear Search");
        linearChoice.addActionListener(new LinearSearchPanelListener());
        menu.add(linearChoice);
        Font menuFont = new Font("Verdana", Font.PLAIN, 18);

        menu.setFont(menuFont);
        Dimension menuPreferredSize = new Dimension(1600, 30);
        menu.setPreferredSize(menuPreferredSize);

        JMenuBar bar = new JMenuBar();
        bar.add(menu);
        setJMenuBar(bar);

    }


    public static void main(String[] args){
        Login loginFrame = new Login();
        loginFrame.setVisible(true);
        loginFrame.setLocationRelativeTo(null);

        loginFrame.setLoginListener(new LoginListener() {
            @Override
            public void onLoginSuccess() {
                loginFrame.dispose();

                app gui = new app();
                gui.setVisible(true);
                gui.setLocationRelativeTo(null);
            }
        });
    }}

