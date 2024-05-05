package PharmacyCompanyPOJOs;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;
import java.util.Objects;

public class Medicine implements Serializable 
{
	private static final long serialVersionUID = 98763412L;
	
	private Integer code;
	private String name;
	private String instructions;
	private Float price;
	private Integer stock;
	private Date expirations;
	private Pharmacist pharmacist;
	private Blob image;
	
	public Medicine() {
		super();
		this.pharmacist = new Pharmacist();
	}
	

	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}

	public Date getExpirations() {
		return expirations;
	}

	public void setExpirations(Date expirations) {
		this.expirations = expirations;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(code, expirations, image, instructions, name, pharmacist, price, stock);
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
		return Objects.equals(code, other.code) && Objects.equals(expirations, other.expirations)
				&& Objects.equals(image, other.image) && Objects.equals(instructions, other.instructions)
				&& Objects.equals(name, other.name) && Objects.equals(pharmacist, other.pharmacist)
				&& Objects.equals(price, other.price) && Objects.equals(stock, other.stock);
	}


	public Medicine(Integer code, String name, Float price,String instructions, Integer stock, Date expiration,
			Pharmacist pharmacist, Blob image) {
		super();
		this.code = code;
		this.name = name;
		this.price = price;
		this.instructions = instructions;
		this.stock = stock;
		this.expirations = expiration;
		this.pharmacist = pharmacist;
		this.image = image;
	}
	
	public Medicine(String name, Float price,String instructions, Integer stock, Date expiration, Pharmacist pharmacist,
			Blob image) {
		super();
		this.name = name;
		this.price = price;
		this.instructions = instructions;
		this.stock = stock;
		this.expirations = expiration;
		this.pharmacist = pharmacist;
		this.image = image;
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
