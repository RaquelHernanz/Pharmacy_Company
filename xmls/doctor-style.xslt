
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" />
    <xsl:template match="/">
        <html>
        <body>
            <h2>Doctor Details</h2>
            <table border="1">
                <tr>
                    <th>Name</th>
                    <td><xsl:value-of select="doctor/@name"/></td>
                </tr>
                <tr>
                    <th>Surname</th>
                    <td><xsl:value-of select="doctor/@surname"/></td>
                </tr>
                <tr>
                    <th>Address</th>
                    <td><xsl:value-of select="doctor/address"/></td>
                </tr>
                <tr>
                    <th>Phone Number</th>
                    <td><xsl:value-of select="doctor/phone_number"/></td>
                </tr>
                <tr>
                    <th>Email</th>
                    <td><xsl:value-of select="doctor/email"/></td>
                </tr>
                <tr>
                    <th>Medicines</th>
                    <td>
                        <xsl:for-each select="doctor/medicines/medicine">
                            <p>Medicine Name: <xsl:value-of select="name"/></p>
                            <p>Price: <xsl:value-of select="price"/></p>
                            <p>Stock: <xsl:value-of select="stock"/></p>
                            <p>Instructions: <xsl:value-of select="instructions"/></p>
                        </xsl:for-each>
                    </td>
                </tr>
            </table>
        </body>
        </html>
    </xsl:template>

</xsl:stylesheet>