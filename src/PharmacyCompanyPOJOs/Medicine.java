package PharmacyCompanyPOJOs;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;
import java.util.Arrays;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Medicine")
@XmlType(propOrder = {"name","price","stock","instructions","expirations","prescribed"})
public class Medicine implements Serializable 
{
	private static final long serialVersionUID = 98763412L;
	
	@XmlTransient
	private Integer code;
	@XmlElement
	private String name;
	@XmlElement
	private String instructions;
	@XmlElement
	private Float price;
	@XmlElement
	private Integer stock;
	@XmlElement
	private Date expirations;
	@XmlTransient
	private Pharmacist pharmacist;
	@XmlTransient
	private byte [] image;
	@XmlElement
	private Boolean prescribed;
	

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
	
	
	
	public Medicine(Integer code, String name, String instructions, Float price, Integer stock, Date expirations,
			Pharmacist pharmacist, byte[] image, Boolean prescribed) {
		super();
		this.code = code;
		this.name = name;
		this.instructions = instructions;
		this.price = price;
		this.stock = stock;
		this.expirations = expirations;
		this.pharmacist = pharmacist;
		this.image = image;
		this.prescribed = prescribed;
	}


	public Medicine(String name, String instructions, Float price, Integer stock, Date expirations,
			Pharmacist pharmacist, byte[] image, Boolean prescribed) {
		super();
		this.name = name;
		this.instructions = instructions;
		this.price = price;
		this.stock = stock;
		this.expirations = expirations;
		this.pharmacist = pharmacist;
		this.image = image;
		this.prescribed = prescribed;
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

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(image);
		result = prime * result
				+ Objects.hash(code, expirations, instructions, name, pharmacist, prescribed, price, stock);
		return result;
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
				&& Arrays.equals(image, other.image) && Objects.equals(instructions, other.instructions)
				&& Objects.equals(name, other.name) && Objects.equals(pharmacist, other.pharmacist)
				&& Objects.equals(prescribed, other.prescribed) && Objects.equals(price, other.price)
				&& Objects.equals(stock, other.stock);
	}


	public Boolean getPrescribed() {
		return prescribed;
	}


	public void setPrescribed(Boolean prescribed) 
	{
		this.prescribed = prescribed;
	}


	@Override
	public String toString() {
		return "Medicine [name=" + name + ", instructions=" + instructions + ", price=" + price
				+ ", stock=" + stock + ", expirations=" + expirations.toString()+ ", Image loaded in database]";
	}


	

	
	
	
	
	
	
	

}
