import java.io.IOException;

public class AllocationSystem {
    private final AllocationMethod strategy;

    public AllocationSystem(AllocationMethod strategy) {
        this.strategy = strategy;
    }

    public String createFile(String path, int size) {
        return strategy.createFile(path, size);
    }

    public String createFolder(String path) {
        return strategy.createFolder(path);
    }

    public String deleteFile(String path) {
        return strategy.deleteFile(path);
    }

    public String deleteFolder(String path) {
        return strategy.deleteFolder(path);
    }

    public void DisplayDiskStatus() {
        strategy.DisplayDiskStatus();
    }


    public void DisplayDiskStructure() {
        strategy.DisplayDiskStructure();
    }

    public void saveFileSystem() throws IOException {
        strategy.saveFileSystem();
    }

    public void loadFileSystem() throws IOException {
        strategy.loadFileSystem();
    }
}
