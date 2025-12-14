public class UnzipCommand implements Command{
    private FileManager fileManager;

    public UnzipCommand(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            System.out.println("Error: Invalid command");
            return;
        }

        FileSystem fs = fileManager.getCurrentDirectory().findChild(args[0]);
        if (fs == null) {
            System.out.println("Error: No such file or directory");
            return;
        }

        if (!(fs instanceof Zip)) {
            System.out.println("Error: Not a zip file");
            return;
        }

        ((Zip) fs).unzip(fileManager.getCurrentDirectory());
        ((Zip) fs).remove();
    }
}
