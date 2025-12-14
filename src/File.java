public abstract class File extends FileSystem implements Removable {
    private boolean readableWritable;
    private boolean runnable;
    public File(String name, Directory parent, String path, boolean runnable, boolean readableWritable){
    super(name, parent, path);
    this.isDir = false;
    this.runnable = runnable;
    this.readableWritable = readableWritable;
    if(parent != null){
        parent.addChild(this);
        }
    }
    public File(String name, Directory parent, String path) {
        super(name, parent, path);
        this.isDir = false;
        this.runnable = false;
        this.readableWritable = false;
        if(parent != null){
            parent.addChild(this);
        }
    }
    public boolean isRunnable(){
        return runnable;
    }
    public boolean isReadableWritable(){
        return readableWritable;
    }
    @Override
    public void remove(){
        if(parent != null){
            parent.getChildren().remove(this);
        }
    }
}
