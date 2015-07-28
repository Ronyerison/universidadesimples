package br.ufpi.es.view.gui.turma;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.ufpi.es.controller.Fachada;
import br.ufpi.es.model.Aluno;
import br.ufpi.es.model.Turma;

/**
 * Classe que monta a Tela matricular aluno em turma
 * @author armandosoaressousa
 *
 */
public class TelaMatricularAluno extends JDialog {

	private static final long serialVersionUID = 1L;

	private Fachada fachada;

	// Título do menu
	private JPanel painelSuperior;
	private JLabel labelTitulo;

	private JPanel painelForm;

	// Labels dos campos
	private JPanel painelEsquerda;
	private JLabel labelMatricula;
	private JLabel labelIdentificador;

	// Campos de texto
	private JPanel painelDireita;
	private JTextField txtmatricula;
	private JTextField txtIdentificador;

	// Botões
	private JPanel painelInferior;
	private JButton botaoLimpar;
	private JButton botaoMatricular;

	/**
	 * Monta a Tela Matricular Aluno em Turma
	 * @param f fachada do sistema
	 */
	public TelaMatricularAluno(Fachada f) {
		// Configurações do dialog
		setTitle("Matricular aluno em Turma");
		setModal(true);
		setSize(500, 250);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		setLayout(new BorderLayout()); // Altera gerenciador de layout padrão

		fachada = f;

		// Insere os componentes no dialog
		painelSuperior = new JPanel();
		painelSuperior.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
		labelTitulo = new JLabel("Matricular aluno");
		labelTitulo.setFont(new Font("sans-serif", Font.BOLD, 16));
		labelTitulo.setForeground(Color.BLUE);
		painelSuperior.add(labelTitulo);

		painelForm = new JPanel(new BorderLayout(10, 10));
		painelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		painelEsquerda = new JPanel(new GridLayout(3, 1, 10, 10));
		labelMatricula = new JLabel("Informe o matricula:");
		labelMatricula.setFont(new Font("sans-serif", Font.BOLD, 12));
		labelIdentificador = new JLabel("Informe o Identificador:");
		labelIdentificador.setFont(new Font("sans-serif", Font.BOLD, 12));
		painelEsquerda.add(labelMatricula);
		painelEsquerda.add(labelIdentificador);

		painelDireita = new JPanel(new GridLayout(3, 1, 10, 10));
		txtmatricula = new JTextField();
		txtIdentificador = new JTextField();

		painelDireita.add(txtmatricula);
		painelDireita.add(txtIdentificador);

		painelInferior = new JPanel();
		painelInferior.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
		botaoLimpar = new JButton("Limpar");
		botaoLimpar.setFont(new Font("sans-serif", Font.BOLD, 13));

		// Adiciona listener do botão "limpar"
		botaoLimpar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txtmatricula.setText("");
				txtIdentificador.setText("");
			}
		});

		botaoMatricular = new JButton("Matriciular");
		botaoMatricular.setFont(new Font("sans-serif", Font.BOLD, 13));

		// Adiciona listener do botão "Matricular"
		botaoMatricular.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (isDadosValidos()) {
					try {
						/*
						 * pre-condicoes:
						 * o aluno deve existir em um repositorio valido, buscar o aluno valido
						 * a turma deve existir em um repositorio valido, buscar a turma valida
						 */
						fachada.matricularAlunoTurma(fachada.buscarAluno(txtmatricula.getText()), fachada.buscarTurma(Integer.valueOf(txtIdentificador.getText())));
						JOptionPane.showMessageDialog(null,
								"Aluno matriculado com sucesso.",
								"Aluno matriculado",
								JOptionPane.INFORMATION_MESSAGE);

						txtIdentificador.setText("");
						txtmatricula.setText("");
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(),
								"Aluno nao matriculado", JOptionPane.ERROR_MESSAGE);
					}
				}
			}

			/**
			 * Valida o formulário
			 * 
			 * @return true se os dados do formulário forem válidos. false caso
			 *         contrário.
			 */
			public boolean isDadosValidos() {
				boolean dadosValidos = true;
				String erro = "Os seguintes campos apresentam erros:\n";

				if (txtmatricula.getText().trim().length() == 0) {
					erro += "- matricula.\n";
					dadosValidos = false;
				}
				if (txtIdentificador.getText().trim().length() == 0) {
					erro += "- Identificador.\n";
					dadosValidos = false;
				}

				if (!dadosValidos) {
					JOptionPane.showMessageDialog(null, erro,
							"Dados Inválidos", JOptionPane.ERROR_MESSAGE);
				}
				return dadosValidos;
			}
		});

		painelInferior.add(botaoLimpar);
		painelInferior.add(botaoMatricular);

		painelForm.add(painelEsquerda, BorderLayout.WEST);
		painelForm.add(painelDireita, BorderLayout.CENTER);

		add(painelSuperior, BorderLayout.NORTH);
		add(painelForm, BorderLayout.CENTER);
		add(painelInferior, BorderLayout.SOUTH);

		setVisible(true); // Exibe o dialog
	}

}