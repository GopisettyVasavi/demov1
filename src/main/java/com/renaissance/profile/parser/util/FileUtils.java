package com.renaissance.profile.parser.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.Future;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.documents4j.api.DocumentType;
import com.documents4j.api.IConverter;
import com.documents4j.job.LocalConverter;
import com.renaissance.profile.parser.dto.FileConversionDTO;
/**
 * This utility class will have all the methods required for file processing and conversion.
 * @author Vasavi
 *
 */
public class FileUtils {
	
	private static Logger logger=LoggerFactory.getLogger(FileUtils.class);
	public static FileConversionDTO convertDoctoPdf(File docFile, String contentType) {
		FileConversionDTO fileDto= new FileConversionDTO();
	
		String filenameWithoutX=FilenameUtils.removeExtension(docFile.getName());
		File pdfFile= new File(ProfileParserConstants.CURRENT_DIR+ProfileParserConstants.UPLOAD_FOLDER +filenameWithoutX+".pdf");
		try {
		 ByteArrayOutputStream bo = new ByteArrayOutputStream();
		 
         InputStream in = new BufferedInputStream(new FileInputStream(docFile));
         IConverter converter = LocalConverter.builder().build();
         Future<Boolean> conversion = converter
                 .convert(in).as(DocumentType.MS_WORD)
                 .to(bo).as(DocumentType.PDF)
                 .prioritizeWith(1000) // optional
                 .schedule();
         conversion.get();
         try (OutputStream outputStream = new FileOutputStream(pdfFile)) {
        	 if(!pdfFile.exists())
        		 pdfFile.createNewFile();
             bo.writeTo(outputStream);
             
             fileDto.setFilepath(pdfFile.getCanonicalPath());
         } catch (IOException e) {
             e.printStackTrace();
         }
         in.close();
         bo.close();
         bo.flush();
         converter.shutDown();
		}catch(Exception e) {
			logger.error("Error occured while converting doc to pdf.{},{}",filenameWithoutX,new Exception(e.getMessage()));
		}
		logger.info("Successfully converted document {} to Pdf as {}", docFile.getName(), pdfFile.getName());
		fileDto.setConvertedFile(pdfFile);
		//fileDto.setMultipartFile(filetoMultipartFile(pdfFile,contentType));
		
		return fileDto;
	}
	
	/**
	 * This method checks whether the given file name is of .pdf type or not.
	 * @param name
	 * @return
	 */
	
public static boolean  isFileTypePdf(String name) {
	String extension="";
	if (name.lastIndexOf(".") != -1 && name.lastIndexOf(".") != 0)
		 extension= name.substring(name.lastIndexOf(".") + 1);
	
	if(extension.equalsIgnoreCase("pdf"))
		return true;
	else return false;
}

	/*
	 * private static MultipartFile filetoMultipartFile(File convFile, String
	 * contentType) {
	 * 
	 * FileInputStream input; MultipartFile multipartFile=null; try { input = new
	 * FileInputStream(convFile); multipartFile = new MockMultipartFile("name",
	 * convFile.getName(), "application/pdf", IOUtils.toByteArray(input)); } catch
	 * (FileNotFoundException e) { logger.error("Error in converting file");
	 * e.printStackTrace(); } catch (IOException e) {
	 * logger.error("Error in converting file"); e.printStackTrace(); } return
	 * multipartFile;
	 * 
	 * }
	 */
}
