//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.08.25 at 11:03:15 AM EDT 
//


package com.silverpop.api.client.command.elements;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RuleElementType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RuleElementType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RULE_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PRIORITY" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="CRITERIA" type="{SilverpopApi:EngageService.MailManagement.UserActions}CriteriaElementType" minOccurs="0"/>
 *         &lt;element name="CONTENTS" type="{SilverpopApi:EngageService.MailManagement.UserActions}String" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XStreamAlias("RULE")
public class RuleElementType {

    @XStreamAlias("RULE_NAME")
    protected String rulename;
    @XStreamAlias("PRIORITY")
    protected int priority;
    @XStreamAlias("CRITERIA")
    protected CriteriaElementType criteria;
    @XStreamAlias("CONTENTS")
    protected ContentsElementType contents;

    public RuleElementType()
    {
        this.criteria = new CriteriaElementType();
        this.contents = new ContentsElementType();
    }

    /**
     * Gets the value of the rulename property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRULENAME() {
        return rulename;
    }

    /**
     * Sets the value of the rulename property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRULENAME(String value) {
        this.rulename = value;
    }

    /**
     * Gets the value of the priority property.
     * 
     */
    public int getPRIORITY() {
        return priority;
    }

    /**
     * Sets the value of the priority property.
     * 
     */
    public void setPRIORITY(int value) {
        this.priority = value;
    }

    /**
     * Gets the value of the criteria property.
     * 
     * @return
     *     possible object is
     *     {@link CriteriaElementType }
     *     
     */
    public CriteriaElementType getCRITERIA() {
        return criteria;
    }

    /**
     * Sets the value of the criteria property.
     * 
     * @param value
     *     allowed object is
     *     {@link CriteriaElementType }
     *     
     */
    public void setCRITERIA(CriteriaElementType value) {
        this.criteria = value;
    }

    /**
     * Gets the value of the contents property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public ContentsElementType getCONTENTS() {
        return contents;
    }

    /**
     * Sets the value of the contents property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCONTENTS(ContentsElementType value) {
        this.contents = value;
    }

}
