package com.example.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExecuteShell {

    private Runtime runtime;

    public void runEcho(String msg) throws IOException, InterruptedException {
        runtime = Runtime.getRuntime();
        String[] cmd = new String[]{"cmd.exe", "/c", "echo", msg};
        Process process = runtime.exec(cmd);
        process.waitFor();

        readBufferMsg(process);
    }

    public void runScript(String path) throws IOException, InterruptedException {
        runtime = Runtime.getRuntime();
//        String[] script = new String[]{"cmd.exe", "/c", path};
        Process process = new ProcessBuilder(path).start();
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
