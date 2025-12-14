
public class LsCommand implements Command {
    private FileManager fileManager;

    public LsCommand(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public void execute(String[] args) {
        Directory targetDir;
        if(args.length == 0){
            targetDir = fileManager.getCurrentDirectory();
        }
        else{
            String path = args[0];
            targetDir = resolvePath(path);
            if (targetDir == null) {
                System.out.println("Error: Path does not exist");
                return;
            }
        }
        for (FileSystem child : targetDir.getChildren()) {
            System.out.println(child.getName());
        }

    }
    private Directory resolvePath(String path){
        if (path.startsWith("/")) {
            if(path.equals("/")){
                return fileManager.getRoot();
            }
            String[] parts = path.substring(1).split("/");
            Directory current = fileManager.getRoot();

            for(String part : parts){
                if(part.isEmpty())
                    continue;

                FileSystem fs = current.findChild(part);
                if(fs == null || !(fs instanceof Directory)){
                    return null;
                }
                current = (Directory) fs;
            }
            return current;
        }
        if (path.equals(".")) {
            return fileManager.getCurrentDirectory();
        }

        if (path.equals("..")) {
            Directory parent = fileManager.getCurrentDirectory().getParent();
            return parent != null ? parent : fileManager.getCurrentDirectory();
        }
        FileSystem fs = fileManager.getCurrentDirectory().findChild(path);
        if (fs != null && fs instanceof Directory) {
            return (Directory) fs;
        }
        return null;
    }
}
