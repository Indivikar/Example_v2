package app.example.FileSystemViewer.PathStringsToTreeStructure;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class PathStringsToTreeStructureDemo {

  public static void main(final String... args) {
    final String[] list = new String[]{
      "/mnt/sdcard/folder1/a/b/file1.file",
      "/mnt/sdcard/folder1/a/b/file2.file",
      "/mnt/sdcard/folder1/a/b/file3.file",
      "/mnt/sdcard/folder1/a/b/file4.file",
      "/mnt/sdcard/folder1/a/b/file5.file",
      "/mnt/sdcard/folder1/e/c/file6.file",
      "/mnt/sdcard/folder2/d/file7.file",
      "/mnt/sdcard/folder2/d/file8.file",
      "/mnt/sdcard/file9.file"
    };

    final DirectoryNode directoryRootNode = createDirectoryTree(list);

    System.out.println(directoryRootNode);
    /* Output is:
      {value='mnt', children=[{value='sdcard', children=[{value='folder1', children=[{value='a', children=[{value='b',
      children=[{value='file1.file', children=[]}, {value='file2.file', children=[]}, {value='file3.file',
      children=[]}, {value='file4.file', children=[]}, {value='file5.file', children=[]}]}]}, {value='e',
      children=[{value='c', children=[{value='file6.file', children=[]}]}]}]}, {value='folder2',
      children=[{value='d', children=[{value='file7.file', children=[]}, {value='file8.file',
      children=[]}]}]}, {value='file9.file', children=[]}]}]}
     */
  }

  public static DirectoryNode createDirectoryTree(final String[] list) {
    DirectoryNode treeRootNode = null;
    for (final String rawPath : list) {
      final String path = rawPath.startsWith("/") ? rawPath.substring(1) : rawPath;
      final String[] pathElements = path.split("/");
      DirectoryNode movingNode = null;
      for (final String pathElement : pathElements) {
        movingNode = new DirectoryNode(movingNode, pathElement);
      }

      if (treeRootNode == null) {
        treeRootNode = movingNode.getRoot();
      } else {
        treeRootNode.merge(movingNode.getRoot());
      }
    }

    return treeRootNode;
  }

  private static class DirectoryNode {

    private final Set<DirectoryNode> children = new LinkedHashSet<>();

    private final String value;

    private final DirectoryNode parent;

    public DirectoryNode(final DirectoryNode parent, final String value) {
      this.parent = parent;
      if (this.parent != null) {
        this.parent.children.add(this);
      }

      this.value = value;
    }

    public Set<DirectoryNode> getChildren() {
      return this.children;
    }

    public String getValue() {
      return this.value;
    }

    public DirectoryNode getParent() {
      return this.parent;
    }

    public int getLeafCount() {
      int leafCount = this.isLeaf() ? 1 : 0;
      for (final DirectoryNode child : this.children) {
        leafCount += child.getLeafCount();
      }

      return leafCount;
    }

    public boolean isLeaf() {
      return this.children.isEmpty();
    }

    public DirectoryNode getRoot() {
      return this.parent == null ? this : this.parent.getRoot();
    }

    public void merge(final DirectoryNode that) {
      if (!this.value.equals(that.value)) {
        return;
      } else if (this.children.isEmpty()) {
        this.children.addAll(that.children);
        return;
      }

      final DirectoryNode[] thisChildren = this.children
        .toArray(new DirectoryNode[this.children.size()]);
      for (final DirectoryNode thisChild : thisChildren) {
        for (final DirectoryNode thatChild : that.children) {
          if (thisChild.value.equals(thatChild.value)) {
            thisChild.merge(thatChild);
          } else if (!this.children.contains(thatChild)) {
            this.children.add(thatChild);
          }
        }
      }
    }

    @Override
    public boolean equals(final Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      final DirectoryNode that = (DirectoryNode) o;
      return Objects.equals(this.value, that.value)
        && Objects.equals(this.parent, that.parent);
    }

    @Override
    public int hashCode() {
      return Objects.hash(this.value, this.parent);
    }


    @Override
    public String toString() {
      return "{" +
        "value='" + this.value + '\'' +
        ", children=" + this.children +
        '}';
    }
  }
}
