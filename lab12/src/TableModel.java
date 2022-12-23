import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TableModel extends AbstractTableModel {

    private ArrayList<XmlFileSpecifications> elements = new ArrayList<XmlFileSpecifications>();
    private final static String[] COLUMNS = {"fileName", "rootElementName", "charCount", "nestingDepth"};

    public TableModel(ArrayList<XmlFileSpecifications> input) {
        elements = input;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return (columnIndex < 2) ? String.class : Integer.class;
    }

    @Override
    public int getRowCount() {
        return elements.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMNS.length;
    }

    @Override
    public String getColumnName(int index) {
        return COLUMNS[index];
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return true;
    }

    @Override
    public Object getValueAt(int row, int column) {
        if(column == 0)
            return (Object) elements.get(row).getFileName();
        if(column == 1)
            return (Object) elements.get(row).getRootElementName();
        if(column == 2)
            return (Object) elements.get(row).getCharCount();
        if(column == 3)
            return (Object) elements.get(row).getNestingDepth();
        return null;
    }

    @Override
    public void setValueAt(Object value, int row, int column) {
        if(column == 0)
            elements.get(row).setFileName((String) value);
        if(column == 1)
            elements.get(row).setRootElementName((String) value);
        if(column == 2)
            elements.get(row).setCharCount((Integer) value);
        if(column == 3)
            elements.get(row).setNestingDepth((Integer) value);
    }

    public void deleteSelectedRows(JTable table) {
        for (int row : table.getSelectedRows())
            if (row < elements.size())
                elements.remove(row);
    }

    public ArrayList<XmlFileSpecifications> getElements() {
        return elements;
    }

    public void setElements(ArrayList<XmlFileSpecifications> elements) {
        this.elements = elements;
    }

    public static String[] getColumns() {
        return COLUMNS;
    }

}
