package pojos_Package;

public class Order 
{
	private Integer code;
	private Float totalprice;
	private Integer quantity;
	private Pharmacist pharmacist;
	private Administrator administrator;
	
	public Order() {
		super();
	}
	
	

	@Override
	public String toString() {
		return "Order [code=" + code + ", totalprice=" + totalprice + ", quantity=" + quantity + ", pharmacist="
				+ pharmacist + ", administrator=" + administrator + "]";
	}
	
	
	

}
