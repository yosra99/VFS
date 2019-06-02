import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class loadSystemType {
    private JButton linkedButton;
    private JButton indexedButton;
    private JButton contigousButton;
    public JPanel panelMain;

    public loadSystemType() {
        linkedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame=new JFrame("Commands");
                try {
                    frame.setContentPane(new Commands("link",0).panelMain);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                frame.setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );
                frame.pack();
                frame.setLocationRelativeTo( null );
                frame.setVisible( true );
            }
        });
        indexedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame=new JFrame("Commands");
                try {
                    frame.setContentPane(new Commands("index",0).panelMain);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                frame.setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );
                frame.pack();
                frame.setLocationRelativeTo( null );
                frame.setVisible( true );
            }
        });
        contigousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame=new JFrame("Commands");
                try {
                    frame.setContentPane(new Commands("con",0).panelMain);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                frame.setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );
                frame.pack();
                frame.setLocationRelativeTo( null );
                frame.setVisible( true );
            }
        });
    }
}
