public class CatCommand implements Command{
    private FileManager fileManager;

    public CatCommand(FileManager fileManager) {
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
        } else if (!(fs instanceof Readable)) {
            System.out.println("Error: File is not Readable");
        } else {
            String content = ((Readable) fs).read();
            if (content != null && !content.isEmpty()) {
                System.out.println(content);
            }
        }
    }
}
