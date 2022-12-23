<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">

    <xsl:output method="html"/>

    <xsl:template match="/">
        <html>
            <head>
                <style>

                    table {
                    background: #bbbbbb;
                    }

                    th {
                    color: black;
                    padding: 5px 5px;
                    background: grey;
                    }

                    td {
                    color: black;
                    padding: 5px 5px;
                    background: #88aabb;
                    }

                </style>
            </head>
            <body>
                <table>
                    <tr>
                        <td align="center"><strong>fileName</strong></td>
                        <td align="center"><strong>rootElementName</strong></td>
                        <td align="center"><strong>charCount</strong></td>
                        <td align="center"><strong>nestingDepth</strong></td>
                    </tr>

                    <xsl:for-each select="InstancesOfXmlSpecifications/XmlFileSpecifications">
                        <tr>
                            <td><xsl:value-of select="@fileName"/></td>
                            <td><xsl:value-of select="rootElementName"/></td>
                            <td><xsl:value-of select="charCount"/></td>
                            <td><xsl:value-of select="nestingDepth"/></td>
                        </tr>
                    </xsl:for-each>

                    <tr>
                        <td>Total chars: </td>
                        <td><xsl:value-of select="sum(InstancesOfXmlSpecifications/XmlFileSpecifications/charCount)"/></td>
                    </tr>

                    <tr>
                        <td>Average nesting depth:</td>
                        <td><xsl:value-of select="sum(InstancesOfXmlSpecifications/XmlFileSpecifications/nestingDepth) div count (InstancesOfXmlSpecifications/XmlFileSpecifications)"/></td>
                    </tr>
                    
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>