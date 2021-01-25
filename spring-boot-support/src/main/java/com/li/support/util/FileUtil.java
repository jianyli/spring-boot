package com.li.support.util;

import java.io.File;
import java.io.IOException;

/**
 * @author lijianyou
 * @date 2020/10/20 10:44
 * @description
 */
public class FileUtil {

    public static File createTempFile(String prefix, String suffix) throws IOException {
        File tempFile = File.createTempFile(prefix, suffix);
        return tempFile;
    }
}
