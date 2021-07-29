package tests;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class DocxTests {

    @Test
    public void docxResourcesTest() throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream stream = classLoader.getResourceAsStream("TestDocxFile.docx");
        assert stream != null;
        XWPFDocument document = new XWPFDocument(stream);
        XWPFWordExtractor xwpfWordExtractor = new XWPFWordExtractor(document);
        String docxContent = xwpfWordExtractor.getText();
        Assertions.assertThat(docxContent).contains("Selenide = UI Testing Framework powered by Selenium WebDriver");
    }
}
