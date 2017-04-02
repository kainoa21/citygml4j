//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2017.03.28 um 08:23:51 PM CEST 
//


package net.opengis.gml;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Represents a coordinate tuple in one, two, or three dimensions. Deprecated with GML 3.0 and replaced by 
 * 			DirectPositionType.
 * 
 * <p>Java-Klasse für CoordType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="CoordType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="X" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="Y" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Z" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CoordType", propOrder = {
    "x",
    "y",
    "z"
})
public class CoordType {

    @XmlElement(name = "X", required = true)
    protected BigDecimal x;
    @XmlElement(name = "Y")
    protected BigDecimal y;
    @XmlElement(name = "Z")
    protected BigDecimal z;

    /**
     * Ruft den Wert der x-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getX() {
        return x;
    }

    /**
     * Legt den Wert der x-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setX(BigDecimal value) {
        this.x = value;
    }

    public boolean isSetX() {
        return (this.x!= null);
    }

    /**
     * Ruft den Wert der y-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getY() {
        return y;
    }

    /**
     * Legt den Wert der y-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setY(BigDecimal value) {
        this.y = value;
    }

    public boolean isSetY() {
        return (this.y!= null);
    }

    /**
     * Ruft den Wert der z-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getZ() {
        return z;
    }

    /**
     * Legt den Wert der z-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setZ(BigDecimal value) {
        this.z = value;
    }

    public boolean isSetZ() {
        return (this.z!= null);
    }

}
