public class FindCommand implements Command{
    private FileManager fileManager;

    public FindCommand(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            System.out.println("Error: Invalid command");
            return;
        }
        String name = args[0];
        for (FileSystem child : fileManager.getCurrentDirectory().getChildren()) {
            findRecursive(child, name, "");
        }
    }

    private void findRecursive(FileSystem fs, String target, String path) {
        String currentPath = path.isEmpty() ? fs.getName() : path + "/" + fs.getName();

        if (fs.getName().equals(target)) {
            System.out.println(currentPath);
        }

        if (!(fs instanceof Directory)) return;

        Directory dir = (Directory) fs;

        for (FileSystem child : dir.getChildren()) {
            findRecursive(child, target, currentPath);
        }
    }
}

