<%@ page import="java.io.IOException" %>
<%@ page import="javax.naming.*" %>
<HTML>
<HEAD>
    <TITLE>Jndi context</TITLE>
</HEAD>
<BODY BGCOLOR="#ffffff">
<H2 style="text-align: center;">Inventory System</H2>
<%!

    void listContext(Context ctx, java.lang.String indent, javax.servlet.jsp.JspWriter out) throws IOException {
        try {
            NamingEnumeration list = ctx.listBindings("");
            while (list.hasMore()) {
                Binding item = (Binding) list.next();
                Object o = item.getObject();
                if (o instanceof Context) {
                    String name = item.getName();
                    out.println(indent+name+"/"+"<br/>");
                    listContext((Context) o, indent + name +"/", out);
                } else {
                    String className = item.getClassName();
                    String name = item.getName();
                    out.println(indent + name + " => " + className+" <br/>");
                }
            }
        } catch (NamingException ex) {
            out.println("JNDI failure: " + ex.getMessage());
        }
    }

%>
<p>
<ul>
    <%
        InitialContext ctx = new InitialContext();
        listContext(ctx, "  ", out);
        Object lookup = ctx.lookup("java:comp/env/envPropertiePath");
        out.println(lookup.toString());
    %>
</ul>
</p>


</BODY>
</HTML>