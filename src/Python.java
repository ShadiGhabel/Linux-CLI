import java.util.ArrayList;
import java.util.List;

public class Python extends File implements Readable, Writable, Executable {
    private List<String> commands;

    public Python(String name, Directory parent, String path){
        super(name, parent, path,true ,true);
        this.commands = new ArrayList<>();
    }
    @Override
    public String read(){
        return String.join("\n", commands);
    }
    @Override
    public void write(String string){
        commands.clear();
        if (string != null && !string.isEmpty()) {
            String[] lines = string.split("\n");
            for (String line : lines) {
                commands.add(line);
            }
        }
    }
    @Override
    public void run(){
        for (String command : commands) {
            if (command.trim().startsWith("print(")) {
                executePrint(command.trim());
            }
            else if (!command.trim().isEmpty()) {
                System.out.println("Error: Invalid command");
            }
        }
    }
    private void executePrint(String command) {
        int start = command.indexOf("(\"") + 2;
        int end = command.lastIndexOf("\")");
        if (start > 1 && end > start) {
            String text = command.substring(start, end);
            System.out.println(text);
        }
    }
}
