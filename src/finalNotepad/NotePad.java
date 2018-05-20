package finalNotepad;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;
public class NotePad implements WindowListener, ActionListener {
    private final Frame f;
    private final TextArea ta;
    private FileDialog fd;
    private final MenuBar mb;
    private final Menu m1, m2, format, m3, m4, font1, font2, font3;
    private final MenuItem mi1, mi2, mi3, mi4, undo, cut, copy, paste, delete, selectAll, td, about; 
    private final MenuItem fname1, fname2, fname3, fname4, fstyle1, fstyle2, fstyle3, fstyle4, fsize1, fsize2, fsize3, fsize4;
    String name, path, n, str4, msg, copiedText;
    Font fnt;
    String months[] = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
    GregorianCalendar gcalendar;
    public NotePad() {
        f = new Frame("Notepad");
        ta = new TextArea("", 0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
        //TextArea(String text, int rows, int columns, int scrollbars)
        mb = new MenuBar();
        m1 = new Menu("File");
        m2 = new Menu("Edit");
        format = new Menu("Format");
        m3 = new Menu("View");
        m4 = new Menu("Help");
        mi1 = new MenuItem("New");
        mi2 = new MenuItem("Open");
        mi3 = new MenuItem("Save");
        mi4 = new MenuItem("Exit");
        undo = new MenuItem("Undo");
        cut = new MenuItem("Cut");
        copy = new MenuItem("Copy");
        paste = new MenuItem("Paste");
        delete = new MenuItem("Delete");
        selectAll = new MenuItem("Select All");
        td = new MenuItem("Time/Date");
        font1 = new Menu("Font");
        font2 = new Menu("Font Style");
        font3 = new Menu("Size");
        font1.add(fname1 = new MenuItem("Courier"));
        font1.add(fname2 = new MenuItem("Sans Serif"));
        font1.add(fname3 = new MenuItem("Monospaced"));
        font1.add(fname4 = new MenuItem("Symbol"));
        font2.add(fstyle1 = new MenuItem("Regular"));
        font2.add(fstyle2 = new MenuItem("Bold"));
        font2.add(fstyle3 = new MenuItem("Italic"));
        font2.add(fstyle4 = new MenuItem("Bold Italic"));
        font3.add(fsize1 = new MenuItem("12"));
        font3.add(fsize2 = new MenuItem("14"));
        font3.add(fsize3 = new MenuItem("18"));
        font3.add(fsize4 = new MenuItem("20"));
        about = new MenuItem("About Notepad");
        mi1.setActionCommand("new");
        mi2.setActionCommand("open");
        mi3.setActionCommand("save");
        mi4.setActionCommand("exit");
    }
    public void launch() {
        f.addWindowListener(this);
        f.setMenuBar(mb);
        fnt = new Font("Serif", Font.BOLD, 18);
        ta.setFont(fnt);
        f.add(ta);
        mb.add(m1);
        mb.add(m2);
        mb.add(format);
        mb.add(m3);
        mb.add(m4);
        m1.add(mi1);
        m1.add(mi2);
        m1.add(mi3);
        m1.addSeparator();
        m1.add(mi4);
        m2.add(undo);
        m2.addSeparator();
        m2.add(cut);
        m2.add(copy);
        m2.add(paste);
        m2.add(delete);
        m2.addSeparator();
        m2.add(selectAll);
        m2.add(td);
        format.add(font1);
        format.add(font2);
        format.add(font3);
        m4.add(about);
        mi1.addActionListener(this);
        mi2.addActionListener(this);
        mi3.addActionListener(this);
        mi4.addActionListener(this);
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        delete.addActionListener(this);
        selectAll.addActionListener(this);
        td.addActionListener(this);
        fname1.addActionListener(this);
        fname2.addActionListener(this);
        fname3.addActionListener(this);
        fname4.addActionListener(this);
        fstyle1.addActionListener(this);
        fstyle2.addActionListener(this);
        fstyle3.addActionListener(this);
        fstyle4.addActionListener(this);
        fsize1.addActionListener(this);
        fsize2.addActionListener(this);
        fsize3.addActionListener(this);
        fsize4.addActionListener(this);
        about.addActionListener(this);
        f.setSize(700, 500);
        f.setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        try {
            MenuItem mi = (MenuItem) e.getSource();
            if ("new".equals(e.getActionCommand())) {
                if("".equals(ta.getText())){
                    //System.out.println("empty string");
                    //ta.setText("");
                }  
                else{
                    // https://www.mkyong.com/swing/java-swing-how-to-make-a-confirmation-dialog/
                    
                    Object[] options = {"Save",
                    "Don't Save",
                    "Cancel"};
                    
                    int optionValue = JOptionPane.showOptionDialog(f,//parent container of JOptionPane
                            "Do you want to save changes?","Notepad",
                            JOptionPane.YES_NO_CANCEL_OPTION,
                            // 0=yes, 1=no, 2=cancel
                            JOptionPane.PLAIN_MESSAGE,
                            null,//do not use a custom Icon
                            options,//the titles of buttons
                           // options[2]);//default button title
                            null);
                    //System.out.println(n);
          
                    //JOptionPane.showMessageDialog(f, "Do you want to save changes?", "Notepad", JOptionPane.PLAIN_MESSAGE);
                    if (optionValue == 0) {
                        fd = new FileDialog(f, "Save", FileDialog.SAVE);
                        fd.setVisible(true);
                        name = fd.getFile();
                        path = fd.getDirectory();
                        String strr = ta.getText();
                        File fl = new File(path, name);
                        FileOutputStream fos = new FileOutputStream(fl);
                        char arr[];
                        arr = strr.toCharArray();
                        for (int i = 0; i < arr.length; i++) {
                            fos.write(arr[i]);
                        }
                        fos.close();
                    }
                    else if(optionValue==1){
                        ta.setText("");
                    }
                    else if(optionValue==3){
                        String text = ta.getText();
                        ta.setText(text);
                    }
                }
            } 
            else if (mi == mi2) {
                fd = new FileDialog(f, "Open", FileDialog.LOAD);
                fd.setVisible(true);
                name = fd.getFile();
                path = fd.getDirectory();
                n = path + name;
                File fl = new File(path, name);
                FileInputStream fis = new FileInputStream(fl);
                int ch;
                ta.setText("");
                while ((ch = fis.read()) != -1) {
                    ta.appendText((char)ch+"");
                }
                fis.close();
            }
            else if (mi == mi3) {
                fd = new FileDialog(f,"Save",FileDialog.SAVE);
                fd.setVisible(true);
                name = fd.getFile();
                path = fd.getDirectory();
                String strr = ta.getText();
                File fl = new File(path, name);
                FileOutputStream fos = new FileOutputStream(fl);
                char arr[];
                arr = strr.toCharArray();
                for (int i = 0; i < arr.length; i++) {
                    fos.write(arr[i]);
                }
                fos.close();
            } 
            else if (e.getActionCommand() == "exit") {
                System.exit(0);
            } 
            else if (e.getSource() == copy) {
                copiedText = "";
                if (ta.getSelectedText() != null) {
                    copiedText = ta.getSelectedText();
                }
            } 
            else if (e.getSource() == paste) {
                ta.insert(copiedText, ta.getCaretPosition());
            } 
            else if (e.getSource() == cut) {
                if (ta.getSelectedText() != null) {
                    copiedText = ta.getSelectedText();
                    int start = ta.getSelectionStart();
                    int end = ta.getSelectionEnd();
                    String startText = ta.getText().substring(0,start);
                    String endText = ta.getText().substring(end,ta.getText().length());
                    ta.setText(startText + endText);
                }
            } 
            else if (e.getSource() == delete) {
                if (ta.getSelectedText() != null) {
                    int start = ta.getSelectionStart();
                    int end = ta.getSelectionEnd();
                    String startText = ta.getText().substring(0,start);
                    String endText = ta.getText().substring(end,ta.getText().length());
                    ta.setText(startText + endText);
                }
            } 
            else if (e.getSource() == selectAll) {
                ta.selectAll();
            } 
            else if (e.getSource() == td) {
                gcalendar = new GregorianCalendar();
                String h = String.valueOf(gcalendar.get(Calendar.HOUR));
                String m = String.valueOf(gcalendar.get(Calendar.MINUTE));
                String s = String.valueOf(gcalendar.get(Calendar.SECOND));
                String date = String.valueOf(gcalendar.get(Calendar.DATE));
                String mon = months[gcalendar.get(Calendar.MONTH)];
                String year = String.valueOf(gcalendar.get(Calendar.YEAR));
                String hms = "Time" + "- " + h + ":" + m + ":" + s + "  Date" + "  " + date + "  " + mon + " " + year;
                int loc = ta.getCaretPosition();
                ta.insert(hms, loc);
            } 
            else if (fname1 == e.getSource()) {
                int fontSize = fnt.getSize();
                int fontStyle = fnt.getStyle();
                fnt = new Font("Courier", fontStyle, fontSize);
                ta.setFont(fnt);
            } 
            else if (fname2 == e.getSource()) {
                int fontSize = fnt.getSize();
                int fontStyle = fnt.getStyle();
                fnt = new Font("Sans Serif", fontStyle, fontSize);
                ta.setFont(fnt);
            } 
            else if (fname3 == e.getSource()) {
                int fontSize = fnt.getSize();
                int fontStyle = fnt.getStyle();
                fnt = new Font("Monospaced", fontStyle, fontSize);
                ta.setFont(fnt);
            }
            else if (fname4 == e.getSource()) {
                int fontSize = fnt.getSize();
                int fontStyle = fnt.getStyle();
                fnt = new Font("Symbol", fontStyle, fontSize);
                ta.setFont(fnt);
                System.out.println(fnt.getFamily());
            } 
            else if (fstyle1 == e.getSource()) {
                String fontName = fnt.getName();
                int fontSize = fnt.getSize();
                fnt = new Font(fontName, Font.PLAIN, fontSize);
                ta.setFont(fnt);
            }
            else if (fstyle2 == e.getSource()) {
                String fontName = fnt.getName();
                int fontSize = fnt.getSize();
                fnt = new Font(fontName, Font.BOLD, fontSize);
                ta.setFont(fnt);
            } 
            else if (fstyle3 == e.getSource()) {
                String fontName = fnt.getName();
                int fontSize = fnt.getSize();
                fnt = new Font(fontName, Font.ITALIC, fontSize);
                ta.setFont(fnt);
            } 
            else if (fstyle4 == e.getSource()) {
                String fontName = fnt.getName();
                int fontSize = fnt.getSize();
                fnt = new Font(fontName, Font.BOLD | Font.ITALIC, fontSize);
                ta.setFont(fnt);
            }
            else if (fsize1 == e.getSource()) {
                String fontName = fnt.getName();
                int fontStyle = fnt.getStyle();
                fnt = new Font(fontName, fontStyle, 12);
                ta.setFont(fnt);
            }
            else if (fsize2 == e.getSource()) {
                String fontName = fnt.getName();
                int fontStyle = fnt.getStyle();
                fnt = new Font(fontName, fontStyle, 14);
                ta.setFont(fnt);
            } 
            else if (fsize3 == e.getSource()) {
                String fontName = fnt.getName();
                int fontStyle = fnt.getStyle();
                fnt = new Font(fontName, fontStyle, 18);
                ta.setFont(fnt);
            }
            else if (fsize4 == e.getSource()) {
                String fontName = fnt.getName();
                int fontStyle = fnt.getStyle();
                fnt = new Font(fontName, fontStyle, 20);
                ta.setFont(fnt);
            } 
            else if (e.getSource() == about) {
                JOptionPane.showMessageDialog(f, "This Notepad is made by Shahinuzzaman Sabuj", "About", JOptionPane.PLAIN_MESSAGE);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    public void windowClosing(WindowEvent e) {System.exit(0);}
    public void windowActivated(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowOpened(WindowEvent e) {}
    public void windowClosed(WindowEvent e) {}
    public static void main(String[] args) {
        NotePad np = new NotePad();
        np.launch();
    }
}
