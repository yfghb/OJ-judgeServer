package com.judgeServerApp.cmd;


/**
 * @author yang
 */
public class JavaCmd {

    public static String linuxCompile(String filename){
        return "javac " + DirPath.LINUX_PATH + filename + ".java";
    }

    public static String windowsCompile(String filename){
        return "javac " + DirPath.WINDOWS_PATH + filename + ".java";
    }

    public static String linuxRunning(String filename){
        return "cmd /c set CLASSPATH=" + DirPath.LINUX_PATH + " && java " + filename;
    }

    public static String windowsRunning(String filename){
        return "cmd /c set CLASSPATH=" + DirPath.WINDOWS_PATH + " && java " + filename;
    }

}
