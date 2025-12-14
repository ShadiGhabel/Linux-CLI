import java.util.ArrayList;
import java.util.List;

public class Shell extends File implements Readable, Writable, Executable{
    private List<String> commands;

    public Shell(String name, Directory parent, String path){
        super(name, parent, path, true, true);
        this.commands = new ArrayList<>();

    }
    @Override
    public void write(String string){
        commands.clear();
        if (string != null && !string.isEmpty()) {
            String[] lines = string.split("\n");
            for (String line : lines) {
                if (!line.trim().isEmpty()) {
                    commands.add(line.trim());
                }
            }
        }
    }
    @Override
    public void run(){
        //code
    }
    public List<String> getCommands() {
        return commands;
    }
    @Override
    public String read(){
        return String.join("\n", commands);
    }
}
