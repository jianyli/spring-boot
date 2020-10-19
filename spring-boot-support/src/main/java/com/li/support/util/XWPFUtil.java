package com.li.support.util;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
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
}
