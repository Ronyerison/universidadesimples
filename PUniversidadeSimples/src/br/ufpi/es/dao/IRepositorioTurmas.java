package br.ufpi.es.dao;

import java.util.List;

import br.ufpi.es.model.Aluno;
import br.ufpi.es.model.Turma;
import br.ufpi.es.system.exception.aluno.AlunoNaoExistenteException;
import br.ufpi.es.system.exception.turma.TurmaNaoExistenteException;
import br.ufpi.es.system.exception.turma.TurmasNaoCadastradasException;

public interface IRepositorioTurmas {

	/**
	 * Insere uma turma.
	 * @param turma
	 * @throws
	 */
	public void insereTurma(Turma turma);

	/**
	 * Dada a descricao da disciplina, retorna um turma.
	 * 
	 * @param descricao
	 * @return turma.
	 * @throws TurmaNaoExistenteException.
	 */
	public Turma buscarTurma(int idenficador)
			throws TurmaNaoExistenteException;

	/**
	 * Checa se existe turma dada a descricao.
	 * 
	 * @param descricao
	 * @return true se existe, false se nao existe.
	 */
	public boolean verificaExistenciaTurma(int idenficador);

	/**
	 * Metodo que altera os dados de uma determinada turma. A opcao do atributo
	 * a ser alterado, a disciplina e a nova informacao devem ser informados. As
	 * opcoes sao: 1 - Departamento. 2 - Disciplina 3 - Horario 4 - Quantidade
	 * de alunos.
	 * 
	 * @param op
	 * @param identificador
	 * @param info
	 * @throws TurmaNaoExistenteException
	 */
	public void alterarTurma(int op, int identificador, String info)
			throws TurmaNaoExistenteException;

	/**
	 * Dada a descricao da disciplina, remove a turma.
	 * 
	 * @param disciplina
	 * @throws TurmaNaoExistenteException.
	 */
	public void removerTurma(int identificador)
			throws TurmaNaoExistenteException;

	/**
	 * Lista todos as turmas do repositorio de turmas.
	 * 
	 * @return Lista de turmas.
	 * @throws ProfessoresNaoCadastradosException.
	 */
	public List<Turma> listarTurmas() throws TurmasNaoCadastradasException;

	/**
	 * Quantidade de turmas no repositorio
	 * @return quantidade
	 */
	public int quantidadeTurmas();
	
	/**
	 * Dada uma turma especifica, insere o aluno dado
	 * @param aluno
	 * @param turma
	 * @throws TurmasNaoCadastradasException
	 */
	public void insereAlunoTurma(Aluno aluno, Turma turma) throws TurmasNaoCadastradasException;
	
	/**
	 * Dada uma turma especifica, remove o aluno dado
	 * @param aluno
	 * @param turma
	 * @throws AlunoNaoExistenteException
	 * @throws TurmasNaoCadastradasException
	 */
	public void removeAlunoTurma(Aluno aluno, Turma turma) throws AlunoNaoExistenteException, TurmasNaoCadastradasException;	
	
	/**
	 * Dada uma turma, lista seus alunos
	 * @param turma
	 * @return lista de alunos
	 */
	public List<Aluno> listaAlunos(Turma turma);
	
	/**
	 * Dada uma nova turma atualiza os dados da turma encontrada
	 * @param identificador da turma original
	 * @param novaTurma
	 * @throws TurmaNaoExistenteException
	 */
	public void alterarTurma(int identificador, Turma novaTurma) throws TurmaNaoExistenteException;
}