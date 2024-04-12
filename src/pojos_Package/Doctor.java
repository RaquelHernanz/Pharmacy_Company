package pojos_Package;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Doctor extends Client 
{
	private List <String> prescriptions;

	
	public Doctor() {
		super();
		this.prescriptions = new LinkedList <String>();
		//Provisionalmente se quedará como LinkedList
	}


	public List<String> getPrescriptions() {
		return prescriptions;
	}


	public void setPrescriptions(List<String> prescriptions) {
		this.prescriptions = prescriptions;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(prescriptions);
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Doctor other = (Doctor) obj;
		return Objects.equals(prescriptions, other.prescriptions);
	}
	
	
	
	
}
