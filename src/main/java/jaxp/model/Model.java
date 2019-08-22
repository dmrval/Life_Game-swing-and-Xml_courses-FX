package jaxp.model;

import jaxp.entity.*;
import org.apache.xerces.dom.NodeImpl;
import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Model {

    public University startModel(String path) {
        Node node = load(path);
        if (node == null) {
            University tmp = new University();
            return tmp;
        }

        NodeImpl universityNodeList = (NodeImpl) node.getChildNodes();
        NodeList studentsNodes = universityNodeList.item(0).getChildNodes();
        University university = new University();
        university.setStudentList(parseStudents(studentsNodes));
        return university;
    }

    private List<Student> parseStudents(NodeList nodeList) {
        List<Student> studentList = new ArrayList<Student>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (!nodeList.item(i).getNodeName().equals("#text")) {
                Student student = new Student();
                NodeList studentNode = nodeList.item(i).getChildNodes();
                for (int j = 0; j < studentNode.getLength(); j++) {
                    Node temp = studentNode.item(j);
                    if (!temp.getNodeName().equals("#text")) {
                        String currentValue = temp.getTextContent();
                        if (checkContent("FullName", temp)) {
                            student.setFullName(currentValue);
                        }
                        if (checkContent("City", temp)) {
                            student.setCity(currentValue);
                        }
                        if (checkContent("Email", temp)) {
                            student.setEmail(currentValue);
                        }
                        if (checkContent("TrainingStartDate", temp)) {
                            student.setTrainingStartDate(currentValue);
                        }
                        if (checkContent("SignedAcontract", temp)) {
                            student.setSignedAcontract(Boolean.parseBoolean(currentValue));
                        }
                        if (checkContent("Program", temp)) {
                            student.setProgram(parseProgram(temp.getChildNodes()));
                        }
                    }
                }
                studentList.add(student);
            }
        }
        return studentList;
    }

    private boolean checkContent(String name, Node node) {
        if (node.getNodeName().equals(name)) {
            return true;
        } else return false;
    }


    private Program parseProgram(NodeList programNode) {
        Program prog = new Program();
        for (int j = 0; j < programNode.getLength(); j++) {
            Node temp = programNode.item(j);
            if (!temp.getNodeName().equals("#text")) {
                String currentVal = temp.getTextContent();
                if (checkContent("ProgName", temp)) {
                    prog.setProgName(currentVal);
                }
                if (checkContent("ProgAuthor", temp)) {
                    prog.setProgAuthor(currentVal);
                }
                if (checkContent("ProgCreationDate", temp)) {
                    prog.setCreationDate(currentVal);
                }
                if (checkContent("Courses", temp)) {
                    prog.setCourseList(parseCoursesList(temp.getChildNodes()));
                }
            }
        }
        return prog;
    }

    private List<Course> parseCoursesList(NodeList childNode) {
        List<Course> courseArrayList = new ArrayList<Course>();
        for (int j = 0; j < childNode.getLength(); j++) {
            Node temp = childNode.item(j);
            if (!temp.getNodeName().equals("#text")) {
                courseArrayList.add(parseCourse(temp.getChildNodes()));
            }
        }
        return courseArrayList;
    }

    private Course parseCourse(NodeList courceNode) {
        Course course = new Course();
        Element childEl = (Element) courceNode;
        String idValue = childEl.getAttribute("id");
        course.setId(Integer.parseInt(idValue));
        for (int j = 0; j < courceNode.getLength(); j++) {
            Node temp = courceNode.item(j);
            if (!temp.getNodeName().equals("#text")) {
                String currentVal = temp.getTextContent();
                if (checkContent("CourceName", temp)) {
                    course.setCourceName(currentVal);
                }
                if (checkContent("CourceAuthor", temp)) {
                    course.setCourceAuthor(currentVal);
                }
                if (checkContent("CourceCreationDate", temp)) {
                    course.setCourceCreationDate(currentVal);
                }
                if (checkContent("Tasks", temp)) {
                    course.setTaskList(parseTaskList(temp.getChildNodes()));
                }
            }
        }
        return course;
    }

    private List<Task> parseTaskList(NodeList childNodes) {
        List<Task> taskArrayList = new ArrayList<Task>();
        for (int j = 0; j < childNodes.getLength(); j++) {
            Node temp = childNodes.item(j);
            if (!temp.getNodeName().equals("#text")) {
                taskArrayList.add(parseTask(temp.getChildNodes()));
            }
        }
        return taskArrayList;
    }

    private Task parseTask(NodeList taskNode) {
        Task task = new Task();
        Element childEl = (Element) taskNode;
        String hourseValue = childEl.getAttribute("hours");
        String typeValue1 = childEl.getAttribute("Theory");
        String typeValue2 = childEl.getAttribute("Practical");
        if (!hourseValue.equals("")) {
            task.setHourse(Integer.parseInt(hourseValue));
        }
        if (!typeValue1.equals("") || !typeValue2.equals(""))
            task.setTypeTask(new TypeTask(typeValue1, typeValue2));
        for (int j = 0; j < taskNode.getLength(); j++) {
            Node temp = taskNode.item(j);
            if (!temp.getNodeName().equals("#text")) {
                String currentVal = temp.getTextContent();
                if (temp.getNodeName().equals("TaskName")) {
                    task.setTaskName(currentVal);
                }
                if (temp.getNodeName().equals("Assessment")) {
                    if (!temp.getTextContent().equals("")) {
                        task.setAssessment(Integer.parseInt(currentVal));
                    }
                }
            }
        }
        return task;
    }


    private Node load(String xmlfile) {
        Document doc = null;
        try {
            DOMParser parser = new DOMParser();
            parser.reset();
            parser.parse(xmlfile);
            doc = parser.getDocument();
            if (!doc.getDocumentElement().getTagName().equals("University")) {
                return null;
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (SAXException saxe) {
            saxe.printStackTrace();
        }
        return doc;
    }


}
