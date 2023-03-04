package com.judgeServerApp.cmd;


/**
 * @author yang
 */
public class JavaCmd {

    public static String[] linuxCompile(String filename){
        String cmd = "javac " + DirPath.LINUX_PATH + filename + ".java";
        return new String[]{"/bin/sh","-c",cmd};
    }

    public static String[] windowsCompile(String filename){
        String cmd = "javac " + DirPath.WINDOWS_PATH + filename + ".java";
        return new String[]{"cmd","/c",cmd};
    }

    public static String[] linuxRunning(String filename){
        String cmd = "cd " + DirPath.LINUX_PATH + "&& java " + filename;
        return new String[]{"/bin/sh","-c",cmd};
    }

    public static String[] windowsRunning(String filename){
        String cmd = "set CLASSPATH=" + DirPath.WINDOWS_PATH + " && java " + filename;
        return new String[]{"cmd","/c",cmd};
    }

}
