package org.demo.easypoitest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;

@Controller
@RequestMapping(value = "exportexcel")
public class ExcelUtil {
	public Workbook exportSheets(){
        // 查询数据,此处省略
        List list = new ArrayList<>();
        int count1 = 0 ;
        EasyPOIModel easyPOIModel11 = new EasyPOIModel(String.valueOf(count1++), "信科", new User("张三","男",20)) ;
        EasyPOIModel easyPOIModel12 = new EasyPOIModel(String.valueOf(count1++), "信科", new User("李四","男",17)) ;
        EasyPOIModel easyPOIModel13 = new EasyPOIModel(String.valueOf(count1++), "信科", new User("淑芬","女",34)) ;
        EasyPOIModel easyPOIModel14 = new EasyPOIModel(String.valueOf(count1++), "信科", new User("仲达","男",55)) ;
        list.add(easyPOIModel11) ;
        easyPOIModel11 = null ;
        list.add(easyPOIModel12) ;
        easyPOIModel12 = null ;
        list.add(easyPOIModel13) ;
        easyPOIModel13 = null ;
        list.add(easyPOIModel14) ;
        easyPOIModel14 = null ;
        List list1 = new ArrayList<>();
        int count2 = 0 ;
        EasyPOIModel easyPOIModel21 = new EasyPOIModel(String.valueOf(count2++),"软件",new User("德林","男",22)) ;
        EasyPOIModel easyPOIModel22 = new EasyPOIModel(String.valueOf(count2++),"软件",new User("智勇","男",28)) ;
        EasyPOIModel easyPOIModel23 = new EasyPOIModel(String.valueOf(count2++),"软件",new User("廉贞","女",17)) ;
        list1.add(easyPOIModel21) ;
        easyPOIModel21 = null;
        list1.add(easyPOIModel22) ;
        easyPOIModel22 = null;
        list1.add(easyPOIModel23) ;
        easyPOIModel23 = null;
        // 设置导出配置
        // 获取导出excel指定模版
        TemplateExportParams params = new TemplateExportParams("d:/项目测试文件夹/easypoiExample.xlsx");
        Map<> mapMap = new HashMap<>() ;
        // 创建参数对象（用来设定excel得sheet得内容等信息）
        ExportParams params1 = new ExportParams() ;
        // 设置sheet得名称
        params1.setSheetName("表格1"); ;
        ExportParams params2 = new ExportParams() ;
        params2.setSheetName("表格2") ;
        // 创建sheet1使用得map
        Map dataMap1 = new HashMap<>();
        // title的参数为ExportParams类型，目前仅仅在ExportParams中设置了sheetName
        dataMap1.put("title",params1) ;
        // 模版导出对应得实体类型
        dataMap1.put("entity",EasyPOIModel.class) ;
        // sheet中要填充得数据
        dataMap1.put("data",list) ;
        // 创建sheet2使用得map
        Map dataMap2 = new HashMap<>();
        dataMap2.put("title", params2) ;
        dataMap2.put("entity", EasyPOIModel.class) ;
        dataMap2.put("data", list1) ;
        // 将sheet1和sheet2使用得map进行包装
        List<> sheetsList = new ArrayList<>() ;
        sheetsList.add(dataMap1);
        sheetsList.add(dataMap2);
        // 执行方法
        return ExcelExportUtil.exportExcel(sheetsList, ExcelType.HSSF) ;
    }
}
