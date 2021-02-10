/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.room.booking.system;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 *
 * @author jackf
 */
public class Receipt extends javax.swing.JFrame {
    Double bheight = 0.0;
    /**
     * Creates new form Receipt
     */
    public Receipt() {
        initComponents();
        this.setLocationRelativeTo(null);
    }
    
    public final static Runnable runnable =
     (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation"); // error sound

    public static void errorMesg(String mesg,String title){
        runnable.run(); 
        JOptionPane.showMessageDialog(null, mesg, title, JOptionPane.INFORMATION_MESSAGE);
    }
    
    public PageFormat getPageFormat(PrinterJob pj){
        PageFormat pf = pj.defaultPage();
        Paper paper = pf.getPaper();
        
        double bodyHeight = bheight;
        double headerHeight = 5.0;
        double footerHeight = 5.0;
        double width = cm_to_pp(8);
        double height = cm_to_pp(headerHeight+bodyHeight+footerHeight - cm_to_pp(1));
        paper.setSize(width,height);
        paper.setImageableArea(0,10,width,height);
        
        pf.setOrientation(PageFormat.PORTRAIT);
        pf.setPaper(paper);
        
        return pf;
    }
    
    protected static double cm_to_pp(double cm){
        return toPPI(cm * 0.393600787);
    }
    
    protected static double toPPI (double inch){
        return inch * 72d;
    }
    
    public class Billprintable implements Printable{
        
        
        public int print(Graphics graphics, PageFormat pageformat, int pageindex)
        throws PrinterException
        {
           
            int result = NO_SUCH_PAGE;
            LocalDateTime now = LocalDateTime.now();
            if (pageindex == 0){
                
                Graphics2D gra2D = (Graphics2D) graphics;
                double width = pageformat.getImageableWidth();
                gra2D.translate((int) pageformat.getImageableX(),(int) pageformat.getImageableY());
                
            try{
                int y = 20;
                int yshift = 10;
                int headerRectheight = 10;
                
                gra2D.setFont(new Font("Monospaced",Font.PLAIN,9));
                gra2D.drawString("--------------------------------------",12,y); y+=yshift;
                gra2D.drawString("               No 46             ",12,y); y+=yshift;
                gra2D.drawString("         Jalan Batu Nilam 30     ",12,y); y+=yshift;
                gra2D.drawString("        Bandar Bukit Tinggi 2,   ",12,y); y+=yshift;
                gra2D.drawString("            41200, Klang         ",12,y); y+=yshift;
                gra2D.drawString("             011-2219875         ",12,y); y+=yshift;
                gra2D.drawString("--------------------------------------",12,y); y+=yshift;
                gra2D.drawString("Room ID:                    " +jSearchField.getText() + "  ",10,y); y+=yshift;
                gra2D.drawString("--------------------------------------",10,y); y+=yshift;
                gra2D.drawString("Customer Name:              " +jCustomerNameField.getText() + "  ",10,y); y+=yshift;
                gra2D.drawString("--------------------------------------",10,y); y+=yshift;
                gra2D.drawString("Check In Date:              " +jCheckInDate.getText() + "  ",10,y); y+=yshift; 
                gra2D.drawString("--------------------------------------",10,y); y+=yshift;
                gra2D.drawString("Check Out Date:             " +jCheckOutDate.getText() + "  ",10,y); y+=yshift; 
                gra2D.drawString("--------------------------------------",10,y); y+=yshift;
                gra2D.drawString("Room Charges:               " +jRoomChargesField.getText() + "  ",10,y); y+=yshift;
                gra2D.drawString("--------------------------------------",10,y); y+=yshift;
                gra2D.drawString("Service Tax:                " +jServiceTaxField.getText() + "  ",10,y); y+=yshift;
                gra2D.drawString("--------------------------------------",10,y); y+=yshift;
                gra2D.drawString("Tourism Tax:                " +jTourismTaxField.getText() + "  ",10,y); y+=yshift;
                gra2D.drawString("--------------------------------------",10,y); y+=yshift;
                gra2D.drawString("Total Payment:              " +jTotalPaymentField.getText() + "  ",10,y); y+=yshift;
                gra2D.drawString("--------------------------------------",10,y); y+=yshift;
                gra2D.drawString("               THANK YOU!    ",10,y); y+=yshift;
                gra2D.drawString("           jackfeng@gmail.com  ",10,y); y+=yshift;
                gra2D.drawString("--------------------------------------",10,y); y+=yshift;
                

            }
               catch(Exception e){
                   System.out.println(e);
               } 
                
            result = PAGE_EXISTS;
            
            }
           
            return result;
        }
    }
   class pay  {
     public int DayofStay;
     public double RoomCharges,ServiceTax,TourismTax;

     pay(int DOS,double RC,double ST,double TT){
     DayofStay = DOS;
     RoomCharges = RC;
     ServiceTax = ST;
     TourismTax = TT;
     }

     public int GetDay(){
     return DayofStay;
     }
     public double GetRoomCharges(){
     return RoomCharges;
     }
     public double GetServiceTax(){
     return ServiceTax;
     }
     public double GetTax(){
     return TourismTax;
     }
     
     

     public double GetPayment(){

     double p = GetDay()*GetRoomCharges();
     return p;
     }

     public double GetPaymentServiceTax(){

     double ST = GetPayment()*GetServiceTax();
     return ST;
     }

     public double GetPaymentTax(){

     double TT = GetDay()*GetTax();
     return TT;
     }

     public double GetTotalPayment(){

     double TP = GetPayment()+GetPaymentServiceTax()+GetPaymentTax();
     return TP;
     }
       
   }
        
   


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jCustomerNameField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jConfirmButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jDurationField = new javax.swing.JTextField();
        jSearchButton = new javax.swing.JButton();
        jSearchField = new javax.swing.JTextField();
        jCheckOutDate = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jBackButton = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jRoomChargesField = new javax.swing.JTextField();
        jServiceTaxField = new javax.swing.JTextField();
        jTourismTaxField = new javax.swing.JTextField();
        jTotalPaymentField = new javax.swing.JTextField();
        jCheckInDate = new javax.swing.JTextField();
        jPrintButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(228, 241, 254));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Receipt");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(202, 202, 202)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel2.setText("RoomID: ");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel3.setText("Customer Name :");

