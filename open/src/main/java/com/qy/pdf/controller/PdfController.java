package com.qy.pdf.controller;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

@Controller
@RequestMapping("/pdf")
public class PdfController {

    @GetMapping("/export")
    public void exportPdf (HttpServletResponse response) {
        Document document = new Document(PageSize.A4);
        response.setContentType("application/pdf");// 设置输出格式头信息
        response.setHeader("Content-Disposition", "attachment; filename=111");

        try {

            // step 2: creation of the writer
            PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());

            // step 3: we open the document
            document.open();

            BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            Font font = new Font(bfChinese,8,Font.BOLD);//正常字体
            Font fontc = new Font(bfChinese, 8, Font.NORMAL);//正常加粗字体
            Font fontBold = new Font(bfChinese, 10, Font.BOLD);//正常加粗字体
            Font fontBigBold = new Font(bfChinese, 16, Font.BOLD);//加粗大字体
            //创建一个段落
            Paragraph theme = new Paragraph( "xxxxx个人参保证明", fontBigBold);
            //设置该段落为水平居中
            theme.setAlignment(Element.ALIGN_CENTER);
            //将该段落放入该文件中
            document.add(theme);
            //在创建一个段落，文字内容为当前日期
            Paragraph indexInfo = new Paragraph("在我市参加社会保险情况如下： ", font);
            //设置该段落文字水平靠右
            indexInfo.setAlignment(Element.ALIGN_LEFT);
            //将该段落添加到文件中
            document.add(indexInfo);
            //创建一个pdf的表格
            PdfPTable table1 = new PdfPTable(7);
            //设置该表格的基本属性
            table1.setWidthPercentage(100); // 宽度100%填充
            table1.setSpacingBefore(20f); // 前间距
            table1.setSpacingAfter(1f); // 后间距
            //创建表格的的行对象集合，并指向表格行对象
            ArrayList<PdfPRow> listRow = table1.getRows();
            //将表格设置为八列，并指定列宽
            float[] columnWidths = {
                    4f, 2f, 2f, 2f, 2f, 2f, 2f
            };
            table1.setWidths(columnWidths);
            //创建7个单元格，并指定给第一行
            PdfPCell cells0[] = new PdfPCell[7];
            PdfPRow row0 = new PdfPRow(cells0);
            //配置第一个单元格--单位名称
            cells0[0] = new PdfPCell(new Paragraph("单位名称", fontBold));//单元格内容
            cells0[0].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
            cells0[0].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
            cells0[0].setFixedHeight(25f);//固定高度
            //配置第二个单元格--开始年月
            cells0[1] = new PdfPCell(new Paragraph("开始年月", fontBold));
            cells0[1].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
            cells0[1].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
            //配置第三个单元格--结束年月
            cells0[2] = new PdfPCell(new Paragraph("结束年月", fontBold));
            cells0[2].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
            cells0[2].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
            //配置第4个单元格--缴费险种
            cells0[3] = new PdfPCell(new Paragraph("缴费险种", fontBold));
            cells0[3].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
            cells0[3].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
            //配置第5个单元格--缴费月数
            cells0[4] = new PdfPCell(new Paragraph("缴费月数", fontBold));
            cells0[4].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
            cells0[4].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
            //配置第6个单元格--缴费基数
            cells0[5] = new PdfPCell(new Paragraph("缴费基数", fontBold));
            cells0[5].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
            cells0[5].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
            //配置第7个单元格--统筹区
            cells0[6] = new PdfPCell(new Paragraph("统筹区", fontBold));
            cells0[6].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
            cells0[6].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
            //将第一行加入到表格的行集合中
            listRow.add(row0);
//            //把表格添加到文档中
            document.add(table1);
            //在创建一个段落，文字内容为当前日期
            Paragraph remark = new Paragraph("注:本证明可由参保人员自主打印，作为参保人在我市参加社会保险的有效证明！ ", font);
            //设置该段落文字水平靠右
            remark.setAlignment(Element.ALIGN_LEFT);
            //设置间距
            remark.setSpacingAfter(10f);
            remark.setSpacingBefore(10f);
            //将该段落添加到文件中
            document.add(remark);

            //在创建一个段落，文字内容为当前日期
            Paragraph jgdw = new Paragraph("xxxxxxx局", font);
            //设置该段落文字水平靠右
            jgdw.setAlignment(Element.ALIGN_RIGHT);
            //设置间距
            jgdw.setSpacingAfter(2f);
            //将该段落添加到文件中
            document.add(jgdw);
            //当前日期实例
            Calendar now = Calendar.getInstance();
            //在创建一个段落，文字内容为当前日期
            Paragraph rq = new Paragraph(now.get(Calendar.YEAR) +" 年" + (now.get(Calendar.MONTH) + 1) + " 月"+now.get(Calendar.DAY_OF_MONTH)+" 日",font);
            //设置该段落文字水平靠右
            rq.setAlignment(Element.ALIGN_RIGHT);
            //将该段落添加到文件中
            document.add(rq);

        }
        catch(DocumentException | IOException de) {
            System.err.println(de.getMessage());
        }

        // step 5: we close the document
        document.close();
    }
}
