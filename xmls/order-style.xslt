<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" />
    <xsl:template match="/">
        <html>
        <body>
            <h2>Order Details</h2>
            <table border="1">
                <tr>
                    <th>Quantity</th>
                    <td><xsl:value-of select="order/quantity"/></td>
                </tr>
                <tr>
                    <th>Total Price</th>
                    <td><xsl:value-of select="order/totalprice"/></td>
                </tr>
            </table>
        </body>
        </html>
    </xsl:template>

</xsl:stylesheet>