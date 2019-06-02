import java.util.Vector;

public class Entry {
    public String name;
    public String path;
    public String parentFolder;
    public int indexBlock;
    public int size;
    public Vector<Integer> blocks = new Vector<>();

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


    public Entry(String path, int startBlock, int endBlock, int size) {
        this.size = size;
        this.indexBlock = startBlock;
    }

    public Entry() {
    }

    public String getParentFolder() {
        return parentFolder;
    }

    public void setParentFolder(String parentFolder) {
        this.parentFolder = parentFolder;
    }

    public Vector<Integer> getBlocks() {
        return blocks;
    }

    public void setBlocks(Vector<Integer> blocks) {
        this.blocks = blocks;
    }

    public void addBlockToFileBlocks(int blockNum) {
        blocks.add(blockNum);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndexBlock() {
        return indexBlock;
    }

    public void setIndexBlock(int indexBlock) {
        this.indexBlock = indexBlock;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
