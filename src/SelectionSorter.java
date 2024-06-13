import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SelectionSorter extends JPanel{

    private final static int BAR_WIDTH = 71;
    private final static int BAR_HEIGHT = 400;
    private int[]list;
    private static JPanel mainPanel;

    private SelectionSorter(int[] list){
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
                new Color(0, 53, 53),
                new Color(0, 80, 80),
                new Color(0, 130, 130),
                new Color(0, 163, 163),
                new Color(0, 190, 190)

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

            for(int i = 0; i < (n - 1); i++){
                int min = i;
                for(int j = i+1; j < n; j++){
                    if(list[j] < list[min]){
                        min = j;
                    }
                }

                int temp = list[min];
                list[min] = list[i];
                list[i] = temp;

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
        //variables initialized
        int[] list = new int[20];

        Random random = new Random();
        for(int i = 0; i < list.length; i++){
            list[i] = random.nextInt(SelectionSorter.BAR_HEIGHT);
        }

        return list;
    }


    public static JPanel runSelectionSort(){
        SelectionSorter selectionSort = new SelectionSorter(SelectionSorter.generateListNumbers());

        JLabel title = new JLabel("Selection Sort");
        title.setFont(new Font("SansSerif", Font.BOLD, 50));
        title.setHorizontalAlignment(SwingConstants.CENTER);


        JButton generate = new JButton("Generate Data");
        generate.setBackground(new Color(17, 82, 82));
        generate.setForeground(Color.WHITE);
        generate.addActionListener((e) -> selectionSort.setItems(SelectionSorter.generateListNumbers()));
        JButton sort = new JButton("Sort Data");
        sort.setBackground(new Color(17, 82, 82));
        sort.setForeground(Color.WHITE);
        sort.addActionListener((e) -> selectionSort.sort());

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(generate);
        bottomPanel.add(sort);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(selectionSort, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        mainPanel.add(title, BorderLayout.NORTH);

        return mainPanel;
    }
}