import javafx.application.Application
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.input.KeyCode
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.stage.Stage


class Main : Application() {

    override fun start(stage: Stage) {
        // Status variables
        var importantButtonOn = false
        var searchText = ""

        // Variables for displaying status message
        var counter = 0
        var previousMessage1 = ""
        var previousMessage2 = ""

        // More variables
        var statusBar = StatusBar()
        val toolBar = HBox()
        var selectedNote = Notes()

        // Lists to hold notes
        val listOfNotes = mutableListOf<Notes>()
        val listOfImportantNotes = mutableListOf<Notes>()

        // Layout for app in order of hierarchy (top to bottom)
        val stackPane = StackPane()
        val borderPane = BorderPane()
        val scrollPane = ScrollPane()
        val noteContainer = TilePane()

        stackPane.children.add(borderPane)
        scrollPane.isFitToWidth = true
        scrollPane.content = noteContainer
        noteContainer.style = ("-fx-padding: 10;")
        noteContainer.hgap = 10.0
        noteContainer.vgap = 10.0

        // Delete button functionality
        val deleteButton = Button("Delete")
        deleteButton.setPrefSize(100.0, 30.0)
        deleteButton.setMinSize(45.0, 30.0)
        deleteButton.isDisable = true
        deleteButton.setOnAction {
            listOfNotes.remove(selectedNote)
            if (selectedNote.important) {
                listOfImportantNotes.remove(selectedNote)
            }
            noteContainer.children.remove(selectedNote)
            statusBar.setMessage(listOfNotes.size.toString(), "Deleted Note #${selectedNote.id}")
            previousMessage2 = "Deleted Note #${selectedNote.id}"
            deleteButton.isDisable = true
        }

        // Clear button functionality
        val clearButton = Button("Clear")
        clearButton.setPrefSize(100.0, 30.0)
        clearButton.setMinSize(45.0, 30.0)
        clearButton.isDisable = true
        clearButton.setOnAction {
            var tmpListOfNotes = mutableListOf<Notes>()
            if (importantButtonOn && searchText != "") {
                for (note in listOfNotes) {
                    if (note.important && note.findText(searchText)) {
                        noteContainer.children.remove(note)
                        listOfImportantNotes.remove(note)
                        tmpListOfNotes.add(note)
                    }
                }
                for (note in tmpListOfNotes) {
                    listOfNotes.remove(note)
                }
            } else if (importantButtonOn) {
                for (note in listOfNotes) {
                    if (note.important) {
                        noteContainer.children.remove(note)
                        listOfImportantNotes.remove(note)
                        tmpListOfNotes.add(note)
                    }
                }
                for (note in tmpListOfNotes) {
                    listOfNotes.remove(note)
                }
            } else if (searchText != "") {
                for (note in listOfNotes) {
                    if (note.findText(searchText)) {
                        noteContainer.children.remove(note)
                        if (note.important) {
                            listOfImportantNotes.remove(note)
                        }
                        tmpListOfNotes.add(note)
                    }
                }
                for (note in tmpListOfNotes) {
                    listOfNotes.remove(note)
                }
            } else {
                for (note in listOfNotes) {
                    noteContainer.children.remove(note)
                }
                listOfNotes.clear()
                listOfImportantNotes.clear()
            }
            //clearButton.isDisable = true
            statusBar.setMessage("0", "Cleared ${tmpListOfNotes.size} Notes")
            previousMessage1 = "0"
            previousMessage2 = "Cleared ${tmpListOfNotes.size} Notes"
            tmpListOfNotes.clear()
        }

        // Random button functionality
        val randomButton = Button("Random")
        randomButton.setPrefSize(100.0, 30.0)
        randomButton.setMinSize(45.0, 30.0)
        randomButton.setOnAction {
            var note = Notes()
            note.id = counter
            listOfNotes.add(note)
            noteContainer.children.add(note)
            if (note.important) {
                listOfImportantNotes.add(note)
            }

            note.setOnMouseClicked { mouseEvent ->
                if (mouseEvent.clickCount == 2) {
                    var editForm = Form()
                    editForm.formName.text = "Edit Note $counter"
                    editForm.title.text = note.title.text
                    editForm.body.text = note.body.text
                    stackPane.children.add(editForm)
                    borderPane.isDisable = true
                    if (note.important) {
                        editForm.importantBox.isSelected = true
                    }
                    editForm.cancelBtn.setOnAction {
                        stackPane.children.remove(editForm)
                        borderPane.isDisable = false
                    }
                    editForm.saveBtn.setOnAction {
                        var title = editForm.title.text
                        var body = editForm.body.text
                        note.setTitleBody(title, body)
                        if (editForm.importantBox.isSelected) {
                            note.important = true
                            note.background = (Background(BackgroundFill(Color.LIGHTYELLOW, CornerRadii.EMPTY, Insets.EMPTY)))
                            if (!listOfImportantNotes.contains(note)) {
                                listOfImportantNotes.add(note)
                            }
                        } else {
                            note.important = false
                            note.background = (Background(BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)))
                            if (listOfImportantNotes.contains(note)) {
                                listOfImportantNotes.remove(note)
                            }
                        }
                        stackPane.children.remove(editForm)
                        borderPane.isDisable = false
                    }
                }

                if (note.selected) {
                    note.selected = false
                    note.isSelected(false)
                    selectedNote = Notes()
                    deleteButton.isDisable = true
                    statusBar.setMessage(previousMessage1, previousMessage2)
                } else {
                    // deselect all other notes first
                    for (note in listOfNotes) {
                        note.selected = false
                        note.isSelected(false)
                    }
                    note.selected = true
                    note.isSelected(true)
                    selectedNote = note
                    deleteButton.isDisable = false
                    statusBar.setMessage("#${note.id}  |  ${listOfNotes.size}", previousMessage2)
                }
            }

            clearButton.isDisable = false
            statusBar.setMessage(listOfNotes.size.toString(), "Added Note #${note.id}")
            previousMessage1 = listOfNotes.size.toString()
            previousMessage2 = "Added Note #${note.id}"
            counter++
        }

