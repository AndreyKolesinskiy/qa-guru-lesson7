package tests;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class ZipTests {

    static final String PASSWORD = "1234";
    static final String UNZIP_FILE_PATH = "./src/test/resources/files/unzip";
    static final String UNZIPPED_FILE_PATH = "./src/test/resources/files/unzip/TestTxtFile.txt";

    @Test
    public void zipResourcesTest() throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream stream = classLoader.getResourceAsStream("TestTxtFile.zip");
        String entryAsString = null;
        assert stream != null;
        try (ZipInputStream zipInputStream = new ZipInputStream(stream, StandardCharsets.UTF_8)) {
            while ((zipInputStream.getNextEntry()) != null) {
                entryAsString = IOUtils.toString(zipInputStream, StandardCharsets.UTF_8);
            }
        }
        assertThat(entryAsString).contains("Selenide = UI Testing Framework powered by Selenium WebDriver");
    }

    @Test
    public void zipResourcesWithPasswordTest() throws ZipException, IOException {
        ZipFile zipFile = new ZipFile("./src/test/resources/TestTxtFileWithPassword.zip");
        if (zipFile.isEncrypted()) {
            zipFile.setPassword(PASSWORD);
        }
        zipFile.extractAll(UNZIP_FILE_PATH);
        File unzippedFile = new File(UNZIPPED_FILE_PATH);
        String fileContent = FileUtils.readFileToString(unzippedFile, StandardCharsets.UTF_8);
        assertThat(fileContent).contains("Selenide = UI Testing Framework powered by Selenium WebDriver");
    }
}
