package com.liuning.itext;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * 设置pdf的页脚、页眉、盖章
 *
 * @author liuning
 */
public class PageMarker extends PdfPageEventHelper {

    private BaseFont baseFont;

    public PageMarker(BaseFont baseFont) {
        this.baseFont = baseFont;
    }

    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        super.onEndPage(writer, document);
    }

    @Override
    public void onOpenDocument(PdfWriter writer, Document document) {
        super.onOpenDocument(writer, document);
    }

    @Override
    public void onCloseDocument(PdfWriter writer, Document document) {
        super.onCloseDocument(writer, document);
    }
}
