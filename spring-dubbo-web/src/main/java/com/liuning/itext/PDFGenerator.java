package com.liuning.itext;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;

@Component
public class PDFGenerator {

    private static final Logger log = LoggerFactory.getLogger(PDFGenerator.class);

    private static BaseFont baseFont;

    static {

        try {
            baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        } catch (DocumentException | IOException e) {
            log.error("初始化中文字体异常", e);
        }

    }

    /**
     * 生成pdf文件
     *
     * @param text 文件内容
     * @param path 生成文件的路径
     * @throws DocumentException
     * @throws IOException
     */
    public void generatePdf(String text, String path) throws DocumentException, IOException {

        //设置pdf的大小，一般为A4
        Document document = new Document(PageSize.A4, 40, 40, 40, 40);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(path));
        //设置pdf版本
        writer.setPdfVersion(PdfWriter.VERSION_1_7);
        //设置页脚、页眉等
        writer.setPageEvent(new PageMarker(baseFont));

        Rectangle rect = new Rectangle(36, 54, 559, 788);
        rect.setBorderColor(BaseColor.BLUE);
        writer.setBoxSize("art", rect);

        document.addTitle("皓月冷千山");
        document.addAuthor("刘宁");
        document.addCreationDate();
        //设置页面边距
        document.setMargins(24, 24, 80, 80);
        document.open();

        Font headerFont = new Font(baseFont, 16, Font.BOLD);
        Paragraph header = new Paragraph("只可追忆到想追追不到", headerFont);
        header.setAlignment(1);
        //行间距
        header.setLeading(20f);
        //设置段落上空白
        header.setSpacingBefore(5f);
        //设置段落下空白
        header.setSpacingAfter(20f);
        document.add(header);


//        float[] widths = { 50, 50, 50, 50, 50, 50 };
//        PdfPTable table = new PdfPTable(6); // 表格 参数（6列）
//        table.setTotalWidth(widths);
//
//        PdfPCell c = new PdfPCell(new Paragraph(" □维修  □换新  □不处理", headerFont));   //方框□ 实现方法：搜狗输入法 fangkuang 5 复选/多选
//        c.setHorizontalAlignment(Element.ALIGN_LEFT);
//        c.setVerticalAlignment(Element.ALIGN_MIDDLE);
//        c.setColspan(6);
//        c.setFixedHeight(20);
//        c.setBorderWidthTop(0f);
//        c.setBorderWidthLeft(1.3f);
//        c.setBorderWidthRight(1.3f);
//        c.setBorderWidthBottom(0.5f);
//        table.addCell(c);
//
//        PdfPCell c1 = new PdfPCell(new Paragraph(" ○委托单位  ○终端用户", headerFont));  //圆形○ 实现方法：搜狗输入法 yuanxing 5 单选
//        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
//        c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
//        c1.setColspan(6);
//        c1.setFixedHeight(20);
//        c1.setBorderWidthTop(0f);
//        c1.setBorderWidthLeft(1.3f);
//        c1.setBorderWidthRight(1.3f);
//        c1.setBorderWidthBottom(1.3f);
//        table.addCell(c1);
//        document.add(table);


        PdfContentByte cb = writer.getDirectContent();
        cb.moveTo(0, 0);
        PdfFormField field = PdfFormField.createCheckBox(writer);
        PdfAppearance tpOff = cb.createAppearance(20, 20);
        PdfAppearance tpOn = cb.createAppearance(20, 20);
        tpOff.rectangle(1, 1, 18, 18);
        tpOff.stroke();
//
        tpOn.setRGBColorFill(255, 128, 128);
        tpOn.rectangle(1, 1, 18, 18);
        tpOn.fillStroke();
        tpOn.moveTo(1, 1);
        tpOn.lineTo(19, 19);
        tpOn.moveTo(1, 19);
        tpOn.lineTo(19, 1);
        tpOn.stroke();

        field.setWidget(new Rectangle(100, 700, 120, 720), PdfAnnotation.HIGHLIGHT_INVERT);
        field.setFieldName("Urgent");
        field.setValueAsName("On");
        field.setAppearanceState("Off");
//            field.setAppearance(PdfAnnotation.APPEARANCE_NORMAL, "Off", tpOff);
//            field.setAppearance(PdfAnnotation.APPEARANCE_NORMAL, "On", tpOn);
        writer.addAnnotation(field);


        //暂时用下面的方法解决中文字体，后续用PageMarker解决
        Font font = FontFactory.getFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED, 10f, Font.NORMAL, BaseColor.BLACK);

        // step 4
        document.add(new Paragraph(text, font));
        // step 5
        document.close();
    }

}
