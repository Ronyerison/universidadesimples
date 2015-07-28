package br.ufpi.es.controller;

import java.util.LinkedList;
import java.util.List;

import br.ufpi.es.dao.IRepositorioTurmas;
import br.ufpi.es.dao.RepositorioListaTurmas;
import br.ufpi.es.model.Aluno;
import br.ufpi.es.model.Turma;
import br.ufpi.es.system.exception.AlunoNaoExistenteException;
import br.ufpi.es.system.exception.DepartamentoNaoExisteException;
import br.ufpi.es.system.exception.TurmaNaoExistenteException;
import br.ufpi.es.system.exception.TurmaSemAlunoException;
import br.ufpi.es.system.exception.TurmasNaoCadastradasException;

public class TurmasController {
	private IRepositorioTurmas controleTurmas;

	/**
	 * Insancia o controlador de acordo com o tipo de repositorio.
	 * 
	 * @param tipo
	 *            .
	 */
	public TurmasController(IRepositorioTurmas tipo) {
		if (tipo instanceof RepositorioListaTurmas) {
			this.controleTurmas = new RepositorioListaTurmas();
			this.controleTurmas = tipo;
		}
	}

	/**
	 * Insere uma turma no repositorio
	 * 
	 * @param turma
	 *            .
	 * @throws RepositorioException
	 */
	public void inserir(Turma turma) {
		this.controleTurmas.insereTurma(turma);
	}

	/**
	 * Dada a identificador, retorna a turma.
	 * 
	 * @param identificador
	 * @return turma.
	 * @throws RepositorioException
	 */
	public Turma buscar(int identificador) throws TurmaNaoExistenteException {
		return this.controleTurmas.buscarTurma(identificador);
	}

	/**
	 * Dada a a identificador, checa se a turma existe
	 * 
	 * @param identificador
	 * @return true se existe; false, se nao existe.
	 */
	public boolean verificaSeTurmaExiste(int identificador) {
		return this.controleTurmas.verificaExistenciaTurma(identificador);
	}

	/**
	 * Metodo que altera os dados de uma determinada turma. A opcao do atributo
	 * a ser alterado, a disciplina e a nova informacao devem ser informados. As
	 * opcoes sao: 1 - Departamento. 2 - Disciplina 3 - Horario 4 - Quantidade
	 * de alunos.
	 * 
	 * @param op
	 * @param identificador
	 * @param info
	 * @throws RepositorioException
	 *             , TurmaNaoExistenteException
	 */
	public void alterar(int op, int identificador, String info)
			throws TurmaNaoExistenteException {
		this.controleTurmas.alterarTurma(op, identificador, info);
	}

	/**
	 * Dada a identificador, faz a remocao da turma.
	 * 
	 * @param indentificador
	 * @throws RepositorioException
	 */
	public void remover(int identificador) throws TurmaNaoExistenteException {
		this.controleTurmas.removerTurma(identificador);
	}

	/**
	 * Listar as turmas do repositorio
	 * 
	 * @return Lista de turmas.
	 * @throws RepositorioException
	 * @throws AlunosNaoCadastradosException.
	 */
	public List<Turma> listar() throws TurmasNaoCadastradasException {
		return this.controleTurmas.listarTurmas();
	}

	/**
	 * Retorna todas as turmas de um determinado departamento.
	 * 
	 * @param departamento
	 *            .
	 * @return Lista de turmas.
	 * @throws RepositorioException
	 * @throws TurmasNaoCadastradasException,
	 * @throws DepartamentoSemTurmaException
	 */
	public List<Turma> listarTurmasPorDepartamento(String departamento)
			throws TurmasNaoCadastradasException,
			DepartamentoNaoExisteException {
		List<Turma> turmas = this.controleTurmas.listarTurmas();

		List<Turma> retorno = new LinkedList<Turma>();

		for (Turma t : turmas) {
			if (t.getDepartamento().equals(departamento)) {
				retorno.add(t);
			}
		}

		if (retorno.size() == 0) {
			throw new DepartamentoNaoExisteException();
		}
		return retorno;
	}

	/**
	 * Lista todos os alunos que estao em uma determinada turma.
	 * 
	 * @param turma
	 * @return Lista de alunos.
	 * @throws TurmaSemAlunoException
	 */
	public List<Aluno> listarAlunoPorTurma(Turma t){
		return this.controleTurmas.listaAlunos(t);
	}

	/**
	 * retorna a quantidade de turmas existentes no repositorio.
	 * 
	 * @return quantidade.
	 */
	public int quantidadeTurmas() {
		return this.controleTurmas.quantidadeTurmas();
	}
	
	/**
	 * Insere um aluno em uma dada turma
	 * @param aluno
	 * @param turma
	 * @throws TurmasNaoCadastradasException
	 */
	public void inserirAlunoNaTurma(Aluno aluno, Turma turma) throws TurmasNaoCadastradasException{
		controleTurmas.insereAlunoTurma(aluno, turma);
	}
	
	/**
	 * Dada uma turma, remove o aluno dado
	 * @param aluno
	 * @param turma
	 * @throws AlunoNaoExistenteException
	 * @throws TurmasNaoCadastradasException
	 */
	public void removerAlunoDaTurma(Aluno aluno, Turma turma) throws AlunoNaoExistenteException, TurmasNaoCadastradasException{
		controleTurmas.removeAlunoTurma(aluno, turma);
	}
}