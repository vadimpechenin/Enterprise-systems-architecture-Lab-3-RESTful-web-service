<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <body>
                <H1>List</H1>
                <table border="1">
                    <tr bgcolot="#9acd32">
                        <th>Id</th>
                        <th>Type</th>
                    </tr>
                    <xsl:for-each select="List/item">
                        <tr>
                            <td><xsl:value-of select="id"/></td>
                            <td><xsl:value-of select="type"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>