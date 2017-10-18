package today.accounts.cracker.gui

import java.io.File

/**
 * Created for Accounts Today. All rights reserved/retained
 * @author Jp78 (jp78.me)
 * @since Tuesday, October 2017
 */
object ProgramStarter
{
    val PATH = "Cracker.jar"
    /**
     * Start the cracker with the provided options: **WARNING: DOES NO VALIDATIONS**
     * @return The process this started
     */
    fun start(options : Array<String>) : Process
    {
        val process = ProcessBuilder(PATH,*options).directory(File(javaClass.protectionDomain.codeSource.location.toURI().path)).redirectErrorStream(true).start();
        return process;
    }
}