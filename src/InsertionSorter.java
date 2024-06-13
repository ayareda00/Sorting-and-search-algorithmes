import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class InsertionSorter extends JPanel {
    private final static int BAR_WIDTH = 71;
    private final static int BAR_HEIGHT = 400;
    private int[]list;

    private static JPanel mainPanel;


    private InsertionSorter(int[] list){
        this.list = list;
    }

    private void setItems(int[] list){
        this.list = list;
        repaint();
    }

    private void sort(){
        new SortWorker(list).execute();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Color[] barColors = {
                new Color(128, 83, 72),
                new Color(161, 120, 111),
                new Color(147, 90, 77),
                new Color(107, 67, 58),
                new Color(206, 136, 118)

        };
        int fontSize = 16;

        Font font = new Font("SansSerif", Font.PLAIN, fontSize);
        g.setFont(font);


        for (int i = 0; i < list.length; i++) {
            int x = i * BAR_WIDTH;
            int y = getHeight() - list[i];


            int colorIndex = i % barColors.length;
            g.setColor(barColors[colorIndex]);

            g.fillRect(x, y, BAR_WIDTH, list[i]);

            g.setColor(Color.BLACK);


            g.drawString("" + list[i], x, y - 5); // Adjust the y position as needed
        }
    }


    @Override
        public Dimension getPreferredSize(){
            return new Dimension(list.length * BAR_WIDTH, BAR_HEIGHT + 20);

    }


    private class SortWorker extends SwingWorker<Void, int[]>{
        private int[] list;

        public SortWorker(int[] unsortedItems){
            list = Arrays.copyOf(unsortedItems, unsortedItems.length);
        }

        @Override
        protected Void doInBackground(){
            int n = list.length;
            for (int i = 1; i < n; ++i) {
                int key = list[i];
                int j = i - 1;

                while (j >= 0 && list[j] > key) {
                    list[j + 1] = list[j];
                    j = j - 1;
                }
                list[j + 1] = key;

                publish( Arrays.copyOf(list, list.length) );
                try { Thread.sleep(100); } catch (Exception e) {}
            }

            Toolkit tk = Toolkit.getDefaultToolkit();
            tk.beep();
            return null;
        }

        @Override
        protected void process(List<int[]> processList){
            int[] list = processList.get(processList.size() - 1);
            setItems( list );
        }

        @Override
        protected void done() {}
    }

    private static int[]generateListNumbers(){
        int[] list = new int[20];

        Random random = new Random();
        for(int i = 0; i < list.length; i++){
            list[i] = random.nextInt(InsertionSorter.BAR_HEIGHT);
        }

        return list;
    }


    public static JPanel runInsertionSort(){
        InsertionSorter insertionSort = new InsertionSorter(InsertionSorter.generateListNumbers());

        JLabel title = new JLabel("Insertion Sort");
        title.setFont(new Font("SansSerif", Font.BOLD, 50));
        title.setHorizontalAlignment(SwingConstants.CENTER);


        JButton generate = new JButton("Generate Data");
        generate.setBackground(new Color(107, 67, 58));
        generate.setForeground(Color.WHITE);
        generate.addActionListener((e) -> insertionSort.setItems(InsertionSorter.generateListNumbers()));

        JButton sort = new JButton("Sort Data");
        sort.setBackground(new Color(107, 67, 58));
        sort.setForeground(Color.WHITE);
        sort.addActionListener((e) -> insertionSort.sort());


        JPanel bottomPanel = new JPanel();
        bottomPanel.add( generate );
        bottomPanel.add( sort );

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(insertionSort, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        mainPanel.add(title, BorderLayout.NORTH);

        return mainPanel;
    }
}
