package PharmacyCompanyPOJOs;

import java.io.Serializable;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import PharmacyCompanyXMLUTILSs.SQLDateAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="medicine")
@XmlType(propOrder = {"name","price","stock","instructions","expirations","orders"})
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
	@XmlJavaTypeAdapter(SQLDateAdapter.class)
	private Date expirations;
	@XmlTransient
	private Pharmacist pharmacist;
	@XmlTransient
	private byte [] image;
	@XmlAttribute
	private Boolean prescribed;
	@XmlElement (name = "order")
	@XmlElementWrapper(name="orders")
	private List <Order> orders;
	

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
			Pharmacist pharmacist, byte[] image, Boolean prescribed, List <Order> orders) {
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
		this.orders = orders;
	}


	public Medicine(String name, String instructions, Float price, Integer stock, Date expirations,
			Pharmacist pharmacist, byte[] image, Boolean prescribed,List <Order> orders) {
		super();
		this.name = name;
		this.instructions = instructions;
		this.price = price;
		this.stock = stock;
		this.expirations = expirations;
		this.pharmacist = pharmacist;
		this.image = image;
		this.prescribed = prescribed;
		this.orders = orders;
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


	public Boolean getPrescribed() {
		return prescribed;
	}


	public void setPrescribed(Boolean prescribed) 
	{
		this.prescribed = prescribed;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(image);
		result = prime * result
				+ Objects.hash(code, expirations, instructions, name, orders, pharmacist, prescribed, price, stock);
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
				&& Objects.equals(name, other.name) && Objects.equals(orders, other.orders)
				&& Objects.equals(pharmacist, other.pharmacist) && Objects.equals(prescribed, other.prescribed)
				&& Objects.equals(price, other.price) && Objects.equals(stock, other.stock);
	}


	@Override
	public String toString() {
		return "Medicine [name=" + name + ", instructions=" + instructions + ", price=" + price + ", stock=" + stock
				+ ", expirations=" + expirations + ", pharmacist=" + pharmacist + ", prescribed=" + prescribed + "]";
	}


	


	

	
	
	
	
	
	
	

}
