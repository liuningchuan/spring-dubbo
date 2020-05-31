package com.liuning.utils;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Utils - html转换成PDF
 * 只需要引入依赖:
 * <dependency>
 * <groupId>org.xhtmlrenderer</groupId>
 * <artifactId>flying-saucer-pdf-itext5</artifactId>
 * <version>9.0.3</version>
 * </dependency>
 *
 * @author liuning
 * @date 2020-05-30 23:03
 */
public class HtmlToPdfUtils {

    private static final Logger log = LoggerFactory.getLogger(HtmlToPdfUtils.class);

    private HtmlToPdfUtils() {
    }

    /**
     * html文本转换成PDF
     *
     * @param text    html字符串
     * @param pdfPath pdf生成路径
     */
    public static void textToPdf(String text, String pdfPath) {

        try {
            OutputStream outputStream = new FileOutputStream(pdfPath);

            ITextRenderer iTextRenderer = new ITextRenderer();
            iTextRenderer.setDocumentFromString(text);

            //解决中文字体，需要单独下载字体
            //同时在前端样式中加入font-family:SimSun;
            ITextFontResolver fontResolver = iTextRenderer.getFontResolver();
            fontResolver.addFont(ClassLoader.getSystemClassLoader().getResource("font/simsun.ttc").getPath(),
                    BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);

            iTextRenderer.layout();
            iTextRenderer.createPDF(outputStream);

            outputStream.flush();
            outputStream.close();
        } catch (DocumentException | IOException e) {
            log.warn("PDF转换异常：" + e);
        }
    }

    /**
     * html文件转换成pdf
     *
     * @param htmlFile html文件
     * @param pdfPath  pdf生成路径
     */
    private static void htmlToPdf(File htmlFile, String pdfPath) {
        try {
            OutputStream outputStream = new FileOutputStream(pdfPath);

            ITextRenderer iTextRenderer = new ITextRenderer();
            iTextRenderer.setDocument(htmlFile);
            iTextRenderer.layout();
            iTextRenderer.createPDF(outputStream);

            outputStream.flush();
            outputStream.close();
        } catch (IOException | DocumentException e) {
            log.warn("PDF转换异常：" + e);
        }
    }

    public static void main(String[] args) {
        File file = new File(ClassLoader.getSystemClassLoader().getResource("templates/index.ftl").getPath());
        String pdfPath1 = System.getProperty("user.dir") + "/test1.pdf";
        String pdfPath2 = System.getProperty("user.dir") + "/test2.pdf";

        HtmlToPdfUtils.htmlToPdf(file, pdfPath1);

        HtmlToPdfUtils.textToPdf("<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "<head>" +
                "    <meta charset=\"utf-8\"/>" +
                "    <title>FreeMarker</title>" +
                "</head>" +
                "<body style=\"font-family: SimSun,serif\">" +
                "<p><input type=\"checkbox\" checked=\"checked\" disabled=\"disabled\" name=\"vehicle\" value=\"Bike\" /> I have a bike</p>" +
                "<h1>hello world刘宁233333</h1>" +
                "<h1 style=\"color: red\">${name}</h1>" +
                "</body>" +
                "</html>", pdfPath2);
    }

}
