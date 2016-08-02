/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gk;

/**
 *
 * @author gandhark
 */

/**
 *
 * @author gandhark
 */
import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


public class TestTableSortFilter extends JPanel {

    private String[] columnNames
            = {"Country", "Capital", "Population in Millions"};

    private Object[][] data;
    private TableFromMySqlDatabase td= new TableFromMySqlDatabase();
    
    private Object data1[][] =(Object[][]) td.mymetod();
            
            
    
    
    

    private DefaultTableModel model = new DefaultTableModel(data1, columnNames);
    private JTable jTable = new JTable(model);

    private TableRowSorter<TableModel> rowSorter
            = new TableRowSorter<>(jTable.getModel());

    private JTextField jtfFilter = new JTextField();
    private JButton jbtFilter = new JButton("Filter");

    
    public void sortData() throws SQLException
    {
       //System.out.println( Arrays.toString(td.mymethod()));
    }
    
    
    
    
    
    public TestTableSortFilter() throws SQLException {
       // System.out.println(td.mymethod());
        this.data = new Object[][]{/*{"USA", "Washington DC", 280, true},
            {"Canada", "Ottawa", 32, true},
            {"United Kingdom", "London", 60, true},
            {"Germany", "Berlin", 83, true},
            {"France", "Paris", 60, true},
            {"Norway", "Oslo", 4.5, true},
            {"India", "New Delhi", 1046, true}*/
            
        };
       //  System.out.println(Arrays.toString(td.mymethod()));
        jTable.setRowSorter(rowSorter);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Specify a word to match:"),
                BorderLayout.WEST);
        panel.add(jtfFilter, BorderLayout.CENTER);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.SOUTH);
        add(new JScrollPane(jTable), BorderLayout.CENTER);

        jtfFilter.getDocument().addDocumentListener(new DocumentListener(){

            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = jtfFilter.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = jtfFilter.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            public void run() {
                try {
                    JFrame frame = new JFrame("Row Filter");
                    frame.add(new TestTableSortFilter());
                    frame.pack();
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(TestTableSortFilter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
        try
        {
        new TestTableSortFilter().sortData();
        }
        catch (Exception e)
        {
            System.out.println(e);
                }
    }
}
