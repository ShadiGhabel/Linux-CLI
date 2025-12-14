public class CdCommand implements Command{
    private FileManager fileManager;

    public CdCommand(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            System.out.println("Error: Invalid command");
            return;
        }
        Directory targetDir = fileManager.pathResolver(args[0]);

        if (targetDir == null) {
            System.out.println("Error: Path does not exist");
        } else {
            fileManager.setCurrentDirectory(targetDir);
        }
    }
}

