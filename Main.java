import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class Main{
    public static  DataBase db = new DataBase();
    public static void main(String[] args)
    {
        if(db.xmlExists())
        {
           db.xmlRead();
        }
        GUI();
    }

    public static void GUI()
    {
        //create main frame
        JFrame frame = new JFrame("Lab2| Timur Sorokin");
        frame.setSize(640,480);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //create container
        JPanel panel  = new JPanel();
        BoxLayout boxlayout = new BoxLayout(panel,BoxLayout.Y_AXIS);
        JButton addVehicle = new JButton("Add");
        addVehicle.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                           addVehicle();
                    }
                });

        JButton showVehicle = new JButton("Show");
        showVehicle.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        showVehicle();
                    }
                });

        JButton exit = new JButton("Exit");
        exit.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        System.exit(0);
                    }
                });
        panel.add(addVehicle);
        panel.add(showVehicle);
        panel.add(exit);
        panel.setLayout(boxlayout);
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void addVehicle()
    {
        JFrame frame = new JFrame ("Add Vehicle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel ();
        BoxLayout boxlayout = new BoxLayout(panel,BoxLayout.Y_AXIS);
        panel.setLayout(boxlayout);
        JLabel brand = new JLabel("Insert brand");
        JLabel model = new JLabel("Insert model");
        JLabel colour = new JLabel ("Insert colour");
        JLabel power = new JLabel ("Insert power");
        JLabel registration = new JLabel ("Insert registration");
        JTextField tbrand = new JTextField();
        JTextField tmodel = new JTextField();
        JTextField tcolour = new JTextField();
        JTextField tpower = new JTextField();
        JTextField tregistration = new JTextField();
        JButton ok  = new JButton("Add vehicle");
        ok.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                           Vehicle vehicle = new Vehicle(tbrand.getText(),tmodel.getText(),tregistration.getText(),tpower.getText(),tcolour.getText());
                           db.addToBase(vehicle);
                           db.xmlWrite(tbrand.getText(),tmodel.getText(),tcolour.getText(),tpower.getText(),tregistration.getText());

                    }
                });


        panel.add(brand);
        panel.add(tbrand);
        panel.add(model);
        panel.add(tmodel);
        panel.add(colour);
        panel.add(tcolour);
        panel.add(power);
        panel.add(tpower);
        panel.add(registration);
        panel.add(tregistration);
        panel.add(ok);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
            }
    public static void showVehicle()
    {
         JFrame frame = new JFrame ("Vehicles");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTextArea text = new JTextArea();
        JScrollPane sp = new JScrollPane(text);
        text.setText(db.showBase());
        frame.getContentPane().add(sp);
        frame.add(text);
        frame.pack();
        frame.setVisible(true);
        db.xmlRead();
    }
        
        
}
