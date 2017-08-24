
package cn.com.webxml.trans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TranslatorSentenceStringResult" type="{http://WebXml.com.cn/}ArrayOfString" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "translatorSentenceStringResult"
})
@XmlRootElement(name = "TranslatorSentenceStringResponse")
public class TranslatorSentenceStringResponse {

    @XmlElement(name = "TranslatorSentenceStringResult")
    protected ArrayOfString translatorSentenceStringResult;

    /**
     * ��ȡtranslatorSentenceStringResult���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getTranslatorSentenceStringResult() {
        return translatorSentenceStringResult;
    }

    /**
     * ����translatorSentenceStringResult���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setTranslatorSentenceStringResult(ArrayOfString value) {
        this.translatorSentenceStringResult = value;
    }

}
