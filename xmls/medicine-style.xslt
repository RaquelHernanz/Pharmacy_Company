<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" />
    <xsl:template match="/">
        <html>
        <body>
            <h2>Medicine Details</h2>
            <table border="1">
                <tr>
                    <th>Name</th>
                    <td><xsl:value-of select="medicine/name"/></td>
                </tr>
                <tr>
                    <th>Instructions</th>
                    <td><xsl:value-of select="medicine/instructions"/></td>
                </tr>
                <tr>
                    <th>Price</th>
                    <td><xsl:value-of select="medicine/price"/></td>
                </tr>
                <tr>
                    <th>Stock</th>
                    <td><xsl:value-of select="medicine/stock"/></td>
                </tr>
                <tr>
                    <th>Expirations</th>
                    <td><xsl:value-of select="medicine/expirations"/></td>
                </tr>
                <tr>
                    <th>Orders</th>
                    <td>
                        <xsl:for-each select="medicine/orders/order">
                            <p>Order Quantity: <xsl:value-of select="quantity"/></p>
                            <p>Total Price: <xsl:value-of select="totalprice"/></p>
                        </xsl:for-each>
                    </td>
                </tr>
            </table>
        </body>
        </html>
    </xsl:template>

</xsl:stylesheet>