import java.io.IOException;

public interface AllocationMethod {
    public String createFile(String path, int size);

    public String createFolder(String path);

    public String deleteFile(String path);

    public String deleteFolder(String path);

    public void DisplayDiskStatus();


    public void DisplayDiskStructure();

    public void saveFileSystem() throws IOException;

    public void loadFileSystem() throws IOException;
}
