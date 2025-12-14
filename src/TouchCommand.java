public class TouchCommand implements Command{
    private FileManager fileManager;

    public TouchCommand(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            System.out.println("Error: Invalid command");
            return;
        }
        for (String fileName : args) {
            if (fileManager.getCurrentDirectory().findChild(fileName) != null) {
                System.out.println("Error: File or directory already exists");
                continue;
            }
            if (!fileName.contains(".")) {
                System.out.println("Error: Invalid file type");
                continue;
            }
            String ext = fileName.substring(fileName.lastIndexOf('.') + 1);

            String path = fileManager.getCurrentDirectory().getPath().equals("/")
                    ? "/" + fileName
                    : fileManager.getCurrentDirectory().getPath() + "/" + fileName;

            switch (ext) {
                case "txt":
                    new Text(fileName, fileManager.getCurrentDirectory(), path);
                    break;
                case "py":
                    new Python(fileName, fileManager.getCurrentDirectory(), path);
                    break;
                case "sh":
                    new Shell(fileName, fileManager.getCurrentDirectory(), path);
                    break;
                case "zip":
                    new Zip(fileName, fileManager.getCurrentDirectory(), path);
                    break;
                default:
                    System.out.println("Error: Invalid file type");
            }
        }
    }
}
