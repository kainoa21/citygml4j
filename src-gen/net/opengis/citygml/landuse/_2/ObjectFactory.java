//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2017.03.28 um 08:23:51 PM CEST 
//


package net.opengis.citygml.landuse._2;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the net.opengis.citygml.landuse._2 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName __GenericApplicationPropertyOfLandUse_QNAME = new QName("http://www.opengis.net/citygml/landuse/2.0", "_GenericApplicationPropertyOfLandUse");
    private final static QName _LandUse_QNAME = new QName("http://www.opengis.net/citygml/landuse/2.0", "LandUse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: net.opengis.citygml.landuse._2
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link LandUseType }
     * 
     */
    public LandUseType createLandUseType() {
        return new LandUseType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/citygml/landuse/2.0", name = "_GenericApplicationPropertyOfLandUse")
    public JAXBElement<Object> create_GenericApplicationPropertyOfLandUse(Object value) {
        return new JAXBElement<Object>(__GenericApplicationPropertyOfLandUse_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LandUseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/citygml/landuse/2.0", name = "LandUse", substitutionHeadNamespace = "http://www.opengis.net/citygml/2.0", substitutionHeadName = "_CityObject")
    public JAXBElement<LandUseType> createLandUse(LandUseType value) {
        return new JAXBElement<LandUseType>(_LandUse_QNAME, LandUseType.class, null, value);
    }

}
