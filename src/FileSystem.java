public abstract class FileSystem {
    protected String name;
    protected String path;
    protected Directory parent;
    protected boolean isDir;

    public FileSystem(String name, Directory parent, String path, boolean isDir){
        this.name = name;
        this.parent = parent;
        this.path = path;
        this.isDir = isDir;
    }
    public FileSystem(String name, Directory parent, String path){
        this.name = name;
        this.path = path;
        this.parent = parent;
        this.isDir = false;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setParent(Directory parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public Directory getParent() {
        return parent;
    }

    public String getPath() {
        return path;
    }
    public boolean isDirectory(){
        return isDir;
    }

}
