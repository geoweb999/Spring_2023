import java.util.*;

public class Folder {

	private List<FileItem> fileList;
	private List<Folder> subFolders;
	private String name;
	
	public Folder(String name) {
		this.name = name;
		this.fileList = new ArrayList<FileItem>();
		this.subFolders = new ArrayList<Folder>();
	}
	

	public String getName() {
		return name;
	}
	
	public List<FileItem> getFileList() {
		return fileList;
	}
	public List<Folder> getSubFolders() {
		return subFolders;
	}

	public boolean addFile(FileItem file) {
		return fileList.add(file);
	}
	
	public boolean addSubFolder(Folder folder) {
		return subFolders.add(folder);
	}

	@Override
	public String toString() {
		return name;
	}
}
