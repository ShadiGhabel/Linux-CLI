
public class PwdCommand implements Command{
    private FileManager fileManager;

    public PwdCommand(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public void execute(String[] args) {
        System.out.println(fileManager.getCurrentDirectory().getPath());
    }
}
