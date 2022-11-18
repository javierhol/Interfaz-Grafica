import javax.swing.*;
import java.sql.*;

public class Estudiante extends JFrame {

    JPanel panel;
    private JTextField IdText;
    private JTextField NombreText;
    private JTextField ApellidoText;
    private JTextField EdadText;
    private JTextField TelText;
    private JTextField CursoText;
    private JButton InsertarBt;
    private JButton ConsultarBt;
    private JList Lista;
    Connection conn = null;
    PreparedStatement p;
    Statement s;
    ResultSet rs;
    DefaultListModel model = new DefaultListModel();

    public Estudiante() {
        ConsultarBt.addActionListener(e -> {
            try {
                listar();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }});
        InsertarBt.addActionListener(e -> {
            try {
                insert();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public void listar() throws SQLException {
        conectar();
        Lista.setModel(model);
        s= conn.createStatement();
        rs= s.executeQuery("SELECT * FROM student");
        model.removeAllElements();
        while (rs.next()) {
            model.addElement(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
        }

    }

    public void insert() throws SQLException {
        conectar();
        System.out.println("aaaaa"+conn);
            p = conn.prepareStatement("INSERT INTO student VALUES (?,?,?,?,?,?)");
            p.setString(1, IdText.getText());
            p.setString(2, NombreText.getText());
            p.setString(3, ApellidoText.getText());
            p.setString(4, EdadText.getText());
            p.setString(5, TelText.getText());
            p.setString(6, CursoText.getText());
            if (p.executeUpdate() > 0) {
                Lista.setModel(model);
                model.removeAllElements();
                model.addElement("Registro insertado");

                IdText.setText("");
                NombreText.setText("");
                ApellidoText.setText("");
                EdadText.setText("");
                TelText.setText("");
                CursoText.setText("");
        }
    }
    public void conectar() {
        try {

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/estudiante", "root", "");
            System.out.println("Conectado");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


