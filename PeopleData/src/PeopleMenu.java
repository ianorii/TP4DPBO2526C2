import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class PeopleMenu extends JFrame {
    private JPanel mainPanel;
    private JTable personTable;
    private JTextField idField;
    private JTextField namaField;
    private JTextField tahunField;
    private JComboBox kategoriComboBox;
    private JSlider kebugaranSlider;
    private JButton addUpdateButton;
    private JButton cancelButton;
    private JButton deleteButton;
    private JLabel titleLabel;

    // variabel global
    private ArrayList<Person> listPerson;
    int selectedIndex = -1;

    // main program
    public static void main(String[] args) {
        // buat project window
        PeopleMenu menu = new PeopleMenu();

        // atur ukuran window
        menu.setSize(700, 600);

        // meletakkan window di tengah layar
        menu.setLocationRelativeTo(null);

        // isi window
        menu.setContentPane(menu.mainPanel);

        // agar program berhenti ketika window di-close
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // constructor
    public PeopleMenu() {
        // inisialisasi listPerson
        listPerson = new ArrayList<>();

        // isi data list
//        populateList();

        // isi personTable
        personTable.setModel(setTable());

        // set font dan ukuran title
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 20f));

        // atur isi combo box
        String[] kategoriData = {"???", "Pemula", "Menengah", "Ahli"};
        kategoriComboBox.setModel(new DefaultComboBoxModel<>(kategoriData));

        // set slider
        kebugaranSlider.setMinimum(0);
        kebugaranSlider.setMaximum(10);
        kebugaranSlider.setValue(5);

        // sembunyikan button delete
        deleteButton.setVisible(false);

        // saat tombol add/update ditekan
        addUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(selectedIndex == -1) {
                    // jika tidak ada index yang dipilih
                    insertData();
                } else {
                    // jika ada index yang dipilih
                    updateData();
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        personTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }
        });
    };

    // method setTable
    public final DefaultTableModel setTable() {
        // kolom table
        Object[] cols = {"No", "ID", "Nama", "Tahun Lahir", "Kategori", "Kebugaran"};

        // membuat object table dengan cols yang sudah dibuat
        DefaultTableModel tmp = new DefaultTableModel(null, cols);

        // isi tble dengan listPerson
        for(int i = 0; i < listPerson.size(); i++) {
            Object row[] = {i + 1,
                    listPerson.get(i).getId(),
                    listPerson.get(i).getNama(),
                    listPerson.get(i).getTahunLahir(),
                    listPerson.get(i).getKategori(),
                    listPerson.get(i).getKebugaran()
            };
            // isi baris
            tmp.addRow(row);
        }
        return tmp;
    }

    // method insert
    public void insertData() {
        try {
            // ambil value dari semua inputan
            String id = idField.getText();
            String nama = namaField.getText();
            int tahunLahir = Integer.parseInt(tahunField.getText());
            String kategori = kategoriComboBox.getSelectedItem().toString();
            int kebugaran = kebugaranSlider.getValue();

            // menambahkan data ke dalam list
            listPerson.add(new Person(id, nama, tahunLahir, kategori, kebugaran));

            // update table
            personTable.setModel(setTable());

            // bersihkan form
            clearForm();

            // feedback
            System.out.println("Insert Berhasil");
            JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan");
        } catch (NumberFormatException ex) {
            // Error handling untuk tahun
            JOptionPane.showMessageDialog(null, "Tahun harus berupa angka", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // method update
    public void updateData() {
        try {
            // ambil value dari semua inputan
            String id = idField.getText();
            String nama = namaField.getText();
            int tahunLahir = Integer.parseInt(tahunField.getText());
            String kategori = kategoriComboBox.getSelectedItem().toString();
            int kebugaran = kebugaranSlider.getValue();

            // memperbarui data
            listPerson.get(selectedIndex).setId(id);
            listPerson.get(selectedIndex).setNama(nama);
            listPerson.get(selectedIndex).setTahunLahir(tahunLahir);
            listPerson.get(selectedIndex).setKategori(kategori);
            listPerson.get(selectedIndex).setKebugaran(kebugaran);

            // kosongkan form
            clearForm();

            // feedback
            System.out.println("Update Berhasil");
            JOptionPane.showMessageDialog(null, "Data berhasil diubah");
        } catch (NumberFormatException ex) {
            // Error handling untuk tahun
            JOptionPane.showMessageDialog(null, "Tahun harus berupa angka", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // method clear form
    public void clearForm() {
        // mengosongkan semua textfield
        idField.setText("");
        namaField.setText("");
        tahunField.setText("");
        kategoriComboBox.setSelectedItem(0);
        kebugaranSlider.setValue(0);

        // ubah tulisan pada addUpdateButton
        addUpdateButton.setText("Add");

        // sembuntikan delete button
        deleteButton.setVisible(false);

        // ubah selectedItem menjadi default
        selectedIndex = -1;
    }
}