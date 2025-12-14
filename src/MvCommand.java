public class MvCommand implements Command{
    private FileManager fileManager;

    public MvCommand(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 2) {
            System.out.println("Error: Invalid command");
            return;
        }

        String srcName = args[0];
        String destPath = args[1];

        FileSystem src = fileManager.getCurrentDirectory().findChild(srcName);
        if (src == null) {
            System.out.println("Error: No such file or directory");
            return;
        }
        if (destPath.contains("/")) {

            String[] parts = destPath.split("/");
            if (parts.length != 2) {
                System.out.println("Error: Path does not exist");
                return;
            }

            String destDirName = parts[0];
            String destFileName = parts[1];

            FileSystem destfs = fileManager.getCurrentDirectory().findChild(destDirName);

            if (!(destfs instanceof Directory)) {
                System.out.println("Error: Path does not exist");
                return;
            }

            Directory destDir = (Directory) destfs;

            if (destDir.findChild(destFileName) != null) {
                System.out.println("Error: File or directory already exists");
                return;
            }

            if (src instanceof File) {
                String srcExt = src.getName().substring(src.getName().lastIndexOf('.') + 1);
                String destExt = destFileName.substring(destFileName.lastIndexOf('.') + 1);

                if (!srcExt.equals(destExt)) {
                    System.out.println("Error: Cannot modify file type");
                    return;
                }
            }

            fileManager.getCurrentDirectory().getChildren().remove(src);
            src.setParent(destDir);
            src.setName(destFileName);
            src.setPath(destDir.getPath() + "/" + destFileName);
            destDir.addChild(src);
            return;
        }
        if (fileManager.getCurrentDirectory().findChild(destPath) != null) {
            System.out.println("Error: File or directory already exists");
            return;
        }
        if (src instanceof File) {
            String srcExt = src.getName().substring(src.getName().lastIndexOf('.') + 1);
            String destExt = destPath.substring(destPath.lastIndexOf('.') + 1);

            if (!srcExt.equals(destExt)) {
                System.out.println("Error: Cannot modify file type");
                return;
            }
        }
        src.setName(destPath);
        src.setPath(fileManager.getCurrentDirectory().getPath().equals("/") ? "/" + destPath
                : fileManager.getCurrentDirectory().getPath() + "/" + destPath);
    }
}
