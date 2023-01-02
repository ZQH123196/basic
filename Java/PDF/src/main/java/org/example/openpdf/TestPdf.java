package org.example.openpdf;



import cn.hutool.core.convert.Convert;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;

public class TestPdf {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("date", "2022-12-29");

        map.put("subtitle", "(subtitle)");

        map.put("payerBankName", "付款人户名");
        map.put("payerOpenBankName", "付款人开户行");
        map.put("payerBankNo", "付款人行别");

        map.put("payeeBankName", "收款人户名");
        map.put("payeeOpenBankName", "收款人开户行");
        map.put("payeeBankNo", "收款人行别");


        map.put("currency", "人民币");
        BigDecimal moneyLowercase = BigDecimal.valueOf(8999.99);
        String moneyUppercase = Convert.digitToChinese(moneyLowercase);
        map.put("moneyUppercase", moneyUppercase);
        map.put("moneyLowercase", moneyLowercase.toString());



        //源模板地址
        String sourceFile = "src/main/resources/form/A5Pdf_form_template.pdf";
        //生成文件的目标地址
        String targetFile = "target/A5Pdf.pdf";
        File templateFile = new File(sourceFile);
        try {
       /* 获取模板文件中的所有表单域
        Set<String> templateFileFieldNames = PdfUtils.getTemplateFileFieldNames(sourceFile);
            System.out.println(templateFileFieldNames);*/
            PdfUtils.fillParam(map, FileUtils.readFileToByteArray(templateFile), targetFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
