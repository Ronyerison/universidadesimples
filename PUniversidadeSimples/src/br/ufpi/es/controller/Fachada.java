package br.ufpi.es.controller;

import java.sql.SQLException;
import java.util.List;

import br.ufpi.es.dao.IRepositorioAlunos;
import br.ufpi.es.dao.IRepositorioProfessores;
import br.ufpi.es.dao.IRepositorioTurmas;
import br.ufpi.es.dao.RepositorioBancoAlunos;
import br.ufpi.es.dao.RepositorioListaAlunos;
import br.ufpi.es.dao.RepositorioListaProfessores;
import br.ufpi.es.dao.RepositorioListaTurmas;
import br.ufpi.es.model.Aluno;
import br.ufpi.es.model.Professor;
import br.ufpi.es.model.Turma;
import br.ufpi.es.system.exception.AlunoNaoExistenteException;
import br.ufpi.es.system.exception.AlunosNaoCadastradosException;
import br.ufpi.es.system.exception.BuscaListaException;
import br.ufpi.es.system.exception.DepartamentoNaoExisteException;
import br.ufpi.es.system.exception.InserirListaException;
import br.ufpi.es.system.exception.ProfessorNaoExistenteException;
import br.ufpi.es.system.exception.ProfessorSemTurmaException;
import br.ufpi.es.system.exception.ProfessoresNaoCadastradosException;
import br.ufpi.es.system.exception.TurmaNaoExistenteException;
import br.ufpi.es.system.exception.TurmaSemAlunoException;
import br.ufpi.es.system.exception.TurmasNaoCadastradasException;
import br.ufpi.es.system.exception.VerificarExistenciaAlunoListaException;

public class Fachada implements IFachada {
	private AlunosController meuControleAlunos;
	private ProfessoresController meuControleProfessor;
	private TurmasController meuControleTurmas;

    private IRepositorioAlunos repositorioListaAlunos;
//	private IRepositorioAlunos repositorioBancoAlunos;
	private IRepositorioProfessores repositorioProfessores;
	private IRepositorioTurmas repositorioTurmas;

	/**
	 * Construtor padrao da classe Fachada. Ao instanciar um objeto do tipo
	 * Fachada, e definido um tipo de repositorio para armazenar os dados.
	 */
	public Fachada() {
		 this.repositorioListaAlunos = new RepositorioListaAlunos();
		//this.repositorioBancoAlunos = new RepositorioBancoAlunos();

		this.repositorioProfessores = new RepositorioListaProfessores();
		this.repositorioTurmas = new RepositorioListaTurmas();

		this.meuControleAlunos = new AlunosController(repositorioListaAlunos);
		this.meuControleProfessor = new ProfessoresController(repositorioProfessores);
		this.meuControleTurmas = new TurmasController(repositorioTurmas);
	}

	/**
	 * Insere um aluno no repositorio de alunos.
	 * 
	 * @throws RepositorioException
	 */
	@Override
	public void inserirAluno(Aluno aluno) throws Exception {
		this.meuControleAlunos.inserir(aluno);
	}

	/**
	 * Metodo altera os dados de um determinado aluno, de um repositorio de alunos. A opcao do atributo a ser
	 * alterado, a matricula do aluno e a nova informacao devem ser informados.
	 * * As opcoes sao: 1 - Matricula 2 - Nome 3 - Curso.
	 * 
	 * @param op
	 * @param matricula
	 * @param info.
	 * @throws Exception
	 * @throws RepositorioException
	 * @throws AlunoNaoExistenteException
	 */
	@Override
	public void alterarAluno(Aluno a) throws Exception {
		this.meuControleAlunos.alterarAluno(a);
	}

	/**
	 * Remove um determiando aluno do repositorio de alunos.
	 * 
	 * @param matricula
	 *           
	 * @throws RepositorioException
	 * @throws AlunoNaoExistenteException
	 */
	@Override
	public void removerAluno(String matricula)
			throws AlunoNaoExistenteException, Exception {
		this.meuControleAlunos.remover(matricula);
	}

	/**
	 * Metodo que busca uma determinado aluno pela matricula no repositorio de alunos.
	 * 
	 * @param matricula
	 * @throws RepositorioException
	 * @throws AlunoNaoExistenteException
	 */
	@Override
	public Aluno buscarAluno(String matricula)
			throws AlunoNaoExistenteException, Exception {
		return this.meuControleAlunos.buscar(matricula);
	}

