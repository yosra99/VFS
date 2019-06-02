import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class newSystemType {
    private JButton linkedButton;
    private JButton indexedButton;
    private JButton contigousButton;
    public JPanel panelMain;
    private JTextField textField1;

    public newSystemType() {
        linkedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame=new JFrame("Commands");
                int size=Integer.parseInt(textField1.getText());
                try {
                    frame.setContentPane(new Commands("link",size).panelMain);
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
                int size=Integer.parseInt(textField1.getText());
                try {
                    frame.setContentPane(new Commands("index",size).panelMain);
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
                int size=Integer.parseInt(textField1.getText());
                try {
                    frame.setContentPane(new Commands("con",size).panelMain);
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
