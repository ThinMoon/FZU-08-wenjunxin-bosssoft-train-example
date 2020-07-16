package com.bosssoft.hr.train.xml;

import com.bosssoft.hr.train.pojo.Student;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

/**
 * @author 瘦明月
 * 使用dom解析方式
 * 一次性读取XML，并在内存中表示为树形结构
 */
@Slf4j
public class DOMOperation implements XMLOperation<Student> {

    private static final String PATH = "src/main/resources/student.tld";


    /**
     * 获取document对象
     * @return
     */
    public Document getDoc() {
        /* 1.创建解析器工厂 */
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        /* 2.通过解析器工厂得到解析器 */
        DocumentBuilder db = null;
        try {
            db = dbf.newDocumentBuilder();
            return db.parse(PATH);
        } catch (Exception e) {
            log.error("xml文件解析失败：{}", e);
        }
        return null;

    }

    /**
     * 从内存写到文档做同步操作
     * @param document
     */
    public void flushXML(Document document) {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        try {
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(PATH));
            transformer.transform(domSource, streamResult);
        } catch (TransformerException e) {
            log.error("xml文件刷写失败：{}", e);
        }
    }


    @Override
    public boolean create(Student object) {

        Integer userId = object.getId();
        Document document = getDoc();
        if (userId != null) {
            //student节点
            Element element = document.createElement("student");
            element.setAttribute("id", userId.toString());

            //student节点下name节点
            Element name = document.createElement("name");
            name.setTextContent(object.getName());
            //student节点下age节点
            Element age = document.createElement("age");
            age.setTextContent(object.getAge().toString());
            //注意不能连续追加
            element.appendChild(name);
            element.appendChild(age);

            document.getFirstChild().appendChild(element);

            //刷写
            flushXML(document);
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Student object) {

        Document doc = getDoc();
        Integer id = object.getId();

        if (id != null) {
            //获取到student标签节点列表
            NodeList students = doc.getElementsByTagName("student");
            for (int i = 0; i < students.getLength(); i++) {
                Element item = (Element) students.item(i);
                //判断id是否相同 若相同则为我们需要删除的节点
                if(id.equals(Integer.parseInt(item.getAttribute("id")))) {
                    //获取到父节点 利用父节点删除子节点
                    item.getParentNode().removeChild(item);
                    //更新
                    flushXML(doc);
                    return true;
                }
            }

        }

        return false;
    }

    @Override
    public boolean update(Student object) {
        Document doc = getDoc();
        Integer id = object.getId();

        if (id != null) {
            //获取到student标签节点列表
            NodeList students = doc.getElementsByTagName("student");
            for (int i = 0; i < students.getLength(); i++) {
                Element item = (Element) students.item(i);
                //判断id是否相同 若相同则为我们需要更新的节点
                if(id.equals(Integer.parseInt(item.getAttribute("id")))) {

                    item.getElementsByTagName("name").item(0).setTextContent(object.getName());
                    item.getElementsByTagName("age").item(0).setTextContent(object.getAge().toString());
                    //更新
                    flushXML(doc);
                    return true;
                }
            }

        }

        return false;
    }

    @Override
    public Student query(Student object) {

        Document doc = getDoc();

        if (object.getId() != null) {
            NodeList students = doc.getElementsByTagName("student");
            for (int i = 0; i < students.getLength(); i++) {
                Element item = (Element) students.item(i);
                if (object.getId().equals(Integer.parseInt(item.getAttribute("id")))) {
                    String name = item.getElementsByTagName("name").item(0).getTextContent();
                    String age = item.getElementsByTagName("age").item(0).getTextContent();
                    object.setName(name);
                    object.setAge(Integer.parseInt(age));
                    return object;
                }
            }
        }

        return object;
    }
}
