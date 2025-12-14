public class MkdirCommand implements Command{
    private FileManager fileManager;

    public MkdirCommand(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            System.out.println("Error: Invalid command");
            return;
        }
        String name = args[0];
        if (fileManager.getCurrentDirectory().findChild(name) != null) {
            System.out.println("Error: File or directory already exists");
            return;
        }

        String newPath = fileManager.getCurrentDirectory().getPath().equals("/")
                ? "/" + name
                : fileManager.getCurrentDirectory().getPath() + "/" + name;

        new Directory(name, fileManager.getCurrentDirectory(), newPath);
    }
}
