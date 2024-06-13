import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


 class HeapSorter extends JPanel {

    private final static int BAR_WIDTH = 71;
    private final static int BAR_HEIGHT = 400;
    private int[]list;
    private static JPanel mainPanel;

    private HeapSorter(int[] list){
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
                new Color(170, 197, 170),
                new Color(174, 238, 174),
                new Color(119, 168, 119),
                new Color(62, 98, 62),
                new Color(85, 145, 85)

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


        public void heapify(int list[], int n, int i){
            int largest = i;
            int l = 2 * i + 1;
            int r = 2 * i + 2;


            if(l < n && list[l] >  list[largest]){
                largest = l;
            }


            if(r < n && list[r] > list[largest]){
                largest = r;
            }

            if(largest != i){
                int temp = list[i];
                list[i] = list[largest];
                list[largest] = temp;


                publish( Arrays.copyOf(list, list.length) );
                try { Thread.sleep(100); } catch (Exception e) {}


                heapify(list, n, largest);
            }
        }


        @Override
        protected Void doInBackground(){
            int n = list.length;


            for(int i = (n/2 - 1); i >= 0; i--){
                heapify(list, n, i);
            }


            for(int i = n - 1; i > 0; i--){

                int temp = list[0];
                list[0] = list[i];
                list[i] = temp;


                publish( Arrays.copyOf(list, list.length) );
                try { Thread.sleep(100); } catch (Exception e) {}

                heapify(list, i, 0);
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
            list[i] = random.nextInt(HeapSorter.BAR_HEIGHT);
        }

        return list;
    }


    public static JPanel runHeapSort(){
        HeapSorter heapSort = new HeapSorter(HeapSorter.generateListNumbers());

        JLabel title = new JLabel("Heap Sort");
        title.setFont(new Font("SansSerif", Font.BOLD, 50));
        title.setHorizontalAlignment(SwingConstants.CENTER);



        JButton generate = new JButton("Generate Data");
        generate.setBackground(new Color(62, 98, 62));
        generate.setForeground(Color.WHITE);
        generate.addActionListener((e)->heapSort.setItems(HeapSorter.generateListNumbers()));
        JButton sort = new JButton("Sort Data");
        sort.setBackground(new Color(62, 98, 62));
        sort.setForeground(Color.WHITE);
        sort.addActionListener((e) -> heapSort.sort());



        JPanel bottomPanel = new JPanel();
        bottomPanel.add(generate);
        bottomPanel.add(sort);


        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(heapSort, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        mainPanel.add(title, BorderLayout.NORTH);

        return mainPanel;
    }
}