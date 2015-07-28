package br.ufpi.es.view.gui.turma;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.ufpi.es.controller.Fachada;
import br.ufpi.es.model.Aluno;
import br.ufpi.es.model.Turma;
import br.ufpi.es.system.exception.turma.TurmaNaoExistenteException;

/**
 * Classe que monta a Tela para listar alunos de uma turma
 * @author armandosoaressousa
 *
 */
public class TelaListarAlunosTurma extends JDialog {
	
	private static final long serialVersionUID = 1L;

	private Fachada fachada;

	// Título do menu
	private JPanel painelSuperior;
	private JLabel labelTitulo;

	private JPanel painelForm;

	// Labels dos campos
	//private JPanel painelEsquerda;
	private JLabel labelIDBusca;

	// Campos de texto
	private JPanel painelBusca;
	private JTextField txtIDBusca;
	private JButton buttonBuscar;
	
    private JPanel painelCentral;
    private JPanel painelDireita;
	private JTextArea txtAlunos;
	
	/**
	 * Monta a Tela de Listar turma
	 * @param f fachada do sistema
	 */
	public TelaListarAlunosTurma(Fachada f) {
		// Configurações do dialog
		setTitle("Listar Turma");
		setModal(true);
		setSize(500, 300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		setLayout(new BorderLayout()); // Altera gerenciador de layout padrão

		fachada = f;

		// Insere os componentes no dialog
		painelSuperior = new JPanel();
		painelSuperior.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
		labelTitulo = new JLabel("Listar Alunos da Turma");
		labelTitulo.setFont(new Font("sans-serif", Font.BOLD, 16));
		labelTitulo.setForeground(Color.BLUE);
		painelSuperior.add(labelTitulo);

		painelForm = new JPanel(new BorderLayout(10, 10));
		painelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		//painelEsquerda = new JPanel(new GridLayout(4, 1, 10, 10));
		labelIDBusca = new JLabel("Informe o identificador:");
		labelIDBusca.setFont(new Font("sans-serif", Font.BOLD, 12));

		//painelEsquerda.add(labelIDBusca);

		painelCentral = new JPanel(new BorderLayout());
		painelCentral.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		txtAlunos = new JTextArea();
		txtAlunos.setEditable(false);
		txtAlunos.setSelectedTextColor(Color.BLACK);
		txtAlunos.setSelectionColor(Color.WHITE);
		txtAlunos.setFont(new Font("monospaced", Font.PLAIN, 12));
		painelCentral.add(new JScrollPane(txtAlunos));
		
		painelDireita = new JPanel(new GridLayout(1, 1, 10, 10));
		painelBusca = new JPanel(new BorderLayout(10, 0));
	
		txtIDBusca = new JTextField();
		buttonBuscar = new JButton("Buscar");
		buttonBuscar.setFont(new Font("sans-serif", Font.BOLD, 13));
		// Adiciona o listener ao botão "Buscar"
		buttonBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String idTurma = txtIDBusca.getText();
				txtAlunos.setText("");
				if (idTurma.compareTo("") != 0) { // verifica se id da turma ja existe
					try {
						Turma turma = fachada.buscarTurma(Integer.valueOf(idTurma));
						//colocar as informacoes em uma lista com os dados dos alunos da turma						
						List<Aluno> listaAlunosAux;
						listaAlunosAux = fachada.buscarTurma(turma.getIdTurma()).getAlunos();
						if (listaAlunosAux == null || listaAlunosAux.size() == 0){
							JOptionPane.showMessageDialog(null, "Nao existem alunos","Nao existem alunos matriculados nesta turma.",JOptionPane.INFORMATION_MESSAGE);
						}else{
							Iterator<Aluno> iterator = listaAlunosAux.iterator();
							//percorre a lista de alunos e exibe em texto
							while(iterator.hasNext()){
								Aluno elemento = iterator.next();
								txtAlunos.append(String.format("%s\n", "Aluno: " + elemento.getNome()));
							}		
						}
					} catch (TurmaNaoExistenteException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage()+ ".", "Turma Não Existente",JOptionPane.INFORMATION_MESSAGE);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(),"Erro", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null,"Você deve informar o identificador da turma.","Campo obrigatório", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		painelBusca.add(labelIDBusca, BorderLayout.WEST);
		painelBusca.add(txtIDBusca, BorderLayout.CENTER);
		painelBusca.add(buttonBuscar, BorderLayout.EAST);
		painelDireita.add(painelBusca);
		painelForm.add(painelDireita, BorderLayout.SOUTH);
		painelForm.add(painelCentral, BorderLayout.CENTER);
		
		add(painelSuperior, BorderLayout.NORTH);
		add(painelForm, BorderLayout.CENTER);

		setVisible(true);
	}
}