	/**
	 * Metodo que lista todos os alunos cadastrados no repositorio de alunos.
	 * 
	 * @return Lista de alunos.
	 * @throws Exception
	 * @throws RepositorioException
	 * @throws AlunosNaoCadastradosException
	 */
	@Override
	public List<Aluno> listarAlunos() throws AlunosNaoCadastradosException,
			Exception {
		return this.meuControleAlunos.listar();
	}

	/**
	 * retorna a quantidade alunos inseridos no repositorio de alunos.
	 * 
	 * @throws Exception
	 */
	public int quantidadeAlunos() throws Exception {
		return this.meuControleAlunos.quantidadeAlunos();
	}

	/**
	 * Dada a matricula do aluno, checa se ele existe no repositorio de alunos
	 * 
	 * @param matricula
	 * @return true se existe
	 * @throws RepositorioException
	 */
	public boolean verificaSeExisteAluno(String matricula) throws Exception {
		return this.meuControleAlunos.verificaSeAlunoExiste(matricula);
	}

	/* ###################### PROFESSORES ############################ */
	/**
	 * Insere um professor no repositorio de professores.
	 * 
	 * @param professor
	 * @throws RepositorioException
	 */
	public void inserirProfessor(Professor professor) {
		this.meuControleProfessor.inserir(professor);
	}

	/**
	 * Metodo que altera os dados de um determinado professor no repositorio de professores. A opcao do
	 * atributo a ser alterado, o cpf do professor e a nova informacao devem ser
	 * passados para o metodo. * As opcoes sao: 1 - CPF 2 - Nome 3 - Titulo 4 - Lotacao
	 * 
	 * @param op
	 * @param cpf
	 * @param info.
	 * @throws BuscaListaException 
	 */
	public void alterarProfessor(int op, String cpf, String info)
			throws ProfessorNaoExistenteException, BuscaListaException {
		this.meuControleProfessor.alterar(op, cpf, info);
	}

	/**
	 * Verifica se um determinado professor esta presente no repositorio.
	 * 
	 * @param cpf
	 * @return false, se nao existe; true, caso exista.
	 * @throws RepositorioException
	 */
	public boolean verificaExistenciaProfessor(String cpf) {
		return this.meuControleProfessor.verificaSeProfessorExiste(cpf);
	}

	/**
	 * Remove um determiando professor do repositorio de professores.
	 * 
	 * @param cpf
	 * @throws BuscaListaException 
	 */
	public void removerProfessor(String cpf)
			throws ProfessorNaoExistenteException, BuscaListaException {
		this.meuControleProfessor.remover(cpf);
	}

	/**
	 * Metodo que busca uma determinado professor pelo cpf do repositorio de professores.
	 * 
	 * @param cpf
	 * @throws BuscaListaException 
	 */
	@Override
	public Professor buscarProfessor(String cpf)
			throws ProfessorNaoExistenteException, BuscaListaException {
		return this.meuControleProfessor.buscar(cpf);
	}

	/**
	 * Metodo que lista todos os professores cadastrados no repositorio de professores.
	 */
	@Override
	public List<Professor> listarProfessores()
			throws ProfessoresNaoCadastradosException {
		return this.meuControleProfessor.listar();
	}

	/**
	 * Dado um professor, retorna todas as turmas em que ele leciona.
	 * 
	 * @param professor
	 * @return Lista de turmas.
	 * @throws ProfessorSemTurmaException
	 */
	public List<Turma> listarTurmaPorProfessor(Professor professor)
			throws ProfessorSemTurmaException {
		return this.meuControleProfessor.listarTurmaPorProfessor(professor);
	}
	
	/**
	 * Retorna a quantidade de turmas de um professor
	 * @param professor
	 * @return quantidade de turmas
	 * @throws ProfessorSemTurmaException
	 */
	public int qtdTurmasPorProfessor(Professor professor) throws ProfessorSemTurmaException {
		List<Turma> turmasAux;
		turmasAux = listarTurmaPorProfessor(professor);
		return (turmasAux.size());
	}

	/**
	 * Dados um professor e uma turma, relaciona a turma ao professor e
	 * vice-versa.
	 * 
	 * @param professor
	 * @param turma
	 */
	public void associarProfessorTurma(Professor professor, Turma turma) {
		this.meuControleProfessor.associaProfessorTurma(professor, turma);
	}

	/**
	 * Dados um professor e um turma, remove a turma do professor.
	 * 
	 * @param professor
	 * @param turma
	 */
	public void removerProfessorTurma(Professor professor, Turma turma) {
		this.meuControleProfessor.removeProfessorTurma(professor, turma);
	}

	/**
	 * retorna a quantidade de professores inseridas no repositorio
	 */
	public int quantidadeProfessores() {
		return this.meuControleProfessor.quantidadeProfessores();
	}

	/* ###################### TURMAS ############################ */

