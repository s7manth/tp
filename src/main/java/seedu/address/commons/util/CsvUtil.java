package seedu.address.commons.util;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CsvUtil {

    public static boolean isTheFileConformingToHeaders(Path path, String expectedCsvHeaders) throws IOException, CsvValidationException {
        CSVReader csvReader = new CSVReader(new FileReader(String.valueOf(path)));
        csvReader.skip(2);
        String[] allColumnNames = csvReader.readNext();

        Set<String> setOfColumnNames = new HashSet<>(Arrays.asList(allColumnNames));

        String[] expectedColumnNames = expectedCsvHeaders.split(",");

        for (String h : expectedColumnNames) {
            if (!setOfColumnNames.contains(h)) {
                return false;
            }
        }

        return true;
    }

    public static List<String> extractColumn(String header, Path path) throws IOException, CsvValidationException {
        CSVReader csvReader = new CSVReader(new FileReader(String.valueOf(path)));
        csvReader.skip(2);
        String[] allColumnNames = csvReader.readNext();
        int positionOfHeader = Arrays.asList(allColumnNames).indexOf(header);

        List<String> toReturn = new ArrayList<>();

        String[] line;
        while ((line = csvReader.readNext()) != null) {
            toReturn.add(line[positionOfHeader].trim());
        }

        return toReturn;
    }
}
