import javafx.scene.control.Label
import javafx.scene.layout.StackPane

class StatusBar: StackPane() {
    var label = Label("0")
    init {
        children.add(label)
        style = ("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                + "-fx-border-width: 1;" + "-fx-border-color: #c4c4c4;")
    }

    fun setMessage(s1: String, s2: String) {
        label.text = "$s1           $s2"
    }
}