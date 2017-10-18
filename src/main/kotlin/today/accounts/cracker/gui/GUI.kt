package today.accounts.cracker.gui

import javafx.application.Application
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.control.Alert
import javafx.scene.control.Button
import javafx.scene.image.Image
import javafx.scene.layout.AnchorPane
import javafx.stage.Stage
import jp.uphy.javafx.console.ConsoleView
import today.accounts.cracker.gui.options.CombosOption
import today.accounts.cracker.gui.options.ProxyOption
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

/**
 * Created for Accounts Today. All rights reserved/retained
 * @author Jp78 (jp78.me)
 * @since Tuesday, October 2017
 */
class GUI : Application()
{
    val version = 0;
    var running: Process? = null;
    @FXML
    lateinit var start: Button;
    val options = listOf(
            CombosOption(),
            ProxyOption()
    )

    override fun start(primaryStage: Stage)
    {
        val loader = FXMLLoader(Main::class.java.classLoader.getResource("gui.fxml"));
        loader.setController(this)
        primaryStage.title = "Accounts Today Cracker GUI | By: Jp78 | Version: $version";
        primaryStage.icons.add(Image("https://avatars0.githubusercontent.com/u/31424010?s=460&v=4"))
        primaryStage.isResizable = false;
        val root: AnchorPane = loader.load()
        val scene = Scene(root, root.prefWidth, root.prefHeight);
        primaryStage.scene = scene;
        primaryStage.onCloseRequest = EventHandler {
            running?.destroy()
            System.exit(0)
        }
        options.forEach { it.init(root.children) }
    }

    fun start()
    {
        if (start.text == "Stop")
        {
            running?.destroy();
        }
        else
        {
            //Start up sequence.
            val args = LinkedList<String>()

            for (o in options)
            {
                if (o.required() && !o.isPresent())
                {
                    val alert = Alert(Alert.AlertType.ERROR);
                    alert.title = "AT Crack GUI"
                    alert.headerText = "Option Error!"
                    alert.contentText = o.error();
                    alert.showAndWait();
                    return;
                }
                if (o.isPresent()) args.add(o.line())
            }
            val alert = Alert(Alert.AlertType.CONFIRMATION)
            alert.title = "AT Crack GUI"
            alert.headerText = "Started!"
            alert.contentText = "Program started!"
            alert.show();
            this.running = ProgramStarter.start(args.toArray() as Array<String>)
        }
    }

    fun startConsoleOutput()
    {
        val console = ConsoleView();
        val thread = Thread {
            try
            {
                while (true)
                {
                    val reader = BufferedReader(InputStreamReader(running?.inputStream))

                    var line: String? = null;

                    while (line != null)
                    {
                        console.out.print(line + "\n")
                        line = reader.readLine();
                    }
                }
            }
            catch (e: Exception)
            {
                e.printStackTrace()
            }

        }
    }
}