package mojMiniHashingProjekat;

public interface DynamicTableI {
	
	public void tableInsert(Automobile x) throws Exception;
	public void tableDelete (Automobile x) throws Exception;
	public Automobile search(String idNumber) throws Exception;
}
