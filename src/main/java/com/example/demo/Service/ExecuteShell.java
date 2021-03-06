package com.example.demo.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExecuteShell {

    private Runtime runtime;

    public void runEcho(String msg) throws IOException, InterruptedException {
        runtime = Runtime.getRuntime();
        String[] cmd;
        if(System.getProperty("os.name").toLowerCase().contains("windows"))
            cmd = new String[]{"cmd.exe", "/c", "echo", msg};
        else
            cmd = new String[]{"echo", msg};
        /* windows */
//        String[] cmd = new String[]{"cmd.exe", "/c", "echo", msg};
        /* linux */
//        String[] cmd = new String[]{"echo", msg};
        Process process = runtime.exec(cmd);
        process.waitFor();

        readBufferMsg(process);
    }

    public void runScript(String path) throws IOException, InterruptedException {
        runtime = Runtime.getRuntime();
//        String[] script = new String[]{"cmd.exe", "/c", path};
        /* windows */
//        Process process = new ProcessBuilder(path).start();
        /* linux */
        Process process = new ProcessBuilder("bash", "-f", new File(path).getAbsolutePath()).start();
        process.waitFor();

        readBufferMsg(process);
    }


    public void readBufferMsg(Process process) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String output = "";
        while((output = reader.readLine()) != null){
            System.out.println(output);
        }
    }
}
