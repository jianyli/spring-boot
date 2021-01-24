package com.li.support.util;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Objects;

/**
 * @author lijianyou
 * @date 2020/10/19 20:06
 * @description
 */
public class XWPFUtil {

    /**
     * word转PDF 格式
     */
    public static final int wdFormatPDF = 17;

    private static Logger logger = LoggerFactory.getLogger(XWPFUtil.class);
    public static void wordToPdf(String source, String target) {
        ComThread.InitSTA();
        ActiveXComponent app = null;
        try {
            app = new ActiveXComponent("Word.Application");
            app.setProperty("Visible", false);
            Dispatch documents = app.getProperty("Documents").toDispatch();
            Dispatch doc = Dispatch.call(documents,"Open", target, false, true).toDispatch();
            File file = new File(target);
            if (file.exists()) {
                file.delete();
            }
            Dispatch.call(doc,"SaveAs",target, wdFormatPDF);
            Dispatch.call(doc,"Close",false);
        } catch (Exception e) {
            logger.info("word转pdf出错", e);
        } finally {
            if (!Objects.isNull(app)) {
                app.invoke("Quit",0);
            }
        }
        ComThread.Release();
    }

    public static void mergePdf(File outFile, List<String> pdfPath) throws Exception {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(outFile));
        Rectangle rect = new Rectangle(36, 54, 559, 788);
        rect.setBorderColor(BaseColor.BLACK);
        rect = PageSize.A4;
        writer.setBoxSize("art", rect);
        document.open();
        PdfContentByte cb = writer.getDirectContent();
        for (String s : pdfPath) {
            PdfReader reader = new PdfReader(s);
            Integer pageNumber = reader.getNumberOfPages();
            for (int i = 1; i <= pageNumber; i++) {
                document.newPage();
                document.setPageSize(PageSize.A4);
                PdfImportedPage page = writer.getImportedPage(reader, i);
                cb.addTemplate(page,0,0);
            }
        }
        document.close();
    }
}
