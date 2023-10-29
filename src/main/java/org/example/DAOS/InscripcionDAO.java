package org.example.DAOS;

import org.example.DTOS.InscripcionDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InscripcionDAO {
    private Connection connection;

    public InscripcionDAO(Connection connection) {
        this.connection = connection;
    }


    public void insert(InscripcionDTO inscripcion) {
        try {
            String query = "INSERT INTO Inscripciones (id_estudiante, id_curso, fecha_inscripcion) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, inscripcion.getIdEstudiante());
            statement.setInt(2, inscripcion.getIdCurso());
            statement.setDate(3, inscripcion.getFechaInscripcion());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(InscripcionDTO inscripcion) {
        try {
            String query = "UPDATE Inscripciones SET id_estudiante=?, id_curso=?, fecha_inscripcion=? WHERE id_inscripcion=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, inscripcion.getIdEstudiante());
            statement.setInt(2, inscripcion.getIdCurso());
            statement.setDate(3, inscripcion.getFechaInscripcion());
            statement.setInt(4, inscripcion.getIdInscripcion());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int idInscripcion) {
        try {
            String query = "DELETE FROM Inscripciones WHERE id_inscripcion=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idInscripcion);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<InscripcionDTO> getAllInscripciones() {
        List<InscripcionDTO> inscripciones = new ArrayList<>();
        try {
            String query = "SELECT * FROM Inscripciones";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                InscripcionDTO inscripcion = new InscripcionDTO();
                inscripcion.setIdInscripcion(resultSet.getInt("id_inscripcion"));
                inscripcion.setIdEstudiante(resultSet.getInt("id_estudiante"));
                inscripcion.setIdCurso(resultSet.getInt("id_curso"));
                inscripcion.setFechaInscripcion(resultSet.getDate("fecha_inscripcion"));
                inscripciones.add(inscripcion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inscripciones;
    }
}
