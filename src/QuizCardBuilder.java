import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class QuizCardBuilder implements ActionListener {
    private static QuizCardBuilder QuizCardBuilder;
    ArrayList <QuizCard> quizCards = new ArrayList<>();
    int answer = 0;
    JTextArea questionTextArea = new JTextArea(280,200);
    JTextArea answerTextArea = new JTextArea(280,200);
    JButton nextAnswerButton = new JButton("NextAnswer");



    public static void main (String[] args){
        QuizCardBuilder quizCardBuilder = new QuizCardBuilder();
        quizCardBuilder.buildInterface();

    }
    void buildInterface (){
        JMenuBar jMenuBar = new JMenuBar();
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JMenu fileMenu = new JMenu("File");
        JMenuItem deleteMenu = new JMenuItem("Delete");
        JMenuItem saveMenu = new JMenuItem("Save");
        fileMenu.add(saveMenu);
        fileMenu.add(deleteMenu);
        jMenuBar.add(fileMenu);

        frame.setJMenuBar(jMenuBar);
        frame.setSize(400,600);
        JLabel questionLabel = new JLabel("QUESTION");
        questionLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        panel.add(questionLabel);
        panel.add(Box.createVerticalStrut(5));

        questionTextArea.setLineWrap(true);
        JScrollPane questionScroll = new JScrollPane(questionTextArea);
        questionScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        questionScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        panel.add(questionTextArea);
        panel.add(Box.createVerticalStrut(20));
        JLabel answerLabel = new JLabel("ANSWER");
        answerLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        panel.add(answerLabel);
        panel.add(Box.createVerticalStrut(5));


        questionTextArea.setLineWrap(true);
        JScrollPane answerScroll = new JScrollPane(answerTextArea);
        answerScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        answerScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        panel.add(answerTextArea);
        panel.add(Box.createVerticalStrut(20));


        nextAnswerButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        panel.add(nextAnswerButton);



        frame.getContentPane().add(panel,BorderLayout.CENTER);
        frame.setVisible(true);
        nextAnswerButton.addActionListener(this);
        deleteMenu.addActionListener(this);
        saveMenu.addActionListener(this);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());

        if (e.getActionCommand().equals("NextAnswer")) {
            QuizCard quizCard = new QuizCard();
            quizCard.setQuestion(questionTextArea.getText());
            quizCard.setAnswer(questionTextArea.getText());
            quizCards.add(quizCard);
            questionTextArea.setText(null);
            answerTextArea.setText(null);
        };
        if (e.getActionCommand().equals("Save")) {};
        if (e.getActionCommand().equals("Delete")) {};




    }
}
