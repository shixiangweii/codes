package com.sxw.code.execl.lkx;

import java.io.Serializable;
import java.util.Map;

public class ExcelParam implements Serializable{

	private static final long serialVersionUID = -4231868339831975335L;

    private String filePath;
	
	private String classPath;
	
	private Integer rowNumIndex;
	
	private Integer sheetIndex;
	
	private Map map;

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getClassPath() {
		return classPath;
	}

	public void setClassPath(String classPath) {
		this.classPath = classPath;
	}

	public Integer getRowNumIndex() {
		return rowNumIndex;
	}

	public void setRowNumIndex(Integer rowNumIndex) {
		this.rowNumIndex = rowNumIndex;
	}

	public Integer getSheetIndex() {
		return sheetIndex;
	}

	public void setSheetIndex(Integer sheetIndex) {
		this.sheetIndex = sheetIndex;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	

}