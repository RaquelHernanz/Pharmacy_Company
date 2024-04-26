package pojos_Package;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;


public class Client implements Serializable
{
	private static final long serialVersionUID = 98763412L;
	
	private Integer id;
	private String name;
	private String surnmame;
	private String adress;
	private Integer phone_number;
	private String email;
	private List <Medicine> medicines;
	/*Password no debe ponerse por ahora, a posteriori cuando hagamos el login*/
	
	private String text;
	
	public Client(Integer id2, String name2, String surname, Integer phone_number2, String email2) {
		super();
		this.medicines = new LinkedList <Medicine> ();
	}
	
	
	public Client(Integer id, String name, String surnmame, String adress, Integer phone_number, String email) {
		super();
		this.id = id;
		this.name = name;
		this.surnmame = surnmame;
		this.adress = adress;
		this.phone_number = phone_number;
		this.email = email;
	}


	@Override
	public int hashCode() {
		return Objects.hash(adress, email, id, medicines, name, phone_number, surnmame);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		return Objects.equals(adress, other.adress) && Objects.equals(email, other.email)
				&& Objects.equals(id, other.id) && Objects.equals(medicines, other.medicines)
				&& Objects.equals(name, other.name) && Objects.equals(phone_number, other.phone_number)
				&& Objects.equals(surnmame, other.surnmame);
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurnmame() {
		return surnmame;
	}
	public void setSurnmame(String surnmame) {
		this.surnmame = surnmame;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public Integer getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(Integer phone_number) {
		this.phone_number = phone_number;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public List<Medicine> getMedicines() {
		return medicines;
	}

	public void setMedicines(List<Medicine> medicines) {
		this.medicines = medicines;
	}

	@Override
	public String toString() {
		return "Client [name=" + name + ", surnmame=" + surnmame + ", adress=" + adress
				+ ", phone_number=" + phone_number + ", email=" + email + "]";
	}
	
	
	


}
