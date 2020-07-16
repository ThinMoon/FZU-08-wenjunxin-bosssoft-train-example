package com.bosssoft.hr.train.xml;

import com.bosssoft.hr.train.pojo.Student;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@Slf4j
public class SAXOperation implements XMLOperation<Student> {

    private static final String PATH = "src/main/resources/student.tld";
    private static final String STUDENT="student";
    /**
     *  封装document
     */
    public Document getDocument() {
        //1、创建解析器
        SAXReader saxReader = new SAXReader();
        try {
            return saxReader.read(new File(PATH));
        } catch (DocumentException e) {
            log.error("xml文件解析失败：{}", e);
        }
        return null;
    }

    /**
     * 刷新xml
     * @param document
     */
    public void flushXML(Document document) {
        OutputFormat outputFormat = OutputFormat.createPrettyPrint();
        try {
            XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(new File(PATH)), outputFormat);
            xmlWriter.write(document);
            xmlWriter.close();
        } catch (IOException e) {
            log.error("xml文件刷写失败：{}", e);
        }
    }



    @Override
    public boolean create(Student object) {

        if (object != null) {
            Document document = getDocument();
            //获取根节点
            Element rootElement = document.getRootElement();
            //增加元素
            Element student = rootElement.addElement("student");
            //设置元素属性
            student.addAttribute("id", object.getId().toString());
            student.addElement("name").setText(object.getName());
            student.addElement("age").setText(object.getAge().toString());

            flushXML(document);
            return true;
        }

        return false;
    }

    @Override
    public boolean remove(Student object) {

        Integer id = object.getId();
        if (id != null) {
            Document document = getDocument();
            Element rootElement = document.getRootElement();
            Iterator iterator = rootElement.elementIterator();
            while (iterator.hasNext()) {
                Element student = (Element) iterator.next();
                if (id.equals(Integer.parseInt(student.attribute("id").getText()))) {
                    iterator.remove();
                    flushXML(document);
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean update(Student object) {

        Integer id = object.getId();
        if (id != null) {
            Document document = getDocument();
            Element rootElement = document.getRootElement();
            Iterator iterator = rootElement.elementIterator();
            while (iterator.hasNext()) {
                Element student = (Element) iterator.next();
                if (id.equals(Integer.parseInt(student.attribute("id").getText()))) {
                    student.element("name").setText(object.getName());
                    student.element("age").setText(object.getAge().toString());
                    flushXML(document);
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public Student query(Student object) {

        Integer id = object.getId();
        if (id != null) {
            Document document = getDocument();
            Element rootElement = document.getRootElement();
            Iterator iterator = rootElement.elementIterator();
            while (iterator.hasNext()) {
                Element student = (Element) iterator.next();
                if (id.equals(Integer.parseInt(student.attribute("id").getText()))) {
                    object.setName(student.element("name").getText());
                    object.setAge(Integer.parseInt(student.element("age").getText()));
                    flushXML(document);
                    return object;
                }
            }
        }
        return null;
    }
}
