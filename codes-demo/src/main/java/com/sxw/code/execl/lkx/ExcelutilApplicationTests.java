package com.sxw.code.execl.lkx;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class ExcelutilApplicationTests {

	@Test
    public void contextLoads() throws Exception {
		
		String keyValue ="手机名称:phoneName,颜色:color,售价:price,时间:sj"; 
		ExcelParam param = new ExcelParam();
		param.setFilePath("D://testsss.xls");
		param.setMap(ExcelUtil.getMap(keyValue));
		param.setClassPath("com.lkx.model.PhoneModel");
		param.setSheetIndex(2);
		List<PhoneModel> list =  ExcelUtil.readXlsPart(param);
	     for (PhoneModel phoneModel : list) {
			System.out.println(phoneModel);
		}

	     /*String keyValue ="手机名称:phoneName,颜色:color,售价:price,时间:sj"; 
	     List<PhoneModel> list =  ExcelUtil.readXls("D://testsss.xls",ExcelUtil.getMap(keyValue),"com.lkx.model.PhoneModel");
	     for (PhoneModel phoneModel : list) {
			System.out.println(phoneModel);
		}*/
	}
	
	@Test
	public void testXDD() throws Exception
	{
		String keyValue = "车辆属性:attrubute,车牌号:cartNumber,排放标准:emissionStandard,"
				+ "车牌型号:cartModel,车架号:frameNumber,承运商名称:nameCarrier,司机联系电话:driverPhone,"
				+ "运行线路:runLine,匹配头:matchingHead,匹配挂:matchingHanger,行驶证登记日期:registDrivingTime,"
				+ "录入时间:createTime,备注:remark";
		List<PhoneModel> list =  ExcelUtil.readXls("D://05426a25a0b230a5.xlsx",ExcelUtil.getMap(keyValue),"com.lkx.model.ReportDO");
	     for (PhoneModel phoneModel : list) {
			System.out.println(phoneModel);
		}
	}
	@Test
	public void testExportExcel() throws Exception{
		
		List<PhoneModel> list = new ArrayList<PhoneModel>();
		for(int i=0;i<100;i++){
			PhoneModel model = new PhoneModel();
			model.setColor("金色"+i);
			model.setPhoneName("苹果"+i+"S");
			model.setPrice(i);
			list.add(model);
		}
		String keyValue ="手机名称:phoneName,颜色:color,售价:price"; 
		ExcelUtil.exportExcel("d:/testsss.xls",keyValue,list,"com.lkx.model.PhoneModel");
		
	}

}