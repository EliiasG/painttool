module me.eliasg.painttool {
    requires javafx.controls;
    requires javafx.fxml;


    opens me.eliasg.painttool to javafx.fxml;
    exports me.eliasg.painttool;
    exports me.eliasg.painttool.popups;
    opens me.eliasg.painttool.popups to javafx.fxml;
    exports me.eliasg.painttool.inspectorpropperties;
    opens me.eliasg.painttool.inspectorpropperties to javafx.fxml;
    exports me.eliasg.painttool.sceneitems;
    opens me.eliasg.painttool.sceneitems to javafx.fxml;
}