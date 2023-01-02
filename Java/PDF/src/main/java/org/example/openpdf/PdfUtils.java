package org.example.openpdf;



import com.lowagie.text.Document;
import com.lowagie.text.pdf.*;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * pdf 处理工具类：用于填充 pdf 模板数据使用
 *  * 依赖的包：openpdf 或者 itext
 */
public class PdfUtils {
    /**
     * Description: 使用map中的参数填充pdf，map中的key和pdf表单中的field对应 <br>
     * @Param  fieldValueMap:文件字段  file:源文件的字节流   contractFileName：生成目标文件存放的地址
     * @return
     */
    public static void fillParam(Map<String, String> fieldValueMap, byte[] file, String contractFileName) {
        FileOutputStream fos = null;
        try {
            //生成pdf 的路径
            fos = new FileOutputStream(contractFileName);
            PdfReader reader = null;
            PdfStamper stamper = null;
            BaseFont base;
            try {
                reader = new PdfReader(file);
                stamper = new PdfStamper(reader, fos);
                stamper.setFormFlattening(true);
                base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
                AcroFields acroFields = stamper.getAcroFields();
                 for (String key : acroFields.getAllFields().keySet()) {
                    //设置一些填充位置的字体属性
                     acroFields.setFieldProperty(key, "textfont", base, null);
                     acroFields.setFieldProperty(key, "textsize", new Float(9), null);
                 }
                if (fieldValueMap != null) {
                    //将map填充到对应的位置中
                    for (String fieldName : fieldValueMap.keySet()) {
                        acroFields.setField(fieldName, fieldValueMap.get(fieldName));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (stamper != null) {
                    try {
                        stamper.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (reader != null) {
                    reader.close();
                }
            }

        } catch (Exception e) {
            System.out.println("填充参数异常");
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(fos);
        }
    }


    /**
     * Description: 获取pdf表单中的fieldNames<br>
     */
    public static Set<String> getTemplateFileFieldNames(String pdfFileName) {
        Set<String> fieldNames = new TreeSet<String>();
        PdfReader reader = null;
        try {
            reader = new PdfReader(pdfFileName);
            Set<String> keys = reader.getAcroFields().getFields().keySet();
            for (String key : keys) {
                int lastIndexOf = key.lastIndexOf(".");
                int lastIndexOf2 = key.lastIndexOf("[");
                fieldNames.add(key.substring(lastIndexOf != -1 ? lastIndexOf + 1 : 0, lastIndexOf2 != -1 ? lastIndexOf2 : key.length()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }

        return fieldNames;
    }


    /**
     * Description: 读取文件数组<br>
     */
    public static byte[] fileBuff(String filePath) throws IOException {
        File file = new File(filePath);
        long fileSize = file.length();
        if (fileSize > Integer.MAX_VALUE) {
            //System.out.println("file too big...");
            return null;
        }
        FileInputStream fi = new FileInputStream(file);
        byte[] file_buff = new byte[(int) fileSize];
        int offset = 0;
        int numRead = 0;
        while (offset < file_buff.length && (numRead = fi.read(file_buff, offset, file_buff.length - offset)) >= 0) {
            offset += numRead;
        }
        // 确保所有数据均被读取
        if (offset != file_buff.length) {
            throw new IOException("Could not completely read file " + file.getName());
        }
        fi.close();
        return file_buff;
    }

    /**
     * Description: 合并pdf <br>
     */
    public static void mergePdfFiles(String[] files, String savepath) {
        Document document = null;
        try {
            document = new Document(); //默认A4大小
            PdfCopy copy = new PdfCopy(document, new FileOutputStream(savepath));
            document.open();
            for (int i = 0; i < files.length; i++) {
                PdfReader reader = null;
                try {
                    reader = new PdfReader(files[i]);
                    int n = reader.getNumberOfPages();
                    for (int j = 1; j <= n; j++) {
                        document.newPage();
                        PdfImportedPage page = copy.getImportedPage(reader, j);
                        copy.addPage(page);
                    }
                } finally {
                    if (reader != null) {
                        reader.close();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭PDF文档流，OutputStream文件输出流也将在PDF文档流关闭方法内部关闭
            if (document != null) {
                document.close();
            }

        }
    }


}
