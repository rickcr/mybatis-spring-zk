package net.learntechnology.empmaint.domain;


public class Department extends BaseVO {
	private static final long serialVersionUID = -6810736897253521583L;
	private int id;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
