package guru.qa.filetest;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import guru.qa.filetest.model.Employee;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class FileTest {
    ClassLoader cl = FileTest.class.getClassLoader();


    @Test
    void zipParseTest() throws Exception {
        try (
                InputStream is = cl.getResourceAsStream("example/archive.zip");
                ZipInputStream zis = new ZipInputStream(is)
        ) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().contains(".pdf") && !entry.getName().contains("MACOSX")) {  // особенность файловой системы mac os
                    System.out.println("PDF = " + entry.getName());
                    PDF content = new PDF(zis);
                    assertThat(content.text).contains("Рекомендации перед собеседованием:");


                } else if (entry.getName().contains(".xlsx") && !entry.getName().contains("MACOSX")) {
                    System.out.println("XLSX = " + entry.getName());
                    XLS content = new XLS(zis);
                    assertThat(content.excel.getSheetAt(0).getRow(3).getCell(1).getStringCellValue()).contains("Основная з/плата");


                } else if (entry.getName().contains(".csv") && !entry.getName().contains("MACOSX")) {
                    System.out.println("CSV = " + entry.getName());
                    CSVReader reader = new CSVReader(new InputStreamReader(zis));
                    List<String[]> content = reader.readAll();
                    assertThat(content.get(3)[0]).contains("johnson81");
                }
            }
        }
    }

    @Test
    void jsonParseTest() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File employeeJsonfile = new File("src/test/resources/employee.json");
        Employee employee = objectMapper.readValue(employeeJsonfile, Employee.class);

        assertThat(employee.getName()).contains("Aleix Melon");
        assertThat(employee.getRole()).contains("Dev");
        assertThat(employee.married).isFalse();
        assertThat(employee.address.getCountry()).contains("Austria");


    }


}