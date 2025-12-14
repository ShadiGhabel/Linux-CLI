import java.util.ArrayList;
import java.util.List;

public class Directory extends FileSystem implements Removable {
   private List<FileSystem> children;
   public Directory(String name, Directory parent, String path){
       super(name, parent, path);
       this.children = new ArrayList<>();
       if (parent != null) {
           parent.addChild(this);
       }
       this.isDir = true;
   }
   public void addChild(FileSystem fileSystem){
        if(fileSystem != null && !children.contains(fileSystem)){
            children.add(fileSystem);
        }
   }
   public List<FileSystem> getChildren(){
       return children;
   }
   @Override
   public void remove(){
       if(parent != null){
           parent.getChildren().remove(this);
       }
       for(FileSystem child : new ArrayList<>(children)){
           if(child instanceof Removable){
               ((Removable) child).remove();
           }
       }
       children.clear();
   }
   public FileSystem findChild(String name){
       for(FileSystem child : children){
           if(child.getName().equals(name)){
               return child;
           }
       }
       return null;
   }
   public boolean isEmpty(){
       return children.isEmpty();
   }
}
