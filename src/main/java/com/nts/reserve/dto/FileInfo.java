package com.nts.reserve.dto;

public class FileInfo {
	private int id;
	private String contentType;
	private String fileName;
	private String saveFileName;
	private int deleteFlag;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getSaveFileName() {
		return saveFileName;
	}

	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}

	public int getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	@Override
	public String toString() {
		return "FileInfo [id=" + id + ", contentType=" + contentType + ", fileName=" + fileName + ", saveFileName="
				+ saveFileName + ", deleteFlag=" + deleteFlag + "]";
	}

}
