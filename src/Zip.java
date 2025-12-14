import java.util.ArrayList;
import java.util.List;

public class Zip extends File {
    private List<FileSystem> children;
    public Zip(String name, Directory parent, String path){
        super(name, parent, path, false, false);
        this.children = new ArrayList<>();
    }
    public void unzip(Directory directory){
        if (directory != null) {
            for (FileSystem fileSystem : children) {
                fileSystem.setParent(directory);
                String newPath = directory.getPath().equals("/") ?
                        "/" + fileSystem.getName() :
                        directory.getPath() + "/" + fileSystem.getName();
                fileSystem.setPath(newPath);
                directory.addChild(fileSystem);
            }
        }
    }
    public List<FileSystem> getChildren(){
        return children;
    }
    public void zip(List<FileSystem> fileSystemList){
        if (fileSystemList != null) {
            children.clear();
            children.addAll(fileSystemList);
        }
    }
}
