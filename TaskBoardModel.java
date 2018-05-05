import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * TaskBoardModel is a collection of ProjectModels.
 * It contains ProjectModels and the file in which it is saved.
 *
 * Created by Lok Man Chu
 */
public class TaskBoardModel implements Serializable {

    private String name;
    private ArrayList<ProjectModel> projects;
    private File file;

    /**
     * Constructor of TaskBoardModel
     * Assigns default values to all fields
     */
    public TaskBoardModel()
    {
        name = "TaskBoard1";
        projects = new ArrayList<ProjectModel>();
        file = null;
    }

    /**
     * Set method for TaskBoardModel name
     * @param name new String name for TaskBoardModel
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get method for TaskBoardModel name
     * @return String name of TaskBoardModel
     */
    public String getName()
    {
        return name;
    }

    /**
     * Adds a ProjectModel to TaskBoardModel
     * @param projectModel ProjectModel to be added
     */
    public void addProjects(ProjectModel projectModel)
    {
        projects.add(projectModel);
    }

    /**
     * Deleted ProjectModel from TaskBoardModel
     * @param projectModel ProjectModel to delete
     */
    public void deleteProjects(ProjectModel projectModel)
    {
        projects.remove(projectModel);
    }

    /**
     * Returns the number of ProjectModels within TaskBoardModel
     * @return number of Projects
     */
    public int numProjects()
    {
        return projects.size();
    }

    /**
     * Returns the project at given index
     * @param index index of ProjectModel
     * @return ProjectModel at index
     */
    public ProjectModel getProject(int index)
    {
        return projects.get(index);
    }

    /**
     * Returns the File which TaskBoardModel is saved.
     * @return save File of TaskBoardModel
     */
    public File getFile() {
        return file;
    }

    /**
     * Sets the File in which TaskBoardModel is saved
     * @param file SaveFile
     */
    public void setFile(File file) {
        this.file = file;
    }
}
