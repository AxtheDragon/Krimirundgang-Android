package de.hottenstein.krimirundgang;

import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class JsonFormatIntegrityTest {

       public String passingJson ="{\n" +
            "  \"Name\": \"foo\",\n" +
            "  \"Stops\": [\n" +
            "    {\n" +
            "      \"Title\": \"foo title\",\n" +
            "      \"Description\": \"foo description\",\n" +
            "      \"Location\":\n" +
            "        {\n" +
            "           \"Latitude\": 13.37,\n" +
            "           \"Longitude\": 31.337\n" +
            "        },\n" +
            "      \"Content\": \"foo long text\",\n" +
            "      \"Order\": 0\n" +
            "    },\n" +
            "    {\n" +
            "      \"Title\": \"foo title 2\",\n" +
            "      \"Description\": \"foo description 2\",\n" +
            "      \"Location\":\n" +
            "      {\n" +
            "        \"Latitude\": 13.47,\n" +
            "        \"Longitude\": 31.237\n" +
            "      },\n" +
            "      \"Content\": \"foo long text2\",\n" +
            "      \"Order\": 1\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    public String noName ="{\n" +
            "  \"Name\":"+ //Error
            "  \"Stops\": [\n" +
            "    {\n" +
            "      \"Title\": \"foo title\",\n" +
            "      \"Description\": \"foo description\",\n" +
            "      \"Location\":\n" +
            "        {\n" +
            "           \"Latitude\": 13.37,\n" +
            "           \"Longitude\": 31.337\n" +
            "        },\n" +
            "      \"Content\": \"foo long text\",\n" +
            "      \"Order\": 0\n" +
            "    },\n" +
            "    {\n" +
            "      \"Title\": \"foo title 2\",\n" +
            "      \"Description\": \"foo description 2\",\n" +
            "      \"Location\":\n" +
            "      {\n" +
            "        \"Latitude\": 13.47,\n" +
            "        \"Longitude\": 31.237\n" +
            "      },\n" +
            "      \"Content\": \"foo long text2\",\n" +
            "      \"Order\": 1\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    public String noStops ="{\n" +
            "  \"Name\":"+
            "  \"Stops\": " + //Error
            "    {\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    public String noLongitude ="{\n" +
            "  \"Name\": \"foo\",\n" +
            "  \"Stops\": [\n" +
            "    {\n" +
            "      \"Title\": \"foo title\",\n" +
            "      \"Description\": \"foo description\",\n" +
            "      \"Location\":\n" +
            "        {\n" +
            "           \"Latitude\": 13.37,\n" +
            "           \"Longitude\": \n" + //Error
            "        },\n" +
            "      \"Content\": \"foo long text\",\n" +
            "      \"Order\": 0\n" +
            "    },\n" +
            "    {\n" +
            "      \"Title\": \"foo title 2\",\n" +
            "      \"Description\": \"foo description 2\",\n" +
            "      \"Location\":\n" +
            "      {\n" +
            "        \"Latitude\": 13.47,\n" +
            "        \"Longitude\": 31.237\n" +
            "      },\n" +
            "      \"Content\": \"foo long text2\",\n" +
            "      \"Order\": 1\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    public String noLatitude ="{\n" +
            "  \"Name\": \"foo\",\n" +
            "  \"Stops\": [\n" +
            "    {\n" +
            "      \"Title\": \"foo title\",\n" +
            "      \"Description\": \"foo description\",\n" +
            "      \"Location\":\n" +
            "        {\n" +
            "           \"Latitude\": 13.37,\n" +
            "           \"Longitude\": 31.337\n" +
            "        },\n" +
            "      \"Content\": \"foo long text\",\n" +
            "      \"Order\": 0\n" +
            "    },\n" +
            "    {\n" +
            "      \"Title\": \"foo title 2\",\n" +
            "      \"Description\": \"foo description 2\",\n" +
            "      \"Location\":\n" +
            "      {\n" +
            "        \"Latitude\": \n" + //Error
            "        \"Longitude\": 31.237\n" +
            "      },\n" +
            "      \"Content\": \"foo long text2\",\n" +
            "      \"Order\": 1\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    public String noContent ="{\n" +
            "  \"Name\": \"foo\",\n" +
            "  \"Stops\": [\n" +
            "    {\n" +
            "      \"Title\": \"foo title\",\n" +
            "      \"Description\": \"foo description\",\n" +
            "      \"Location\":\n" +
            "        {\n" +
            "           \"Latitude\": 13.37,\n" +
            "           \"Longitude\": 31.337\n" +
            "        },\n" +
            "      \"Content\": \"foo long text\",\n" +
            "      \"Order\": 0\n" +
            "    },\n" +
            "    {\n" +
            "      \"Title\": \"foo title 2\",\n" +
            "      \"Description\": \"foo description 2\",\n" +
            "      \"Location\":\n" +
            "      {\n" +
            "        \"Latitude\": 13.47,\n" +
            "        \"Longitude\": 31.237\n" +
            "      },\n" +
            "      \"Content\": \n" + //Error
            "      \"Order\": 1\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    public String noOrder ="{\n" +
            "  \"Name\": \"foo\",\n" +
            "  \"Stops\": [\n" +
            "    {\n" +
            "      \"Title\": \"foo title\",\n" +
            "      \"Description\": \"foo description\",\n" +
            "      \"Location\":\n" +
            "        {\n" +
            "           \"Latitude\": 13.37,\n" +
            "           \"Longitude\": 31.337\n" +
            "        },\n" +
            "      \"Content\": \"foo long text\",\n" +
            "      \"Order\": 0\n" +
            "    },\n" +
            "    {\n" +
            "      \"Title\": \"foo title 2\",\n" +
            "      \"Description\": \"foo description 2\",\n" +
            "      \"Location\":\n" +
            "      {\n" +
            "        \"Latitude\": 13.47,\n" +
            "        \"Longitude\": 31.237\n" +
            "      },\n" +
            "      \"Content\": \"foo long text2\",\n" +
            "      \"Order\": \n" + //Error
            "    }\n" +
            "  ]\n" +
            "}";

    public String noTitle ="{\n" +
            "  \"Name\": \"foo\",\n" +
            "  \"Stops\": [\n" +
            "    {\n" +
            "      \"Title\": ,\n" + //Error
            "      \"Description\": \"foo description\",\n" +
            "      \"Location\":\n" +
            "        {\n" +
            "           \"Latitude\": 13.37,\n" +
            "           \"Longitude\": 31.337\n" +
            "        },\n" +
            "      \"Content\": \"foo long text\",\n" +
            "      \"Order\": 0\n" +
            "    },\n" +
            "    {\n" +
            "      \"Title\": \"foo title 2\",\n" +
            "      \"Description\": \"foo description 2\",\n" +
            "      \"Location\":\n" +
            "      {\n" +
            "        \"Latitude\": 13.47,\n" +
            "        \"Longitude\": 31.237\n" +
            "      },\n" +
            "      \"Content\": \"foo long text2\",\n" +
            "      \"Order\": \n" +
            "    }\n" +
            "  ]\n" +
            "}";

    public String noDescription ="{\n" +
            "  \"Name\": \"foo\",\n" +
            "  \"Stops\": [\n" +
            "    {\n" +
            "      \"Title\": ,\n" +
            "      \"Description\": \"foo description\",\n" +
            "      \"Location\":\n" +
            "        {\n" +
            "           \"Latitude\": 13.37,\n" +
            "           \"Longitude\": 31.337\n" +
            "        },\n" +
            "      \"Content\": \"foo long text\",\n" +
            "      \"Order\": 0\n" +
            "    },\n" +
            "    {\n" +
            "      \"Title\": \"foo title 2\",\n" +
            "      \"Description\": \"\",\n" + //Error
            "      \"Location\":\n" +
            "      {\n" +
            "        \"Latitude\": 13.47,\n" +
            "        \"Longitude\": 31.237\n" +
            "      },\n" +
            "      \"Content\": \"foo long text2\",\n" +
            "      \"Order\": \n" +
            "    }\n" +
            "  ]\n" +
            "}";

    public String nullDescription ="{\n" +
            "  \"Name\": \"foo\",\n" +
            "  \"Stops\": [\n" +
            "    {\n" +
            "      \"Title\": ,\n" +
            "      \"Description\": ,\n" + //Error
            "      \"Location\":\n" +
            "        {\n" +
            "           \"Latitude\": 13.37,\n" +
            "           \"Longitude\": 31.337\n" +
            "        },\n" +
            "      \"Content\": \"foo long text\",\n" +
            "      \"Order\": 0\n" +
            "    },\n" +
            "    {\n" +
            "      \"Title\": \"foo title 2\",\n" +
            "      \"Description\": \"foo Description 2\",\n" +
            "      \"Location\":\n" +
            "      {\n" +
            "        \"Latitude\": 13.47,\n" +
            "        \"Longitude\": 31.237\n" +
            "      },\n" +
            "      \"Content\": \"foo long text2\",\n" +
            "      \"Order\": \n" +
            "    }\n" +
            "  ]\n" +
            "}";



    @Test
    public void testCheckerPass(){
        Boolean passingResult=JsonChecker.checkJson(passingJson);
        assertTrue(passingResult);
    }
    @Test
    public void testCheckerNoName(){
        Boolean TestResult=JsonChecker.checkJson(noName);
        assertFalse(TestResult);
    }
    @Test
    public void testCheckerNoStops(){
        Boolean TestResult=JsonChecker.checkJson(noStops);
        assertFalse(TestResult);
    }
    @Test
    public void testCheckerNoLongitude(){
        Boolean TestResult=JsonChecker.checkJson(noLongitude);
        assertFalse(TestResult);
    }
    @Test
    public void testCheckerNoLatitude(){
        Boolean TestResult=JsonChecker.checkJson(noLatitude);
        assertFalse(TestResult);
    }
    @Test
    public void testCheckerNoContent(){
        Boolean TestResult=JsonChecker.checkJson(noContent);
        assertFalse(TestResult);
    }
    @Test
    public void testCheckerNoOrder(){
        Boolean TestResult=JsonChecker.checkJson(noOrder);
        assertFalse(TestResult);
    }
    @Test
    public void testCheckerNoTitle(){
        Boolean TestResult=JsonChecker.checkJson(noTitle);
        assertFalse(TestResult);
    }
    @Test
    public void testCheckerNoDescription(){
        Boolean TestResult=JsonChecker.checkJson(noDescription);
        assertFalse(TestResult);
    }
    @Test
    public void testCheckerNullDescription(){
        Boolean TestResult=JsonChecker.checkJson(nullDescription);
        assertFalse(TestResult);
    }

}
