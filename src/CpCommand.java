public class CpCommand implements Command{
    private FileManager fileManager;

    public CpCommand(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 2) {
            System.out.println("Error: Invalid command");
            return;
        }

        FileSystem src = fileManager.getCurrentDirectory().findChild(args[0]);
        String destName = args[1];

        if (src == null) {
            System.out.println("Error: No such file or directory");
            return;
        }

        if (fileManager.getCurrentDirectory().findChild(destName) != null) {
            System.out.println("Error: File or directory already exists");
            return;
        }

        String newPath = fileManager.getCurrentDirectory().getPath().equals("/")
                ? "/" + destName
                : fileManager.getCurrentDirectory().getPath() + "/" + destName;

        if (src instanceof Directory) {
            Directory srcDir = (Directory) src;
            Directory newDir = new Directory(destName, fileManager.getCurrentDirectory(), newPath);

            for (FileSystem child : srcDir.getChildren()) {
                String cpPath = newDir.getPath() + "/" + child.getName();

                if (child instanceof Text) {
                    Text nf = new Text(child.getName(), newDir, cpPath);
                    nf.write(((Text) child).read());
                } else if (child instanceof Python) {
                    new Python(child.getName(), newDir, cpPath);
                } else if (child instanceof Shell) {
                    new Shell(child.getName(), newDir, cpPath);
                }
            }
            return;
        }

        if (src instanceof Text) {
            Text nf = new Text(destName, fileManager.getCurrentDirectory(), newPath);
            nf.write(((Text) src).read());
        } else if (src instanceof Python) {
            new Python(destName, fileManager.getCurrentDirectory(), newPath);
        } else if (src instanceof Shell) {
            new Shell(destName, fileManager.getCurrentDirectory(), newPath);
        }
    }
}
