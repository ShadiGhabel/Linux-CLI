import java.util.*;
public class ZipCommand implements Command{
    private FileManager fileManager;

    public ZipCommand(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 2) {
            System.out.println("Error: Invalid command");
            return;
        }

        String zipName = args[0];
        if (!zipName.endsWith(".zip")) {
            zipName += ".zip";
        }
        if (fileManager.getCurrentDirectory().findChild(zipName) != null) {
            System.out.println("Error: File or directory already exists");
            return;
        }
        String path = fileManager.getCurrentDirectory().getPath().equals("/")
                ? "/" + zipName
                : fileManager.getCurrentDirectory().getPath() + "/" + zipName;

        Zip zipFile = new Zip(zipName, fileManager.getCurrentDirectory(), path);
        List<FileSystem> files = new ArrayList<>();

        for (int i = 1; i < args.length; i++) {
            FileSystem fs = fileManager.getCurrentDirectory().findChild(args[i]);
            if (fs != null) {
                files.add(fs);
            }
        }
        zipFile.zip(files);
        for (FileSystem f : files) {
            if (f instanceof Removable) {
                ((Removable) f).remove();
            }
        }
    }
}
