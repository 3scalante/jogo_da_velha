import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.*;

public class Evento01 extends JFrame implements ActionListener{
    String turno = "X";
    JLabel label = new JLabel("",JLabel.CENTER);
    JButton botao[][] = new JButton[3][3];
    boolean game = true;
    int nTurno = 1;

    public Evento01(){
        iniciarComponentes();

        this.setSize(300, 300);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void iniciarComponentes() {
        // TODO Auto-generated method stub

        label.setText(turno);

        Container c1 = getContentPane();
        c1.setLayout(new BorderLayout());

        Container c2 = new JPanel();
        c2.setLayout(new GridLayout(3, 3));


        c1.add(label, BorderLayout.NORTH);
        c1.add(c2, BorderLayout.CENTER);



        for (int i = 0; i<3; i++){
            for(int j = 0; j<3; j++){
                botao[i][j] = new JButton("");
                botao[i][j].setActionCommand("");
                botao[i][j].addActionListener(this);
                c2.add(botao[i][j]);
            }
        }




    }

    private void verify(String x) {
        // TODO Auto-generated method stub
        int win = 0;

        //verifica linhas
        for(int k = 0; k<3; k++){

            for (int i = 0; i<3; i++){
                for(int j = 0; j<3; j++){

                    if(i==k && botao[i][j].getText() == x){
                        win++;
                    }

                }
            }
            if(win==3){
                x+=" na linha "+k;
                winner(x);
            }
            win = 0;

        }


        //verifica colunas
        for(int k = 0; k<3; k++){

            for (int i = 0; i<3; i++){
                for(int j = 0; j<3; j++){

                    if(j==k && botao[i][j].getText() == x){
                        win++;
                    }

                }
            }

            if(win==3){
                x+=" na coluna "+k;
                winner(x);
            }
            win = 0;
        }


        //verifica verticais
        for (int i = 0; i<3; i++){
            for(int j = 0; j<3; j++){

                if(i==j && botao[i][j].getText() == x){
                    win++;
                }

            }
        }
        if(win==3){
            x+=" na vertical principal ";
            winner(x);
        }
        win = 0;
        // outra vertical
        for (int i = 0; i<3; i++){
            for(int j = 0; j<3; j++){

                if(i+j==2 && botao[i][j].getText() == x){
                    win++;
                }

            }
        }
        if(win==3){
            x+=" na vertical inversa ";
            winner(x);
        }
        win = 0;
    }

    private void winner(String x) {
        // TODO Auto-generated method stub
        game = false;
        JOptionPane.showMessageDialog(label, x+" GANHOU!");
        label.setText(x+" GANHOU!");
    }

    public void actionPerformed(ActionEvent e){


        JButton x = (JButton)e.getSource();


        if(x.getText() == "" && game){

            x.setText(turno);
            verify("X");
            nTurno++;


            turno = "O";
            iaPlay();
            verify("O");

            label.setText(turno);
        }



    }


     private void iaPlay() {
        // TODO Auto-generated method stub

         turno = "X";

         int win = 0;
         int self = 0;
         //DEFENDE
         //verifica linhas

        if (nTurno == 2) {
            attackColumn(1);
            return;
        }



         if(nTurno<6) {
             if (defend()) return;

             if (attack()) return;

         }else {
             if (attack()) return;
             if (defend()) return;
         }
         System.out.println("chegou no random");
         randomAttack();
    }

    private void randomAttack() {
        // TODO Auto-generated method stub
        int win = 0, opo = 0;

         for(int k = 0; k<3; k++){

             for (int i = 0; i<3; i++){
                 for(int j = 0; j<3; j++){

                     if(i==k && botao[i][j].getText() == "O"){
                         win++;
                     }
                     if(i==k && botao[i][j].getText() == "X"){
                         opo++;
                     }

                 }
             }
             if(win>=0 && opo+win < 3){
                 attackLine(k);
                 return ;
             }
             win = 0;
             opo = 0;

         }
         win=0;
         opo = 0;
         //verifica colunas
         for(int k = 0; k<3; k++){

             for (int i = 0; i<3; i++){
                 for(int j = 0; j<3; j++){

                     if(j==k && botao[i][j].getText() == "O"){
                         win++;
                     }
                     if(j==k && botao[i][j].getText() == "x"){
                         opo++;
                     }

                 }
             }

             if(win>=0 && opo+win < 3){
                 attackColumn(k);
                 return ;
             }
             win = 0;
             opo = 0;
         }
         win=0;
         opo = 0;
         //verifica verticais
         for (int i = 0; i<3; i++){
             for(int j = 0; j<3; j++){

                 if(i==j && botao[i][j].getText() == "O"){
                     win++;
                 }
                 if(i==j && botao[i][j].getText() == "X"){
                     opo++;
                 }

             }
         }
         if(win>=0 && opo+win < 3){
            attackMV();
            return ;
        }
         win=0;
         opo = 0;

         // outra vertical
         for (int i = 0; i<3; i++){
             for(int j = 0; j<3; j++){

                 if(i+j==2 && botao[i][j].getText() == "O"){
                     win++;
                 }
                 if(i+j==2 && botao[i][j].getText() == "X"){
                     opo++;
                 }

             }
         }
         if(win>=0 && opo+win < 3){
            attackIV();
            return ;
        }

         return ;
    }

    private boolean attack() {
        // TODO Auto-generated method stub
        //verifica linhas
        int win = 0, opo = 0;

         for(int k = 0; k<3; k++){

             for (int i = 0; i<3; i++){
                 for(int j = 0; j<3; j++){

                     if(i==k && botao[i][j].getText() == "O"){
                         win++;
                     }
                     if(i==k && botao[i][j].getText() == "X"){
                         opo++;
                     }

                 }
             }
             if(win>0 && opo+win < 3){

                 attackLine(k);
                 return true;
             }
             win = 0;
             opo = 0;

         }
         win=0;
         opo = 0;
         //verifica colunas
         for(int k = 0; k<3; k++){

             for (int i = 0; i<3; i++){
                 for(int j = 0; j<3; j++){

                     if(j==k && botao[i][j].getText() == "O"){
                         win++;
                     }
                     if(j==k && botao[i][j].getText() == "X"){
                         opo++;
                     }

                 }
             }

             if(win>0 && opo+win < 3){
                 System.out.println("ATACOU"+k);
                 attackColumn(k);
                 return true;
             }
             win = 0;
             opo = 0;
         }
         win=0;
         opo = 0;
         //verifica verticais
         for (int i = 0; i<3; i++){
             for(int j = 0; j<3; j++){

                 if(i==j && botao[i][j].getText() == "O"){
                     win++;
                 }
                 if(i==j && botao[i][j].getText() == "X"){
                     opo++;
                 }

             }
         }
         if(win>0 && opo+win < 3){
            attackMV();
            return true;
        }
         win=0;
         opo = 0;

         // outra vertical
         for (int i = 0; i<3; i++){
             for(int j = 0; j<3; j++){

                 if(i+j==2 && botao[i][j].getText() == "O"){
                     win++;
                 }
                 if(i+j==2 && botao[i][j].getText() == "X"){
                     opo++;
                 }

             }
         }
         if(win>0 && opo+win < 3){
            attackIV();
            return true;
        }

         return false;
    }

    private boolean defend() {
        // TODO Auto-generated method stub
        int win=0, self = 0;
        for(int k = 0; k<3; k++){

             for (int i = 0; i<3; i++){
                 for(int j = 0; j<3; j++){

                     if(j==k && botao[i][j].getText() == "X"){
                         win++;
                     }
                     if(j==k && botao[i][j].getText() == "O"){
                         self++;
                     }

                 }
             }
             if(win>1 && win + self <3){
                 attackLine(k);
                 return true;
             }

             self = 0;
             win = 0;
         }

        self = 0;
        win = 0;
         //verifica colunas
         for(int k = 0; k<3; k++){

             for (int i = 0; i<3; i++){
                 for(int j = 0; j<3; j++){

                     if(i==k && botao[i][j].getText() == "X"){
                         win++;
                     }
                     if(i==k && botao[i][j].getText() == "O"){
                         self++;
                     }

                 }
             }

             if(win>1 && win + self <3){
                 attackColumn(k);
                 return true;
             }
             self = 0;
             win = 0;
         }
         self = 0;
         win=0;

         //verifica verticais
         for (int i = 0; i<3; i++){
             for(int j = 0; j<3; j++){

                 if(i==j && botao[i][j].getText() == "X"){
                     win++;
                 }
                 if(i==j && botao[i][j].getText() == "O"){
                     self++;
                 }

             }
         }
         if(win>1 && win + self <3){
             attackMV();
            return true;
        }
         win=0;
         self = 0;

         // outra vertical
         for (int i = 0; i<3; i++){
             for(int j = 0; j<3; j++){

                 if(i+j==2 && botao[i][j].getText() == "X"){
                     win++;
                 }
                 if(i+j==2 && botao[i][j].getText() == "O"){
                     self++;
                 }

             }
         }
         if(win>1 && win + self <3){
            attackIV();
            return true;
        }

         win = 0;
         self = 0;
         return false;
    }

    private void attackIV() {
        // TODO Auto-generated method stub
        for (int i = 0; i<3; i++){
             for(int j = 0; j<3; j++){

                 if(i+j==2 && botao[i][j].getText() == ""){
                     botao[i][j].setText("O");
                     return;
                 }

             }
         }

    }

    private void attackMV() {
        // TODO Auto-generated method stub
        for (int i = 0; i<3; i++){
             for(int j = 0; j<3; j++){

                 if(i==j && botao[i][j].getText() == ""){
                     botao[i][j].setText("O");
                     return;
                 }

             }
         }

    }

    private void attackColumn(int k) {
        // TODO Auto-generated method stub
        for (int i = 0; i<3; i++){
                for(int j = 0; j<3; j++){

                    if(i==k && botao[i][j].getText() == ""){
                        System.out.println("ATACOU"+i+" "+j);
                        botao[i][j].setText("O");
                        return;
                    }

                }
            }


    }


    private void attackLine(int k) {
        // TODO Auto-generated method stub
        for (int i = 0; i<3; i++){
            for(int j = 0; j<3; j++){

                if(j==k && botao[i][j].getText() == ""){
                    botao[i][j].setText("O");
                    return;
                }

            }
        }

    }

    public static void main(String[] args) {
        Evento01 Evento = new Evento01();
    }

}
