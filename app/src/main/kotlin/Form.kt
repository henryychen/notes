import javafx.geometry.Insets
import javafx.scene.control.*
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.scene.text.Text


class Form: GridPane() {
    // Variables
    var title = TextField()
    var body = TextArea()
    var importantBox = CheckBox()
    var saveBtn = Button("Save")
    var cancelBtn = Button("Cancel")
    var formName = Text("Add/Edit Form")

    init {
        maxHeight = 300.0
        maxWidth = 500.0
        background = Background(BackgroundFill(Color.LIGHTGRAY, null, null))
        title.setPrefSize(420.0, 20.0)
        body.setPrefSize(420.0, 150.0)
        body.isWrapText = true
        padding = Insets(10.0, 10.0, 10.0, 10.0)
        hgap = 10.0
        vgap = 10.0

        var titleRow = HBox(Label("Title     "))
        titleRow.children.add(title)
        var bodyRow = HBox(Label("Body    "))
        bodyRow.children.add(body)
        var importantRow = HBox()
        importantRow.children.add(Label("            "))
        importantRow.children.add(importantBox)
        importantRow.children.add(Label("   Important   "))
        saveBtn.setPrefSize(80.0, 10.0)
        cancelBtn.setPrefSize(80.0, 10.0)
        var buttonRow = HBox(Label("                                        " +
                "                                                "))
        buttonRow.children.add(saveBtn)
        buttonRow.children.add(Label("   "))
        buttonRow.children.add(cancelBtn)

        add(formName, 0, 0)
        add(titleRow, 0, 1)
        add(bodyRow, 0, 2)
        add(importantRow, 0, 3)
        add(buttonRow, 0, 4)
    }
}