/*
 * package com.renaissance.profile.parser.util;
 * 
 * import java.io.File; import java.io.OutputStream; import java.net.URLEncoder;
 * import java.nio.file.Files; import java.util.List;
 * 
 * import javax.servlet.http.HttpServletResponse;
 * 
 * import org.apache.poi.xwpf.usermodel.ParagraphAlignment; import
 * org.apache.poi.xwpf.usermodel.XWPFDocument; import
 * org.apache.poi.xwpf.usermodel.XWPFParagraph; import
 * org.apache.poi.xwpf.usermodel.XWPFRun; import
 * org.docx4j.openpackaging.packages.WordprocessingMLPackage; import
 * org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart; import
 * org.slf4j.Logger; import org.slf4j.LoggerFactory; import
 * org.docx4j.dml.wordprocessingDrawing.Inline; import org.docx4j.jaxb.Context;
 * import org.docx4j.model.table.TblFactory; import
 * org.docx4j.openpackaging.exceptions.Docx4JException; import
 * org.docx4j.openpackaging.packages.WordprocessingMLPackage; import
 * org.docx4j.openpackaging.parts.WordprocessingML.BinaryPartAbstractImage;
 * import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
 * import org.docx4j.wml.*;
 * 
 * import javax.xml.bind.JAXBElement; import javax.xml.bind.JAXBException;
 * public class WordUtils {
 * 
 * private static final Logger logger=LoggerFactory.getLogger(WordUtils.class);
 * public static void downloadBigActivity(HttpServletResponse response) throws
 * Exception { XWPFDocument doc = new XWPFDocument();//Create Word document
 * XWPFParagraph p = doc.createParagraph();//New paragraph
 * p.setAlignment(ParagraphAlignment.CENTER);//Set the alignment of the
 * paragraph XWPFRun r = p.createRun();//Create title
 * r.setText("Analysis of large-scale events on the first day of 2020");
 * r.setBold(true);//Set to bold r.setColor("000000");//Set the color
 * r.setFontSize(21); //Set the font size r.addCarriageReturn();//Enter and line
 * feed XWPFParagraph p1 = doc.createParagraph();
 * p1.setAlignment(ParagraphAlignment.BOTH);
 * 
 * XWPFRun c1 = p1.createRun(); c1.setText
 * ("1, December 31 evening will hold various activities");
 * c1.setColor("000000"); c1.setFontSize(12); c1.addCarriageReturn(); String
 * fileNameURL = URLEncoder.encode("myWord.doc", "UTF-8");
 * response.setCharacterEncoding("UTF-8");
 * //response.setHeader("Content-disposition", "attachment;filename=" +
 * fileName); response.setHeader("Content-disposition", "attachment;filename=" +
 * fileNameURL + ";" + "filename*=utf-8''" + fileNameURL);
 * response.setContentType("application/octet-stream"); //Refresh the buffer
 * response.flushBuffer(); OutputStream ouputStream =
 * response.getOutputStream(); //workbook writes Excel to the response output
 * stream for the page to download the Excel file doc.write(ouputStream);
 * ouputStream.flush(); ouputStream.close(); }
 * 
 * public static void doxtest() { try { WordprocessingMLPackage wordPackage =
 * WordprocessingMLPackage.createPackage(); MainDocumentPart mainDocumentPart =
 * wordPackage.getMainDocumentPart();
 * mainDocumentPart.addStyledParagraphOfText("Title", "Hello World!");
 * mainDocumentPart.addParagraphOfText("Welcome To Docx4j"); ObjectFactory
 * factory = Context.getWmlObjectFactory(); P p = factory.createP(); R r =
 * factory.createR(); Text t = factory.createText();
 * t.setValue("Welcome To Dox4j"); r.getContent().add(t); p.getContent().add(r);
 * RPr rpr = factory.createRPr(); BooleanDefaultTrue b = new
 * BooleanDefaultTrue(); rpr.setB(b); rpr.setI(b); rpr.setCaps(b); Color red =
 * factory.createColor(); red.setVal("green"); rpr.setColor(red); r.setRPr(rpr);
 * mainDocumentPart.getContent().add(p);
 * 
 * File image = new File("D:\\logo.jpg"); byte[] fileContent =
 * Files.readAllBytes(image.toPath()); BinaryPartAbstractImage imagePart =
 * BinaryPartAbstractImage.createImagePart(wordPackage, fileContent); Inline
 * inline = imagePart.createImageInline("Renaissance logo", "Alt Text", 1, 2,
 * false); P Imageparagraph = addImageToParagraph(inline);
 * mainDocumentPart.getContent().add(Imageparagraph);
 * 
 * int writableWidthTwips =
 * wordPackage.getDocumentModel().getSections().get(0).getPageDimensions().
 * getWritableWidthTwips(); int columnNumber = 3; Tbl tbl =
 * TblFactory.createTable(3, 3, writableWidthTwips / columnNumber); List<Object>
 * rows = tbl.getContent(); for (Object row : rows) { Tr tr = (Tr) row;
 * List<Object> cells = tr.getContent(); for (Object cell : cells) { Tc td =
 * (Tc) cell; td.getContent().add(p); } }
 * 
 * mainDocumentPart.getContent().add(tbl); File exportFile = new
 * File("D:\\welcome1.docx"); wordPackage.save(exportFile); }catch(Exception e)
 * { logger.error("ERROr in Docx...{}", e.getMessage()); } } private static P
 * addImageToParagraph(Inline inline) { ObjectFactory factory = new
 * ObjectFactory(); P p = factory.createP(); R r = factory.createR();
 * p.getContent().add(r); Drawing drawing = factory.createDrawing();
 * r.getContent().add(drawing); drawing.getAnchorOrInline().add(inline); return
 * p; } }
 */