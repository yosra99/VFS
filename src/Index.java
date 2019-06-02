import javax.swing.*;
import java.io.*;
import java.util.Vector;

public class Index implements AllocationMethod {
    public Vector<Integer> bitVector = new Vector<Integer>();
    public static Vector<Entry> allFiles = new Vector<Entry>();

    public Index(int size) {
        for (int i = 0; i < size; i++)
            bitVector.add(0);
        Entry root = new Entry();
        root.setName("<root>");
        root.setParentFolder("-");
        root.setPath("root");
        root.setSize(0);
        allFiles.add(root);
    }
    public Index(){}
    @Override
    public String createFile(String path, int size) {
        Entry file = new Entry();
        file.setPath(path);
        String[] paths = path.split("/");
        String parentPath = "";
        for (int i = 0; i < paths.length - 1; i++) {
            if (i == paths.length - 2)
                parentPath += paths[i];
            else
                parentPath += paths[i] + "/";
        }
        String name = paths[paths.length - 1];
        String parentFolder = "<" + paths[paths.length - 2] + ">";
        file.setName(name);
        file.setParentFolder(parentFolder);
        file.setSize(size);
        int countFreeBlocks = 0;
        for (int i = 0; i < bitVector.size(); i++) {
            if (bitVector.get(i) == 0)
                countFreeBlocks++;
        }
        if (countFreeBlocks < size) {//--------------> || allFiles.chiled(parent) != name
            return ("THERE IS NO EMPTY SPACE!");
        }
        if (searchForFileByPath(path) != -1) {
            return ("THIS FILE IS ALREADY EXIST!");
        }

        int indexOfParentInAllFiles = searchForFileByPath(parentPath);
        if (indexOfParentInAllFiles == -1) {
            return ("THE PARENT OF THIS FILE IS NOT EXIST!");
        }
        allFiles.add(indexOfParentInAllFiles + 1, file);
        int indexOfFirstFreeBlock = bitVector.indexOf(0);

        file.setIndexBlock(indexOfFirstFreeBlock);
        bitVector.set(indexOfFirstFreeBlock, 1);
        int tmpSize = size;
        while (tmpSize > 0 && bitVector.indexOf(1) != -1) {//while we don't allocate the whole file and there is a free blocks
            int nextFreeBlock = bitVector.indexOf(0);
            file.addBlockToFileBlocks(nextFreeBlock);
            bitVector.set(nextFreeBlock, 1);
            tmpSize--;
        }
        return "File create successfully";
    }

    @Override
    public String createFolder(String path) {
        if (searchForFileByPath(path) != -1) {
            return ("THIS FILE IS ALREADY EXIST!");
        }
        Entry folder = new Entry();
        folder.setPath(path);
        String[] paths = path.split("/");
        String parentPath = "";
        for (int i = 0; i < paths.length - 1; i++) {
            if (i == paths.length - 2)
                parentPath += paths[i];
            else
                parentPath += paths[i] + "/";
        }
        String name = "<" + paths[paths.length - 1] + ">";
        String parentFolder = "<" + paths[paths.length - 2] + ">";
        folder.setName(name);
        folder.setParentFolder(parentFolder);
        folder.setSize(0);
        int indexOfParentInallFiles = searchForFileByPath(parentPath);
        if (indexOfParentInallFiles == -1) {
            return ("THE PARENT OF THIS FOLDER IS NOT EXIST!");
        }
        allFiles.add(indexOfParentInallFiles + 1, folder);
        return "Folder create successfully";
    }

    @Override
    public String deleteFile(String path) {
        if (searchForFileByPath(path) == -1) {
            return ("FILE IS NOT EXIST!");
        }
        int indOfFileInallFiles = searchForFileByPath(path);
        Entry file = allFiles.get(indOfFileInallFiles);
        allFiles.remove(indOfFileInallFiles);
        bitVector.set(file.getIndexBlock(), 0);
        for (int i = 0; i < file.getBlocks().size(); i++) {
            bitVector.set(file.getBlocks().get(i), 0);
        }
        return "File delete successfully";
    }

