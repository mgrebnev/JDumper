package com.solightingstats.utils;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
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
}
