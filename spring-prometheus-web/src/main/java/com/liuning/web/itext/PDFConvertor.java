package com.liuning.web.itext;

import com.itextpdf.text.*;
import com.itextpdf.text.html.HtmlUtilities;
import com.itextpdf.text.pdf.*;
import com.itextpdf.tool.xml.Tag;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.css.CSS;
import com.itextpdf.tool.xml.css.CssFile;
import com.itextpdf.tool.xml.css.StyleAttrCSSResolver;
import com.itextpdf.tool.xml.css.apply.ChunkCssApplier;
import com.itextpdf.tool.xml.html.CssAppliers;
import com.itextpdf.tool.xml.html.CssAppliersImpl;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.regex.Pattern;

@Component
public class PDFConvertor {

    private static final Logger log = LoggerFactory.getLogger(PDFConvertor.class);

    private static BaseFont baseFont;

    public PDFConvertor() {

    }

    static {

        try {
            baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        } catch (DocumentException | IOException e) {
            log.error("初始化中文字体异常", e);
        }

    }

    public void convertToPdf(String text, String path) {
        try {
            Rectangle pageSize = new Rectangle(375, 667);
            Document document = new Document(pageSize, 38, 38, 60, 65);
            PdfWriter pdfWriter = null;
            ByteArrayInputStream inputStream = new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8));

            pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(path));
            Rectangle rect = new Rectangle(36, 54, 559, 788);
            rect.setBorderColor(BaseColor.BLACK);
            pdfWriter.setBoxSize("art", rect);

            HeaderFooter header = new HeaderFooter();
            pdfWriter.setPageEvent(header);
            document.open();

            CSSResolver cssResolver = new StyleAttrCSSResolver();
            CssFile cssFile = XMLWorkerHelper.getCSS(XMLWorkerHelper.class.getResourceAsStream("/default.css"));
            cssResolver.addCss(cssFile);

            //将ChunkCssApplier设置为自定义的
            CssAppliers cssAppliers = new CssAppliersImpl();
            cssAppliers.setChunkCssAplier(new FmChunkCssApplier());
            HtmlPipelineContext htmlContext = new HtmlPipelineContext(cssAppliers);
            htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());

            //Pipelines
            PdfWriterPipeline pdf = new PdfWriterPipeline(document, pdfWriter);
            HtmlPipeline html = new HtmlPipeline(htmlContext, pdf);
            CssResolverPipeline css = new CssResolverPipeline(cssResolver, html);

            //XML Worker
            XMLWorker worker = new XMLWorker(css, true);
            XMLParser p = new XMLParser(worker);
            p.parse(inputStream, StandardCharsets.UTF_8);

            document.close();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }

    }

    static class HeaderFooter extends PdfPageEventHelper {

        PdfTemplate total;

        public void onOpenDocument(PdfWriter writer, Document document) {
            total = writer.getDirectContent().createTemplate(30, 16);
        }

        public void onEndPage(PdfWriter writer, Document document) {
            PdfPTable headTable = new PdfPTable(1);
            PdfPTable footTable = new PdfPTable(2);
            try {
                headTable.setWidths(new int[]{24});
                headTable.setTotalWidth(527);
                headTable.setLockedWidth(true);
                headTable.getDefaultCell().setFixedHeight(45);
                headTable.getDefaultCell().setBorder(Rectangle.BOTTOM);
                headTable.writeSelectedRows(0, -1, 34, 830, writer.getDirectContent());

                footTable.setWidths(new int[]{24, 2});
                footTable.setTotalWidth(527);
                footTable.setLockedWidth(true);
                footTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
                footTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
                footTable.addCell(String.format("%d /", writer.getPageNumber()));

                PdfPCell cell = new PdfPCell(Image.getInstance(total));
                cell.setBorder(Rectangle.NO_BORDER);

                footTable.addCell(cell);
                footTable.writeSelectedRows(0, -1, 34, 55, writer.getDirectContent());

            } catch (DocumentException e) {
                log.warn("pdf转换异常" + e);
            }
        }

        public void onCloseDocument(PdfWriter writer, Document document) {
            ColumnText.showTextAligned(total, Element.ALIGN_LEFT,
                    new Phrase(String.valueOf(writer.getPageNumber() - 1)),
                    2, 2, 0);
        }

    }


    static class FmChunkCssApplier extends ChunkCssApplier {

        /**
         * 判断是否存在中文
         *
         * @param str
         * @return
         */
        private static boolean isChinese(String str) {
            if (str == null) {
                return false;
            }
            String regex = ".*[\\u4e00-\\u9faf]";
            return Pattern.matches(regex, str);
        }

        @Override
        public Chunk apply(Chunk c, Tag t) {

            Font f = applyFontStyles(t);

            if (null != baseFont && isChinese(c.getContent())) {
                f = new Font(baseFont, f.getSize(), f.getStyle(), f.getColor());
            }

            //copy from source
            float size = f.getSize();
            Map<String, String> rules = t.getCSS();
            for (Map.Entry<String, String> entry : rules.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (CSS.Property.FONT_STYLE.equalsIgnoreCase(key)) {
                    if (value.equalsIgnoreCase(CSS.Value.OBLIQUE)) {
                        c.setSkew(0, 12);
                    }
                } else if (CSS.Property.LETTER_SPACING.equalsIgnoreCase(key)) {
                    String letterSpacing = rules.get(CSS.Property.LETTER_SPACING);
                    float letterSpacingValue = 0f;
                    if (utils.isRelativeValue(value)) {
                        letterSpacingValue = utils.parseRelativeValue(letterSpacing, f.getSize());
                    } else if (utils.isMetricValue(value)) {
                        letterSpacingValue = utils.parsePxInCmMmPcToPt(letterSpacing);
                    }
                    c.setCharacterSpacing(letterSpacingValue);
                } else if (null != rules.get(CSS.Property.XFA_FONT_HORIZONTAL_SCALE)) { // only % allowed; need a catch block NumberFormatExc?
                    c.setHorizontalScaling(Float.parseFloat(rules.get(CSS.Property.XFA_FONT_HORIZONTAL_SCALE).replace("%", "")) / 100);
                }
            }
            // following styles are separate from the for each loop, because they are based on font settings like size.
            if (null != rules.get(CSS.Property.VERTICAL_ALIGN)) {
                String value = rules.get(CSS.Property.VERTICAL_ALIGN);
                if (value.equalsIgnoreCase(CSS.Value.SUPER) || value.equalsIgnoreCase(CSS.Value.TOP) || value.equalsIgnoreCase(CSS.Value.TEXT_TOP)) {
                    c.setTextRise((float) (size / 2 + 0.5));
                } else if (value.equalsIgnoreCase(CSS.Value.SUB) || value.equalsIgnoreCase(CSS.Value.BOTTOM) || value.equalsIgnoreCase(CSS.Value.TEXT_BOTTOM)) {
                    c.setTextRise(-size / 2);
                } else {
                    c.setTextRise(utils.parsePxInCmMmPcToPt(value));
                }
            }
            String xfaVertScale = rules.get(CSS.Property.XFA_FONT_VERTICAL_SCALE);
            if (null != xfaVertScale) {
                if (xfaVertScale.contains("%")) {
                    size *= Float.parseFloat(xfaVertScale.replace("%", "")) / 100;
                    c.setHorizontalScaling(100 / Float.parseFloat(xfaVertScale.replace("%", "")));
                }
            }
            if (null != rules.get(CSS.Property.TEXT_DECORATION)) {
                String[] splitValues = rules.get(CSS.Property.TEXT_DECORATION).split("\\s+");
                for (String value : splitValues) {
                    if (CSS.Value.UNDERLINE.equalsIgnoreCase(value)) {
                        c.setUnderline(null, 0.75f, 0, 0, -0.125f, PdfContentByte.LINE_CAP_BUTT);
                    }
                    if (CSS.Value.LINE_THROUGH.equalsIgnoreCase(value)) {
                        c.setUnderline(null, 0.75f, 0, 0, 0.25f, PdfContentByte.LINE_CAP_BUTT);
                    }
                }
            }
            if (null != rules.get(CSS.Property.BACKGROUND_COLOR)) {
                c.setBackground(HtmlUtilities.decodeColor(rules.get(CSS.Property.BACKGROUND_COLOR)));
            }
            f.setSize(size);
            c.setFont(f);

            Float leading = null;
            if (rules.get(CSS.Property.LINE_HEIGHT) != null) {
                String value = rules.get(CSS.Property.LINE_HEIGHT);
                if (utils.isNumericValue(value)) {
                    leading = Float.parseFloat(value) * c.getFont().getSize();
                } else if (utils.isRelativeValue(value)) {
                    leading = utils.parseRelativeValue(value, c.getFont().getSize());
                } else if (utils.isMetricValue(value)) {
                    leading = utils.parsePxInCmMmPcToPt(value);
                }
            }

            if (leading != null) {
                c.setLineHeight(leading);
            }
            return c;

        }

    }

}
