package com.renaissance.profile.parser.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.jsolve.templ4docx.core.Docx;
import pl.jsolve.templ4docx.core.VariablePattern;
import pl.jsolve.templ4docx.variable.TextVariable;
import pl.jsolve.templ4docx.variable.Variables;

public class XWPFTest {
	private static final Logger logger = LoggerFactory.getLogger(XWPFTest.class);

	//@SuppressWarnings("resource")
	public static void test(String name) {
		try {

			String resourcePath = "D:\\template2.docx";
			/*
			 * Path templatePath =
			 * Paths.get(XWPFTest.class.getClassLoader().getResource(resourcePath).toURI());
			 * XWPFDocument doc = new XWPFDocument(Files.newInputStream(templatePath)); doc
			 * = replaceTextFor(doc, "${name}", "Vasavi"); saveWord("D:\\document.docx",
			 * doc); doc.close();
			 */

			//String resourcePath = "D:\\template2.dotx";

			/*
			 * InputStream templateInputStream =
			 * XWPFTest.class.getClassLoader().getResourceAsStream(resourcePath);
			 * 
			 * WordprocessingMLPackage wordMLPackage =
			 * WordprocessingMLPackage.load(templateInputStream);
			 * 
			 * MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();
			 * 
			 * VariablePrepare.prepare(wordMLPackage);
			 * 
			 * HashMap<String, String> variables = new HashMap<>(); variables.put("name",
			 * "Vasavi");
			 * 
			 * documentPart.variableReplace(variables);
			 * 
			 * ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			 * 
			 * wordMLPackage.save(outputStream);
			 */
			
			/*
			 * DocxTemplate template = new DocxTemplate(); template.setVariablePattern(new
			 * VariablePattern("#{", "}")); String path ="D://template2.docx"; //String
			 * resourcePath = "D:\\template2.docx"; // prepare map of variables for template
			 * Map<String, String> variables = new HashMap<String, String>();
			 * variables.put("#{name}", "John"); //variables.put("#{lastName}", "Sky");
			 * 
			 * Docx filledTemplate = template.fillTemplate(path, variables);
			 * 
			 * template.save(filledTemplate, "D://filledDocument.docx");
			 */
			
			Docx docx = new Docx("D:\\template.docx"); 
			docx.setVariablePattern(new VariablePattern("#{", "}"));
			
			 
			  
			  // preparing variables
			Variables variables = new Variables();
			  variables.addTextVariable(new TextVariable("#{name}", name));
			  variables.addTextVariable(new TextVariable("#{client_address}", "Infosys Technologies Limited\r\n" + 
			  		"			  Level 4&5, 818 Bourke Street, Docklands, \r\n" + 
			  		"			  Victoria - 3008 Australia"));
			  variables.addTextVariable(new TextVariable("#{invoice_number}", "0007805"));
			  variables.addTextVariable(new TextVariable("#{invoice_date}", "02-09-2020"));
			  variables.addTextVariable(new TextVariable("#{payment_terms}", "60 days"));
			  variables.addTextVariable(new TextVariable("#{vendor_number}", "700007822"));
			  variables.addTextVariable(new TextVariable("#{unit_price}", "$625.00"));
			  variables.addTextVariable(new TextVariable("#{no_of_units}", "16.00"));
			  variables.addTextVariable(new TextVariable("#{amount}", "$10,000.00"));
			  variables.addTextVariable(new TextVariable("#{total_amount}", "$10,000.00"));
			  variables.addTextVariable(new TextVariable("#{gst}", "$1,000.00"));
			  variables.addTextVariable(new TextVariable("#{total_amount_gst}", "$11,000.00"));

			  
			  

			 // variables.addTextVariable(new TextVariable("#{lastname}", "Stypka"));
			  
			  // fill template 
			  docx.fillTemplate(variables);
			  
			  // save filled .docx file 
			  docx.save("D:\\"+name+".docx");
			  
			 
			  

		} catch (Exception e) {
			logger.error("ERROr in Docx...{}", e.getMessage());

		}
	}

	private static XWPFDocument replaceTextFor(XWPFDocument doc, String findText, String replaceText) {
		doc.getParagraphs().forEach(p -> {
			p.getRuns().forEach(run -> {
				String text = run.text();
				if (text.contains(findText)) {
					run.setText(text.replace(findText, replaceText), 0);
				}
			});
		});

		return doc;
	}

	private static void saveWord(String filePath, XWPFDocument doc) throws FileNotFoundException, IOException {
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(filePath);
			doc.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

}
