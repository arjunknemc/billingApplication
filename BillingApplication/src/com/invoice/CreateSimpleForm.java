/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invoice;

import be.quodlibet.boxable.BaseTable;
import be.quodlibet.boxable.Cell;
import be.quodlibet.boxable.Row;
import com.pojo.Bill;
import com.pojo.BillDetails;
import com.sql.model.ShopDetails;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceRGB;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationWidget;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAppearanceCharacteristicsDictionary;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDTextField;

/**
 * An example of creating an AcroForm and a form field from scratch.
 *
 * The form field is created with properties similar to creating a form with
 * default settings in Adobe Acrobat.
 *
 */
public final class CreateSimpleForm {

    public CreateSimpleForm() {
    }

    public static void main(String[] args) throws IOException {
        CreateSimpleForm form = new CreateSimpleForm();
        //form.create();
    }
    
    public boolean createBill(ShopDetails shopDetails , Bill bill){
        
        try{
            PDDocument document = new PDDocument();
            PDPage page = new PDPage(PDRectangle.A5);
            document.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            createBillHeader(document, page, shopDetails, bill);
            createTable(document, page, contentStream, bill);
            document.save("/Users/arjun.n/Desktop/BillingSoftware/invoice/"+"4.pdf");
            return true;
        }catch(Exception e){
            
        }
        
        return false;
    }
    
    public void createBillHeader(PDDocument document, PDPage page , ShopDetails shopDetails , Bill bill)throws IOException {
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            // Add GST Details panel
            PDTextField textBox = createTextBox(document);
            addTextBoxToPage(textBox, page, 0, 350, 170, 50, false);
            textBox.setValue("GST: " +shopDetails.getGst_no() +" STATE: " +shopDetails.getState() +"CODE: " +shopDetails.getGst_state_code());

            // Add Company Name and details
            PDTextField nameOfCompany = createTextBox(document);
            addTextBoxToPage(nameOfCompany, page, 120, 750, 150, 50, false);
            nameOfCompany.setValue( shopDetails.getName() +shopDetails.getAddress() +shopDetails.getLocality());

            // Add Bill Number and Date
            PDTextField billNumber = createTextBox(document);
            addTextBoxToPage(billNumber, page, 250, 750, 100, 50, false);
            billNumber.setValue("BillNO:" +bill.getInvoiceId() +"DATE:23/11/1990");
    }

    public Row<PDPage> createHeader(BaseTable table) {
        Row<PDPage> headerRow = table.createRow(15f);
        Cell<PDPage> siCell = headerRow.createCell(5, "SI");
        siCell.setFont(PDType1Font.HELVETICA_BOLD);

        Cell<PDPage> descriptionCell = headerRow.createCell(25, "Description");
        descriptionCell.setFont(PDType1Font.HELVETICA_BOLD);

        Cell<PDPage> qtyCell = headerRow.createCell(15, "Quantity");
        qtyCell.setFont(PDType1Font.HELVETICA_BOLD);

        Cell<PDPage> rateCell = headerRow.createCell(15, "Rate");
        rateCell.setFont(PDType1Font.HELVETICA_BOLD);

        Cell<PDPage> amtCell = headerRow.createCell(15, "AMount");
        amtCell.setFont(PDType1Font.HELVETICA_BOLD);

        //cell.setFillColor(Color.BLACK);
        table.addHeaderRow(headerRow);
        return headerRow;
    }

    public void createTable(PDDocument mainDocument, PDPage myPage, PDPageContentStream contentStream, Bill bill) throws IOException {

        //Dummy Table
        float margin = 20;
        // starting y position is whole page height subtracted by top and bottom margin
        float yStartNewPage = myPage.getMediaBox().getHeight() - (2 * margin);
        // we want table across whole page width (subtracted by left and right margin ofcourse)
        float tableWidth = myPage.getMediaBox().getWidth() - (2 * margin);
        boolean drawContent = true;
        float bottomMargin = 30;
        // y position is your coordinate of top left corner of the table

        BaseTable table = new BaseTable(250, yStartNewPage, bottomMargin, tableWidth, margin, mainDocument, myPage, true, drawContent);

        Row<PDPage> headerRow = createHeader(table);
        table.addHeaderRow(headerRow);

        ArrayList<BillDetails> d =  bill.getDetails();
        for(int i=0 ; i < d.size() ; i++){
            Row<PDPage> row = table.createRow(10f);
            row.createCell(5, "1");
            row.createCell(25, d.get(i).getDescription());
            row.createCell(15, Double.toString(d.get(i).getQty()));
            row.createCell(15, Double.toString(d.get(i).getRate()));
            row.createCell(15, Double.toString(d.get(i).getAmt()));
        }

        table.draw();
        contentStream.close();

    }

    public void addTextBoxToPage(PDTextField textBox, PDPage page, float x, float y, float weight, float height, boolean required) throws IOException {
        // Specify the annotation associated with the field
        PDAnnotationWidget widget = textBox.getWidgets().get(0);
        PDRectangle rect = new PDRectangle(x, y, weight, height);
        widget.setRectangle(rect);
        widget.setPage(page);

        if (required) {
            widget.setAppearanceCharacteristics(apperance(required));
        }

        widget.setPrinted(true);
        page.getAnnotations().add(widget);

    }

    public PDAppearanceCharacteristicsDictionary apperance(boolean required) {
        PDAppearanceCharacteristicsDictionary fieldAppearance
                = new PDAppearanceCharacteristicsDictionary(new COSDictionary());
        fieldAppearance.setBorderColour(new PDColor(new float[]{0, 0, 0}, PDDeviceRGB.INSTANCE));
        //fieldAppearance.setBackground(new PDColor(new float[]{1,1,0}, PDDeviceRGB.INSTANCE));

        return fieldAppearance;
    }

    public PDTextField createTextBox(PDDocument document) {

        PDFont font = PDType1Font.HELVETICA;
        PDResources resources = new PDResources();
        resources.put(COSName.getPDFName("Helv"), font);
        // Add a new AcroForm and add that to the document
        PDAcroForm acroForm = new PDAcroForm(document);
        document.getDocumentCatalog().setAcroForm(acroForm);

        // Add and set the resources and default appearance at the form level
        acroForm.setDefaultResources(resources);

        // Acrobat sets the font size on the form level to be
        // auto sized as default. This is done by setting the font size to '0'
        String defaultAppearanceString = "/Helv 0 Tf 0 g";
        acroForm.setDefaultAppearance(defaultAppearanceString);

        // Add a form field to the form.
        PDTextField textBox = new PDTextField(acroForm);

        // Acrobat sets the font size to 12 as default
        // This is done by setting the font size to '12' on the
        // field level.
        // The text color is set to blue in this example.
        // To use black, replace "0 0 1 rg" with "0 0 0 rg" or "0 g".
        defaultAppearanceString = "/Helv 12 Tf 0 0 0 rg";
        textBox.setDefaultAppearance(defaultAppearanceString);
        textBox.setMultiline(true);

        // add the field to the acroform
        acroForm.getFields().add(textBox);

        return textBox;
    }
}
