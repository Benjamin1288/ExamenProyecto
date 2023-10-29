package org.example.Form;

import org.example.DAOS.CursoDAO;
import org.example.DAOS.EstudianteDAO;
import org.example.DAOS.InscripcionDAO;
import org.example.DTOS.CursoDTO;
import org.example.DTOS.EstudianteDTO;
import org.example.DTOS.InscripcionDTO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InscripcionApp extends JFrame {
    private InscripcionDAO inscripcionDAO;
    private EstudianteDAO estudianteDAO;
    private CursoDAO cursoDAO;
    private JTextField idField;
    private JTextField estudianteField;
    private JTextField cursoField;
    private JTextField fechaField;
    private JTextField estudianteIdField;
    private JTextField estudianteNombreField;
    private JTextField estudianteApellidoField;
    private JTextField estudianteEmailField;
    private JTextField cursoNombreField;
    private JTextField cursoProfesorField;
    private JTextField cursoIdField;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private JButton addEstudianteButton;
    private JButton addCursoButton;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JPanel mainPanel;

    public InscripcionApp() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/proyecto2", "postgres", "mysecretpassword");
            inscripcionDAO = new InscripcionDAO(connection);
            estudianteDAO = new EstudianteDAO(connection);
            cursoDAO = new CursoDAO(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        addEstudianteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEstudiante();
            }
        });

        addCursoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCurso();
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addInscripcion();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateInscripcion();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteInscripcion();
            }
        });


        setTitle("Aplicación de Inscripciones");
        setSize(1000, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(mainPanel);
        setLocationRelativeTo(null);
        setVisible(true);

    }



    private void addInscripcion() {
        try {
            int idEstudiante = Integer.parseInt(estudianteField.getText());
            int idCurso = Integer.parseInt(cursoField.getText());
            Date fechaInscripcion = dateFormat.parse(fechaField.getText());

            InscripcionDTO inscripcion = new InscripcionDTO();
            inscripcion.setIdEstudiante(idEstudiante);
            inscripcion.setIdCurso(idCurso);
            inscripcion.setFechaInscripcion(new java.sql.Date(fechaInscripcion.getTime()));

            inscripcionDAO.insert(inscripcion);
            System.out.println("Inscripcion añadida");
        } catch (ParseException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private void updateInscripcion() {
        try {
            int idInscripcion = Integer.parseInt(idField.getText());
            int idEstudiante = Integer.parseInt(estudianteField.getText());
            int idCurso = Integer.parseInt(cursoField.getText());
            Date fechaInscripcion = dateFormat.parse(fechaField.getText());

            InscripcionDTO inscripcion = new InscripcionDTO();
            inscripcion.setIdInscripcion(idInscripcion);
            inscripcion.setIdEstudiante(idEstudiante);
            inscripcion.setIdCurso(idCurso);
            inscripcion.setFechaInscripcion(new java.sql.Date(fechaInscripcion.getTime()));

            inscripcionDAO.update(inscripcion);
            System.out.println("Actualizacion");
        } catch (ParseException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private void deleteInscripcion() {
        int idInscripcion = Integer.parseInt(idField.getText());
        inscripcionDAO.delete(idInscripcion);
        System.out.println("Eliminado");
    }

    private void addEstudiante() {
        try {
            int idEstudiante = Integer.parseInt(estudianteIdField.getText());
            String nombre = estudianteNombreField.getText();
            String apellido = estudianteApellidoField.getText();
            String email = estudianteEmailField.getText();

            EstudianteDTO estudiante = new EstudianteDTO();
            estudiante.setIdEstudiante(idEstudiante);
            estudiante.setNombre(nombre);
            estudiante.setApellido(apellido);
            estudiante.setEmail(email);

            estudianteDAO.insert(estudiante);
            System.out.println("Estudiante añadido");
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private void addCurso() {
        try {
            int idCurso = Integer.parseInt(cursoIdField.getText());
            String nombreCurso = cursoNombreField.getText();
            String profesor = cursoProfesorField.getText();

            CursoDTO curso = new CursoDTO();
            curso.setIdCurso(idCurso);
            curso.setNombreCurso(nombreCurso);
            curso.setProfesor(profesor);

            cursoDAO.insert(curso);
            System.out.println("Curso añadido");
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

}
