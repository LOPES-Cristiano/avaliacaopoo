package avaliacao;

public class Animal {
	
	private String sexo;
	private double peso;
	private int codigo;
	private double valor;
	private double valorArroba;

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
		calcularValor();
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public int getCodigo() {
		return codigo;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public double getValorArroba() {
		return valorArroba;
	}

	public void setValorArroba(double valorArroba) {
		this.valorArroba = valorArroba;
		calcularValor();
	}
	
	private void calcularValor() {
		if (peso > 0 && valorArroba > 0) {
			double arrobas = peso / 14.688;
			this.valor = arrobas * valorArroba;
		} else {
			this.valor = 0;
		}
	}


}
