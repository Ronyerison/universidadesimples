package br.ufpi.es.system.exception.professor;

@SuppressWarnings("serial")
public class ProfessorNaoExistenteException extends Exception{
	
	public ProfessorNaoExistenteException(){
		super("Nao exite professor com esse dado.");
	}
}
