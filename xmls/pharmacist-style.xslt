
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" />
  <xsl:template match="/">
        <html>
            <body>
                <h2>Pharmacist Details</h2>
                <table border="1">
                    <tr>
                        <th>Name</th>
                        <td><xsl:value-of select="pharmacist/@name"/></td>
                    </tr>
                    <tr>
                        <th>Surname</th>
                        <td><xsl:value-of select="pharmacist/@surname"/></td>
                    </tr>
                    <tr>
                        <th>Phone Number</th>
                        <td><xsl:value-of select="pharmacist/phone_number"/></td>
                    </tr>
                    <tr>
                        <th>Email</th>
                        <td><xsl:value-of select="pharmacist/email"/></td>
                    </tr>
                    <tr>
                        <th>Medicines Created</th>
                        <td>
                            <xsl:for-each select="pharmacist/medicines_created/medicine">
                                <p>Medicine Name: <xsl:value-of select="name"/></p>
                                <p>Price: <xsl:value-of select="price"/></p>
                                <p>Stock: <xsl:value-of select="stock"/></p>
                                <p>Instructions: <xsl:value-of select="instructions"/></p>
                            </xsl:for-each>
                        </td>
                    </tr>
                    <tr>
                        <th>Orders</th>
                        <td>
                            <xsl:for-each select="pharmacist/orders/order">
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