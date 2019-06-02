import javax.swing.*;
import java.io.*;
import java.util.Vector;

public class Contigous implements AllocationMethod {
    public Vector<Integer> bitVector = new Vector<Integer>();
    public Vector<FileData> structure = new Vector<FileData>();
    public Vector<String> path = new Vector<String>();

    // public Vector<String> toPrint = new Vector<String>();
    public Contigous(int sizee) {
        FileData ob = new FileData();
        for (int i = 0; i < sizee; i++) {
            bitVector.add(0);
        }
        ob.name = "<root>";
        ob.pathh = "<root>";
        ob.parent = "";
        ob.blocks = 0;
        ob.sizee = 0;
        structure.add(ob);
        // toPrint.add("<root>");
    }
    public Contigous(){}
    @Override
    public String createFile(String name, int sizee) {
        FileData object = new FileData();
        int c = 0;
        String[] arr;
        Vector<Integer> counter = new Vector<Integer>();
        Vector<Integer> indexes = new Vector<Integer>();
        arr = name.split("/");
        if (path.contains(name)) {
            return ("ALREADY EXISTS");
        } else {
            path.add(name);
            object.pathh = name;
            object.name = arr[arr.length - 1];
            object.parent = arr[arr.length - 2];

            int x;
            for (x = 0; x < bitVector.size(); x++) {

                if (bitVector.get(x) == 0) {
                    c++;

                } else {
                    counter.add(c);
                    indexes.add(x - c);
                    c = 0;
                }

            }
            counter.add(c);
            indexes.add(x - c);
            c = 0;

            int c1 = 0;
            int index = 0;
            for (int i = 0; i < counter.size(); i++) {

                c1 = counter.get(0);
                if (counter.get(i) > c1) {
                    c1 = counter.get(i);
                    index = i;
                }

            }
            System.out.println("TAKE BLOCK OF: " + c1 + " ZEROES"
                    + " and INDEX " + indexes.get(index));

            for (int i = indexes.get(index); i < indexes.get(index) + sizee; i++) {
                bitVector.set(i, 1);
            }
            object.blocks = c1;
            object.index = indexes.get(index);
            String a = "";
            for (int i = 0; i < arr.length; i++) {
                a += " ";
            }
            a += arr[arr.length - 1];
            // toPrint.add(a);
            for (int i = 0; i < structure.size(); i++) {
                // System.out.println("====="+structure.get(i).name +"  "+
                // arr[arr.length-2]);
                if (structure.get(i).name.equals("<" + arr[arr.length - 2] + ">")) {
                    // System.out.println("HERE");
                    object.sizee = sizee;
                    structure.add(i + 1, object);
                    // structure.insertElementAt(object, i);
                }
            }
            return "File create successfully";
        }
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

    public void printpaths() {
        System.out.println("THE PATH VECTOR IS : ");
        for (int i = 0; i < path.size(); i++) {

            System.out.println(path.get(i));

        }

    }

    @Override
    public void DisplayDiskStructure() {
        // System.out.println("THE DIRECTORY IS: "+structure.size());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < structure.size(); i++) {
            String a = "";
            // System.out.println(i + structure.get(i).pathh);
            String[] arr = structure.get(i).pathh.split("/");
            for (int c = 0; c < arr.length; c++) {
                a += "  ";
            }
            a += structure.get(i).name;
            System.out.println(a);
            sb.append(a).append("\n");

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
    public String createFolder(String p) {
        String[] arr = p.split("/");
        FileData ob = new FileData();
        String a = "";
        if (path.contains(p)) {
            return (p + " ALREADY EXISTSS!!");
        } else {
            path.add(p);
            ob.pathh = p;
            // System.out.println("***");
            for (int i = 0; i < arr.length; i++) {
                a += " ";
            }
            boolean f = true;
            for (int i = 0; i < structure.size(); i++) {

                // System.out.println("------"+structure.get(i).name
                // +"  "+"<"+arr[arr.length-2]+">");

                if (structure.get(i).name.equals("<" + arr[arr.length - 2]
                        + ">")) {
                    // System.out.println("***");
                    ob.name = "<" + arr[arr.length - 1] + ">";
                    ob.parent = arr[arr.length - 2];
                    ob.blocks = 0;
                    ob.index = 0;
                    ob.sizee = 0;
                    structure.add(i + 1, ob);
                    // structure.insertElementAt(ob, i);
                    f = false;
                }
            }
            if (f == true) {
                // System.out.println("***");
                ob.name = "<" + arr[arr.length - 1] + ">";
                ob.parent = arr[arr.length - 2];
                ob.blocks = 0;
                ob.index = 0;
                ob.sizee = 0;
                structure.add(1, ob);
                // toPrint.add(a);
            }
            return "Folder create successfully";
        }
    }

    @Override
    public String deleteFolder(String p) {
        if (!path.contains(p)) {
            return ("FOLDER NOT EXIST!");
        }
        String[] arr = p.split("/");
        FileData ob = new FileData();
        Vector<FileData> temp = new Vector();

        path.remove(p);
        for (int i = 0; i < structure.size(); i++) {
            // System.out.print(arr[arr.length-1]);
            if (structure.get(i).pathh.contains(arr[arr.length - 1])) {
                // System.out.println("HERE "+structure.get(i).pathh);
                ob = structure.get(i);
                temp.add(ob);

            }

        }

        for (int x = 0; x < temp.size(); x++) {
            if (temp.get(x).pathh.contains(".txt")) {
                path.remove(temp.get(x).pathh);
                deleteFile(temp.get(x).pathh);
                structure.remove(temp.get(x));

            } else {
                path.remove(temp.get(x).pathh);
                structure.remove(temp.get(x));
            }

        }
        return "Folder delete successfully";
    }

    @Override
    public String deleteFile(String pathh) {
        if (!path.contains(pathh)) {
            return ("FILE NOT EXIST!");
        }
        path.remove(pathh);
        FileData ob = new FileData();
        for (int i = 0; i < structure.size(); i++) {
            if (structure.get(i).pathh.equals(pathh)) {
                // structure.remove(structure.get(i));
                ob = structure.get(i);
            }

        }

        System.out.println(ob.index + " " + ob.sizee);
        for (int j = ob.index; j < ob.index + ob.sizee; j++) {
            bitVector.set(j, 0);

        }
        structure.remove(ob);
        return "File delete successfully";
    }

    @Override
    public void saveFileSystem() throws IOException {
        String str = "";
        String str1 = "";
        String str2 = "";
        BufferedWriter writer = new BufferedWriter(new FileWriter("BV.txt"));
        BufferedWriter writer1 = new BufferedWriter(new FileWriter("PATH.txt"));
        BufferedWriter writer2 = new BufferedWriter(new FileWriter("Directory Structure.txt"));
        for (int i = 0; i < bitVector.size(); i++) {
            str += bitVector.get(i) + " ";
        }
        writer.write(str);

        for (int i = 0; i < path.size(); i++) {
            str1 += path.get(i) + " ";
        }
        writer1.write(str1);
        for (int i = 0; i < structure.size(); i++) {
            str2 += structure.get(i).name + "|";
            str2 += structure.get(i).parent + "|";
            str2 += structure.get(i).pathh + "|";
            str2 += structure.get(i).sizee + "|";
            str2 += structure.get(i).index + "|";
            str2 += structure.get(i).blocks + "|";
            str2 += "*";
        }
        writer2.write(str2);
        writer.close();
        writer1.close();
        writer2.close();
    }

    @Override
    public void loadFileSystem() throws IOException {
        bitVector.clear();
        BufferedReader br = new BufferedReader(new FileReader("BV.txt"));
        BufferedReader br1 = new BufferedReader(new FileReader("PATH.txt"));
        BufferedReader br2 = new BufferedReader(new FileReader("Directory Structure.txt"));
        String st, st2, d;
        String[] st1 = null;
        String[] st3 = null;
        String[] d1 = null;
        //fill bitvector
        while ((st = br.readLine()) != null) {
            st1 = st.split(" ");
        }
        for (int i = 0; i < st1.length; i++) {
            // System.out.println(st1[i]);
            bitVector.add(Integer.parseInt(st1[i]));

        }
        System.out.println("LOADED BIT VECTOR!");
        //fill paths
        while ((st2 = br1.readLine()) != null) {
            st3 = st2.split(" ");
        }
        for (int x = 0; x < st3.length; x++) {
            // System.out.println(path.get(i));
            path.add(st3[x]);

        }
        System.out.println("LOADED PATHS!");
        //fill directory
        FileData ob = new FileData();
        String[] temp = null;
        while ((d = br2.readLine()) != null) {
            //System.out.println(d);
            d1 = d.split("\\*");
        }
        Vector<FileData> t = new Vector();
        for (int x = 0; x < d1.length; x++) {
            ob = new FileData();
//			System.out.println(" 66 " +d1[x]);
            temp = d1[x].split("\\|");
            ob.name = temp[0];
            ob.parent = temp[1];
            ob.pathh = temp[2];
            ob.sizee = Integer.parseInt(temp[3]);
            ob.index = Integer.parseInt(temp[4]);
            ob.blocks = Integer.parseInt(temp[5]);
            structure.add(ob);
        }
        System.out.println("LOADED DIRECTORY STRUCTURE!");
        br.close();
        br1.close();
        br2.close();
    }
}
