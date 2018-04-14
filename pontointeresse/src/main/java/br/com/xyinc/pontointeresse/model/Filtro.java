package br.com.xyinc.pontointeresse.model;

public class Filtro {

	private Integer x;
	private Integer y;
	private Integer dMax;
	
	public Filtro() {
	}

	public Filtro(Integer x, Integer y, Integer dMax) {
		this.x = x;
		this.y = y;
		this.dMax = dMax;
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public Integer getdMax() {
		return dMax;
	}

	public void setdMax(Integer dMax) {
		this.dMax = dMax;
	}
	
	public boolean preenchido() {
		return (getX() != null && getY() != null && getdMax() != null);
	}
	
}
