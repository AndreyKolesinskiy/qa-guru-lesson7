package tests;

import com.codeborne.selenide.Selenide;
import com.codeborne.xlstest.XLS;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import static com.codeborne.selenide.Selenide.$;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class XlsxTests {

    @Test
    public void xlsxFileGithubTest() throws FileNotFoundException {
        Selenide.open("https://github.com/AndreyKolesinskiy/qa-guru-lesson7/blob/master/src/test/resources/TestXlsxFile.xlsx");
        File downloadedXlsxFile = $("#raw-url").download();
        XLS parsedXlsx = new XLS(downloadedXlsxFile);
        assertThat(parsedXlsx.excel.getSheetAt(0).getRow(0).getCell(0).getStringCellValue())
                .contains("Selenide = UI Testing Framework powered by Selenium WebDriver");
    }

    @Test
    public void xlsxFileResourcesTest() throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream stream = classLoader.getResourceAsStream("TestXlsxFile.xlsx");
        assert stream != null;
        XSSFWorkbook xlsxFile = new XSSFWorkbook(stream);
        assertThat(xlsxFile.getSheetAt(0).getRow(0).getCell(0).getStringCellValue())
                .contains("Selenide = UI Testing Framework powered by Selenium WebDriver");
    }
}
