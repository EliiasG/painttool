package me.eliasg.painttool;

import javafx.beans.property.DoubleProperty;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.*;
import me.eliasg.painttool.popups.ConfirmWindow;
import me.eliasg.painttool.sceneitems.SceneItem;
import me.eliasg.painttool.sceneitems.SceneObject;
import me.eliasg.painttool.sceneitems.SceneRoot;

public class PaintToolScene
{
    private final TreeItem<SceneItem> root;
    private final TreeView<SceneItem> treeView;
    private final Pane inspector;
    private final ScrollPane scrollPane;
    private final ProgramState programState;

    public PaintToolScene(ProgramState programState)
    {
        //set up the tree view
        this.programState = programState;
        root = new TreeItem<>(new SceneRoot());
        root.setExpanded(true);
        treeView = new TreeView<>(root);
        //set up the inspector
        scrollPane = new ScrollPane();
        scrollPane.setMaxWidth(300);
        scrollPane.setMinWidth(300);
        inspector = new Pane();
        scrollPane.minViewportHeightProperty().bind(inspector.heightProperty().subtract(45));
        scrollPane.maxHeightProperty().bind(scrollPane.minViewportHeightProperty());
        inspector.getChildren().add(scrollPane);

        //when a node is selected, update the inspector
        treeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
        {
            programState.setSelectedItem(newValue);
            if(programState.getSelectedItem() == null)
            {
                programState.setSelectedItem(root);
            }
            scrollPane.setContent(programState.getSelectedItem().getValue().getInspector());
        });

    }

    public void addObject(SceneObject object)
    {
        if(programState.getSelectedItem() == null) programState.setSelectedItem(root);
        programState.getSelectedItem().setExpanded(true);
        programState.getSelectedItem().getChildren().add(new TreeItem<>(object));
    }

    public void deleteSelected()
    {
        if(programState.getSelectedItem() != null && programState.getSelectedItem() != root)
        {
            ConfirmWindow confirmWindow = new ConfirmWindow("Delete Object", "Are you sure you want to delete: \"" + programState.getSelectedItem().getValue().toString() + "\"?");
            if(confirmWindow.isConfirmed()) programState.getSelectedItem().getParent().getChildren().remove(programState.getSelectedItem());
        }
    }

    public TreeView<SceneItem> getTreeView()
    {
        return treeView;
    }

    public Pane getInspector()
    {
        return inspector;
    }

    public TreeItem<SceneItem> getRoot()
    {
        return root;
    }
}
