import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class App {
    private JButton newSystemButton;
    private JButton loadSystemButton;
    private JPanel panelMain;

    public App() {
        newSystemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame=new JFrame("newSystemType");
                frame.setContentPane(new newSystemType().panelMain);
                frame.setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );
                frame.pack();
                frame.setLocationRelativeTo( null );
                frame.setVisible(true);
            }
        });
        loadSystemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame=new JFrame("loadSystemType");
                frame.setContentPane(new loadSystemType().panelMain);
                frame.setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );
                frame.pack();
                frame.setLocationRelativeTo( null );
                frame.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame=new JFrame("App");
        frame.setContentPane(new App().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo( null );
        frame.setVisible(true);
    }
}
