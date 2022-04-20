package me.eliasg.painttool;

import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.Pane;
import me.eliasg.painttool.sceneitems.SceneItem;

public class ProgramState
{
    private TreeItem<SceneItem> selectedItem = null;
    private Geometry editingGeometry = null;
    private Scene scene = null;

    public TreeItem<SceneItem> getSelectedItem()
    {
        return selectedItem;
    }

    public void setSelectedItem(TreeItem<SceneItem> selectedItem)
    {
        if(selectedItem != this.selectedItem) editingGeometry = null;
        this.selectedItem = selectedItem;
    }

    public void setScene(Scene scene)
    {
        this.scene = scene;
    }

    public Scene getScene()
    {
        return scene;
    }

    public Geometry getEditingGeometry()
    {
        return editingGeometry;
    }

    public void setEditingGeometry(Geometry editingGeometry)
    {
        this.editingGeometry = editingGeometry;
    }
}
