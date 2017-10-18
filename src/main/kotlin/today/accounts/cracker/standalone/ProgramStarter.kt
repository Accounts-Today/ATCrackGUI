package today.accounts.cracker.standalone

import java.io.File

/**
 * Created for Accounts Today. All rights reserved/retained
 * @author Jp78 (jp78.me)
 * @since Tuesday, October 2017
 */
object ProgramStarter
{
    val PATH = "Cracker.jar"

    fun start(args : Array<String>): Process
    {
        val start = StringBuilder("\"${File(ProgramStarter.javaClass.protectionDomain.codeSource.location.toURI()).parentFile.absolutePath}" + File.separator + PATH + "\"");
        for (arg in args) start.append(" $arg")
        println("Start args: java -jar ${start.toString()}");
        return Runtime.getRuntime().exec("java -jar " + start.toString())
    }
}