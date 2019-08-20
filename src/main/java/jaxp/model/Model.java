package jaxp.model;

import jaxp.entity.*;
import org.apache.xerces.dom.NodeImpl;
import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Model {
    final String XMLPATH =
            "C:\\Users\\Damir_Valeev\\IdeaProjects\\lifeProject\\src\\main\\xmlTask\\StudentReport.xml";
    University univer;

    public University startModel() {
        Node node = load(XMLPATH);
        NodeImpl universityNodeList = (NodeImpl) node.getChildNodes();
        NodeList studentsNodes = universityNodeList.item(0).getChildNodes();
        University university = new University();
        university.setStudentList(parseStudents(studentsNodes));
        univer = university;
        return university;
    }

    public static List<Student> parseStudents(NodeList nodeList) {
        List<Student> studentList = new ArrayList<Student>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (!nodeList.item(i).getNodeName().equals("#text")) {
                Student student = new Student();
                NodeList studentNode = nodeList.item(i).getChildNodes();
                for (int j = 0; j < studentNode.getLength(); j++) {
                    if (!studentNode.item(j).getNodeName().equals("#text")) {
                        if (studentNode.item(j).getNodeName().equals("FullName")) {
                            student.setFullName(studentNode.item(j).getTextContent());
                        }
                        if (studentNode.item(j).getNodeName().equals("City")) {
                            student.setCity(studentNode.item(j).getTextContent());
                        }
                        if (studentNode.item(j).getNodeName().equals("Email")) {
                            student.setEmail(studentNode.item(j).getTextContent());
                        }
                        if (studentNode.item(j).getNodeName().equals("TrainingStartDate")) {
                            student.setTrainingStartDate(studentNode.item(j).getTextContent());
                        }
                        if (studentNode.item(j).getNodeName().equals("SignedAcontract")) {
                            student.setSignedAcontract(Boolean.parseBoolean(studentNode.item(j).getTextContent()));
                        }
                        if (studentNode.item(j).getNodeName().equals("Program")) {
                            student.setProgram(parseProgram(studentNode.item(j).getChildNodes()));
                        }

                    }
                }
                studentList.add(student);
            }
        }
        return studentList;
    }

    private static Program parseProgram(NodeList programNode) {
        Program prog = new Program();
        for (int j = 0; j < programNode.getLength(); j++) {
            if (!programNode.item(j).getNodeName().equals("#text")) {
                if (programNode.item(j).getNodeName().equals("ProgName")) {
                    prog.setProgName(programNode.item(j).getTextContent());
                }
                if (programNode.item(j).getNodeName().equals("ProgAuthor")) {
                    prog.setProgAuthor(programNode.item(j).getTextContent());
                }
                if (programNode.item(j).getNodeName().equals("ProgCreationDate")) {
                    prog.setCreationDate(programNode.item(j).getTextContent());
                }
                if (programNode.item(j).getNodeName().equals("Courses")) {
                    prog.setCourseList(parseCoursesList(programNode.item(j).getChildNodes()));
                }
            }
        }
        return prog;
    }

    private static List<Course> parseCoursesList(NodeList childNode) {
        List<Course> courseArrayList = new ArrayList<Course>();
        for (int j = 0; j < childNode.getLength(); j++) {
            if (!childNode.item(j).getNodeName().equals("#text")) {
                courseArrayList.add(parseCourse(childNode.item(j).getChildNodes()));
            }
        }
        return courseArrayList;
    }

    private static Course parseCourse(NodeList courceNode) {
        Course course = new Course();
        Element childEl = (Element) courceNode;
        String idValue = childEl.getAttribute("id");
        course.setId(Integer.parseInt(idValue));
        for (int j = 0; j < courceNode.getLength(); j++) {
            if (!courceNode.item(j).getNodeName().equals("#text")) {
                if (courceNode.item(j).getNodeName().equals("CourceName")) {
                    course.setCourceName(courceNode.item(j).getTextContent());
                }
                if (courceNode.item(j).getNodeName().equals("CourceAuthor")) {
                    course.setCourceAuthor(courceNode.item(j).getTextContent());
                }
                if (courceNode.item(j).getNodeName().equals("CourceCreationDate")) {
                    course.setCourceCreationDate(courceNode.item(j).getTextContent());
                }
                if (courceNode.item(j).getNodeName().equals("Tasks")) {
                    course.setTaskList(parseTaskList(courceNode.item(j).getChildNodes()));
                }
            }
        }
        return course;
    }


    private static List<Task> parseTaskList(NodeList childNodes) {
        List<Task> taskArrayList = new ArrayList<Task>();
        for (int j = 0; j < childNodes.getLength(); j++) {
            if (!childNodes.item(j).getNodeName().equals("#text")) {
                taskArrayList.add(parseTask(childNodes.item(j).getChildNodes()));
            }
        }
        return taskArrayList;
    }

    private static Task parseTask(NodeList taskNode) {
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
            if (!taskNode.item(j).getNodeName().equals("#text")) {
                if (taskNode.item(j).getNodeName().equals("TaskName")) {
                    task.setTaskName(taskNode.item(j).getTextContent());
                }
                if (taskNode.item(j).getNodeName().equals("Assessment")) {
                    if (!taskNode.item(j).getTextContent().equals("")) {
                        task.setAssessment(Integer.parseInt(taskNode.item(j).getTextContent()));
                    }
                }
            }
        }
        return task;
    }

    public static Node load(String xmlfile) {
        Document doc = null;
        try {
            DOMParser parser = new DOMParser();
            parser.reset();
            parser.parse(xmlfile);
            doc = parser.getDocument();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (SAXException saxe) {
            saxe.printStackTrace();
        }
        return doc;
    }

    public University getUniver() {
        return univer;
    }
}