	/**
	 * Insere uma turma no repositorio de turmas.
	 * 
	 * @param turma
	 * @throws RepositorioException
	 */
	public void inserirTurma(Turma turma) {
		this.meuControleTurmas.inserir(turma);
	}

	/**
	 * Metodo que altera os dados de uma determinada turma no repositorio de turmas. A opcao do atributo
	 * a ser alterado, a disciplina e a nova informacao devem ser informados. As
	 * opcoes sao: 1 - Departamento. 2 - Disciplina 3 - Horario 4 - Quantidade
	 * de alunos.
	 * 
	 * @param op
	 * @param identificador
	 * @param info
	 * @throws RepositorioException
	 * @throws TurmaNaoExistenteException
	 */
	public void alterarTurma(int op, int identificador, String info)
			throws TurmaNaoExistenteException {
		this.meuControleTurmas.alterar(op, identificador, info);
	}

	/**
	 * Dado o identificador da turma, retorna um turma.
	 * 
	 * @param identificador
	 * @return turma.
	 * @throws RepositorioException
	 * @throws TurmaNaoExistenteException
	 */
	public Turma buscarTurma(int identificador)
			throws TurmaNaoExistenteException {
		return this.meuControleTurmas.buscar(identificador);
	}

	/**
	 * Dado o identificador da turma, remove a turma.
	 * 
	 * @param identificador
	 * @throws RepositorioException
	 * @throws TurmaNaoExistenteException
	 */
	public void removerTurma(int identificador)
			throws TurmaNaoExistenteException {
		this.meuControleTurmas.remover(identificador);
	}

	/**
	 * Lista todas as turmas do repositorio de turmas.
	 * 
	 * @return Lista de turmas.
	 * @throws TurmasNaoCadastradasException
	 */
	public List<Turma> listarTurmas() throws TurmasNaoCadastradasException {
		return this.meuControleTurmas.listar();
	}

	/**
	 * Matricula um aluno em determinada turma.
	 * 
	 * @param aluno
	 * @param turma
	 * @throws TurmasNaoCadastradasException 
	 */
	public void matricularAlunoTurma(Aluno a, Turma t) throws TurmasNaoCadastradasException {
		this.meuControleAlunos.matricularAlunoTurma(a, t);
	}

	/**
	 * Remove um aluno de uma deterteminada turma.
	 * 
	 * @param aluno
	 * @param turma.
	 * @throws TurmasNaoCadastradasException 
	 * @throws AlunoNaoExistenteException 
	 */
	public void trancarTurmaAluno(Aluno a, Turma t) throws AlunoNaoExistenteException, TurmasNaoCadastradasException {
		this.meuControleAlunos.trancarTurmaAluno(a, t);
	}

	/**
	 * Lista todas as turmas pertencentes a um determinado departamento.
	 * 
	 * @param departamento
	 * @return Lista de turmas.
	 * @throws RepositorioException
	 * @throws TurmasNaoCadastradasException,
	 * @throws DepartamentoSemTurmaException
	 */
	public List<Turma> listarTurmasPorDepartamento(String departamento)
			throws TurmasNaoCadastradasException,
			DepartamentoNaoExisteException {
		return this.meuControleTurmas.listarTurmasPorDepartamento(departamento);
	}

	/**
	 * Lista todos os alunos que estao em uma determinada turma.
	 * 
	 * @param turma
	 * @return Lista de alunos.
	 * @throws TurmaSemAlunoException
	 */
	public List<Aluno> listarAlunoPorTurma(Turma t)
			throws TurmaSemAlunoException {
		return this.meuControleTurmas.listarAlunoPorTurma(t);
	}

	/**
	 * Retorna a quantidade de turmas existentes no repositorio de turmas.
	 * 
	 * @return quantidade.
	 */
	public int quantidadeTurma() {
		return this.meuControleTurmas.quantidadeTurmas();
	}
	
	/**
	 * Retorna a quantidade de turmas de um aluno
	 * @param aluno
	 * @return quantidade de turmas
	 */
	public int quantidadeTurmasAluno(Aluno aluno){
		return(0);
	}

	/**
	 * Dado o identificador de turma, checa se a turma existe
	 * 
	 * @param identificador
	 * @return true se existe; false, se nao existe.
	 */
	public boolean verificaExistenciaTurma(int identificador) {
		return this.meuControleTurmas.verificaSeTurmaExiste(identificador);
	}

	/**
	 * @throws Exception 
	 * 
	 */
	public void removerAluno(Aluno aluno) throws AlunoNaoExistenteException, Exception{
		this.meuControleAlunos.remover(aluno.getMatricula());
	}

}