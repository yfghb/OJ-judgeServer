package com.judgeServerApp.cmd;

/**
 * @author yang
 */
public class PythonCmd {
    public static String[] windowsRunning(String filename){
        String cmd = "cd " + DirPath.WINDOWS_PATH + " && python " + filename + ".py";
        return new String[]{"cmd","/c",cmd};
    }
    public static String[] linuxRunning(String filename){
        String cmd = "cd " + DirPath.LINUX_PATH + " && python " + filename + ".py";
        return new String[]{"/bin/sh","-c",cmd};
    }
}
