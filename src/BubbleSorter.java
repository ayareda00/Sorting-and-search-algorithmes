import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BubbleSorter extends JPanel {
    private final static int BAR_WIDTH = 71;
    private final static int BAR_HEIGHT = 400;
    private int[]list;
    private static JPanel mainPanel;

    public BubbleSorter(){
    }
    public BubbleSorter(int[] list){
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
                new Color(188, 186, 239),
                new Color(51, 53, 84, 255),
                new Color(123, 135, 227),
                new Color(95, 91, 155),
                new Color(34, 36, 58)

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

            g.drawString("" + list[i], x, y - 5);
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
            int temp = 0;

            for(int i = 0; i < (n-1); i++){
                for(int j = 0; j < (n-i-1); j++){
                    if(list[j] > list[j+1]){

                        temp = list[j];
                        list[j] = list[j+1];
                        list[j+1] = temp;

                        publish( Arrays.copyOf(list, list.length) );
                        try { Thread.sleep(100); } catch (Exception e) {}
                    }
                }
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
            list[i] = random.nextInt(BubbleSorter.BAR_HEIGHT);
        }

        return list;
    }


    public static JPanel runBubbleSort(){
        BubbleSorter bubbleSort = new BubbleSorter(BubbleSorter.generateListNumbers());

        JLabel title = new JLabel("Bubble Sort");
        title.setFont(new Font("SansSerif", Font.BOLD, 50));
        title.setHorizontalAlignment(SwingConstants.CENTER);


        JButton generate = new JButton("Generate Data");
        generate.setBackground(new Color(51, 53, 84));
        generate.setForeground(Color.WHITE);
        generate.addActionListener((e)->bubbleSort.setItems(BubbleSorter.generateListNumbers()));

        JButton sort = new JButton("Sort Data");
        sort.setBackground(new Color(51, 53, 84));
        sort.setForeground(Color.WHITE);
        sort.addActionListener((e) -> bubbleSort.sort());


        JPanel bottomPanel = new JPanel();
        bottomPanel.add( generate );
        bottomPanel.add( sort );

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(bubbleSort, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        mainPanel.add(title, BorderLayout.NORTH);

        return mainPanel;
    }
}