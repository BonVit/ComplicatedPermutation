import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Created by bonar on 1/26/2017.
 */
public class MainForm extends JFrame implements ActionListener{

    private JPanel mMainPanel;
    private JButton encryptButton;
    private JButton openButton;
    private JTextArea initialData;
    private JPanel mKeyPanel;
    private JPanel mTablePanel;
    private JSpinner mKeyLength;
    private JButton mBuildButton;
    private JSpinner mLines;
    private JButton decryptButton;
    private JTextArea resultField;

    private ArrayList<JSpinner> mKey;
    private ArrayList<TextField> mTable;

    MainForm()
    {
        mMainPanel.setPreferredSize(new Dimension(500, 500));

        setContentPane(mMainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);

        mKeyLength.setModel(new SpinnerNumberModel(5, 1, 100, 1));
        mLines.setModel(new SpinnerNumberModel(10, 1, 100, 1));

        mKeyPanel.setLayout(new GridLayout());

        mBuildButton.addActionListener(this);
        encryptButton.addActionListener(this);
        openButton.addActionListener(this);
        openButton.setVisible(false);
        decryptButton.addActionListener(this);

        adjustKeyPanel();
        adjustTablePanel();
    }

    private void adjustKeyPanel()
    {
        mKeyPanel.removeAll();
        mKey = new ArrayList<JSpinner>();
        for(int i = 0; i < (Integer) mKeyLength.getValue(); i++)
        {
            mKey.add(new JSpinner(new SpinnerNumberModel(i, 0, (int) mKeyLength.getValue() - 1, 1)));
            mKeyPanel.add(mKey.get(mKey.size() - 1));
        }

        revalidate();
    }

    private void adjustTablePanel()
    {
        mTablePanel.removeAll();
        mTablePanel.setLayout(new GridLayout((Integer) mLines.getValue(), (Integer) mKeyLength.getValue()));
        mTable = new ArrayList<TextField>();
        for(int i = 0; i < (Integer) mLines.getValue(); i++)
        {
            for(int j = 0; j < (Integer) mKeyLength.getValue(); j++)
            {
                mTable.add(new TextField(""));
                mTable.get(mTable.size() - 1).setEditable(false);
                mTable.get(mTable.size() - 1).addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if(((TextField) e.getSource()).getText().equals("UNUSED"))
                            ((TextField) e.getSource()).setText("");
                        else
                            ((TextField) e.getSource()).setText("UNUSED");
                    }
                });

                mTablePanel.add(mTable.get(mTable.size() - 1));
            }
        }

        revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == openButton)
        {

        }

        if(e.getSource() == decryptButton)
        {
            if(resultField.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null, "Enter or load data first!",  "Input error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            int[] key = new int[mKey.size()];
            for(int i = 0; i < key.length; i++)
                key[i] = (Integer) mKey.get(i).getValue();

            String data = resultField.getText();

            String[] matrix = new String[(int) (mTable.size() / mKey.size())];

            for(int i = 0, k = 0; i < matrix.length; i++)
            {
                matrix[i] = new String("");
                for(int j = 0; j < key.length; j++)
                {
                    if (mTable.get(i * key.length + j).getText().equals("UNUSED")) {
                        matrix[i] += "\0";
                    }
                    else
                    {
                        if(k >= data.length())
                            matrix[i] += " ";
                        else {
                            matrix[i] += data.charAt(k);
                            String tmp = new String("");
                            tmp += data.charAt(k);
                            mTable.get(i * key.length + j).setText(tmp);
                        }
                        k++;
                    }
                }
            }

            initialData.setText(ComplicatedPermutations.decpypt(key, matrix));

            revalidate();


        }

        if(e.getSource() == mBuildButton)
        {
            adjustKeyPanel();
            adjustTablePanel();
        }

        if(e.getSource() == encryptButton)
        {
            if(initialData.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null, "Enter or load data first!",  "Input error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            int[] key = new int[mKey.size()];
            for(int i = 0; i < key.length; i++)
                key[i] = (Integer) mKey.get(i).getValue();

            String data = initialData.getText();

            String[] matrix = new String[(int) (mTable.size() / mKey.size())];

            for(int i = 0, k = 0; i < matrix.length; i++)
            {
                matrix[i] = new String("");
                for(int j = 0; j < key.length; j++)
                {
                    if (mTable.get(i * key.length + j).getText().equals("UNUSED")) {
                        matrix[i] += "\0";
                    }
                    else
                    {
                        if(k >= data.length())
                            matrix[i] += " ";
                        else {
                            matrix[i] += data.charAt(k);
                            String tmp = new String("");
                            tmp += data.charAt(k);
                            mTable.get(i * key.length + j).setText(tmp);
                        }
                        k++;
                    }
                }
            }

            resultField.setText(ComplicatedPermutations.encrypt(key, matrix));

            revalidate();
        }
    }

    public static void main(String[] args)
    {
        MainForm ui = new MainForm();
    }

}
