/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import entities.Compromisso;
import entities.Convite;
import entities.Usuario;
import enums.TipoStatus;
import enums.TipoStatusConvite;
import java.awt.HeadlessException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import services.CompromissoService;
import services.ConviteService;
import services.UsuarioService;

/**
 *
 * @author rebec
 */
public class ConviteWindow extends javax.swing.JFrame {

    private Usuario usuario;
    private PanelWindow panel;
    private List<Usuario> usuarios;
    private List<Compromisso> compromissos;

    /**
     * Creates new form ConviteWindow
     */
    public ConviteWindow() {
        initComponents();
    }

    public ConviteWindow(Usuario usuario, PanelWindow panel) {
        initComponents();
        this.usuario = usuario;
        this.panel = panel;
        buscarUsuarios();
        buscarComprimissos();
        panel.setVisible(false);
    }

    private void buscarUsuarios() {
        try {
            DefaultTableModel model = (DefaultTableModel) tableUsuarios.getModel();
            model.fireTableDataChanged();
            model.setRowCount(0);

            this.usuarios = UsuarioService.buscarTodos(this.usuario.getId());

            for (Usuario usuario : this.usuarios) {
                model.addRow(new Object[]{
                    usuario.getId(),
                    usuario.getNome(),
                    usuario.getEmail()
                });
                model.fireTableDataChanged();
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(PanelWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void buscarComprimissos() {
        try {
            DefaultTableModel model = (DefaultTableModel) tableCompromissos.getModel();
            model.fireTableDataChanged();
            model.setRowCount(0);

            this.compromissos = CompromissoService.buscarTodos(this.usuario.getId());

            for (Compromisso compromisso : this.compromissos) {
                model.addRow(new Object[]{
                    compromisso.getId(),
                    compromisso.getTitulo()
                });
                model.fireTableDataChanged();
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(PanelWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void convidar() {
        int rowUsuario = tableUsuarios.getSelectedRow();
        int rowCompromisso = tableCompromissos.getSelectedRow();

        if (rowUsuario == -1) {
            JOptionPane.showMessageDialog(null, "Selecione um usuário para convidar.", "", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if (rowCompromisso == -1) {
            JOptionPane.showMessageDialog(null, "Selecione um compromisso para convidar.", "", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int idUsuario = Integer.parseInt(tableUsuarios.getModel().getValueAt(rowUsuario, 0).toString());
        int idCompromisso = Integer.parseInt(tableUsuarios.getModel().getValueAt(rowCompromisso, 0).toString());

        int confirmacao = JOptionPane.showConfirmDialog(null, "Deseja convidar?", "Aceitar", JOptionPane.YES_NO_OPTION);

        Compromisso compromisso = null;

        for (Compromisso c : this.compromissos) {
            if (c.getId() == idCompromisso) {
                compromisso = c;
                break;
            }
        }

        Usuario usuario = null;

        for (Usuario u : this.usuarios) {
            if (u.getId() == idUsuario) {
                usuario = u;
                break;
            }
        }

        if (confirmacao == 1 || compromisso == null || usuario == null) {
            return;
        }

        try {
            Convite convite = new Convite();
            convite.setIdUsurio(usuario.getId());
            convite.setIdCompromisso(compromisso.getId());
            convite.setStatus(TipoStatus.ATIVO);
            convite.setStatusConvite(TipoStatusConvite.PENDENTE);

            ConviteService.cadastrarConvite(convite);

            JOptionPane.showMessageDialog(null, "Convite enviado com sucesso!", "", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (HeadlessException | SQLException ex) {
            Logger.getLogger(PanelWindow.class.getName()).log(Level.SEVERE, null, ex);
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

        lblNovoConvite = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableUsuarios = new javax.swing.JTable();
        lblUsuario = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableCompromissos = new javax.swing.JTable();
        btnConvidar = new javax.swing.JButton();
        btnVoltar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblNovoConvite.setText("Novo convite");

        tableUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Nome", "Email"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableUsuarios);

        lblUsuario.setText("Usuários");

        jLabel1.setText("Compromissos");

        tableCompromissos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Nome"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tableCompromissos);

        btnConvidar.setText("Enviar convite");
        btnConvidar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConvidarActionPerformed(evt);
            }
        });

        btnVoltar.setText("Voltar");
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(161, 161, 161)
                                .addComponent(lblNovoConvite))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblUsuario))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 54, Short.MAX_VALUE)
                .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(btnConvidar, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNovoConvite)
                .addGap(18, 18, 18)
                .addComponent(lblUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConvidar)
                    .addComponent(btnVoltar))
                .addContainerGap(56, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConvidarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConvidarActionPerformed
        convidar();
    }//GEN-LAST:event_btnConvidarActionPerformed

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        this.panel.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnVoltarActionPerformed

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
            java.util.logging.Logger.getLogger(ConviteWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConviteWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConviteWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConviteWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConviteWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConvidar;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblNovoConvite;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JTable tableCompromissos;
    private javax.swing.JTable tableUsuarios;
    // End of variables declaration//GEN-END:variables
}
