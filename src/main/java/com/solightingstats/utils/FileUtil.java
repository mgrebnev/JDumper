package com.solightingstats.utils;

import com.solightingstats.Main;

import java.io.File;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtil {
    public static List<String> getAllLines(File file){
        try {
            return Files.readAllLines(file.toPath(), Charset.defaultCharset());
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }
    
    public static String getText(File file){
        try {
            return Files.readAllLines(file.toPath(), Charset.defaultCharset())
                        .stream()
                        .collect(Collectors.joining());
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }
    
    public static String getCurrentPath(){
        try {
            return Paths.get(Main.class
                                 .getProtectionDomain()
                                 .getCodeSource()
                                 .getLocation()
                                 .toURI())
                        .getParent()
                        .toString();
        }catch (Exception ex){
            return "";
        }
    }
}
