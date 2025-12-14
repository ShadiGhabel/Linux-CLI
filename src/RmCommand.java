public class RmCommand implements Command{
    private FileManager fileManager;

    public RmCommand(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            System.out.println("Error: Invalid command");
            return;
        }
        boolean recursive = args[0].equals("-r");
        String name = recursive && args.length > 1 ? args[1] : args[0];

        FileSystem fs = fileManager.getCurrentDirectory().findChild(name);
        if (fs == null) {
            System.out.println("Error: No such file or directory");
            return;
        }

        if (recursive && !(fs instanceof Directory)) {
            System.out.println("Error: Invalid command");
            return;
        }

        if (!recursive && fs instanceof Directory) {
            System.out.println("Error: Invalid command");
            return;
        }

        if (fs instanceof Removable) {
            ((Removable) fs).remove();
        }
    }
}
