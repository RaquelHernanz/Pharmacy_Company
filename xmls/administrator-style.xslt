<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" />
    <xsl:template match="/">
        <html>
        <body>
            <h2>Administrator Details</h2>
            <table border="1">
                <tr>
                    <th>Name</th>
                    <td><xsl:value-of select="administrator/@name"/></td>
                </tr>
                <tr>
                    <th>Surname</th>
                    <td><xsl:value-of select="administrator/@surname"/></td>
                </tr>
                <tr>
                    <th>Phone Number</th>
                    <td><xsl:value-of select="administrator/phone_number"/></td>
                </tr>
                <tr>
                    <th>Email</th>
                    <td><xsl:value-of select="administrator/email"/></td>
                </tr>
                <tr>
                    <th>Orders</th>
                    <td>
                        <xsl:for-each select="administrator/orders/order">
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