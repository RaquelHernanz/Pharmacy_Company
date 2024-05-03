package PharmacyCompanyPOJOs;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;
import java.util.Objects;

public class Medicine implements Serializable 
{
	private static final long serialVersionUID = 98763412L;
	
	private Integer code;
	private String name;
	private String instructions;
	private Integer stock;
	private Date expiration;
	private Pharmacist pharmacist;
	private Blob image;
	
	public Medicine() {
		super();
		this.pharmacist = new Pharmacist();
	}
	
	@Override
	public String toString() {
		return "Medicine [name=" + name + ", instructions=" + instructions + ", stock=" + stock + ", expiration="
				+ expiration + ", pharmacist=" + pharmacist + ", image=" + image + "]";
	}
	
	

	public Medicine(Integer code, String name, String instructions, Integer stock, Date expiration,
			Pharmacist pharmacist, Blob image) {
		super();
		this.code = code;
		this.name = name;
		this.instructions = instructions;
		this.stock = stock;
		this.expiration = expiration;
		this.pharmacist = pharmacist;
		this.image = image;
	}
	
	

	public Medicine(String name, String instructions, Integer stock, Date expiration, Pharmacist pharmacist,
			Blob image) {
		super();
		this.name = name;
		this.instructions = instructions;
		this.stock = stock;
		this.expiration = expiration;
		this.pharmacist = pharmacist;
		this.image = image;
	}
	
	

	@Override
	public int hashCode() {
		return Objects.hash(code, expiration, image, instructions, name, pharmacist, stock);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Medicine other = (Medicine) obj;
		return Objects.equals(code, other.code) && Objects.equals(expiration, other.expiration)
				&& Objects.equals(image, other.image) && Objects.equals(instructions, other.instructions)
				&& Objects.equals(name, other.name) && Objects.equals(pharmacist, other.pharmacist)
				&& Objects.equals(stock, other.stock);
	}

	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInstructions() {
		return instructions;
	}
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public Date getExpiration() {
		return expiration;
	}
	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}
	public Pharmacist getPharmacist() {
		return pharmacist;
	}
	public void setPharmacist(Pharmacist pharmacist) {
		this.pharmacist = pharmacist;
	}

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}
	
	
	
	

}
