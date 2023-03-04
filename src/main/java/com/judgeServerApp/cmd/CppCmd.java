package com.judgeServerApp.cmd;

/**
 * @author yang
 */
public class CppCmd {
    public static String[] windowsCompileC(String filename){
        String cmd = "cd " + DirPath.WINDOWS_PATH + " && g++ " + filename + ".c" + " -o " + filename;
        return new String[]{"cmd","/c",cmd};
    }
    public static String[] linuxCompileC(String filename){
        String cmd = "/usr/bin/g++ " + DirPath.LINUX_PATH + filename + ".c" + " -o " + DirPath.LINUX_PATH + filename + ".out";
        return new String[]{"/bin/sh","-c",cmd};
    }
    public static String[] windowsCompileCpp(String filename){
        String cmd = "cd " + DirPath.WINDOWS_PATH + " && g++ " + filename + ".cpp" + " -o " + filename;
        return new String[]{"cmd","/c",cmd};
    }
    public static String[] linuxCompileCpp(String filename){
        String cmd = "/usr/bin/g++ " + DirPath.LINUX_PATH + filename + ".cpp" + " -o " + DirPath.LINUX_PATH + filename + ".out";
        return new String[]{"/bin/sh","-c",cmd};
    }
    public static String[] windowsRunning(String filename){
        String cmd = "cd " + DirPath.WINDOWS_PATH + " && "+ filename + ".exe";
        return new String[]{"cmd","/c",cmd};
    }
    public static String[] linuxRunning(String filename){
        String cmd = DirPath.LINUX_PATH + filename + ".out";
        return new String[]{"/bin/sh","-c",cmd};
    }


}
