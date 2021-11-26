package kar.sot.parser;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

public class ParsingXML {
    static final String FILENAME = "dblp-2021-11-02.xml";
    static final int TEST_NUMBER = 100;

    public static void main(String[] args) throws URISyntaxException, IOException {
        long startTime = System.nanoTime();
        for (int i = 0; i < TEST_NUMBER; i++) {
            List<String> yearTags = new ArrayList<>();
            Set<String> yearTagsSet = new HashSet<>();
            List<Integer> years = new ArrayList<>();
            List<Publication> publications = new ArrayList<>();

            URI uri = ParsingXML.class.getClassLoader().getResource(FILENAME).toURI();
            File f = new File(uri);
            BufferedReader br = new BufferedReader(new FileReader(f));
            String st;
            while ((st = br.readLine()) != null) {
                if (st.contains("<year>")) {
                    yearTags.add(st);
                    yearTagsSet.add(st);
                }
            }

            Iterator<String> iterator = yearTagsSet.iterator();
            while (iterator.hasNext()) {
                String str = iterator.next();
                char[] yearChars = str.toCharArray();
                if (Character.isDigit(yearChars[6]) && Character.isDigit(yearChars[7])      && Character.isDigit(yearChars[8]) && Character.isDigit(yearChars[9])) {
                    String yearStr = new StringBuilder()
                            .append(yearChars[6])
                            .append(yearChars[7])
                            .append(yearChars[8])
                            .append(yearChars[9])
                            .toString();

                    Integer year = Integer.valueOf(yearStr);
                    years.add(year);
                }
            }

            for (Integer y : years) {
                Publication p = new Publication();
                p.setYear(y);
                p.setPCount(1);
                publications.add(p);
            }

            for (String tag : yearTags) {
                int pos = containsYear(publications, tag);
                if (pos > -1) {
                    Publication p = publications.get(pos);
                    int countOfPublications = p.getPCount();
                    p.setPCount(++countOfPublications);
                }
            }

            for (Publication p : publications) {
                System.out.println(p.toString());
            }
        }
        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;
        double executionTimeInSecs = (double) executionTime / 1_000_000_000;
        System.out.println("The Average Execution Time in secs: " + executionTimeInSecs);
    }

    public static int containsYear(List<Publication> ps, String yearTag) {
        for (int i = 0; i < ps.size(); i++) {
            if (yearTag.contains(String.valueOf(ps.get(i).getYear()))) return i;
        }
        return -1;
    }
}
