/*
 * Copyright 2015 Nokia Solutions and Networks
 * Licensed under the Apache License, Version 2.0,
 * see licence.txt file for details.
 */

package common;


import java.io.*;

public class FileOperator {
    FileWriter fstream;
    BufferedWriter out;

    public void writeFile(String fileName, String content) {
        try {
            fstream = new FileWriter(fileName);
            out = new BufferedWriter(fstream);
            out.write(content);
            out.close();
            fstream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readFile(String fileName) {
        String fileContent = "";
        try {
            BufferedReader fileIn = new BufferedReader(new FileReader(fileName));
            String line = "";
            while ((line = fileIn.readLine()) != null) {
                fileContent += line;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContent;
    }
}
