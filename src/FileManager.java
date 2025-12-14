import java.util.*;

public class FileManager {
    private Directory root;
    private Directory currentDirectory;
    private Map<String, Command> commands = new HashMap<>();

    public FileManager() {
        this.root = new Directory("/", null, "/");
        this.currentDirectory = root;
        registerCommands();
    }

    private void registerCommands() {
        commands.put("pwd", new PwdCommand(this));
        commands.put("ls", new LsCommand(this));
        commands.put("cd", new CdCommand(this));
        commands.put("mkdir", new MkdirCommand(this));
        commands.put("touch", new TouchCommand(this));
        commands.put("rm", new RmCommand(this));
        commands.put("cat", new CatCommand(this));
        commands.put("echo", new EchoCommand(this));
        commands.put("cp", new CpCommand(this));
        commands.put("mv", new MvCommand(this));
        commands.put("find", new FindCommand(this));
        commands.put("zip", new ZipCommand(this));
        commands.put("unzip", new UnzipCommand(this));
    }

    public void executeCommand(String input) {
        if (input == null || input.trim().isEmpty()) return;
        input = input.trim();

        if (input.startsWith("./")) {
            String fileName = input.substring(2);
            FileSystem fs = currentDirectory.findChild(fileName);

            if (fs == null) {
                System.out.println("Error: No such file or directory");
                return;
            }

            if (!(fs instanceof File)) {
                System.out.println("Error: Cannot execute " + fileName);
                return;
            }

            File f = (File) fs;

            if (!f.isRunnable()) {
                System.out.println("Error: Cannot execute " + fileName);
                return;
            }

            if (f instanceof Shell) {
                for (String cmd : ((Shell) f).getCommands()) {
                    executeCommand(cmd);
                }
            }
            else if (f instanceof Python) {
                ((Python) f).run();
            }

            return;
        }

        String[] parts = parseCommand(input);
        if (parts.length == 0) return;

        String cmdName = parts[0];
        String[] args = Arrays.copyOfRange(parts, 1, parts.length);

        Command cmd = commands.get(cmdName);
        if (cmd != null) cmd.execute(args);
        else System.out.println("Error: Invalid command");
    }
    private String[] parseCommand(String input) {
        input = input.replace("“", "\"").replace("”", "\"");
        List<String> parts = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;

        for (char c : input.toCharArray()) {
            if (c == '"')
                inQuotes = !inQuotes;
            else if (c == ' ' && !inQuotes) {
                if (current.length() > 0) {
                    parts.add(current.toString());
                    current.setLength(0);
                }
            }
            else current.append(c);
        }

        if (current.length() > 0)
            parts.add(current.toString());
        return parts.toArray(new String[0]);
    }

    public Directory getCurrentDirectory() {
        return currentDirectory;
    }

    public void setCurrentDirectory(Directory directory) {
        this.currentDirectory = directory;
    }

    public Directory getRoot() {
        return root;
    }
}