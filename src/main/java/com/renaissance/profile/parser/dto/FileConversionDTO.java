package com.renaissance.profile.parser.dto;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public class FileConversionDTO {
	
	String filepath;
	MultipartFile multipartFile;
	File convertedFile;
	public String getFilepath() {
		return filepath;
	}

	/*
	 * @Override public String toString() { return "FileConversionDTO [filepath=" +
	 * filepath + ", multipartFile=" + multipartFile.getOriginalFilename() +
	 * ", convertedFile=" + convertedFile + "]"; }
	 */
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public MultipartFile getMultipartFile() {
		return multipartFile;
	}
	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}
	public File getConvertedFile() {
		return convertedFile;
	}
	public void setConvertedFile(File convertedFile) {
		this.convertedFile = convertedFile;
	}

}