        jCustomerNameField.setEditable(false);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel4.setText("Check Out Date:");

        jConfirmButton.setText("Confirm");
        jConfirmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jConfirmButtonActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel5.setText("Check In Date:");

        jDurationField.setEditable(false);

        jSearchButton.setText("Search");
        jSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSearchButtonActionPerformed(evt);
            }
        });

        jCheckOutDate.setEditable(false);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel6.setText("Duration(days):");

        jBackButton.setText("Back");
        jBackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBackButtonActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel7.setText("Room Charges: ");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel8.setText("Service Tax: ");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel9.setText("Tourism Tax: ");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel10.setText("Total Payment: ");

        jRoomChargesField.setEditable(false);

        jServiceTaxField.setEditable(false);

        jTourismTaxField.setEditable(false);

        jTotalPaymentField.setEditable(false);

        jPrintButton.setText("Print");
        jPrintButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPrintButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jCustomerNameField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                            .addComponent(jSearchField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jCheckInDate, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jCheckOutDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jBackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jConfirmButton))
                                .addComponent(jDurationField, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPrintButton, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jRoomChargesField, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                                .addComponent(jServiceTaxField)
                                .addComponent(jTourismTaxField)
                                .addComponent(jTotalPaymentField)))))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCustomerNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jCheckInDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckOutDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jDurationField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jConfirmButton)
                    .addComponent(jBackButton))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jRoomChargesField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jServiceTaxField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTourismTaxField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTotalPaymentField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jPrintButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSearchButtonActionPerformed

        String search = jSearchField.getText();
       
            if (jSearchField.getText().equals("")){
            Receipt.errorMesg("Please Enter RoomID!", "Error");
            return;
            }
        try{
            File Flist = new File("addbooking.txt");
            Scanner sc1 = new Scanner(Flist);
            while(sc1.hasNextLine()){
                String Line = sc1.nextLine();
                if(Line.contains(search)){
                    String[] wordsinLine = Line.split(",");
                    if(search.equals(wordsinLine[0]))
                        jSearchField.setText(wordsinLine[0]);
                        jCustomerNameField.setText(wordsinLine[4]);
                        jCheckInDate.setText(wordsinLine[2]);
                        jCheckOutDate.setText(wordsinLine[3]);
               
                      
                        
                                
                                
                        
                        
                   
                }
            }sc1.close();
        } catch (FileNotFoundException ex) {
           Receipt.errorMesg("Error Occured", "Error");
        }

               // TODO add your handling code here:                         
    }//GEN-LAST:event_jSearchButtonActionPerformed

    private void jConfirmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jConfirmButtonActionPerformed
     
        DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String CheckIn = jCheckInDate.getText();
        String CheckOut = jCheckOutDate.getText();
        LocalDate Inday = LocalDate.parse(CheckIn,dateformat);
        LocalDate OutDay = LocalDate.parse(CheckOut,dateformat);

        long DOS = ChronoUnit.DAYS.between(Inday, OutDay);

        String calDayofStay = Long.toString(DOS);
           jDurationField.setText(calDayofStay);
           
     
        int DayofStay = Integer.parseInt(calDayofStay);

        pay Checkout = new pay (DayofStay,350,0.1,10);

       

        String Charges = String.valueOf(Checkout.GetPayment());
        jRoomChargesField.setText("RM "+Charges);

        String ServiceTax = String.valueOf(Checkout.GetPaymentServiceTax());
        jServiceTaxField.setText("RM "+ServiceTax);

        String TourismTax = String.valueOf(Checkout.GetPaymentTax());
        jTourismTaxField.setText("RM "+TourismTax);

        String TotalPayment = String.valueOf(Checkout.GetTotalPayment());
        jTotalPaymentField.setText("RM "+TotalPayment);
        
       

               
              
            // TODO add your handling code here:
    }//GEN-LAST:event_jConfirmButtonActionPerformed

    private void jBackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBackButtonActionPerformed
        ManageBooking rgf = new ManageBooking();
        rgf.setVisible(true);
        rgf.pack();
        rgf.setLocationRelativeTo(null);
        rgf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();          // TODO add your handling code here:
    }//GEN-LAST:event_jBackButtonActionPerformed

    private void jPrintButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPrintButtonActionPerformed
        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setPrintable(new Billprintable(),getPageFormat(pj));

        try{
            pj.print();
        }
        catch (PrinterException ex){
            ex.printStackTrace();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jPrintButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Receipt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Receipt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Receipt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Receipt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Receipt().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBackButton;
    private javax.swing.JTextField jCheckInDate;
    private javax.swing.JTextField jCheckOutDate;
    private javax.swing.JButton jConfirmButton;
    private javax.swing.JTextField jCustomerNameField;
    private javax.swing.JTextField jDurationField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton jPrintButton;
    private javax.swing.JTextField jRoomChargesField;
    private javax.swing.JButton jSearchButton;
    private javax.swing.JTextField jSearchField;
    private javax.swing.JTextField jServiceTaxField;
    private javax.swing.JTextField jTotalPaymentField;
    private javax.swing.JTextField jTourismTaxField;
    // End of variables declaration//GEN-END:variables
}
