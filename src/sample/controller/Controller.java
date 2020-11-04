package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.ConnectUtil;
import sample.model.ModelTableCourses;
import sample.model.ModelTableLessons;
import sample.model.ModelTableTeacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Controller {
    Connection conn;
    public Controller() throws ClassNotFoundException {conn= ConnectUtil.conDB();}
    @FXML
    private TableView<ModelTableCourses> txtTableCourses;
    @FXML
    private TableColumn<ModelTableCourses,String> txtCoursId;
    @FXML
    private TableColumn<ModelTableCourses,String> txtCoursTitle;
    @FXML
    private TableColumn<ModelTableCourses,String> txtCoursLength;
    @FXML
    private TableColumn<ModelTableCourses,String> txtCoursDescription;



    @FXML
    private TableView<ModelTableLessons> txtTableLessons;
    @FXML
    private TableColumn<ModelTableLessons,String> txtLessonsId;
    @FXML
    private TableColumn<ModelTableLessons,String> txtLessonsTeacher;
    @FXML
    private TableColumn<ModelTableLessons,String> txtLessonsRoom;
    @FXML
    private TableColumn<ModelTableLessons,String> txtLessonsCourse;
    @FXML
    private TableColumn<ModelTableLessons,String> txtLessonsDate;



    @FXML
    private TableView<ModelTableTeacher> txtTableTeachers;
    @FXML
    private TableColumn<ModelTableTeacher,String>txtTeachersId;
    @FXML
    private TableColumn<ModelTableTeacher,String>txtTeachersName;
    @FXML
    private TableColumn<ModelTableTeacher,String>txtTeacherAddr;
    @FXML
    private TableColumn<ModelTableTeacher,String>txtTeacherPhone;

    ObservableList<ModelTableCourses> listCourses = FXCollections.observableArrayList();
    ObservableList<ModelTableLessons>listLessons = FXCollections.observableArrayList();
    ObservableList<ModelTableTeacher>listTeacher = FXCollections.observableArrayList();


    public void initialize(){
        try {
            String sql = "SELECT * FROM courses";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()){
                listCourses.add(new ModelTableCourses(result.getString(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4)));
            }
            txtCoursId.setCellValueFactory(new PropertyValueFactory<>("id"));
            txtCoursTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
            txtCoursLength.setCellValueFactory(new PropertyValueFactory<>("length"));
            txtCoursDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
            txtTableCourses.setItems(listCourses);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            String sql="SELECT * From lessons";
            PreparedStatement statement= conn.prepareStatement(sql);
            ResultSet result= statement.executeQuery();
            while (result.next()){
                listLessons.add(new ModelTableLessons(result.getString(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getString(5)));
            }
            txtLessonsId.setCellValueFactory(new PropertyValueFactory<>("id"));
            txtLessonsCourse.setCellValueFactory(new PropertyValueFactory<>("course"));
            txtLessonsDate.setCellValueFactory(new PropertyValueFactory<>("lesson_date"));
            txtLessonsRoom.setCellValueFactory(new PropertyValueFactory<>("room"));
            txtLessonsTeacher.setCellValueFactory(new PropertyValueFactory<>("teacher"));
            txtTableLessons.setItems(listLessons);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            String sql="SELECT * FROM teachers";
            PreparedStatement statement=conn.prepareStatement(sql);
            ResultSet result=statement.executeQuery();
            while (result.next()){
                listTeacher.add(new ModelTableTeacher(result.getString(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4)));
            }
            txtTeachersId.setCellValueFactory(new PropertyValueFactory<>("id"));
            txtTeachersName.setCellValueFactory(new PropertyValueFactory<>("name"));
            txtTeacherAddr.setCellValueFactory(new PropertyValueFactory<>("address"));
            txtTeacherPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
            txtTableTeachers.setItems(listTeacher);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

}
