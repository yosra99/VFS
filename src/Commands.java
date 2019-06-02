import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Commands {
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton displayDiskStatusButton;
    private JButton displayDiskStructureButton;
    private JButton createNewFileButton;
    private JButton createNewFolderButton;
    private JTextField textField5;
    private JButton deleteFileButton;
    private JButton deleteFolderButton;
    public JPanel panelMain;
    private JButton exitButton;
    public AllocationSystem system;

    public Commands(String type, int size) throws IOException {
        if (type.equals("con")) {
            if (size > 0)
                system = new AllocationSystem(new Contigous(size));
            else {
                system = new AllocationSystem(new Contigous());
                system.loadFileSystem();
            }
        } else if (type.equals("link")) {
            if (size > 0)
                system = new AllocationSystem(new Linked(size));
            else {
                system = new AllocationSystem(new Linked());
                system.loadFileSystem();
            }
        } else {
            if (size > 0)
                system = new AllocationSystem(new Index(size));
            else {
                system = new AllocationSystem(new Index());
                system.loadFileSystem();
            }
        }
        createNewFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String path = textField1.getText();
                int size = Integer.parseInt(textField2.getText());
                String result = system.createFile(path, size);
                JOptionPane.showMessageDialog(null, result);
            }
        });
        createNewFolderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String path = textField5.getText();
                String result = system.createFolder(path);
                JOptionPane.showMessageDialog(null, result);
            }
        });
        deleteFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String path = textField3.getText();
                String result = system.deleteFile(path);
                JOptionPane.showMessageDialog(null, result);
            }
        });
        deleteFolderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String path = textField4.getText();
                String result = system.deleteFolder(path);
                JOptionPane.showMessageDialog(null, result);
            }
        });
        displayDiskStatusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                system.DisplayDiskStatus();
            }
        });
        displayDiskStructureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                system.DisplayDiskStructure();
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    system.saveFileSystem();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                System.exit(0);
            }
        });
    }

}
