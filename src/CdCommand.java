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
        String path = args[0];
        if (path.startsWith("/")) {
            if (path.equals("/")) {
                fileManager.setCurrentDirectory(fileManager.getRoot());
                return;
            }
            String[] parts = path.substring(1).split("/");
            Directory current = fileManager.getRoot();

            for (String part : parts) {
                if (part.isEmpty()) continue;

                FileSystem fs = current.findChild(part);
                if (fs == null) {
                    System.out.println("Error: Path does not exist");
                    return;
                }
                if (!(fs instanceof Directory)) {
                    System.out.println("Error: Not a directory");
                    return;
                }
                current = (Directory) fs;
            }

            fileManager.setCurrentDirectory(current);
            return;
        }
        if (path.equals("..")) {
            if (fileManager.getCurrentDirectory().getParent() != null) {
                fileManager.setCurrentDirectory(fileManager.getCurrentDirectory().getParent());
            }
            return;
        }
        if (path.equals(".")) return;

        FileSystem fs = fileManager.getCurrentDirectory().findChild(path);

        if (fs == null) {
            System.out.println("Error: Path does not exist");
        } else if (!(fs instanceof Directory)) {
            System.out.println("Error: Not a directory");
        } else {
            fileManager.setCurrentDirectory((Directory) fs);
        }
    }
}
