<%-- 
    Document   : results
    Created on : May 19, 2023, 1:00:59 AM
    Author     : Ali
--%>

<%@page import="java.io.StringWriter"%>
<%@page import="java.io.StringReader"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="javax.xml.transform.*, javax.xml.transform.stream.*"%>
<%
    String xml = (String) request.getAttribute("xml");
    Source source = new StreamSource(new StringReader(xml));
    TransformerFactory transformerFactory = TransformerFactory.newInstance();
    Transformer transformer = transformerFactory.newTransformer();
    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    StringWriter writer = new StringWriter();
    transformer.transform(source, new StreamResult(writer));
    String formattedXml = writer.toString();
%>
<html>
    <head>
        <title>Results</title>
    </head>
    <body>
        <h1>Results</h1>
        <pre><%=formattedXml%></pre>
    </body>
</html>
