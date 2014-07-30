package ciscoroutertool.gui;

import ciscoroutertool.rules.Rule;
import ciscoroutertool.scanner.FullReport;
import ciscoroutertool.scanner.HostReport;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import java.util.ArrayList;

/**
 * This window displays the output in a tree format and lets the user remove
 * nodes from the report, and then output it to a file.
 * @version 0.01ALPHA
 * @author Andrew H. Johnston
 */
public class OutputReview extends javax.swing.JFrame {

    private FullReport report;
    private DefaultMutableTreeNode selected = null;
    
    /**
     * Creates new form OutputReview
     */
    public OutputReview(FullReport r) {
        report = r;
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("root");
        ArrayList<HostReport> reports = report.getReports();
        for (HostReport h : reports) {
            DefaultMutableTreeNode host = new DefaultMutableTreeNode(h.getHost().toString());
            ArrayList<Rule> matchedRules = h.getMatchedRules();
            for (Rule rule : matchedRules) {
                DefaultMutableTreeNode ruleName = new DefaultMutableTreeNode(rule.getName());
                DefaultMutableTreeNode ruleDesc = new DefaultMutableTreeNode(rule.getDescription());
                ruleName.add(ruleDesc);
                host.add(ruleName);
            }
            root.add(host);
        }
        initComponents();
        reportTree = new JTree(root);
        //The following allows for us to select one node at a time (for deletion)
        reportTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        //Hide the root node so it looks like the hosts are the root node
        reportTree.setRootVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblPrompt = new javax.swing.JLabel();
        btnDelete = new javax.swing.JButton();
        btnCSV = new javax.swing.JButton();
        btnHTML1 = new javax.swing.JButton();
        btnCSV1 = new javax.swing.JButton();
        treeScrollPane = new javax.swing.JScrollPane();
        reportTree = new javax.swing.JTree();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Review Output");

        lblPrompt.setText("Please review the following finds. Press delete to remove an item from the list.");

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnCSV.setText("Output to XML");

        btnHTML1.setText("Output to HTML");

        btnCSV1.setText("Output to CSV");

        treeScrollPane.setViewportView(reportTree);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPrompt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnCSV)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCSV1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnHTML1))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(treeScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 649, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(589, 589, 589)
                                    .addComponent(btnDelete))))
                        .addGap(0, 14, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPrompt, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDelete)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(treeScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHTML1)
                    .addComponent(btnCSV1)
                    .addComponent(btnCSV))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        //TODO: Remove relevant HostReport from FullReport
        selected = (DefaultMutableTreeNode) reportTree.getLastSelectedPathComponent();
        if (selected == null) {
            //Nothing is selected
            return;
        }
        MutableTreeNode parent = (MutableTreeNode) selected.getParent();
        int index = parent.getIndex(selected);
        parent.remove(selected);

        DefaultTreeModel model = (DefaultTreeModel) reportTree.getModel();
        model.nodesWereRemoved(parent, new int[]{index}, new Object[]{selected});

    }//GEN-LAST:event_btnDeleteActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCSV;
    private javax.swing.JButton btnCSV1;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnHTML1;
    private javax.swing.JLabel lblPrompt;
    private javax.swing.JTree reportTree;
    private javax.swing.JScrollPane treeScrollPane;
    // End of variables declaration//GEN-END:variables
}
