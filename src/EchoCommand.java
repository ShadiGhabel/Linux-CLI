public class EchoCommand implements Command{
    private FileManager fileManager;

    public EchoCommand(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 3) {
            System.out.println("Error: Invalid command");
            return;
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < args.length && !args[i].equals(">") && !args[i].equals(">>")) {
            if (sb.length() > 0) sb.append(" ");
            sb.append(args[i]);
            i++;
        }

        if (i >= args.length - 1) {
            System.out.println("Error: Invalid command");
            return;
        }

        String text = sb.toString().replace("\\n", "\n");
        String operator = args[i];
        String fileName = args[i + 1];

        FileSystem fs = fileManager.getCurrentDirectory().findChild(fileName);

        if (operator.equals(">")) {
            if (fs == null) {

                if (!fileName.contains(".")) {
                    System.out.println("Error: Invalid file type");
                    return;
                }
                String ext = fileName.substring(fileName.lastIndexOf('.') + 1);
                String newPath = fileManager.getCurrentDirectory().getPath().equals("/") ?
                        "/" + fileName : fileManager.getCurrentDirectory().getPath() + "/" + fileName;

                switch (ext) {
                    case "txt":
                        fs = new Text(fileName, fileManager.getCurrentDirectory(), newPath);
                        break;
                    case "py":
                        fs = new Python(fileName, fileManager.getCurrentDirectory(), newPath);
                        break;
                    case "sh":
                        fs = new Shell(fileName, fileManager.getCurrentDirectory(), newPath);
                        break;
                    default:
                        System.out.println("Error: Invalid file type");
                        return;
                }
            }
            if (!(fs instanceof Writable)) {
                System.out.println("Error: File is not writable");
                return;
            }
            ((Writable) fs).write(text);
        }

        else if (operator.equals(">>")) {

            if (fs == null) {
                System.out.println("Error: No such file or directory");
                return;
            }

            if (!(fs instanceof Writable) || !(fs instanceof Readable)) {
                System.out.println("Error: File is not writable");
                return;
            }

            String current = ((Readable) fs).read();
            if (current == null) current = "";

            ((Writable) fs).write(
                    current.isEmpty() ? text : current + "\n" + text
            );
        }
    }
}
