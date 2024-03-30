package com.wubin.test;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashAttributeSet;
import javax.print.attribute.standard.PrinterName;
import java.awt.*;
import java.awt.print.*;

public class TestPrint {

    public static void printerList() {
//        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
//        for (PrintService printService : printServices) {
//            System.out.println(printService.getName());
//        }

        PrintService[] printServices = PrinterJob.lookupPrintServices();
        for (PrintService printService : printServices) {
            System.out.println(printService.getName());
        }
    }

    public static void printDefault() throws PrinterException {
        Paper paper = new Paper();
        paper.setSize(150, 95);
        paper.setImageableArea(10, 10, 150, 95);

        PageFormat pageFormat = new PageFormat();
        pageFormat.setOrientation(PageFormat.PORTRAIT);
        pageFormat.setPaper(paper);

        Book book = new Book();
        book.append(new Printable() {
            @Override
            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                if (pageIndex == 0) {

                    Graphics2D g2 = (Graphics2D) graphics;

                    Image src = Toolkit.getDefaultToolkit().getImage("D:/test/Code39.png");
                    g2.drawImage(src, 0, 0, null);

                    return PAGE_EXISTS;
                }
                return NO_SUCH_PAGE;
            }
        }, pageFormat);

        PrinterJob printerJob = PrinterJob.getPrinterJob();

        printerJob.setPageable(book);
        printerJob.print();
    }

    public static void printSelect(String printerName) throws PrinterException {
        HashAttributeSet set = new HashAttributeSet();
        set.add(new PrinterName("", null));
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, set);
        if (printServices.length == 0) {
            return;
        }

        Paper paper = new Paper();
        paper.setSize(150, 95);
        paper.setImageableArea(10, 10, 150, 95);

        PageFormat pageFormat = new PageFormat();
        pageFormat.setOrientation(PageFormat.PORTRAIT);
        pageFormat.setPaper(paper);

        Book book = new Book();
        book.append(new Printable() {
            @Override
            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                if (pageIndex == 0) {

                    return PAGE_EXISTS;
                }
                return NO_SUCH_PAGE;
            }
        }, pageFormat);

        PrinterJob printerJob = PrinterJob.getPrinterJob();
        printerJob.setPrintService(printServices[0]);
        printerJob.setPageable(book);
        printerJob.print();
    }

    public static void main(String[] args) throws PrinterException {
        printerList();
//        printDefault();
//        printSelect("Argox OS-214 plus series PPLA");
    }

}
