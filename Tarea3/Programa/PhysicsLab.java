import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

public class PhysicsLab {
    public static void main(String[] args) {
        PhysicsLab_GUI iFrame = new PhysicsLab_GUI();
        iFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        iFrame.setSize(MyWorldView.WIDTH+100, MyWorldView.HEIGHT+100);
        iFrame.setVisible(true);
    }

    static class PhysicsLab_GUI extends JFrame {
        JInternalFrame intFrame = new JInternalFrame("Tarea3");
        JPanel jsp1 = new JPanel();
        JPanel jsp2 = new JPanel();


        public PhysicsLab_GUI() {
            intFrame.setMaximizable(true);
            intFrame.setIconifiable(true);
            intFrame.setResizable(true);
            intFrame.setClosable(true);
            intFrame.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);

            intFrame.setSize(320, 240);
            intFrame.setVisible(true);

            MyWorld world = new MyWorld();
            MyWorldView worldView = new MyWorldView(world);
            world.setView(worldView);

            jsp1.add(new JLabel("Graficos Correspondientes"));
            JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
            splitPane.setDividerLocation(800);

            //add panels to split pane
            splitPane.add(jsp1, JSplitPane.RIGHT);
            splitPane.add(worldView, JSplitPane.LEFT);

            intFrame.add(splitPane);
            LabMenuListener menuListener = new LabMenuListener(world);
            intFrame.setJMenuBar(createLabMenuBar(menuListener));
            add(intFrame);
        }


        public JMenuBar createLabMenuBar(LabMenuListener menu_l) {
            JMenuBar mb = new JMenuBar();

            JMenu menu = new JMenu("Configuration");
            mb.add(menu);
            JMenu subMenu = new JMenu("Insert");
            menu.add(subMenu);

            JMenuItem menuItem = new JMenuItem("Ball");
            menuItem.addActionListener(menu_l);
            subMenu.add(menuItem);
            menuItem = new JMenuItem("Fixed Hook");
            menuItem.addActionListener(menu_l);
            subMenu.add(menuItem);
            menuItem = new JMenuItem("Spring");
            menuItem.addActionListener(menu_l);
            subMenu.add(menuItem);
            menuItem = new JMenuItem("Oscillator");
            menuItem.addActionListener(menu_l);
            subMenu.add(menuItem);
            menuItem = new JMenuItem("My scenario");
            menuItem.addActionListener(menu_l);
            subMenu.add(menuItem);

            menu = new JMenu("MyWorld");
            mb.add(menu);
            menuItem = new JMenuItem("Start");
            menuItem.addActionListener(menu_l);
            menu.add(menuItem);
            menuItem = new JMenuItem("Stop");
            menuItem.addActionListener(menu_l);
            menu.add(menuItem);
            subMenu = new JMenu("Simulator");
            menuItem = new JMenuItem("Delta time");
            menuItem.addActionListener(menu_l);
            subMenu.add(menuItem);
            menuItem = new JMenuItem("View Refresh time");
            menuItem.addActionListener(menu_l);
            subMenu.add(menuItem);
            menu.add(subMenu);
            return mb;
        }
    }
}