        // Add button functionality
        val addButton = Button("Add")
        addButton.setPrefSize(100.0, 30.0)
        addButton.setMinSize(45.0, 30.0)
        addButton.setOnAction {
            var addForm = Form()
            addForm.formName.text = "Add New Note"
            borderPane.isDisable = true
            stackPane.children.add(addForm)
            addForm.cancelBtn.setOnAction {
                stackPane.children.remove(addForm)
                borderPane.isDisable = false
            }
            addForm.saveBtn.setOnAction {
                var title = addForm.title.text
                var body = addForm.body.text
                var newNote = Notes()

                newNote.setOnMouseClicked { mouseEvent ->
                    if (mouseEvent.clickCount == 2) {
                        var editForm = Form()
                        editForm.formName.text = "Edit Note $counter"
                        editForm.title.text = newNote.title.text
                        editForm.body.text = newNote.body.text
                        stackPane.children.add(editForm)
                        borderPane.isDisable = true
                        if (newNote.important) {
                            editForm.importantBox.isSelected = true
                        }
                        editForm.cancelBtn.setOnAction {
                            stackPane.children.remove(editForm)
                            borderPane.isDisable = false
                        }
                        editForm.saveBtn.setOnAction {
                            var title = editForm.title.text
                            var body = editForm.body.text
                            newNote.setTitleBody(title, body)
                            if (editForm.importantBox.isSelected) {
                                newNote.important = true
                                newNote.background =
                                    (Background(BackgroundFill(Color.LIGHTYELLOW, CornerRadii.EMPTY, Insets.EMPTY)))
                                if (!listOfImportantNotes.contains(newNote)) {
                                    listOfImportantNotes.add(newNote)
                                }
                            } else {
                                newNote.important = false
                                newNote.background =
                                    (Background(BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)))
                                if (listOfImportantNotes.contains(newNote)) {
                                    listOfImportantNotes.remove(newNote)
                                }
                            }
                            stackPane.children.remove(editForm)
                            borderPane.isDisable = false
                        }
                    }
                    if (newNote.selected) {
                        newNote.selected = false
                        newNote.isSelected(false)
                        selectedNote = Notes()
                        deleteButton.isDisable = true
                        statusBar.setMessage(previousMessage1, previousMessage2)
                    } else {
                        // deselect all other notes first
                        for (note in listOfNotes) {
                            note.selected = false
                            note.isSelected(false)
                        }
                        newNote.selected = true
                        newNote.isSelected(true)
                        selectedNote = newNote
                        deleteButton.isDisable = false
                        statusBar.setMessage("#${newNote.id}  |  ${listOfNotes.size}", previousMessage2)
                    }
                }

                newNote.setTitleBody(title, body)
                if (addForm.importantBox.isSelected) {
                    newNote.important = true
                    newNote.background = (Background(BackgroundFill(Color.LIGHTYELLOW, CornerRadii.EMPTY, Insets.EMPTY)))
                    listOfImportantNotes.add(newNote)
                } else {
                    newNote.important = false
                    newNote.background = (Background(BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)))
                }
                listOfNotes.add(newNote)
                noteContainer.children.add(newNote)
                newNote.id = counter
                statusBar.setMessage(listOfNotes.size.toString(), "Added Note #${newNote.id}")
                previousMessage1 = listOfNotes.size.toString()
                previousMessage2 = "Added Note #${newNote.id}"
                counter++
                stackPane.children.remove(addForm)
                borderPane.isDisable = false
            }
            clearButton.isDisable = false
        }

        // Important button functionality
        val importantButton = ToggleButton("!")
        importantButton.setPrefSize(40.0, 30.0)
        importantButton.setMinSize(20.0, 30.0)
        importantButton.setOnAction {
            if (importantButton.isSelected) {
                for (note in listOfNotes) {
                    noteContainer.children.remove(note)
                }
                for (note in listOfImportantNotes) {
                    if (searchText == "") {
                        noteContainer.children.add(note)
                    } else if (note.findText(searchText)) {
                        noteContainer.children.add(note)
                    }
                }
                importantButtonOn = true
                statusBar.setMessage("${listOfImportantNotes.size} (of ${listOfNotes.size})", previousMessage2)
                previousMessage1 = "${listOfImportantNotes.size} (of ${listOfNotes.size})"
            } else {
                for (note in listOfImportantNotes) {
                    noteContainer.children.remove(note)
                }
                for (note in listOfNotes) {
                    if (searchText == "") {
                        noteContainer.children.add(note)
                    } else if (note.findText(searchText)) {
                        noteContainer.children.add(note)
                    }
                }
                importantButtonOn = false
                statusBar.setMessage(listOfNotes.size.toString(), previousMessage2)
                previousMessage1 = listOfNotes.size.toString()
            }
            if (searchText == "" && !importantButtonOn) {
                addButton.isDisable = false
                randomButton.isDisable = false
            } else {
                addButton.isDisable = true
                randomButton.isDisable = true
            }
        }

        // Searchbar functionality
        val searchBar = TextField()
        searchBar.setPrefSize(200.0, 30.0)
        searchBar.setMinSize(100.0, 30.0)
        searchText = ""
        searchBar.onKeyPressed = EventHandler { event ->
            if (searchBar.text.isEmpty()) {
                searchText = ""
            } else if (event.code == KeyCode.BACK_SPACE) {
                if (searchText.isNotEmpty()) {
                    searchText = searchText.substring(0,searchText.length-1)
                }
            } else {
                searchText += event.text
            }
            if (importantButtonOn) {
                for (note in listOfImportantNotes) {
                    noteContainer.children.remove(note)
                    if (note.findText(searchText)) {
                        noteContainer.children.add(note)
                    }
                }
            } else {
                for (note in listOfNotes) {
                    noteContainer.children.remove(note)
                    if (note.findText(searchText)) {
                        noteContainer.children.add(note)
                    }
                }
            }
            if (searchText == "" && !importantButtonOn) {
                addButton.isDisable = false
                randomButton.isDisable = false
            } else {
                addButton.isDisable = true
                randomButton.isDisable = true
            }
            statusBar.setMessage("${noteContainer.children.size} of (${listOfNotes.size})", previousMessage2)
        }

        // Set the scene and stage
        toolBar.style = ("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                + "-fx-border-width: 1;" + "-fx-border-color: #c4c4c4;")
        toolBar.spacing = 10.0
        toolBar.children.add(addButton)
        toolBar.children.add(randomButton)
        toolBar.children.add(deleteButton)
        toolBar.children.add(clearButton)
        toolBar.children.add(importantButton)
        toolBar.children.add(searchBar)

        // Borderpane
        borderPane.top = toolBar
        borderPane.center = scrollPane
        borderPane.bottom = statusBar

        // Create scene
        val scene = Scene(stackPane, 800.0, 600.0)
        stage.title = "A1 Notes hy5chen"
        stage.scene = scene
        stage.minHeight = 400.0
        stage.minWidth = 400.0
        stage.show()
    }
}