package com.li;

import com.itextpdf.text.*;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.*;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Objects;

/**
 * @author lijianyou
 * @date 2020/6/15 20:07
 * @description
 */
public class Test {
    private static Font titleFont;
    public static void main(String[] args) throws Exception {
        ComThread.InitSTA();
        ActiveXComponent word = null;
        try {
            word = new ActiveXComponent("Word.Application");
            word.setProperty("Visible", true);
            Dispatch documents = word.getProperty("Documents").getDispatch();
            Dispatch doc = Dispatch.call(documents,"Open","test/s.docx").toDispatch();
            Dispatch.call(doc,"SaveAs","test/test.pdf",17);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("异常");
        } finally {
            if (!Objects.isNull(word)) {
                word.invoke("Quit");
            }
        }
        ComThread.Release();
    }

}
