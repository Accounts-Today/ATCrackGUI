package today.accounts.cracker.standalone.util

import javafx.application.Platform
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.control.ListView
import javafx.scene.layout.AnchorPane
import javafx.stage.Stage

/**
 * Created for Accounts Today. All rights reserved/retained
 * @author Jp78 (jp78.me)
 * @since Sunday, December 2017
 */
object Console
{
    lateinit var  listView : ListView<String>

    fun init()
    {
        Platform.runLater {
            val root = FXMLLoader.load(Console::class.java.classLoader.getResource("output.fxml")) as AnchorPane
             listView = root.children.first() as ListView<String>

            val stage = Stage()
            stage.isResizable = false;
            stage.title = "ATCrack console"
            stage.scene = Scene(root, root.prefWidth, root.prefHeight)
            stage.show();
        }
    }
    fun write(string: String)
    {
        Platform.runLater {
            listView.items.add(string);
        }
    }
}