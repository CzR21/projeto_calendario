/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import entities.Agenda;
import entities.Compromisso;
import entities.Convite;
import entities.Usuario;
import java.awt.HeadlessException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import services.AgendaService;
import services.CompromissoService;
import services.ConviteService;

/**
 *
 * @author rebec
 */
public final class PanelWindow extends javax.swing.JFrame {

    private Usuario usuario;
    private List<Agenda> agendas;
    private Agenda agendaSelecionada;
    private List<Convite> convites;
    private List<Compromisso> compromissos;

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
        buscarCompromissos();
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

        int id = Integer.parseInt(tableConvites.getModel().getValueAt(row, 0).toString());
        int confirmacao = JOptionPane.showConfirmDialog(null, "Deseja recusar o convite?", "Aceitar", JOptionPane.YES_NO_OPTION);

        Convite convite = null;

        for (Convite c : this.convites) {
            if (c.getId() == id) {
                convite = c;
                break;
            }
        }

        if (confirmacao == 1 || convite == null) {
            return;
        }

        try {
            ConviteService.recusarConvite(convite);
            buscarConvites();
            JOptionPane.showMessageDialog(null, "Convite recusado com sucesso!", "", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (HeadlessException | SQLException ex) {
            Logger.getLogger(PanelWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void aceitarConvite() {
        int row = tableConvites.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Selecione um convite para aceitar.", "", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int id = Integer.parseInt(tableConvites.getModel().getValueAt(row, 0).toString());
        int confirmacao = JOptionPane.showConfirmDialog(null, "Deseja aceitar o convite?", "Aceitar", JOptionPane.YES_NO_OPTION);

        Convite convite = null;

        for (Convite c : this.convites) {
            if (c.getId() == id) {
                convite = c;
                break;
            }
        }

        if (confirmacao == 1 || convite == null) {
            return;
        }

        try {
            ConviteService.aceitarConvite(convite);
            buscarConvites();
            JOptionPane.showMessageDialog(null, "Convite aceito com sucesso!", "", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (HeadlessException | SQLException ex) {
            Logger.getLogger(PanelWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        } catch (HeadlessException | SQLException ex) {
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
                    convite.getCompromisso() != null ? convite.getCompromisso().getTitulo() : "N/A",
                    convite.getCompromisso() != null ? convite.getCompromisso().getDataInicio().toString() : "N/A",
                    convite.getCompromisso() != null ? convite.getCompromisso().getLocal() : "N/A",});
                model.fireTableDataChanged();
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(PanelWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void buscarCompromissos() {
        try {
            DefaultTableModel model = (DefaultTableModel) tableCompromissos.getModel();
            model.fireTableDataChanged();
            model.setRowCount(0);

            this.compromissos = CompromissoService.buscarTodos(this.usuario.getId());

            for (Compromisso compromisso : this.compromissos) {
                model.addRow(new Object[]{
                    compromisso.getId(),
                    compromisso.getAgenda() != null ? compromisso.getAgenda().getNome() : "N/A",
                    compromisso.getTitulo(),
                    compromisso.getDataInicio().toString(),
                    compromisso.getLocal()});
                model.fireTableDataChanged();
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(PanelWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void excluirCompromisso() {
        int row = tableCompromissos.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Selecione um compromisso para excluir.", "", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int id = Integer.parseInt(tableCompromissos.getModel().getValueAt(row, 0).toString());
        int confirmacao = JOptionPane.showConfirmDialog(null, "Deseja excluir?", "Excluir", JOptionPane.YES_NO_OPTION);

        if (confirmacao == 1) { //Não
            return;
        }

        try {
            CompromissoService.removerCompromisso(id);
            buscarCompromissos();
            JOptionPane.showMessageDialog(null, "Compromisso excluído com sucesso!", "", JOptionPane.INFORMATION_MESSAGE);
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
        btnNovoConvite = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        lblListaConvite1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableCompromissos = new javax.swing.JTable();
        btnExcluirCompromisso = new javax.swing.JButton();
        btnEditarCompromisso = new javax.swing.JButton();

        jButton4.setText("jButton1");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblListaAgendas.setText("Listagem das Agendas");

        lblListaConvite.setText("Listagem dos Convites pendentes");

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
        tableAgendas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableAgendasMouseClicked(evt);
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

        btnNovoConvite.setText("Novo convite");
        btnNovoConvite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoConviteActionPerformed(evt);
            }
        });

        lblListaConvite1.setText("Listagem dos Compromissos");

        tableCompromissos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Agenda", "Titulo", "Data", "Local"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tableCompromissos);

        btnExcluirCompromisso.setText("Excluir");
        btnExcluirCompromisso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirCompromissoActionPerformed(evt);
            }
        });

        btnEditarCompromisso.setText("Editar");
        btnEditarCompromisso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarCompromissoActionPerformed(evt);
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
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnExcluirAgenda)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditarAgenda)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAdicionarAgenda))
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnNovoConvite, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRecusarConvite)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAceitarConvite))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnSair)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEditarUsuario))
                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblListaConvite1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNovoCompromisso))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblListaAgendas)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblListaConvite)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnExcluirCompromisso)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEditarCompromisso)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblListaAgendas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditarAgenda)
                    .addComponent(btnAdicionarAgenda)
                    .addComponent(btnExcluirAgenda))
                .addGap(15, 15, 15)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblListaConvite)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRecusarConvite)
                    .addComponent(btnAceitarConvite)
                    .addComponent(btnNovoConvite))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblListaConvite1)
                    .addComponent(btnNovoCompromisso))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditarCompromisso)
                    .addComponent(btnExcluirCompromisso))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
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
        if(agendaSelecionada != null){
            CompromissoWindow frame = new CompromissoWindow(usuario, agendaSelecionada, this);
            frame.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null, "Selecione uma agenda", "", JOptionPane.WARNING_MESSAGE);
        }
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

    private void btnEditarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarUsuarioActionPerformed
        UpdateUsuarioWindow frame = new UpdateUsuarioWindow(this.usuario, this);

        frame.setVisible(true);
    }//GEN-LAST:event_btnEditarUsuarioActionPerformed

    private void btnNovoConviteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoConviteActionPerformed
        // TODO add your handling code here:
        ConviteWindow frame = new ConviteWindow(this.usuario, this);

        frame.setVisible(true);
    }//GEN-LAST:event_btnNovoConviteActionPerformed

    private void tableAgendasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableAgendasMouseClicked
        int row = tableAgendas.getSelectedRow();
        
        if (row != -1) {
            agendaSelecionada = agendas.get(row);
        }
    }//GEN-LAST:event_tableAgendasMouseClicked

    private void btnExcluirCompromissoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirCompromissoActionPerformed
        excluirCompromisso();
    }//GEN-LAST:event_btnExcluirCompromissoActionPerformed

    private void btnEditarCompromissoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarCompromissoActionPerformed
        int row = tableCompromissos.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Selecione um compromisso para editar.", "", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int id = Integer.parseInt(tableCompromissos.getModel().getValueAt(row, 0).toString());

        Compromisso compromisso = null;

        for (Compromisso c : this.compromissos) {
            if (c.getId() == id) {
                compromisso = c;
                break;
            }
        }

        if (compromisso == null) {
            return;
        }

        CompromissoWindow frame = new CompromissoWindow(this.usuario, compromisso.getAgenda(), compromisso,this);

        frame.setVisible(true);
    }//GEN-LAST:event_btnEditarCompromissoActionPerformed

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
    private javax.swing.JButton btnEditarCompromisso;
    private javax.swing.JButton btnEditarUsuario;
    private javax.swing.JButton btnExcluirAgenda;
    private javax.swing.JButton btnExcluirCompromisso;
    private javax.swing.JButton btnNovoCompromisso;
    private javax.swing.JButton btnNovoConvite;
    private javax.swing.JButton btnRecusarConvite;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton jButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lblListaAgendas;
    private javax.swing.JLabel lblListaConvite;
    private javax.swing.JLabel lblListaConvite1;
    private javax.swing.JTable tableAgendas;
    private javax.swing.JTable tableCompromissos;
    private javax.swing.JTable tableConvites;
    // End of variables declaration//GEN-END:variables
}