    @Override
    public String deleteFolder(String path) {
        if (searchForFileByPath(path) == -1) {
            return ("FOLDER IS NOT EXIST!");
        }
        for (int i = 0; i < allFiles.size(); i++) {
            String[] folderPartofAnotherItem = allFiles.get(i).getPath().split(path);
            if (folderPartofAnotherItem.length > 1) {
                String isFile = allFiles.get(i).getPath().substring(allFiles.get(i).getPath().length() - 4);
                if (isFile.equals(".txt")) {
                    deleteFile(allFiles.get(i).getPath());
                    i--;//brcause when we delete element the size of the whole vector is decrease so some elements will not be reachable
                } else {
                    allFiles.remove(i);
                    i--;
                }
            }
        }
        int idx = searchForFileByPath(path);
        allFiles.remove(idx);
        return "Folder delete successfully";
    }

    public static int searchForFileByPath(String path) {
        for (int i = 0; i < allFiles.size(); i++) {
            String pathofFile = allFiles.get(i).getPath();
            if (pathofFile.equals(path)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void DisplayDiskStatus() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bitVector.size(); i++)
            if (bitVector.get(i) == 0) {
                System.out.println("Block[" + i + "] is free");
                sb.append("Block[" + i + "] is free").append("\n");
            } else {
                System.out.println("Block[" + i + "] is allocated");
                sb.append("Block[" + i + "] is allocated").append("\n");
            }
        String out1 = sb.substring(0, sb.length() - 1);
        JTextArea textArea = new JTextArea(20, 25);
        textArea.setText(out1);
        textArea.setEditable(false);

        // wrap a scrollpane around it
        JScrollPane scrollPane = new JScrollPane(textArea);

        // display them in a message dialog
        JOptionPane.showMessageDialog(null, scrollPane);
    }

    @Override
    public void DisplayDiskStructure() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < allFiles.size(); i++) {
            String fileInStructure = "";
            String[] paths = allFiles.get(i).getPath().split("/");
            for (int j = 0; j < paths.length; j++)
                fileInStructure += "   ";
            fileInStructure += "  " + allFiles.get(i).getName();
            System.out.println(fileInStructure);
            sb.append(fileInStructure).append("\n");

        }
        String out1 = sb.substring(0, sb.length() - 1);
        JTextArea textArea = new JTextArea(20, 25);
        textArea.setText(out1);
        textArea.setEditable(false);

        // wrap a scrollpane around it
        JScrollPane scrollPane = new JScrollPane(textArea);

        // display them in a message dialog
        JOptionPane.showMessageDialog(null, scrollPane);
    }

    @Override
    public void saveFileSystem() throws IOException {
        try {
            File file = new File("bitVector.txt");
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            int j = 0, h = 0;
            for (int i = 0; i < bitVector.size(); i++) {
                bw.write(Integer.toString(bitVector.get(i)));
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            File file = new File("metaData.vfs.txt");
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < allFiles.size(); i++) {
                Entry entry = allFiles.get(i);
                bw.write(entry.getPath() + " " + entry.getName() + " " + entry.getParentFolder() + " " + Integer.toString(entry.getSize()) + " " + Integer.toString(entry.getIndexBlock()) + " ");
                for (int j = 0; j < entry.getBlocks().size(); j++)
                    bw.write(Integer.toString(entry.getBlocks().get(j)) + " ");
                bw.write("\r\n");
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void loadFileSystem() throws IOException {
        bitVector.clear();
        allFiles.clear();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("bitVector.txt"));
            String line = reader.readLine();
            while (line != null) {
                String[] parameters = line.split("");
                for (int i = 0; i < parameters.length; i++) {
                    bitVector.add(Integer.parseInt(parameters[i]));
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader("metaData.vfs.txt"));
            String line = reader.readLine();
            while (line != null) {
                String[] parameters = line.split(" ");
                Entry entry = new Entry();
                entry.setPath(parameters[0]);
                entry.setName(parameters[1]);
                entry.setParentFolder(parameters[2]);
                entry.setSize(Integer.parseInt(parameters[3]));
                entry.setIndexBlock(Integer.parseInt(parameters[4]));
                Vector<Integer> blocks = new Vector<>();
                for (int i = 5; i < parameters.length; i++) {
                    blocks.add(Integer.parseInt(parameters[i]));
                }
                entry.setBlocks(new Vector<>(blocks));
                blocks.clear();
                allFiles.add(entry);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

