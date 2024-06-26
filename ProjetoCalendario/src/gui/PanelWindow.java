/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import entities.Agenda;
import entities.Convite;
import entities.Usuario;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import services.AgendaService;
import services.ConviteService;

/**
 *
 * @author rebec
 */
public final class PanelWindow extends javax.swing.JFrame {

    private Usuario usuario;
    private List<Agenda> agendas;
    private List<Convite> convites;

    /**
     * Creates new form PanelWindow
     *
     * @param usuario
     */
    public PanelWindow(Usuario usuario) {
        initComponents();
        setLocationRelativeTo(null);
        this.usuario = usuario;
        buscarAgendas();
        buscarConvites();
    }

    /**
     * Creates new form PanelWindow
     */
    public PanelWindow() {
        initComponents();
        setLocationRelativeTo(null);
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    private void recusarConvite() {
        int row = tableConvites.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Selecione um convite para recusar.", "", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int id = Integer.parseInt(tableAgendas.getModel().getValueAt(row, 0).toString());
        int confirmacao = JOptionPane.showConfirmDialog(null, "Deseja recusar o convite?", "Aceitar", JOptionPane.YES_NO_OPTION);

        if (confirmacao == 1) { //Não
            return;
        }

        // implementar o recusar no banco
    }

    private void aceitarConvite() {
        int row = tableConvites.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Selecione um convite para aceitar.", "", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int id = Integer.parseInt(tableAgendas.getModel().getValueAt(row, 0).toString());
        int confirmacao = JOptionPane.showConfirmDialog(null, "Deseja aceitar o convite?", "Aceitar", JOptionPane.YES_NO_OPTION);

        if (confirmacao == 1) { //Não
            return;
        }

        // implementar o aceite no banco
    }

    private void excluirAgenda() {
        int row = tableAgendas.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Selecione uma agenda para excluir.", "", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int id = Integer.parseInt(tableAgendas.getModel().getValueAt(row, 0).toString());
        int confirmacao = JOptionPane.showConfirmDialog(null, "Deseja excluir?", "Excluir", JOptionPane.YES_NO_OPTION);

        if (confirmacao == 1) { //Não
            return;
        }

        try {
            AgendaService.removerAgenda(id);
            buscarAgendas();
            JOptionPane.showMessageDialog(null, "Agenda excluída com sucesso!", "", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(PanelWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void buscarAgendas() {
        try {
            DefaultTableModel model = (DefaultTableModel) tableAgendas.getModel();
            model.fireTableDataChanged();
            model.setRowCount(0);

            this.agendas = AgendaService.buscarAgendasPorIdUsuario(this.usuario.getId());

            for (Agenda agenda : this.agendas) {
                model.addRow(new Object[]{
                    agenda.getId(),
                    agenda.getNome(),
                    agenda.getDescricao(),
                    agenda.getStatus()
                });
                model.fireTableDataChanged();
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(PanelWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void buscarConvites() {
        try {
            DefaultTableModel model = (DefaultTableModel) tableConvites.getModel();
            model.fireTableDataChanged();
            model.setRowCount(0);

            this.convites = ConviteService.buscarConvitePorIdUsuario(this.usuario.getId());

            for (Convite convite : this.convites) {
                model.addRow(new Object[]{
                    convite.getId(),
                    "Compromisso", // aqui trocar pelo correto depois
                    "Data",
                    "Local"
                });
                model.fireTableDataChanged();
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (Exception ex) {
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

        jButton4 = new javax.swing.JButton();
        lblListaAgendas = new javax.swing.JLabel();
        lblListaConvite = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableAgendas = new javax.swing.JTable();
        btnEditarAgenda = new javax.swing.JButton();
        btnExcluirAgenda = new javax.swing.JButton();
        btnAdicionarAgenda = new javax.swing.JButton();
        btnNovoCompromisso = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableConvites = new javax.swing.JTable();
        btnRecusarConvite = new javax.swing.JButton();
        btnAceitarConvite = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        btnEditarUsuario = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        btnVisualizarAgenda = new javax.swing.JButton();

        jButton4.setText("jButton1");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblListaAgendas.setText("Listagem das Agendas");

        lblListaConvite.setText("Listagem dos Convites");

        tableAgendas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Nome", "Descrição", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableAgendas);

        btnEditarAgenda.setText("Editar");
        btnEditarAgenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarAgendaActionPerformed(evt);
            }
        });

        btnExcluirAgenda.setText("Excluir");
        btnExcluirAgenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirAgendaActionPerformed(evt);
            }
        });

        btnAdicionarAgenda.setText("Adicionar agenda");
        btnAdicionarAgenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarAgendaActionPerformed(evt);
            }
        });

        btnNovoCompromisso.setText("Novo compromisso");
        btnNovoCompromisso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoCompromissoActionPerformed(evt);
            }
        });

        tableConvites.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Compromisso", "Data", "Local"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tableConvites);

        btnRecusarConvite.setText("Recusar convite");
        btnRecusarConvite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecusarConviteActionPerformed(evt);
            }
        });

        btnAceitarConvite.setText("Aceitar convite");
        btnAceitarConvite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceitarConviteActionPerformed(evt);
            }
        });

        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        btnEditarUsuario.setText("Editar usuário");
        btnEditarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarUsuarioActionPerformed(evt);
            }
        });

        btnVisualizarAgenda.setText("Visualizar");
        btnVisualizarAgenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVisualizarAgendaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblListaAgendas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNovoCompromisso))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnExcluirAgenda)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditarAgenda)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnVisualizarAgenda)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAdicionarAgenda))
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnRecusarConvite)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAceitarConvite))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnSair)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEditarUsuario))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblListaConvite))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblListaAgendas)
                    .addComponent(btnNovoCompromisso))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditarAgenda)
                    .addComponent(btnAdicionarAgenda)
                    .addComponent(btnExcluirAgenda)
                    .addComponent(btnVisualizarAgenda))
                .addGap(15, 15, 15)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblListaConvite)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRecusarConvite)
                    .addComponent(btnAceitarConvite))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditarUsuario)
                    .addComponent(btnSair))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdicionarAgendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarAgendaActionPerformed
        CriarEditarAgendaWindow frame = new CriarEditarAgendaWindow(this.usuario, this);

        frame.setVisible(true);
    }//GEN-LAST:event_btnAdicionarAgendaActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnNovoCompromissoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoCompromissoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNovoCompromissoActionPerformed

    private void btnRecusarConviteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecusarConviteActionPerformed
        this.recusarConvite();
    }//GEN-LAST:event_btnRecusarConviteActionPerformed

    private void btnAceitarConviteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceitarConviteActionPerformed
        this.aceitarConvite();
    }//GEN-LAST:event_btnAceitarConviteActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        dispose();
        LoginWindow frame = new LoginWindow();
        frame.setVisible(true);
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnExcluirAgendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirAgendaActionPerformed
        excluirAgenda();
    }//GEN-LAST:event_btnExcluirAgendaActionPerformed

    private void btnEditarAgendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarAgendaActionPerformed
        int row = tableAgendas.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Selecione uma agenda para editar.", "", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int id = Integer.parseInt(tableAgendas.getModel().getValueAt(row, 0).toString());

        Agenda agenda = null;

        for (Agenda a : this.agendas) {
            if (a.getId() == id) {
                agenda = a;
                break;
            }
        }

        if (agenda == null) {
            return;
        }

        CriarEditarAgendaWindow frame = new CriarEditarAgendaWindow(this.usuario, agenda, this);

        frame.setVisible(true);
    }//GEN-LAST:event_btnEditarAgendaActionPerformed

    private void btnVisualizarAgendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisualizarAgendaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVisualizarAgendaActionPerformed

    private void btnEditarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarUsuarioActionPerformed
        UpdateUsuarioWindow frame = new UpdateUsuarioWindow(this.usuario, this);

        frame.setVisible(true);
    }//GEN-LAST:event_btnEditarUsuarioActionPerformed

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
            java.util.logging.Logger.getLogger(PanelWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PanelWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PanelWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PanelWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PanelWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceitarConvite;
    private javax.swing.JButton btnAdicionarAgenda;
    private javax.swing.JButton btnEditarAgenda;
    private javax.swing.JButton btnEditarUsuario;
    private javax.swing.JButton btnExcluirAgenda;
    private javax.swing.JButton btnNovoCompromisso;
    private javax.swing.JButton btnRecusarConvite;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnVisualizarAgenda;
    private javax.swing.JButton jButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblListaAgendas;
    private javax.swing.JLabel lblListaConvite;
    private javax.swing.JTable tableAgendas;
    private javax.swing.JTable tableConvites;
    // End of variables declaration//GEN-END:variables
}
