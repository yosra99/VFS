import java.util.Vector;

public class linkedEntry {
    public String name;
    public String path;
    public String parentFolder;
    public int size;
    public int startBlock;
    public int endBlock;
    public Vector<Block> blocks = new Vector<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getParentFolder() {
        return parentFolder;
    }

    public void setParentFolder(String parentFolder) {
        this.parentFolder = parentFolder;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getStartBlock() {
        return startBlock;
    }

    public void setStartBlock(int startBlock) {
        this.startBlock = startBlock;
    }

    public void setEndBlock(int endBlock) {
        this.endBlock = endBlock;
    }

    public Vector<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(Vector<Block> blocks) {
        this.blocks = blocks;
    }

    public void addBlock(Block block) {
        blocks.add(block);
    }
}
