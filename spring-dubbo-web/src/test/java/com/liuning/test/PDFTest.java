package com.liuning.test;

import com.itextpdf.text.DocumentException;
import com.liuning.StartApplication;
import com.liuning.itext.PDFConvertor;
import com.liuning.itext.PDFGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {StartApplication.class})
public class PDFTest {

    @Autowired
    PDFConvertor pdfConvertor;

    @Autowired
    PDFGenerator pdfGenerator;

    @Test
    public void convertToPdf() {

        String path = "/Users/liuning/Documents/JetBrains/hello_1_7.pdf";

        pdfConvertor.convertToPdf("<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "<head>" +
                "    <meta charset=\"utf-8\"/>" +
                "    <title>FreeMarker</title>" +
                "</head>" +
                "<body>" +
                "<p><input type=\"checkbox\" checked=\"checked\" disabled=\"disabled\" name=\"vehicle\" value=\"Bike\" /> I have a bike</p>" +
                "<h1>hello world</h1>" +
                "<h1 style=\"color: red\">${name}</h1>" +
                "</body>" +
                "</html>", path);
    }

    @Test
    public void generatePdf() throws IOException, DocumentException {

        String path = "/Users/liuning/Documents/JetBrains/hello_1_8.pdf";

        pdfGenerator.generatePdf("刘宁",path);
    }
}
