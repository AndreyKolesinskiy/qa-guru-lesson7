package tests;

import com.codeborne.pdftest.PDF;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static com.codeborne.pdftest.assertj.Assertions.assertThat;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PdfTests {

    @Test
    public void pdfFileGitHubTest() throws IOException {
        open("https://github.com/AndreyKolesinskiy/qa-guru-lesson7/blob/master/src/test/resources/TestPdfFile.pdf");
        File downloadedPdfFile = $("#raw-url").download();
        PDF parsedPdf = new PDF(downloadedPdfFile);
        assertThat(parsedPdf.text).contains("Selenide = UI Testing Framework powered by Selenium WebDriver");
    }

    @Test
    public void pdfFileResourcesTest() throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream stream = classLoader.getResourceAsStream("TestPdfFile.pdf");
        PDDocument pdfDocument = PDDocument.load(stream);
        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        String pdfContent = pdfTextStripper.getText(pdfDocument);
        assertThat(pdfContent).contains("Selenide = UI Testing Framework powered by Selenium WebDriver");
    }
}
