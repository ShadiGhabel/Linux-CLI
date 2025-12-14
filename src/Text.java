public class Text extends File implements Readable, Writable{
    private String content;
    private int line;
    private boolean isEmpty;

    public Text(String name, Directory parent, String path){
        super(name, parent, path, false, true);
        this.content = "";
        this.line = 0;
        this.isEmpty = true;
    }
    public int getLineCount(){
        if(content == null || content.isEmpty()){
            return 0;
        }
        String[] lines = content.split("\n", -1);
        return lines.length;
    }
    public boolean isEmpty(){
        return isEmpty || content == null || content.isEmpty();
    }
    @Override
    public String read(){
        return content != null ? content : "";
    }
    @Override
    public void write(String string){
        this.content = string;
        this.isEmpty = (string == null) || string.isEmpty();
        this.line = getLineCount();
    }
}
