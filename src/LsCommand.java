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
        } else {
            targetDir = fileManager.pathResolver(args[0]);
            if (targetDir == null) {
                System.out.println("Error: Path does not exist");
                return;
            }
        }
        for (FileSystem child : targetDir.getChildren()) {
            System.out.println(child.getName());
        }
    }
}
