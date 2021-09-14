package org.rates;

import org.jdom2.Document;
import org.junit.Test;

import static org.junit.Assert.assertTrue;


/**
 * Unit test for {@link org.rates.Parser} class.
 */
public class ParserTest
{
    private final String url = "https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml";
    Parser parser = new Parser();

    /**
     * Sanity test that Document object is not null.
     */
    @Test
    public void buildDocumentTest()
    {
        Document doc = parser.buildDocument(url);
        assertTrue(doc != null);
    }
}
