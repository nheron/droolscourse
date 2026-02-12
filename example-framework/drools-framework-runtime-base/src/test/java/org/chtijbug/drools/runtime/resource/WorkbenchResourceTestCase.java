package org.chtijbug.drools.runtime.resource;

/**
 * Created with IntelliJ IDEA.
 * User: samuel
 * Date: 26/09/12
 * Time: 18:01
 */
public class WorkbenchResourceTestCase {
    /**
     @Test public void testRemoveTrailingSlash()  {
     String expected = "//String";

     String toEval = WorkbenchResource.removeTrailingSlash("//String/");
     assertEquals("Remove trailind '/' operation failed", expected, toEval);
     }


     @Test public void testGetWebResourceUrl()  {
     WorkbenchResource toTest = new WorkbenchResource("http://localhost:8080/", "drools-guvnor", "amag/", "LATEST", "tomcat", "tomcat");
     String expected = "http://localhost:8080/drools-guvnor/org.drools.guvnor.Guvnor/package/amag/LATEST";

     String toEval = toTest.getWebResourceUrl();
     assertEquals("Expected webresource url not match", expected, toEval);
     }

     @Test public void testGetResource() {
     WorkbenchResource toTest = new WorkbenchResource("http://localhost:8080/", "drools-guvnor", "amag/", "LATEST", "tomcat", "tomcat");

     final String expectedChangeSet = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
     "<change-set xmlns=\"http://drools.org/drools-5.0/change-set\"" +
     "            xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"" +
     "            xsi:schemaLocation=\"http://drools.org/drools-5.0/change-set changeset-1.0.0.xsd\">" +
     "    <add>" +
     "        <resource" +
     "                type=\"PKG\"" +
     "                source=\"http://localhost:8080/drools-guvnor/org.drools.guvnor.Guvnor/package/amag/LATEST\"" +
     "                basicAuthentication=\"enabled\"" +
     "                username=\"tomcat\"" +
     "                password=\"tomcat\">" +
     "        </resource>" +
     "    </add>" +
     "</change-set>";
     try {
     Resource toEval = toTest.getResource();

     InputStreamReader reader = new InputStreamReader(toEval.getInputStream());
     BufferedReader bufferedReader = new BufferedReader(reader);
     StringWriter writer = new StringWriter();
     String line;
     while ( (line=bufferedReader.readLine()) != null ){
     writer.write(line);
     }
     String generatedChangeSet = writer.toString();
     assertEquals("Generated Change set does not match with the expected one.", expectedChangeSet, generatedChangeSet);
     } catch (Exception e) {
     e.printStackTrace();
     fail();
     }
     }

     public static String prettyFormat(String input, int indent) {
     try {
     Source xmlInput = new StreamSource(new StringReader(input));
     StringWriter stringWriter = new StringWriter();
     StreamResult xmlOutput = new StreamResult(stringWriter);
     TransformerFactory transformerFactory = TransformerFactory.newInstance();
     transformerFactory.setAttribute("indent-number", indent);
     Transformer transformer = transformerFactory.newTransformer();
     transformer.setOutputProperty(OutputKeys.INDENT, "yes");
     transformer.transform(xmlInput, xmlOutput);
     return xmlOutput.getWriter().toString();
     } catch (Exception e) {
     throw new RuntimeException(e);
     }
     }

     */
}
