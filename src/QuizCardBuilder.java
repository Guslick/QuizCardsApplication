import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class QuizCardBuilder implements ActionListener {
    private static QuizCardBuilder QuizCardBuilder;
    int answer = 1;
    JTextArea questionTextArea = new JTextArea(280,200);
    JTextArea answerTextArea = new JTextArea(280,200);
    JButton AddQuestionButton = new JButton("  AddQuestion  ");
    QuizCard quizCard = new QuizCard();
    QuizCards quizCards = new QuizCards();
    JLabel answerlabel;
    JFrame frame = new JFrame();
    JButton DeleteQuestionButton = new JButton("DeleteQuestion");



    public static void main (String[] args){
        QuizCardBuilder quizCardBuilder = new QuizCardBuilder();
        quizCardBuilder.buildInterface();

    }
    void buildInterface (){
        JMenuBar jMenuBar = new JMenuBar();
        frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JMenu fileMenu = new JMenu("File");
        JMenuItem deleteMenu = new JMenuItem("Delete");
        JMenuItem saveMenu = new JMenuItem("Save");
        JMenuItem loadMenu = new JMenuItem("Load");
        fileMenu.add(saveMenu);
        fileMenu.add(loadMenu);
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
        questionScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        questionScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        panel.add(questionScroll);
        panel.add(Box.createVerticalStrut(20));
        JLabel answerLabel = new JLabel("ANSWER");
        answerLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        panel.add(answerLabel);
        panel.add(Box.createVerticalStrut(5));


        questionTextArea.setLineWrap(true);
        JScrollPane answerScroll = new JScrollPane(answerTextArea);
        answerScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        answerScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        panel.add(answerScroll);
        panel.add(Box.createVerticalStrut(20));

        JPanel buttonpanel = new JPanel();
        buttonpanel.setLayout(new FlowLayout());
        buttonpanel.add(DeleteQuestionButton);
        buttonpanel.add(AddQuestionButton);

        panel.add(buttonpanel);


        panel.add(Box.createVerticalStrut(30));
        answerlabel = new JLabel("Question number: 1");
        answerlabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        panel.add(answerlabel);

        panel.add(Box.createVerticalStrut(3));

        JPanel naviButtonsPanel = new JPanel();
        naviButtonsPanel.setLayout(new GridLayout(1,2));
        JButton prevButton = new JButton("<");
        JButton nextButton = new JButton(">");

        naviButtonsPanel.add(prevButton);
        naviButtonsPanel.add(nextButton);

        panel.add(naviButtonsPanel);




        frame.setLocationRelativeTo(null);
        frame.getContentPane().add(panel,BorderLayout.CENTER);
        frame.setVisible(true);
        AddQuestionButton.addActionListener(this);
        deleteMenu.addActionListener(this);
        saveMenu.addActionListener(this);
        loadMenu.addActionListener(this);
        prevButton.addActionListener(this);
        nextButton.addActionListener(this);
        DeleteQuestionButton.addActionListener(this);

        frame.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());

        if (e.getActionCommand().equals("  AddQuestion  ")) {
            QuizCard currentQuizCard = new QuizCard();
            currentQuizCard.setQuestion(questionTextArea.getText());
            currentQuizCard.setAnswer(answerTextArea.getText());
            quizCards.quizCards.add(answer-1, currentQuizCard);
            questionTextArea.setText(null);
            answerTextArea.setText(null);
            answer+=1;
            answerlabel.setText("Question number: "+answer);
        };

        if (e.getActionCommand().equals("DeleteQuestion")) {
            try {
                quizCards.quizCards.remove(answer - 1);

                QuizCard currentQuizCard = quizCards.quizCards.get(answer - 1);
                questionTextArea.setText(currentQuizCard.getQuestion());
                answerTextArea.setText(currentQuizCard.getAnswer());
                //if (!(answer==1)){answer-=1;}
                answerlabel.setText("Question number: " + answer);

            } catch (IndexOutOfBoundsException indexOutOfBoundsException){
                if (quizCards.quizCards.toArray().length==0) {
                JOptionPane.showMessageDialog(frame, "All cards deleted");
                }
                questionTextArea.setText(null);
                answerTextArea.setText(null);
                if (!(answer==1)){answer-=1;}

                try {
                    QuizCard currentQuizCard = quizCards.quizCards.get(answer - 1);
                    questionTextArea.setText(currentQuizCard.getQuestion());
                    answerTextArea.setText(currentQuizCard.getAnswer());
                    answerlabel.setText("Question number: " + answer);
                } catch (IndexOutOfBoundsException indexOutOfBoundsException1) {quizCards = new QuizCards();};



            }

        }


        if (e.getActionCommand().equals("Save")) {
            try {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("saving.ssr"));
                objectOutputStream.writeObject(quizCards);
                objectOutputStream.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        };
        if (e.getActionCommand().equals("Delete")) {
            quizCards=null;
            try {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("saving.ssr"));
                objectOutputStream.writeObject(quizCards);
                objectOutputStream.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            questionTextArea.setText("");
            answerTextArea.setText("");
            quizCards = new QuizCards();
            answer=1;
            answerlabel.setText("Question number: " + answer);
        };
        if (e.getActionCommand().equals("Load")) {

                try {
                    ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("saving.ssr"));
                    quizCards = (QuizCards) objectInputStream.readObject();
                    JOptionPane.showMessageDialog(frame, "Cards successfully loaded");
                    answer=1;
                    answerlabel.setText("Question number: "+answer);
                    QuizCard currentQuizCard = quizCards.quizCards.get(answer-1);
                    questionTextArea.setText(currentQuizCard.getQuestion());
                    answerTextArea.setText(currentQuizCard.getAnswer());
                    if (quizCards == null) {quizCards = new QuizCards();}

                } catch (FileNotFoundException exception) {
                    exception.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                } catch (NullPointerException nullPointerException){
                    JOptionPane.showMessageDialog(frame, "Empty file loaded");
                    quizCards = new QuizCards();
                    questionTextArea.setText(null);
                    answerTextArea.setText(null);
                } catch (IndexOutOfBoundsException indexOutOfBoundsException){
                    JOptionPane.showMessageDialog(frame, "Empty file loaded");
                    questionTextArea.setText(null);
                    answerTextArea.setText(null);
                }



        }

        if (e.getActionCommand().equals(">")){

                try {
                    answer += 1;
                    QuizCard currentQuizCard = quizCards.quizCards.get(answer - 1);
                    questionTextArea.setText(currentQuizCard.getQuestion());
                    answerTextArea.setText(currentQuizCard.getAnswer());
                    answerlabel.setText("Question number: " + answer);
                } catch (IndexOutOfBoundsException indexOutOfBoundsException){
                    JOptionPane.showMessageDialog(frame, "No more cards loaded");
                    answer -= 1;
                }

        }

        if (e.getActionCommand().equals("<")){
            if (answer==1){} else {
                answer-=1;
                QuizCard currentQuizCard = quizCards.quizCards.get(answer-1);
                questionTextArea.setText(currentQuizCard.getQuestion());
                answerTextArea.setText(currentQuizCard.getAnswer());
                answerlabel.setText("Question number: "+ answer);


            }

        }


    }
}
