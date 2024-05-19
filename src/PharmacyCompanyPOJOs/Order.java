package PharmacyCompanyPOJOs;

import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Order")
@XmlType(propOrder = {"quantity","totalprice","pharmacist"})
public class Order 
{
	@XmlTransient
	private Integer code;
	@XmlElement
	private Float totalprice;
	@XmlElement
	private Integer quantity;
	@XmlElement
	private Pharmacist pharmacist;
	@XmlTransient
	private Administrator administrator;
	
	public Order() {
		super();
		this.administrator = new Administrator ();
		this.pharmacist = new Pharmacist ();
	}
	

	public Order(Float totalprice, Integer quantity, Pharmacist pharmacist, Administrator administrator) {
		super();
		this.totalprice = totalprice;
		this.quantity = quantity;
		this.pharmacist = pharmacist;
		this.administrator = administrator;
	}
	
	

	public Order(Integer code, Float totalprice, Integer quantity, Pharmacist pharmacist, Administrator administrator) {
		super();
		this.code = code;
		this.totalprice = totalprice;
		this.quantity = quantity;
		this.pharmacist = pharmacist;
		this.administrator = administrator;
	}


	@Override
	public int hashCode() {
		return Objects.hash(administrator, code, pharmacist, quantity, totalprice);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(administrator, other.administrator) && Objects.equals(code, other.code)
				&& Objects.equals(pharmacist, other.pharmacist) && Objects.equals(quantity, other.quantity)
				&& Objects.equals(totalprice, other.totalprice);
	}


	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Float getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(Float totalprice) {
		this.totalprice = totalprice;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Pharmacist getPharmacist() {
		return pharmacist;
	}


	public void setPharmacist(Pharmacist pharmacist) {
		this.pharmacist = pharmacist;
	}

	public Administrator getAdministrator() {
		return administrator;
	}

	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}


	@Override
	public String toString() {
		return "Order [code=" + code + ", totalprice=" + totalprice + ", quantity=" + quantity + ", pharmacist="
				+ pharmacist.getEmail() + ", administrator=" + administrator.getEmail() + "]";
	}
	
	
	

}
